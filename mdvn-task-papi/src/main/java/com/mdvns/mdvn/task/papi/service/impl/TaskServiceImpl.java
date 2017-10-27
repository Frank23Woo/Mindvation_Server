package com.mdvns.mdvn.task.papi.service.impl;

import com.mdvns.mdvn.common.beans.AttchInfo;
import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.common.beans.Staff;
import com.mdvns.mdvn.common.beans.exception.ExceptionEnum;
import com.mdvns.mdvn.common.utils.FetchListUtil;
import com.mdvns.mdvn.task.papi.config.UrlConfig;
import com.mdvns.mdvn.task.papi.domain.*;
import com.mdvns.mdvn.task.papi.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import sun.swing.StringUIClientPropertyKey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class TaskServiceImpl implements TaskService {

    /* 日志常量 */
    private static final Logger LOG = LoggerFactory.getLogger(TaskServiceImpl.class);

    private final String CLASS = this.getClass().getName();

    private static final int PAGE = 0;
    private static final int PAGE_SIZE = 10;

    @Autowired
    private UrlConfig urlConfig;


    @Autowired
    private RestTemplate restTemplate;

    @Override
    public RestResponse createTask(CreateOrUpdateTaskRequest request) throws Exception {
        if (request == null || StringUtils.isEmpty(request.getCreatorId()) ||
                StringUtils.isEmpty(request.getStoryId()) || StringUtils.isEmpty(request.getAssigneeId()) ||
                StringUtils.isEmpty(request.getDescription()) || request.getDeliver() == null ||
                StringUtils.isEmpty(request.getDeliver().getModelId()) || StringUtils.isEmpty(request.getDeliver().getName()) ||
                request.getStartTime() == null || request.getEndTime() == null) {
            return new RestResponse(ExceptionEnum.PARAMS_EXCEPTION.getErroCode(), null);
        }

        RestResponse response = new RestResponse();
        try {
            TaskDetail task = restTemplate.postForObject(urlConfig.getSaveTaskUrl(), request, TaskDetail.class);

            // 查询creator和assinee
            Staff creator = restTemplate.postForObject(urlConfig.getRtrvStaffInfoUrl(), task.getCreatorId(), Staff.class);
            task.setCreator(creator);

            Staff assignee = restTemplate.postForObject(urlConfig.getRtrvStaffInfoUrl(), task.getAssigneeId(), Staff.class);
            task.setAssignee(assignee);

            // todo 查询附件

            response.setResponseBody(task);
            response.setResponseCode("000");
            response.setResponseMsg("ok");
        } catch (RestClientException e) {
            e.printStackTrace();
            response.setResponseCode(ExceptionEnum.TASK_SAVE_FAILED.getErroCode());
            response.setResponseMsg(ExceptionEnum.TASK_SAVE_FAILED.getErrorMsg());
        }

        return response;
    }

    @Override
    public RestResponse retrieveTaskList(RtrvTaskListRequest request) throws Exception {
        RestResponse restResponse = new RestResponse();

        // 参数检查
        if (request == null || request.getStoryId() == null) {
            restResponse.setResponseCode(ExceptionEnum.PARAMS_EXCEPTION.getErroCode());
            restResponse.setResponseMsg(ExceptionEnum.PARAMS_EXCEPTION.getErrorMsg());
            return restResponse;
        }

        try {
            // 查询task sapi
            ParameterizedTypeReference typeReference = new ParameterizedTypeReference<List<TaskDetail>>() {
            };
            List<TaskDetail> taskList = FetchListUtil.fetch(restTemplate, urlConfig.getRtrvTaskListUrl(), request, typeReference);

            // 查询creator和assignee
            List<String> creatorIds = new ArrayList<>();
            List<String> assigneeIds = new ArrayList<>();
            for (TaskDetail task : taskList) {
                creatorIds.add(task.getCreatorId());
                assigneeIds.add(task.getAssigneeId());
            }

            Map<String, Object> paramsMap = new HashMap<>();
            paramsMap.put("staffIdList", creatorIds);
            typeReference = new ParameterizedTypeReference<List<Staff>>() {
            };
            List<Staff> creatorList = (List<Staff>) FetchListUtil.fetch(restTemplate, urlConfig.getRtrvStaffsByIdsUrl(), paramsMap, typeReference);
            paramsMap.put("staffIdList", assigneeIds);
            List<Staff> assigneeList = (List<Staff>) FetchListUtil.fetch(restTemplate, urlConfig.getRtrvStaffsByIdsUrl(), paramsMap, typeReference);

            for (int i = 0; i < taskList.size(); i++) {
                taskList.get(i).setCreator(creatorList.get(i));
                taskList.get(i).setAssignee(assigneeList.get(i));
            }

            //附件
            for (TaskDetail detail : taskList) {
                getTaskAttachment(detail);
            }

            restResponse.setResponseCode("000");
            restResponse.setResponseMsg("ok");
            restResponse.setResponseBody(taskList);
        } catch (RestClientException e) {
            e.printStackTrace();
            restResponse.setResponseCode(ExceptionEnum.BASE_SAPI_EXCEPTION.getErroCode());
            restResponse.setResponseMsg(ExceptionEnum.BASE_SAPI_EXCEPTION.getErrorMsg());
        }

        return restResponse;
    }

    @Override
    public RestResponse updateTask(CreateOrUpdateTaskRequest request) throws Exception {
        RestResponse restResponse = new RestResponse();

        if (request == null || StringUtils.isEmpty(request.getTaskId())) {
            restResponse.setResponseCode(ExceptionEnum.PARAMS_EXCEPTION.getErroCode());
            restResponse.setResponseMsg(ExceptionEnum.PARAMS_EXCEPTION.getErrorMsg());
            return restResponse;
        }

        try {
            TaskDetail result = restTemplate.postForObject(urlConfig.getUpdateTaskUrl(), request, TaskDetail.class);
            if (result == null) {
                restResponse.setResponseCode(ExceptionEnum.TASK_DOES_NOT_EXIST.getErroCode());
                restResponse.setResponseMsg(ExceptionEnum.TASK_DOES_NOT_EXIST.getErrorMsg());
            } else {
                // 查询creator和assinee
                Staff creator = restTemplate.postForObject(urlConfig.getRtrvStaffInfoUrl(), result.getCreatorId(), Staff.class);
                result.setCreator(creator);
                Staff assignee = restTemplate.postForObject(urlConfig.getRtrvStaffInfoUrl(), result.getAssigneeId(), Staff.class);
                result.setAssignee(assignee);

                // 查询附件
                getTaskAttachment(result);

                restResponse.setResponseCode("000");
                restResponse.setResponseMsg("ok");
                restResponse.setResponseBody(result);
            }
        } catch (RestClientException e) {
            e.printStackTrace();
            restResponse.setResponseCode(ExceptionEnum.BASE_SAPI_EXCEPTION.getErroCode());
            restResponse.setResponseMsg(ExceptionEnum.BASE_SAPI_EXCEPTION.getErrorMsg());
        }

        return restResponse;
    }

    // query task attachment
    private TaskDetail getTaskAttachment(TaskDetail task) {
        if (task == null) {
            return null;
        }

        final String ids = task.getAttachmentIds();
        if (!StringUtils.isEmpty(ids)) {
            List<AttchInfo> attchInfos = (List<AttchInfo>) restTemplate.getForEntity(urlConfig.getGetAttachmentListByIdsUrl(), List.class, ids);
            task.setAttachUrlList(attchInfos);
        }

        return task;
    }


}
