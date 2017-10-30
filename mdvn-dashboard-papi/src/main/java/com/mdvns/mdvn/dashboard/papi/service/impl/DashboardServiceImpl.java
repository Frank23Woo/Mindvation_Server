package com.mdvns.mdvn.dashboard.papi.service.impl;

import com.mdvns.mdvn.common.beans.*;
import com.mdvns.mdvn.common.beans.exception.BusinessException;
import com.mdvns.mdvn.common.beans.exception.ExceptionEnum;
import com.mdvns.mdvn.common.utils.FetchListUtil;
import com.mdvns.mdvn.dashboard.papi.config.WebConfig;
import com.mdvns.mdvn.dashboard.papi.domain.*;
import com.mdvns.mdvn.dashboard.papi.domain.SprintInfo;
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


    private List<RtrvDashboardResponse> rtrvDashboard(RtrvAllStoryListRequest request) {
        List<RtrvDashboardResponse> rtrvDashboardResponses = new ArrayList<>();
        //首先判断是否是第一次创建sprintInfo
        String findDashboardInfoByIdUrl = webConfig.getFindDashboardInfoByIdUrl();
        ParameterizedTypeReference parameTypeReference = new ParameterizedTypeReference<List<SprintInfo>>() {
        };
        List<SprintInfo> sprintInfoList = FetchListUtil.fetch(restTemplate, findDashboardInfoByIdUrl, request.getProjId(), parameTypeReference);
        //------------------------已经创建过看板
            for (int i = 0; i < sprintInfoList.size(); i++) {
                RtrvDashboardResponse rtrvDashboardResponse = new RtrvDashboardResponse();
//                //创建者对象
//                String creatorId = sprintInfoList.get(i).getCreatorId();
//                Staff staff = this.restTemplate.postForObject(webConfig.getRtrvStaffInfoUrl(),creatorId,Staff.class);
//                rtrvDashboardResponse.setCreatorInfo(staff);
                //模型对象
                String modelId = sprintInfoList.get(i).getModelId();
                Map modelIdMap = new HashMap();
                modelIdMap.put("modelId", modelId);
                Model model = restTemplate.postForObject(webConfig.getFindModelByIdUrl(), modelId, Model.class);
                rtrvDashboardResponse.setModel(model);
                //通过modelId和projectId查询所有的sprint
                RtrvDashboardRequest rtrvDashboardRequest = new RtrvDashboardRequest();
                rtrvDashboardRequest.setProjId(request.getProjId());
                rtrvDashboardRequest.setModleId(modelId);
                ParameterizedTypeReference pTypeReference = new ParameterizedTypeReference<List<SprintInfo>>() {
                };
                List<SprintInfo> sprintInfos = FetchListUtil.fetch(restTemplate, webConfig.getFindDashboardInfoByIdsUrl(), rtrvDashboardRequest, pTypeReference);
                List<SprintStoryListAndLabelId> sprintStoryLists = new ArrayList<>();
                for (int j = 0; j < sprintInfos.size(); j++) {
                    SprintStoryListAndLabelId sprintStoryList = new SprintStoryListAndLabelId();
                    sprintStoryList.setSprintInfo(sprintInfos.get(j));
                    String labIds = sprintInfos.get(j).getLabelIds();
                    if (null != labIds) {
                        String[] sprintlabIds = labIds.split(",");
                        List<String> sprintlabIdList = Arrays.asList(sprintlabIds);
                        sprintStoryList.setLabelIds(sprintlabIdList);
                    }
                    String stories = sprintInfos.get(j).getItemIds();
                    //通过stories查询出他们的storyList
                    if (null == stories) {
                        sprintStoryLists.add(sprintStoryList);
                        break;
                    }
                    RtrvStoryListByStoryIdsRequest rtrvStoryListByStoryIdsRequest = new RtrvStoryListByStoryIdsRequest();
                    rtrvStoryListByStoryIdsRequest.setPage(1);
                    rtrvStoryListByStoryIdsRequest.setPageSize(Integer.MAX_VALUE);
                    String[] sprintStoryIds = stories.split(",");
                    List<String> sprintStoryIdList = Arrays.asList(sprintStoryIds);
                    rtrvStoryListByStoryIdsRequest.setStoryIds(sprintStoryIdList);
                    try {
                        String url = webConfig.getRtrvStoryInfoListByStoryIdsUrl();
                        ResponseEntity<RtrvStoryListByStoryIdsResponse> response = this.restTemplate.postForEntity(url, rtrvStoryListByStoryIdsRequest, RtrvStoryListByStoryIdsResponse.class);
                        List<Story> storyList = response.getBody().getStories();
                        List<StoryAndLabelId>  storyAndLabelIds = new ArrayList<>();
                        for (int k = 0; k < storyList.size(); k++) {
                            StoryAndLabelId storyAndLabelId = new StoryAndLabelId();
                            String storyId = storyList.get(k).getStoryId();
                            RequirementInfo requirementInfo = this.restTemplate.postForObject(webConfig.getRtrvlabelIdBystoryIdUrl(),storyId,RequirementInfo.class);
                            String labelId = requirementInfo.getFunctionLabelId();
                            storyAndLabelId.setLabelId(labelId);
                            storyAndLabelId.setStory(storyList.get(k));
                            storyAndLabelIds.add(storyAndLabelId);
                        }

                        sprintStoryList.setStories(storyAndLabelIds);
                        sprintStoryList.setTotalElements(response.getBody().getTotalElements());
                        sprintStoryList.setRemarks(response.getBody().getRemarks());
                    } catch (Exception ex) {
                        throw new BusinessException(ExceptionEnum.DASHBOARD_DETAIL_STORY_NOT_RTRV);
                    }
                    sprintStoryLists.add(sprintStoryList);
                }
                rtrvDashboardResponse.setSprintStoryLists(sprintStoryLists);
                rtrvDashboardResponses.add(rtrvDashboardResponse);
            }
        return rtrvDashboardResponses;
    }
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
        List<RtrvDashboardResponse> rtrvDashboardResponses = new ArrayList<>();
        //首先判断是否是第一次创建sprintInfo
        String findDashboardInfoByIdUrl = webConfig.getFindDashboardInfoByIdUrl();
        ParameterizedTypeReference parameTypeReference = new ParameterizedTypeReference<List<SprintInfo>>() {
        };
        List<SprintInfo> sprintInfoList = FetchListUtil.fetch(restTemplate, findDashboardInfoByIdUrl, request.getProjId(), parameTypeReference);

        //------------------------已经创建过看板
        if (sprintInfoList.size() != 0) {

//            rtrvDashboard(request);

            restResponse.setStatusCode(String.valueOf(HttpStatus.OK));
            restResponse.setResponseCode("000");
            restResponse.setResponseMsg("请求成功");
            restResponse.setResponseBody(rtrvDashboard(request));
            return restResponse;
        }
        //----------------(第一次获取)
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

                //---------------------------------创建product backlogs
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
//                    for (int j = 0; j < reqIds.size(); j++) {
//                        backlogsReqIds.remove(reqIds.get(j));
//                    }
                    //---------------------------------创建MVP1，MVP2
//                    if (reqIds.size() != 0) {
//                        rtrvStoryListByReqmntIdsRequest.setReqmntIds(reqIds);
//                        String url = webConfig.getRtrvStoryInfoListUrl();
//                        ResponseEntity<RtrvStoryListByReqmntIdsResponse> response = this.restTemplate.postForEntity(url, rtrvStoryListByReqmntIdsRequest, RtrvStoryListByReqmntIdsResponse.class);
//                        //得到storyIds
//                        List<String> storyIds = new ArrayList();
//                        for (int n = 0; n < response.getBody().getStories().size(); n++) {
//                            String storyId = response.getBody().getStories().get(n).getStoryId();
//                            storyIds.add(storyId);
//                        }
//                        stoIds = StringUtils.join(storyIds, ",");
                        //通过labelIds查询他们下面的storyIds
                        Integer itIndex = iterationModels.get(k).getItIndex();
                        //创建sprintIndex为1,2,3,...的SprintInfo信息(除了sprintIndex为0)
                        CreateSprintInfoRequest creSprintInfoRequest = new CreateSprintInfoRequest();
                        creSprintInfoRequest.setCreatorId(request.getCreatorId());
                        creSprintInfoRequest.setModelId(modelId);
                        creSprintInfoRequest.setLabelIds(labelIds);
                        creSprintInfoRequest.setName(name);
//                        creSprintInfoRequest.setItemIds(stoIds);
                        creSprintInfoRequest.setItemIds("");
                        creSprintInfoRequest.setSprintIndex(itIndex);
                        creSprintInfoRequest.setProjId(request.getProjId());
                        try {
                            String createSprintInfoUrl = webConfig.getCreateSprintInfoUrl();
                            SprintInfo board = this.restTemplate.postForObject(createSprintInfoUrl, creSprintInfoRequest, SprintInfo.class);
                        } catch (Exception ex) {
                            throw new BusinessException(ExceptionEnum.DASHBOARD_NOT_CREATE);
                        }
                    }
                }
//            }
            rtrvDashboard(request);
        } catch (Exception ex) {
            throw new BusinessException(ExceptionEnum.DASHBOARD_DETAIL_STORY_NOT_RTRV);
        }
        restResponse.setStatusCode(String.valueOf(HttpStatus.OK));
        restResponse.setResponseCode("000");
        restResponse.setResponseMsg("请求成功");
//        restResponse.setResponseBody(storyListResponses);
        restResponse.setResponseBody(rtrvDashboard(request));
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

        List<SprintStoryListAndLabelId> sprintStoryLists = new ArrayList<>();
        Map modelIdMap = new HashMap();
        String modelId = request.getModelId();
        modelIdMap.put("modelId", modelId);
        Model model = restTemplate.postForObject(webConfig.getFindModelByIdUrl(), modelId, Model.class);
        rtrvDashboardResponse.setModel(model);
        //通过sprint的UUid获取它的sprintInfo
        List<SprintInfo> sprintInfos = new ArrayList<>();
        List<SprintInfoAndStoryArray> sprintInfoAndStoryArrays = request.getSprintAndStoryArrays();
        for (int i = 0; i < sprintInfoAndStoryArrays.size(); i++) {
            Integer uuId = sprintInfoAndStoryArrays.get(i).getUuId();
            List<String> stories = sprintInfoAndStoryArrays.get(i).getStories();
            UpdateSprintInfoRequest updateSprintInfoRequest = new UpdateSprintInfoRequest();
            updateSprintInfoRequest.setUuId(uuId);
            updateSprintInfoRequest.setStories(stories);
            SprintInfo sprintInfo = this.restTemplate.postForObject(webConfig.getUpdateSprintInfoUrl(), updateSprintInfoRequest, SprintInfo.class);
            sprintInfos.add(sprintInfo);
        }
//
        RtrvStoryListByStoryIdsRequest rtrvStoryListByStoryIdsRequest = new RtrvStoryListByStoryIdsRequest();
        rtrvStoryListByStoryIdsRequest.setPage(1);
        rtrvStoryListByStoryIdsRequest.setPageSize(Integer.MAX_VALUE);
        for (int i = 0; i < sprintInfos.size(); i++) {
            String itemIds = sprintInfos.get(i).getItemIds();
            SprintStoryListAndLabelId sprintStoryList = new SprintStoryListAndLabelId();
//            SprintStoryList sprintStoryList = new SprintStoryList();
            String[] sprintStoryIds = itemIds.split(",");
            List<String> sprintStoryIdList = Arrays.asList(sprintStoryIds);
            rtrvStoryListByStoryIdsRequest.setStoryIds(sprintStoryIdList);
            try {
                String url = webConfig.getRtrvStoryInfoListByStoryIdsUrl();
                ResponseEntity<RtrvStoryListByStoryIdsResponse> response = this.restTemplate.postForEntity(url, rtrvStoryListByStoryIdsRequest, RtrvStoryListByStoryIdsResponse.class);
                List<Story> storyList = response.getBody().getStories();
                List<StoryAndLabelId>  storyAndLabelIds = new ArrayList<>();
                for (int k = 0; k < storyList.size(); k++) {
                    StoryAndLabelId storyAndLabelId = new StoryAndLabelId();
                    String storyId = storyList.get(k).getStoryId();
                    //查询story对应的reqmnt的labelId
                    RequirementInfo requirementInfo = this.restTemplate.postForObject(webConfig.getRtrvlabelIdBystoryIdUrl(),storyId,RequirementInfo.class);
                    String labelId = requirementInfo.getFunctionLabelId();
                    storyAndLabelId.setLabelId(labelId);
                    storyAndLabelId.setStory(storyList.get(k));
                    storyAndLabelIds.add(storyAndLabelId);
                }
                sprintStoryList.setStories(storyAndLabelIds);
                sprintStoryList.setTotalElements(response.getBody().getTotalElements());
                sprintStoryList.setRemarks(response.getBody().getRemarks());
                sprintStoryList.setSprintInfo(sprintInfos.get(i));
                sprintStoryLists.add(sprintStoryList);
            } catch (Exception ex) {
                throw new BusinessException(ExceptionEnum.DASHBOARD_DETAIL_STORY_NOT_RTRV);
            }
        }
        rtrvDashboardResponse.setSprintStoryLists(sprintStoryLists);
        restResponse.setStatusCode(String.valueOf(HttpStatus.OK));
        restResponse.setResponseMsg("请求成功");
        restResponse.setResponseCode("000");
        restResponse.setResponseBody(rtrvDashboardResponse);
        return restResponse;
    }

    /**
     * 更改个人看板
     * @param request
     * @return
     */
    @Override
    public RestResponse updateMyDashboard(UpdateMyDashboardRequest request) {
        //更改
        Task task = new Task();
        try {
            String url = webConfig.getUpdateMyDashboardUrl();
            task= this.restTemplate.postForObject(url, request, Task.class);
        } catch (Exception ex) {
            throw new BusinessException(ExceptionEnum.MYDASHBOARD_NOT_UPDATE);
        }
        String projId = task.getProjId();
        String creatorId = task.getCreatorId();
        //查询
        RtrvMyDashboardInfoRequest rtrvMyDashboardInfoRequest = new RtrvMyDashboardInfoRequest();
        RtrvMyDashboardInfoResponse response = new RtrvMyDashboardInfoResponse();
        rtrvMyDashboardInfoRequest.setProjId(projId);
        rtrvMyDashboardInfoRequest.setCreatorId(creatorId);
        try {
            String url = webConfig.getMyDashboardInfosUrl();
            response= this.restTemplate.postForObject(url, rtrvMyDashboardInfoRequest, RtrvMyDashboardInfoResponse.class);
        } catch (Exception ex) {
            throw new BusinessException(ExceptionEnum.MYDASHBOARD_NOT_RTRV);
        }
        restResponse.setStatusCode(String.valueOf(HttpStatus.OK));
        restResponse.setResponseMsg("请求成功");
        restResponse.setResponseCode("000");
        restResponse.setResponseBody(response);
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

    /**
     * 获取个人看板
     * @param request
     * @return
     */
    @Override
    public RestResponse getMyDashboardInfos(RtrvMyDashboardInfoRequest request) {
        //查询
        RtrvMyDashboardInfoResponse response = new RtrvMyDashboardInfoResponse();
        try {
            String url = webConfig.getMyDashboardInfosUrl();
            response= this.restTemplate.postForObject(url, request, RtrvMyDashboardInfoResponse.class);
        } catch (Exception ex) {
            throw new BusinessException(ExceptionEnum.MYDASHBOARD_NOT_RTRV);
        }
        restResponse.setStatusCode(String.valueOf(HttpStatus.OK));
        restResponse.setResponseMsg("请求成功");
        restResponse.setResponseCode("000");
        restResponse.setResponseBody(response);
        return restResponse;
    }

}
