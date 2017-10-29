package com.mdvns.mdvn.dashboard.papi.service.impl;

import com.mdvns.mdvn.common.beans.IterationModel;
import com.mdvns.mdvn.common.beans.Model;
import com.mdvns.mdvn.common.beans.RequirementInfo;
import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.common.beans.exception.BusinessException;
import com.mdvns.mdvn.common.beans.exception.ExceptionEnum;
import com.mdvns.mdvn.common.utils.FetchListUtil;
import com.mdvns.mdvn.dashboard.papi.config.WebConfig;
import com.mdvns.mdvn.dashboard.papi.domain.*;
import com.mdvns.mdvn.dashboard.papi.service.DashboardService;
import com.mdvns.mdvn.dashboard.papi.utils.LogUtil;

import com.sun.deploy.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class DashboardServiceImpl implements DashboardService {

    /* 注入RestTemplate*/
    @Autowired
    private RestTemplate restTemplate;

    /*注入WebConfig*/
    @Autowired
    private WebConfig webConfig;

    /*注入RestResponse*/
    @Autowired
    private RestResponse restResponse;


    /**
     * 进入dashboard,首先获取product backlogs信息（story列表）
     *
     * @param request
     * @return
     */
    @Override
    public RestResponse rtrvStoryList(RtrvAllStoryListRequest request) {
        LogUtil.sreviceStartLog("rtrvStoryList");
        List<RtrvAllStoryListResponse> storyListResponses = new ArrayList<>();
        //首先判断是否是第一次创建sprintInfo
        String findDashboardInfoByIdUrl = webConfig.getFindDashboardInfoByIdUrl();
        ParameterizedTypeReference parameTypeReference = new ParameterizedTypeReference<List<SprintInfo>>() {
        };
        List<SprintInfo> dashboards = FetchListUtil.fetch(restTemplate, findDashboardInfoByIdUrl, request.getProjId(), parameTypeReference);
        //已经创建过看板
        if (dashboards.size() != 0) {
            List<RtrvDashboardResponse> rtrvDashboardResponses = new ArrayList<>();
            for (int i = 0; i < dashboards.size(); i++) {
                RtrvDashboardResponse rtrvDashboardResponse = new RtrvDashboardResponse();
                String modelId = dashboards.get(i).getModelId();
                Map modelIdMap = new HashMap();
                modelIdMap.put("modelId", modelId);
                Model model = restTemplate.postForObject(webConfig.getFindModelByIdUrl(), modelId, Model.class);
                rtrvDashboardResponse.setModel(model);
                //通过modelId和projectId查询其他的sprint(除了product backlogs)
                RtrvDashboardRequest rtrvDashboardRequest = new RtrvDashboardRequest();
                rtrvDashboardRequest.setProjId(request.getProjId());
                rtrvDashboardRequest.setModleId(modelId);
                List<SprintInfo> sprintInfos = FetchListUtil.fetch(restTemplate, webConfig.getFindDashboardInfoByIdsUrl(), request.getProjId(), parameTypeReference);
                List<SprintStoryList> sprintStoryLists = new ArrayList<>();
                for (int j = 0; j < sprintInfos.size(); j++) {
                    String name = sprintInfos.get(j).getName();
                    String stories = sprintInfos.get(j).getItemIds();
                    Integer sprintIndex = sprintInfos.get(j).getSprintIndex();
                    //通过stories查询出他们的storyList
                    RtrvStoryListByStoryIdsRequest rtrvStoryListByStoryIdsRequest = new RtrvStoryListByStoryIdsRequest();
                    rtrvStoryListByStoryIdsRequest.setPage(1);
                    rtrvStoryListByStoryIdsRequest.setPageSize(Integer.MAX_VALUE);
                    SprintStoryList sprintStoryList = new SprintStoryList();
                    String[] sprintStoryIds = stories.split(",");
                    List<String> sprintStoryIdList = Arrays.asList(sprintStoryIds);
                    rtrvStoryListByStoryIdsRequest.setStoryIds(sprintStoryIdList);
                    try {
                        String url = webConfig.getRtrvStoryInfoListByStoryIdsUrl();
                        ResponseEntity<RtrvStoryListByStoryIdsResponse> response = this.restTemplate.postForEntity(url, rtrvStoryListByStoryIdsRequest, RtrvStoryListByStoryIdsResponse.class);
                        sprintStoryList.setStories(response.getBody().getStories());
                        sprintStoryList.setTotalElements(response.getBody().getTotalElements());
                        sprintStoryList.setRemarks(response.getBody().getRemarks());
                        sprintStoryLists.add(sprintStoryList);
                    } catch (Exception ex) {
                        throw new BusinessException(ExceptionEnum.DASHBOARD_DETAIL_STORY_NOT_RTRV);
                    }
                }
                rtrvDashboardResponse.setSprintStoryLists(sprintStoryLists);
                rtrvDashboardResponses.add(rtrvDashboardResponse);
            }
            restResponse.setStatusCode(String.valueOf(HttpStatus.OK));
            restResponse.setResponseCode("000");
            restResponse.setResponseBody(rtrvDashboardResponses);
            return restResponse;
        }
        //首先得到reqmntInfo的列表信息（主要获取reqmntId）
        try {
            String rtrvReqmntInfoBymodelIdUrl = webConfig.getRtrvReqmntInfoBymodelIdUrl();
            RtrvReqmntInfoByModelRequest reqmntInfoByModelRequest = new RtrvReqmntInfoByModelRequest();
            reqmntInfoByModelRequest.setProjId(request.getProjId());
            reqmntInfoByModelRequest.setCreatorId(request.getCreatorId());
            ParameterizedTypeReference parameterizedTypeReference = new ParameterizedTypeReference<List<RtrvReqmntInfoByModelIdResponse>>() {
            };
            List<RtrvReqmntInfoByModelIdResponse> responseList = FetchListUtil.fetch(restTemplate, rtrvReqmntInfoBymodelIdUrl, reqmntInfoByModelRequest, parameterizedTypeReference);
            for (int i = 0; i < responseList.size(); i++) {
                RtrvAllStoryListResponse rtrvAllStoryListResponse = new RtrvAllStoryListResponse();
                String modelId = responseList.get(i).getModelId();
                List<RequirementInfo> reqmntInfos = responseList.get(i).getRequirementInfos();
                String stoIds = null;
                Map modelIdMap = new HashMap();
                modelIdMap.put("modelId", modelId);
                Model model = restTemplate.postForObject(webConfig.getFindModelByIdUrl(), modelId, Model.class);
                rtrvAllStoryListResponse.setModel(model);
                //每个model下的reqmnt
                //通过reqmntIds查询storyList
                List<String> reqmntIds = new ArrayList();
                for (int k = 0; k < reqmntInfos.size(); k++) {
                    String reqmntId = reqmntInfos.get(k).getReqmntId();
                    if (!reqmntIds.isEmpty() && reqmntIds.contains(reqmntId)) {
                        continue;
                    }
                    reqmntIds.add(reqmntId);
                }
                RtrvStoryListByReqmntIdsRequest rtrvStoryListByReqmntIdsRequest = new RtrvStoryListByReqmntIdsRequest();
                rtrvStoryListByReqmntIdsRequest.setPage(1);
                rtrvStoryListByReqmntIdsRequest.setPageSize(Integer.MAX_VALUE);
                rtrvStoryListByReqmntIdsRequest.setReqmntIds(reqmntIds);
                try {
                    String url = webConfig.getRtrvStoryInfoListUrl();
                    ResponseEntity<RtrvStoryListByReqmntIdsResponse> response = this.restTemplate.postForEntity(url, rtrvStoryListByReqmntIdsRequest, RtrvStoryListByReqmntIdsResponse.class);
                    //得到storyIds
                    List<String> storyIds = new ArrayList();
                    for (int k = 0; k < response.getBody().getStories().size(); k++) {
                        String storyId = response.getBody().getStories().get(k).getStoryId();
                        storyIds.add(storyId);
                    }
                    stoIds = StringUtils.join(storyIds, ",");
                    rtrvAllStoryListResponse.setStories(response.getBody().getStories());
                    rtrvAllStoryListResponse.setTotalElements(response.getBody().getTotalElements());
                    rtrvAllStoryListResponse.setRemarks(response.getBody().getRemarks());
                    storyListResponses.add(rtrvAllStoryListResponse);
                } catch (Exception ex) {
                    throw new BusinessException(ExceptionEnum.DASHBOARD_DETAIL_REQMNTINFO_NOT_RTRV);
                }

                RtrvStoryListByReqmntIdsRequest rtrvStoryListByIdsRequest = new RtrvStoryListByReqmntIdsRequest();
                rtrvStoryListByIdsRequest.setPage(1);
                rtrvStoryListByIdsRequest.setPageSize(Integer.MAX_VALUE);
                List<String> backlogsReqIds = reqmntIds;

                //查询该model下的迭代计划模板（既sprint的name和他们的顺序）
                ParameterizedTypeReference parTypeReference = new ParameterizedTypeReference<List<IterationModel>>() {
                };
                List<IterationModel> iterationModels = FetchListUtil.fetch(restTemplate, webConfig.getFindIterationModelByIdUrl(), modelId, parTypeReference);
                /*对所有的reqmntInfo进行分类，分到各个sprint*/
                for (int k = 0; k < iterationModels.size(); k++) {
                    String name = iterationModels.get(k).getName();
                    String labelIds = iterationModels.get(k).getLabelIds();
                    String[] sprintlabelIds = labelIds.split(",");
                    List<String> sprintlabelIdList = Arrays.asList(sprintlabelIds);
                    List<String> reqIds = new ArrayList();
                    for (int m = 0; m < reqmntInfos.size(); m++) {
                        String labelId = reqmntInfos.get(m).getFunctionLabelId();
                        for (int l = 0; l < sprintlabelIdList.size(); l++) {
                            String sprintlabelId = sprintlabelIdList.get(l);
                            if (sprintlabelId.equals(labelId)) {
                                reqIds.add(reqmntInfos.get(m).getReqmntId());
                            }
                        }
                    }
                    for (int j = 0; j < reqIds.size(); j++) {
                        backlogsReqIds.remove(reqIds.get(j));
                    }
                    if (reqIds.size() != 0) {
                        rtrvStoryListByReqmntIdsRequest.setReqmntIds(reqIds);
                        String url = webConfig.getRtrvStoryInfoListUrl();
                        ResponseEntity<RtrvStoryListByReqmntIdsResponse> response = this.restTemplate.postForEntity(url, rtrvStoryListByReqmntIdsRequest, RtrvStoryListByReqmntIdsResponse.class);
                        //得到storyIds
                        List<String> storyIds = new ArrayList();
                        for (int n = 0; n < response.getBody().getStories().size(); n++) {
                            String storyId = response.getBody().getStories().get(n).getStoryId();
                            storyIds.add(storyId);
                        }
                        stoIds = StringUtils.join(storyIds, ",");
                        //通过labelIds查询他们下面的storyIds
                        Integer itIndex = iterationModels.get(k).getItIndex();
                        //创建sprintIndex为1,2,3,...的SprintInfo信息(除了sprintIndex为0)
                        CreateSprintInfoRequest createSprintInfoRequest = new CreateSprintInfoRequest();
                        createSprintInfoRequest.setCreatorId(request.getCreatorId());
                        createSprintInfoRequest.setModelId(modelId);
                        createSprintInfoRequest.setName(name);
                        createSprintInfoRequest.setItemIds(stoIds);
                        createSprintInfoRequest.setSprintIndex(itIndex);
                        createSprintInfoRequest.setProjId(request.getProjId());
                        try {
                            String createSprintInfoUrl = webConfig.getCreateSprintInfoUrl();
                            SprintInfo board = this.restTemplate.postForObject(createSprintInfoUrl, createSprintInfoRequest, SprintInfo.class);
                        } catch (Exception ex) {
                            throw new BusinessException(ExceptionEnum.DASHBOARD_NOT_CREATE);
                        }
                    }
                }
                //创建sprint(product backlogs)
                String sIds = null;
                if (backlogsReqIds.size() != 0) {
                    rtrvStoryListByIdsRequest.setReqmntIds(backlogsReqIds);
                    String url = webConfig.getRtrvStoryInfoListUrl();
                    ResponseEntity<RtrvStoryListByReqmntIdsResponse> response = this.restTemplate.postForEntity(url, rtrvStoryListByIdsRequest, RtrvStoryListByReqmntIdsResponse.class);
                    //得到storyIds
                    List<String> storyIds = new ArrayList();
                    for (int n = 0; n < response.getBody().getStories().size(); n++) {
                        String storyId = response.getBody().getStories().get(n).getStoryId();
                        storyIds.add(storyId);
                    }
                    sIds = StringUtils.join(storyIds, ",");
                }
                //通过labelIds查询他们下面的storyIds
                CreateSprintInfoRequest createSprintInfoRequest = new CreateSprintInfoRequest();
                createSprintInfoRequest.setCreatorId(request.getCreatorId());
                createSprintInfoRequest.setModelId(modelId);
                createSprintInfoRequest.setName("Product Backlogs");
                createSprintInfoRequest.setItemIds(sIds);
                createSprintInfoRequest.setSprintIndex(0);
                createSprintInfoRequest.setProjId(request.getProjId());
                try {
                    String createSprintInfoUrl = webConfig.getCreateSprintInfoUrl();
                    SprintInfo board = this.restTemplate.postForObject(createSprintInfoUrl, createSprintInfoRequest, SprintInfo.class);
                } catch (Exception ex) {
                    throw new BusinessException(ExceptionEnum.DASHBOARD_NOT_CREATE);
                }
            }
        } catch (Exception ex) {
            throw new BusinessException(ExceptionEnum.DASHBOARD_DETAIL_STORY_NOT_RTRV);
        }
        restResponse.setStatusCode(String.valueOf(HttpStatus.OK));
        restResponse.setResponseCode("000");
        restResponse.setResponseBody(storyListResponses);
        return restResponse;
    }


    /**
     * 更改dashboard
     *
     * @param request
     * @return
     */
    @Override
    public RestResponse updateDashboard(UpdateDashboardRequest request) {
        RtrvDashboardResponse rtrvDashboardResponse = new RtrvDashboardResponse();
        List<SprintStoryList> sprintStoryLists = new ArrayList<>();
        Map modelIdMap = new HashMap();
        String modelId = request.getModelId();
        modelIdMap.put("modelId", modelId);
        Model model = restTemplate.postForObject(webConfig.getFindModelByIdUrl(), modelId, Model.class);
        rtrvDashboardResponse.setModel(model);
        //首先更改dashboard
        List<SprintInfo> sprintInfos = new ArrayList<>();
        SprintInfo dashboard = new SprintInfo();
        RtrvStoryListByStoryIdsRequest rtrvStoryListByStoryIdsRequest = new RtrvStoryListByStoryIdsRequest();
        rtrvStoryListByStoryIdsRequest.setPage(1);
        rtrvStoryListByStoryIdsRequest.setPageSize(Integer.MAX_VALUE);
        try {
            String updateDashboardUrl = webConfig.getUpdateDashboardUrl();
            sprintInfos = this.restTemplate.postForObject(updateDashboardUrl, request, List.class);
            for (int i = 0; i < sprintInfos.size(); i++) {
                String itemIds = sprintInfos.get(i).getItemIds();
                SprintStoryList sprintStoryList = new SprintStoryList();
                String[] sprintStoryIds = itemIds.split(",");
                List<String> sprintStoryIdList = Arrays.asList(sprintStoryIds);
                rtrvStoryListByStoryIdsRequest.setStoryIds(sprintStoryIdList);
                try {
                    String url = webConfig.getRtrvStoryInfoListByStoryIdsUrl();
                    ResponseEntity<RtrvStoryListByStoryIdsResponse> response = this.restTemplate.postForEntity(url, rtrvStoryListByStoryIdsRequest, RtrvStoryListByStoryIdsResponse.class);
                    sprintStoryList.setStories(response.getBody().getStories());
                    sprintStoryList.setTotalElements(response.getBody().getTotalElements());
                    sprintStoryList.setRemarks(response.getBody().getRemarks());
                    sprintStoryLists.add(sprintStoryList);
                } catch (Exception ex) {
                    throw new BusinessException(ExceptionEnum.DASHBOARD_DETAIL_STORY_NOT_RTRV);
                }
            }
        } catch (Exception ex) {
            throw new BusinessException(ExceptionEnum.DASHBOARD_NOT_UPDATE);
        }
        restResponse.setStatusCode(String.valueOf(HttpStatus.OK));
        restResponse.setResponseCode("000");
        restResponse.setResponseBody(rtrvDashboardResponse);
        return restResponse;
    }

    @Override
    public RestResponse assignSprint(AssignStoryListByItRequest request) {
        String modelId = request.getModelId();
        Map modelIdMap = new HashMap();
        modelIdMap.put("modelId", modelId);
        //查询该model下的迭代计划模板（既sprint的name和他们的顺序）
        List<IterationModel> iterationModels = restTemplate.postForObject(webConfig.getFindIterationModelByIdUrl(), modelId, List.class);
        for (int j = 0; j < iterationModels.size(); j++) {
            String name = iterationModels.get(j).getName();
            String labelIds = iterationModels.get(j).getLabelIds();
            Integer itIndex = iterationModels.get(j).getItIndex();
        }
        return null;
    }

}
