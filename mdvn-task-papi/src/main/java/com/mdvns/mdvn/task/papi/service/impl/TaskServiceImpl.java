package com.mdvns.mdvn.task.papi.service.impl;

import com.mdvns.mdvn.common.beans.*;
import com.mdvns.mdvn.common.beans.exception.BusinessException;
import com.mdvns.mdvn.common.beans.exception.ExceptionEnum;
import com.mdvns.mdvn.common.enums.AuthEnum;
import com.mdvns.mdvn.common.utils.FetchListUtil;
import com.mdvns.mdvn.common.utils.StaffAuthUtil;
import com.mdvns.mdvn.task.papi.config.UrlConfig;
import com.mdvns.mdvn.task.papi.domain.*;
import com.mdvns.mdvn.task.papi.domain.TaskDetail;
import com.mdvns.mdvn.task.papi.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

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
        if (request == null || StringUtils.isEmpty(request.getProjId()) || StringUtils.isEmpty(request.getCreatorId()) ||
                StringUtils.isEmpty(request.getStoryId()) ||
                StringUtils.isEmpty(request.getDescription()) || request.getDeliver() == null ||
                StringUtils.isEmpty(request.getDeliver().getModelId()) || StringUtils.isEmpty(request.getDeliver().getName()) ||
                request.getStartTime() == null || request.getEndTime() == null || request.getUsedTime() == null) {
            return new RestResponse(ExceptionEnum.PARAMS_EXCEPTION.getErroCode(), null);
        }

        RestResponse response = new RestResponse();
        TaskDetail task = null;

        try {
            request.setAssigneeId(request.getCreatorId());
            task = restTemplate.postForObject(urlConfig.getSaveTaskUrl(), request, TaskDetail.class);

            // 查询creator和assinee
            Staff creator = restTemplate.postForObject(urlConfig.getRtrvStaffInfoUrl(), task.getCreatorId(), Staff.class);
            task.setCreator(creator);

            Staff assignee = restTemplate.postForObject(urlConfig.getRtrvStaffInfoUrl(), task.getAssigneeId(), Staff.class);
            task.setAssignee(assignee);

            // todo 查询附件


        } catch (RestClientException e) {
            e.printStackTrace();
            response.setResponseCode(ExceptionEnum.TASK_SAVE_FAILED.getErroCode());
            response.setResponseMsg(ExceptionEnum.TASK_SAVE_FAILED.getErrorMsg());
        }
        //分配权限
        System.out.println(request.getProjId() + task.getTaskId() + request.getCreatorId() + AuthEnum.TMEMBER.getCode());
        List<StaffAuthInfo> staffAuthInfos = StaffAuthUtil.assignAuthForCreator(restTemplate, request.getProjId(), task.getTaskId(), request.getCreatorId(), AuthEnum.TMEMBER.getCode());
        task.setStaffAuthInfo(staffAuthInfos);
        LOG.info("新建Task，分配权限成功!");
        //创建一个task之后，如果这个story是done的要改为inprogress
        // 返回story对象
        RtrvStoryDetailRequest rtrvStoryDetailRequest = new RtrvStoryDetailRequest();
        rtrvStoryDetailRequest.setStoryId(request.getStoryId());
        String rtrvStoryBaseInfoUrl = urlConfig.getRtrvStoryBaseInfoUrl();
        Story story = restTemplate.postForObject(rtrvStoryBaseInfoUrl, rtrvStoryDetailRequest, Story.class);
        if (story.getStatus().equals("done")) {
            story.setStatus("inProgress");
            String updateStoryBaseInfoUrl = urlConfig.getUpdateStoryBaseInfoUrl();
            try {
                story = restTemplate.postForObject(updateStoryBaseInfoUrl, story, Story.class);
            } catch (Exception ex) {
                throw new BusinessException(ExceptionEnum.STORY_BASEINFO_NOT_UPDATE);
            }
        }
        task.setStory(story);
        response.setStatusCode(String.valueOf(HttpStatus.OK));
        response.setResponseBody(task);
        response.setResponseCode("000");
        response.setResponseMsg("ok");

        /**
         * 消息推送(创建task)
         */
        try {
            SendMessageRequest sendMessageRequest = new SendMessageRequest();
            ServerPush serverPush = new ServerPush();
            String initiatorId = request.getCreatorId();
            Staff initiator = this.restTemplate.postForObject(urlConfig.getRtrvStaffInfoUrl(), initiatorId, Staff.class);
            serverPush.setInitiator(initiator);
            serverPush.setSubjectType("task");
            serverPush.setSubjectId(task.getTaskId());
            serverPush.setType("create");
            serverPush.setTaskByStoryId(task.getStoryId());
            rtrvStoryDetailRequest.setStaffId(initiatorId);
            ParameterizedTypeReference reqmntTagTypeReference = new ParameterizedTypeReference<List<StoryRoleMember>>() {
            };
            List<StoryRoleMember> storyRoleMembers = FetchListUtil.fetch(restTemplate, urlConfig.getRtrvSRoleMembersUrl(), rtrvStoryDetailRequest, reqmntTagTypeReference);
            //选出不同的成员
            List<String> staffIds = new ArrayList<String>();
            for (int i = 0; i < storyRoleMembers.size(); i++) {
                String id = storyRoleMembers.get(i).getStaffId();
                if (!staffIds.isEmpty() && staffIds.contains(id)) {
                    continue;
                }
                staffIds.add(id);
            }
            //查询该task上story的创建者
            String createId = story.getCreatorId();
            if (!staffIds.contains(createId)) {
                staffIds.add(createId);
            }
            sendMessageRequest.setInitiatorId(initiatorId);
            sendMessageRequest.setStaffIds(staffIds);
            sendMessageRequest.setServerPushResponse(serverPush);
            Boolean flag = this.restTemplate.postForObject(urlConfig.getSendMessageUrl(), sendMessageRequest, Boolean.class);
            System.out.println(flag);
        } catch (Exception e) {
            LOG.error("消息推送(创建task)出现异常，异常信息：" + e);
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

            for (int i = 0; i < taskList.size(); i++) {
                //获取权限信息
                LOG.info("projId:" + taskList.get(i).getProjId());
                LOG.info("taskId:" + taskList.get(i).getTaskId());
                LOG.info("staffId:" + request.getStaffId());
                List<StaffAuthInfo> staffAuthInfos = StaffAuthUtil.rtrvStaffAuthInfo(this.restTemplate, taskList.get(i).getProjId(), taskList.get(i).getTaskId(), request.getStaffId());
                taskList.get(i).setStaffAuthInfo(staffAuthInfos);
            }

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


            restResponse.setStatusCode(String.valueOf(HttpStatus.OK));
            restResponse.setResponseCode("000");
            restResponse.setResponseMsg("ok");
            restResponse.setResponseBody(taskList);
        } catch (RestClientException e) {
            e.printStackTrace();
            restResponse.setResponseCode(ExceptionEnum.SAPI_EXCEPTION.getErroCode());
            restResponse.setResponseMsg(ExceptionEnum.SAPI_EXCEPTION.getErrorMsg());
        }

        return restResponse;
    }

    /**
     * 获取单个task信息
     *
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public RestResponse retrieveTaskInfo(RtrvTaskInfoRequest request) throws Exception {
        RestResponse restResponse = new RestResponse();

        // 参数检查
        if (request == null || request.getTaskId() == null) {
            restResponse.setResponseCode(ExceptionEnum.PARAMS_EXCEPTION.getErroCode());
            restResponse.setResponseMsg(ExceptionEnum.PARAMS_EXCEPTION.getErrorMsg());
            return restResponse;
        }

        try {
            // 查询task sapi
            ParameterizedTypeReference typeReference = new ParameterizedTypeReference<TaskDetail>() {
            };
            String taskId = request.getTaskId();
            TaskDetail taskDetail = restTemplate.postForObject(urlConfig.getRtrvTaskInfoUrl(), taskId, TaskDetail.class);
            // 查询creator和assignee
            String creatorId = taskDetail.getCreatorId();
            Staff creatorInfo = this.restTemplate.postForObject(urlConfig.getRtrvStaffInfoUrl(), creatorId, Staff.class);
            String assigneeId = taskDetail.getAssigneeId();
            Staff assigneeInfo = this.restTemplate.postForObject(urlConfig.getRtrvStaffInfoUrl(), assigneeId, Staff.class);
            taskDetail.setAssignee(assigneeInfo);
            taskDetail.setCreator(creatorInfo);
            //附件
            getTaskAttachment(taskDetail);

            //获取权限信息
            List<StaffAuthInfo> staffAuthInfos = StaffAuthUtil.rtrvStaffAuthInfo(this.restTemplate, taskDetail.getProjId(), taskId, request.getStaffId());
            taskDetail.setStaffAuthInfo(staffAuthInfos);
            LOG.info("获取task权限" + staffAuthInfos.toString());
            restResponse.setResponseCode("000");
            restResponse.setStatusCode(String.valueOf(HttpStatus.OK));
            restResponse.setResponseMsg("ok");
            restResponse.setResponseBody(taskDetail);
        } catch (RestClientException e) {
            e.printStackTrace();
            restResponse.setResponseCode(ExceptionEnum.SAPI_EXCEPTION.getErroCode());
            restResponse.setResponseMsg(ExceptionEnum.SAPI_EXCEPTION.getErrorMsg());
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
        //给消息推送选择推送类型（原taskInfo）
        String taskId = request.getTaskId();
        TaskDetail taskDetail = restTemplate.postForObject(urlConfig.getRtrvTaskInfoUrl(), taskId, TaskDetail.class);
        TaskDetail result = new TaskDetail();
        Story story = new Story();
        try {
            result = restTemplate.postForObject(urlConfig.getUpdateTaskUrl(), request, TaskDetail.class);
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

                //更改一个task之后判断其他所有的task进度是否都为100%
                // 查询task sapi
                RtrvTaskListRequest rtrvTaskListRequest = new RtrvTaskListRequest();
                rtrvTaskListRequest.setStoryId(result.getStoryId());
                ParameterizedTypeReference typeReference = new ParameterizedTypeReference<List<TaskDetail>>() {
                };
                List<TaskDetail> tasks = FetchListUtil.fetch(restTemplate, urlConfig.getRtrvTaskListUrl(), rtrvTaskListRequest, typeReference);

                // 返回story对象
                RtrvStoryDetailRequest rtrvStoryDetailRequest = new RtrvStoryDetailRequest();
                rtrvStoryDetailRequest.setStoryId(result.getStoryId());
                String rtrvStoryBaseInfoUrl = urlConfig.getRtrvStoryBaseInfoUrl();
                story = restTemplate.postForObject(rtrvStoryBaseInfoUrl, rtrvStoryDetailRequest, Story.class);
                //所有的task进度都为100时，story的状态变为done
                Integer progresses = 0;
                for (int i = 0; i < tasks.size(); i++) {
                    progresses += tasks.get(i).getProgress();
                }
                Story st = story;
                String status = null;
                if (progresses / tasks.size() == 100) {
                    status = "done";
                    story.setStatus(status);
                }
                if (progresses / tasks.size() != 100 && progresses / tasks.size() != 0) {
                    status = "inProgress";
                    story.setStatus(status);
                }
                String updateStoryBaseInfoUrl = urlConfig.getUpdateStoryBaseInfoUrl();
                try {
                    st = restTemplate.postForObject(updateStoryBaseInfoUrl, story, Story.class);
                } catch (Exception ex) {
                    throw new BusinessException(ExceptionEnum.STORY_BASEINFO_NOT_UPDATE);
                }

                result.setStory(st);
                restResponse.setStatusCode(String.valueOf(HttpStatus.OK));
                restResponse.setResponseCode("000");
                restResponse.setResponseMsg("ok");
                restResponse.setResponseBody(result);
            }
        } catch (RestClientException e) {
            e.printStackTrace();
            restResponse.setResponseCode(ExceptionEnum.SAPI_EXCEPTION.getErroCode());
            restResponse.setResponseMsg(ExceptionEnum.SAPI_EXCEPTION.getErrorMsg());
        }

        /**
         * 消息推送(更改task)
         */
        try {
            SendMessageRequest sendMessageRequest = new SendMessageRequest();
            ServerPush serverPush = new ServerPush();
            String initiatorId = result.getCreatorId();
            Staff initiator = this.restTemplate.postForObject(urlConfig.getRtrvStaffInfoUrl(), initiatorId, Staff.class);
            serverPush.setInitiator(initiator);
            serverPush.setSubjectType("task");
            serverPush.setSubjectId(request.getTaskId());
            serverPush.setTaskByStoryId(result.getStoryId());
            //选择类型
            Integer oldProgress = taskDetail.getProgress();
            Integer newProgress = result.getProgress();
            if (oldProgress == newProgress) {
                serverPush.setType("update");
            } else {
                serverPush.setOldProgress(oldProgress);
                serverPush.setNewProgress(newProgress);
                serverPush.setType("update progress");
            }
            RtrvStoryDetailRequest rtrvStoryDetailRequest = new RtrvStoryDetailRequest();
            rtrvStoryDetailRequest.setStoryId(result.getStoryId());
            rtrvStoryDetailRequest.setStaffId(initiatorId);
            ParameterizedTypeReference reqmntTagTypeReference = new ParameterizedTypeReference<List<StoryRoleMember>>() {
            };
            List<StoryRoleMember> storyRoleMembers = FetchListUtil.fetch(restTemplate, urlConfig.getRtrvSRoleMembersUrl(), rtrvStoryDetailRequest, reqmntTagTypeReference);
            //选出不同的角色
            List<String> staffIds = new ArrayList<String>();
            for (int i = 0; i < storyRoleMembers.size(); i++) {
                String id = storyRoleMembers.get(i).getStaffId();
                if (!staffIds.isEmpty() && staffIds.contains(id)) {
                    continue;
                }
                staffIds.add(id);
            }
            //查询该task上story的创建者
            String createId = story.getCreatorId();
            if (!staffIds.contains(createId)) {
                staffIds.add(createId);
            }
            sendMessageRequest.setInitiatorId(initiatorId);
            sendMessageRequest.setStaffIds(staffIds);
            sendMessageRequest.setServerPushResponse(serverPush);
            Boolean flag = this.restTemplate.postForObject(urlConfig.getSendMessageUrl(), sendMessageRequest, Boolean.class);
            System.out.println(flag);
        } catch (Exception e) {
            LOG.error("消息推送(更改task)出现异常，异常信息：" + e);
        }

        return restResponse;
    }

    @Override
    public RestResponse addAttachForTask(AddAttachRequest request) {
        RestResponse restResponse = new RestResponse();

        if (request == null || StringUtils.isEmpty(request.getTaskId())) {
            restResponse.setResponseCode(ExceptionEnum.PARAMS_EXCEPTION.getErroCode());
            restResponse.setResponseMsg(ExceptionEnum.PARAMS_EXCEPTION.getErrorMsg());
            return restResponse;
        }

        TaskDetail result = restTemplate.postForObject(urlConfig.getAddAttachForTaskUrl(), request, TaskDetail.class);
        if (result == null) {
            restResponse.setResponseCode(ExceptionEnum.TASK_DOES_NOT_EXIST.getErroCode());
            restResponse.setResponseMsg(ExceptionEnum.TASK_DOES_NOT_EXIST.getErrorMsg());
        } else {
            queryFullTaskDetail(result);
            restResponse.setStatusCode(String.valueOf(HttpStatus.OK));
            restResponse.setResponseCode("000");
            restResponse.setResponseMsg("ok");
            restResponse.setResponseBody(result);
        }
        /**
         * 消息推送(更改task)
         */
        this.serverPushAttach(result);

        return restResponse;
    }

    /**
     * 删除或者添加附件之后的消息推送
     * @param result
     * @return
     */
    public void serverPushAttach(TaskDetail result){
        /**
         * 消息推送(更改task)
         */
        try {
            //给消息推送选择推送类型（原taskInfo）
            SendMessageRequest sendMessageRequest = new SendMessageRequest();
            ServerPush serverPush = new ServerPush();
            String initiatorId = result.getCreatorId();
            Staff initiator = this.restTemplate.postForObject(urlConfig.getRtrvStaffInfoUrl(), initiatorId, Staff.class);
            serverPush.setInitiator(initiator);
            serverPush.setSubjectType("task");
            serverPush.setSubjectId(result.getTaskId());
            serverPush.setTaskByStoryId(result.getStoryId());
            //选择类型
            serverPush.setType("update");
            RtrvStoryDetailRequest rtrvStoryDetailRequest = new RtrvStoryDetailRequest();
            rtrvStoryDetailRequest.setStoryId(result.getStoryId());
            rtrvStoryDetailRequest.setStaffId(initiatorId);
            ParameterizedTypeReference reqmntTagTypeReference = new ParameterizedTypeReference<List<StoryRoleMember>>() {
            };
            List<StoryRoleMember> storyRoleMembers = FetchListUtil.fetch(restTemplate, urlConfig.getRtrvSRoleMembersUrl(), rtrvStoryDetailRequest, reqmntTagTypeReference);
            //选出不同的角色
            List<String> staffIds = new ArrayList<String>();
            for (int i = 0; i < storyRoleMembers.size(); i++) {
                String id = storyRoleMembers.get(i).getStaffId();
                if (!staffIds.isEmpty() && staffIds.contains(id)) {
                    continue;
                }
                staffIds.add(id);
            }
            //查询该task上story的创建者
            String createId = this.restTemplate.postForObject(urlConfig.getRtrvCreatorIdUrl(), result.getStoryId(), String.class);
            if (!staffIds.contains(createId)) {
                staffIds.add(createId);
            }
            sendMessageRequest.setInitiatorId(initiatorId);
            sendMessageRequest.setStaffIds(staffIds);
            sendMessageRequest.setServerPushResponse(serverPush);
            Boolean flag = this.restTemplate.postForObject(urlConfig.getSendMessageUrl(), sendMessageRequest, Boolean.class);
            System.out.println(flag);
        } catch (Exception e) {
            LOG.error("消息推送(更改task)出现异常，异常信息：" + e);
        }
    }

    @Override
    public RestResponse deleteAttachForTask(AddAttachRequest request) {
        RestResponse restResponse = new RestResponse();

        if (request == null || StringUtils.isEmpty(request.getTaskId())) {
            restResponse.setResponseCode(ExceptionEnum.PARAMS_EXCEPTION.getErroCode());
            restResponse.setResponseMsg(ExceptionEnum.PARAMS_EXCEPTION.getErrorMsg());
            return restResponse;
        }
        TaskDetail result = restTemplate.postForObject(urlConfig.getDeleteAttachForTaskUrl(), request, TaskDetail.class);
        if (result == null) {
            restResponse.setResponseCode(ExceptionEnum.TASK_DOES_NOT_EXIST.getErroCode());
            restResponse.setResponseMsg(ExceptionEnum.TASK_DOES_NOT_EXIST.getErrorMsg());
        } else {
            queryFullTaskDetail(result);
            restResponse.setStatusCode(String.valueOf(HttpStatus.OK));
            restResponse.setResponseCode("000");
            restResponse.setResponseMsg("ok");
            restResponse.setResponseBody(result);
        }
        /**
         * 消息推送(更改task)
         */
        this.serverPushAttach(result);

        return restResponse;
    }

    /**
     * 获取某个task的历史记录信息
     *
     * @param request
     * @return
     */
    @Override
    public RestResponse getTaskHistoryInfo(RtrvTaskHistoryListRequest request) {
        RestResponse restResponse = new RestResponse();
        RtrvTaskHistoryListResponse rtrvTaskHistoryListResponse = new RtrvTaskHistoryListResponse();
        // 参数检查
        if (request == null || request.getTaskId() == null) {
            restResponse.setResponseCode(ExceptionEnum.PARAMS_EXCEPTION.getErroCode());
            restResponse.setResponseMsg(ExceptionEnum.PARAMS_EXCEPTION.getErrorMsg());
            return restResponse;
        }

        try {
            // 查询task sapi
            rtrvTaskHistoryListResponse = this.restTemplate.postForObject(urlConfig.getRtrvTaskHistoryInfoUrl(), request, RtrvTaskHistoryListResponse.class);
            List<TaskHistory> taskHistories = rtrvTaskHistoryListResponse.getTaskHistories();
            for (int i = 0; i < taskHistories.size(); i++) {
                TaskHistory taskHistory = taskHistories.get(i);
                // 查询updateInfo
                String updateId = taskHistory.getUpdateId();
                Staff updateInfo = this.restTemplate.postForObject(urlConfig.getRtrvStaffInfoUrl(), updateId, Staff.class);
                taskHistory.setUpdateInfo(updateInfo);
                //增加了附件
                if (!StringUtils.isEmpty(taskHistory.getAddAttachId())) {
                    Integer addAttchId = taskHistory.getAddAttachId();
                    String retrieveUrl = urlConfig.getRetrieveUrl();
                    LOG.info("URLwei：{}", retrieveUrl);
                    AttchInfo attchInfo = this.restTemplate.postForObject(retrieveUrl, addAttchId, AttchInfo.class);
                    taskHistory.setAddAttchInfo(attchInfo);
                }
                //删除了附件
                if (!StringUtils.isEmpty(taskHistory.getDeleteAttachId())) {
                    Integer deleteAttchId = taskHistory.getDeleteAttachId();
                    String retrieveUrl = urlConfig.getRetrieveUrl();
                    LOG.info("URLwei：{}", retrieveUrl);
                    AttchInfo attchInfo = this.restTemplate.postForObject(retrieveUrl, deleteAttchId, AttchInfo.class);
                    taskHistory.setDeleteAttchInfo(attchInfo);
                }
                //修改了进度
                if (!StringUtils.isEmpty(taskHistory.getBeforeProgress()) && !StringUtils.isEmpty(taskHistory.getNowProgress())) {
                    Integer beforeProgress = taskHistory.getBeforeProgress();
                    Integer nowProgress = taskHistory.getNowProgress();
                }
                //修改了备注
                if (!StringUtils.isEmpty(taskHistory.getBeforeRemarks()) && !StringUtils.isEmpty(taskHistory.getNowRemarks())) {
                    String beforeComment = taskHistory.getBeforeRemarks();
                    String nowComment = taskHistory.getNowRemarks();
                }
            }
            restResponse.setResponseCode("000");
            restResponse.setStatusCode(String.valueOf(HttpStatus.OK));
            restResponse.setResponseMsg("ok");
            restResponse.setResponseBody(taskHistories);
        } catch (RestClientException e) {
            e.printStackTrace();
            restResponse.setResponseCode(ExceptionEnum.SAPI_EXCEPTION.getErroCode());
            restResponse.setResponseMsg(ExceptionEnum.SAPI_EXCEPTION.getErrorMsg());
        }

        return restResponse;
    }


    // 查询task的完整信息
    private void queryFullTaskDetail(TaskDetail detail) {
        // 查询creator和assinee
        Staff creator = restTemplate.postForObject(urlConfig.getRtrvStaffInfoUrl(), detail.getCreatorId(), Staff.class);
        detail.setCreator(creator);
        Staff assignee = restTemplate.postForObject(urlConfig.getRtrvStaffInfoUrl(), detail.getAssigneeId(), Staff.class);
        detail.setAssignee(assignee);

        // 查询附件
        getTaskAttachment(detail);
    }


    // query task attachment
    private TaskDetail getTaskAttachment(TaskDetail task) {
        if (task == null) {
            return null;
        }

        final String ids = task.getAttachmentIds();
        if (!StringUtils.isEmpty(ids)) {
            ResponseEntity<RestResponse> responseEntity = restTemplate.getForEntity(urlConfig.getGetAttachmentListByIdsUrl() + ids, RestResponse.class);
            task.setAttachUrlList((List<AttchInfo>) responseEntity.getBody().getResponseBody());
//            ResponseEntity<RestResponse> responseEntity = restTemplate.getForEntity(urlConfig.getGetAttachmentListByIdsUrl(), RestResponse.class, ids);
//            task.setAttachUrlList((List)responseEntity.getBody().getResponseBody());
        }

        return task;
    }


}
