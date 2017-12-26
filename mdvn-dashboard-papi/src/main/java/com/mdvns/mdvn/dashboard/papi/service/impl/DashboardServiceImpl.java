package com.mdvns.mdvn.dashboard.papi.service.impl;

import com.mdvns.mdvn.common.beans.*;
import com.mdvns.mdvn.common.beans.exception.BusinessException;
import com.mdvns.mdvn.common.beans.exception.ExceptionEnum;
import com.mdvns.mdvn.common.utils.FetchListUtil;
import com.mdvns.mdvn.common.utils.MdvnStringUtil;
import com.mdvns.mdvn.common.utils.StaffAuthUtil;
import com.mdvns.mdvn.dashboard.papi.config.WebConfig;
import com.mdvns.mdvn.dashboard.papi.domain.*;
import com.mdvns.mdvn.dashboard.papi.domain.SprintInfo;
import com.mdvns.mdvn.dashboard.papi.service.DashboardService;
import com.mdvns.mdvn.dashboard.papi.utils.LogUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class DashboardServiceImpl implements DashboardService {

    /* 日志常亮 */
    private static final Logger LOG = LoggerFactory.getLogger(DashboardServiceImpl.class);

    private final String CLASS = this.getClass().getName();

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
        LOG.info("开始执行方法：rtrvDashboard");
        List<RtrvDashboardResponse> rtrvDashboardResponses = new ArrayList<>();
        //首先判断是否是第一次创建sprintInfo
        String findDashboardInfoByIdUrl = webConfig.getFindDashboardInfoByIdUrl();
        ParameterizedTypeReference parameTypeReference = new ParameterizedTypeReference<List<SprintInfo>>() {
        };
        List<SprintInfo> sprintInfoList = FetchListUtil.fetch(restTemplate, findDashboardInfoByIdUrl, request, parameTypeReference);
        //------------------------已经创建过看板
        for (int i = 0; i < sprintInfoList.size(); i++) {
            LOG.info("已经创建过看板" + sprintInfoList.get(i));
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
            rtrvDashboardRequest.setCreatorId(request.getCreatorId());
            ParameterizedTypeReference pTypeReference = new ParameterizedTypeReference<List<SprintInfo>>() {
            };
            List<SprintInfo> sprintInfos = FetchListUtil.fetch(restTemplate, webConfig.getFindDashboardInfoByIdsUrl(), rtrvDashboardRequest, pTypeReference);
            List<SprintStoryListAndLabelId> sprintStoryLists = new ArrayList<>();
            for (int j = 0; j < sprintInfos.size(); j++) {
                if (j > 2) {
                    break;
                }
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
                    List<StoryAndLabelId> storyAndLabelIds = new ArrayList<>();
                    for (int k = 0; k < storyList.size(); k++) {
                        StoryAndLabelId storyAndLabelId = new StoryAndLabelId();
                        String storyId = storyList.get(k).getStoryId();
                        RequirementInfo requirementInfo = this.restTemplate.postForObject(webConfig.getRtrvlabelIdBystoryIdUrl(), storyId, RequirementInfo.class);
                        String labelId = requirementInfo.getFunctionLabelId();
                        //获取story对应的reqmnt的labelId
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
        LOG.info("结束执行方法：rtrvDashboard");
        return rtrvDashboardResponses;
    }

    /**
     * 进入dashboard,首先获取product backlogs信息（story列表）(只可以获得当前登录者的dashboard信息，全部可以拖动)
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
        List<SprintInfo> sprintInfoList = FetchListUtil.fetch(restTemplate, findDashboardInfoByIdUrl, request, parameTypeReference);

        //------------------------已经创建过看板
        if (sprintInfoList.size() != 0) {
            LOG.info("获取storyList时已经创建过看板");

//            rtrvDashboard(request);

            restResponse.setStatusCode(String.valueOf(HttpStatus.OK));
            restResponse.setResponseCode("000");
            restResponse.setResponseMsg("请求成功");
            restResponse.setResponseBody(rtrvDashboard(request));
            return restResponse;
        }
        //----------------(第一次获取)
        //首先得到reqmntInfo的列表信息（主要获取reqmntId）
        List<RtrvDashboardResponse> responses = new ArrayList<>();
        try {
            String rtrvReqmntInfoBymodelIdUrl = webConfig.getRtrvReqmntInfoBymodelIdUrl();
            RtrvReqmntInfoByModelRequest reqmntInfoByModelRequest = new RtrvReqmntInfoByModelRequest();
            reqmntInfoByModelRequest.setProjId(request.getProjId());
            reqmntInfoByModelRequest.setCreatorId(request.getCreatorId());
            ParameterizedTypeReference parameterizedTypeReference = new ParameterizedTypeReference<List<RtrvReqmntInfoByModelIdResponse>>() {
            };
            List<RtrvReqmntInfoByModelIdResponse> responseList = FetchListUtil.fetch(restTemplate, rtrvReqmntInfoBymodelIdUrl, reqmntInfoByModelRequest, parameterizedTypeReference);
            System.out.println(responseList);
            for (int i = 0; i < responseList.size(); i++) {
                LOG.info("第一次创建看板");
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
                    stoIds = MdvnStringUtil.join(storyIds, ",");
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
                    sIds = MdvnStringUtil.join(storyIds, ",");
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
            responses = rtrvDashboard(request);
        } catch (Exception ex) {
            throw new BusinessException(ExceptionEnum.DASHBOARD_DETAIL_STORY_NOT_RTRV);
        }
        restResponse.setStatusCode(String.valueOf(HttpStatus.OK));
        restResponse.setResponseCode("000");
        restResponse.setResponseMsg("请求成功");
//        restResponse.setResponseBody(storyListResponses);
        restResponse.setResponseBody(responses);
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
            /*获取具体的uuid*/
            String name = sprintInfoAndStoryArrays.get(i).getName();
            FindUuIdRequest findUuIdRequest = new FindUuIdRequest();
            findUuIdRequest.setName(name);
            findUuIdRequest.setCreatorId(request.getCreatorId());
            findUuIdRequest.setModelId(modelId);
            findUuIdRequest.setProjId(request.getProjId());
            Integer uuId = restTemplate.postForObject(webConfig.getFindUuIdByIdsUrl(), findUuIdRequest, Integer.class);
            List<String> stories = sprintInfoAndStoryArrays.get(i).getStories();
            UpdateSprintInfoRequest updateSprintInfoRequest = new UpdateSprintInfoRequest();
            updateSprintInfoRequest.setUuId(uuId);
            updateSprintInfoRequest.setStories(stories);
            SprintInfo sprintInfo = this.restTemplate.postForObject(webConfig.getUpdateSprintInfoUrl(), updateSprintInfoRequest, SprintInfo.class);
            sprintInfos.add(sprintInfo);
        }
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
                List<StoryAndLabelId> storyAndLabelIds = new ArrayList<>();
                for (int k = 0; k < storyList.size(); k++) {
                    StoryAndLabelId storyAndLabelId = new StoryAndLabelId();
                    String storyId = storyList.get(k).getStoryId();
                    //查询story对应的reqmnt的labelId
                    RequirementInfo requirementInfo = this.restTemplate.postForObject(webConfig.getRtrvlabelIdBystoryIdUrl(), storyId, RequirementInfo.class);
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
     * 移动端，更改看板（单个story移动）
     *
     * @param request
     * @return
     */
    @Override
    public RestResponse updateDashboardForAndroid(UpdateDashboardForAndroidRequest request) {
        LOG.info("开始执行方法：updateSprintCloseStatus");
        if (request == null || request.getStoryId() == null) {
            throw new NullPointerException("updateDashboardForAndroid 或storyId 不能为空");
        }
        SprintInfo sprintInfo = new SprintInfo();
        try {
            String url = webConfig.getUpdateDashboardForAndroidUrl();
            sprintInfo = this.restTemplate.postForObject(url, request, SprintInfo.class);
        } catch (Exception ex) {
            throw new BusinessException(ExceptionEnum.DASHBOARD_ANDROID_NOT_UPDATE);
        }
        String projId = request.getProjId();
        String creatorId = request.getCreatorId();
        RtrvDashboardRequest rtrvAllStoryListRequest = new RtrvDashboardRequest();
        rtrvAllStoryListRequest.setProjId(projId);
        rtrvAllStoryListRequest.setModleId(request.getModelId());
        rtrvAllStoryListRequest.setCreatorId(creatorId);
        restResponse.setStatusCode(String.valueOf(HttpStatus.OK));
        restResponse.setResponseMsg("请求成功");
        restResponse.setResponseCode("000");
        restResponse.setResponseBody(rtrvDashboardByModel(rtrvAllStoryListRequest));
        LOG.info("结束执行方法：updateSprintCloseStatus");
        return restResponse;
    }

    /**
     * 更改个人看板
     *
     * @param request
     * @return
     */
    @Override
    public RestResponse updateMyDashboard(UpdateMyDashboardRequest request) {
        //更改
        Task task = new Task();
        try {
            String url = webConfig.getUpdateMyDashboardForTaskUrl();
            task = this.restTemplate.postForObject(url, request, Task.class);
        } catch (Exception ex) {
            throw new BusinessException(ExceptionEnum.MYDASHBOARD_NOT_UPDATE);
        }
        String projId = task.getProjId();
        String creatorId = task.getCreatorId();
        //查询
        RtrvMyDashboardInfoRequest rtrvMyDashboardInfoRequest = new RtrvMyDashboardInfoRequest();
        rtrvMyDashboardInfoRequest.setProjId(projId);
        rtrvMyDashboardInfoRequest.setCreatorId(creatorId);
        return this.getMyDashboardInfos(rtrvMyDashboardInfoRequest);
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
     *
     * @param request
     * @return
     */
    @Override
    public RestResponse getMyDashboardInfos(RtrvMyDashboardInfoRequest request) {
        //查询个人创建的所有task
        RtrvMyDashboardInfoResponse responseForTask = new RtrvMyDashboardInfoResponse();
        try {
            String url = webConfig.getMyDashboardInfosForTaskUrl();
            responseForTask = this.restTemplate.postForObject(url, request, RtrvMyDashboardInfoResponse.class);
        } catch (Exception ex) {
            throw new BusinessException(ExceptionEnum.MYDASHBOARD_NOT_RTRV);
        }
        //查询所有task各中状态下属于的storyList
        RtrvMyDashboardInfoForStoryResponse responseForStory = new RtrvMyDashboardInfoForStoryResponse();
        //选出不同的story
        /*todo*/
        List<String> storyIds = new ArrayList<>();
        for (int i = 0; i < responseForTask.getToDo().size(); i++) {
            String id = responseForTask.getToDo().get(i).getStoryId();
            if (!storyIds.isEmpty() && storyIds.contains(id)) {
                continue;
            }
            storyIds.add(id);
        }
        if (storyIds.size() > 0) {
            responseForStory.setToDo(this.rtrvSrotys(storyIds));
        }
        /*done*/
        List<String> stoIds = new ArrayList<>();
        for (int i = 0; i < responseForTask.getDone().size(); i++) {
            String id = responseForTask.getDone().get(i).getStoryId();
            if (!stoIds.isEmpty() && stoIds.contains(id)) {
                continue;
            }
            stoIds.add(id);
        }
        if (stoIds.size() > 0) {
            responseForStory.setDone(this.rtrvSrotys(stoIds));
        }
        /*inProgress*/
        List<String> stIds = new ArrayList<>();
        for (int i = 0; i < responseForTask.getInProgress().size(); i++) {
            String id = responseForTask.getInProgress().get(i).getStoryId();
            if (!stIds.isEmpty() && stIds.contains(id)) {
                continue;
            }
            stIds.add(id);
        }
        if (stIds.size() > 0) {
            responseForStory.setInProgress(this.rtrvSrotys(stIds));
        }
        //把task放到各自的story下面
        MyDashboardResponse myDashboardResponse = new MyDashboardResponse();
        /*done*/
        if (responseForTask.getDone().size() > 0) {
            List<StoryAndTasks> done = new ArrayList<>();
            for (int i = 0; i < responseForStory.getDone().size(); i++) {
                StoryAndTasks storyAndTasks = new StoryAndTasks();
                String storyId = responseForStory.getDone().get(i).getStoryId();
                List<Task> tasks = new ArrayList<>();
                for (int j = 0; j < responseForTask.getDone().size(); j++) {
                    String stoId = responseForTask.getDone().get(j).getStoryId();
                    if (stoId.equals(storyId)) {
                        tasks.add(responseForTask.getDone().get(j));
                    }
                }
                storyAndTasks.setTasks(tasks);
                if (storyAndTasks.getTasks().size() > 0) {
                    storyAndTasks.setStory(responseForStory.getDone().get(i));
                    done.add(storyAndTasks);
                }
            }
            myDashboardResponse.setDone(done);
        }
        /*toDo*/
        if (responseForTask.getToDo().size() > 0) {
            List<StoryAndTasks> toDo = new ArrayList<>();
            for (int i = 0; i < responseForStory.getToDo().size(); i++) {
                StoryAndTasks storyAndTasks = new StoryAndTasks();
                String storyId = responseForStory.getToDo().get(i).getStoryId();
                List<Task> tasks = new ArrayList<>();
                for (int j = 0; j < responseForTask.getToDo().size(); j++) {
                    String stoId = responseForTask.getToDo().get(j).getStoryId();
                    if (stoId.equals(storyId)) {
                        tasks.add(responseForTask.getToDo().get(j));
                    }
                }
                storyAndTasks.setTasks(tasks);
                if (storyAndTasks.getTasks().size() > 0) {
                    storyAndTasks.setStory(responseForStory.getToDo().get(i));
                    toDo.add(storyAndTasks);
                }
            }
            myDashboardResponse.setToDo(toDo);
        }
        /*InProgress*/
        if (responseForTask.getInProgress().size() > 0) {
            List<StoryAndTasks> inProgress = new ArrayList<>();
            for (int i = 0; i < responseForStory.getInProgress().size(); i++) {
                StoryAndTasks storyAndTasks = new StoryAndTasks();
                String storyId = responseForStory.getInProgress().get(i).getStoryId();
                List<Task> tasks = new ArrayList<>();
                for (int j = 0; j < responseForTask.getInProgress().size(); j++) {
                    String stoId = responseForTask.getInProgress().get(j).getStoryId();
                    if (stoId.equals(storyId)) {
                        tasks.add(responseForTask.getInProgress().get(j));
                    }
                }
                storyAndTasks.setTasks(tasks);
                if (storyAndTasks.getTasks().size() > 0) {
                    storyAndTasks.setStory(responseForStory.getInProgress().get(i));
                    inProgress.add(storyAndTasks);
                }
            }
            myDashboardResponse.setInProgress(inProgress);
        }
        restResponse.setStatusCode(String.valueOf(HttpStatus.OK));
        restResponse.setResponseMsg("请求成功");
        restResponse.setResponseCode("000");
        restResponse.setResponseBody(myDashboardResponse);
        return restResponse;
    }

    /**
     * //查询项目下的所有story
     *
     * @param storyIds
     * @return
     */
    private List<Story> rtrvSrotys(List<String> storyIds) {
        RtrvStoryListByStoryIdsRequest rtrvStoryListByStoryIdsRequest = new RtrvStoryListByStoryIdsRequest();
        rtrvStoryListByStoryIdsRequest.setPage(1);
        rtrvStoryListByStoryIdsRequest.setPageSize(Integer.MAX_VALUE);
        rtrvStoryListByStoryIdsRequest.setStoryIds(storyIds);
        List<Story> storyList = new ArrayList<>();
        try {
            String url = webConfig.getRtrvStoryInfoListByStoryIdsUrl();
            ResponseEntity<RtrvStoryListByStoryIdsResponse> response = this.restTemplate.postForEntity(url, rtrvStoryListByStoryIdsRequest, RtrvStoryListByStoryIdsResponse.class);
            storyList = response.getBody().getStories();
        } catch (Exception ex) {
            throw new BusinessException(ExceptionEnum.DASHBOARD_DETAIL_STORY_NOT_RTRV);
        }
        return storyList;
    }

    @Override
    public RestResponse updateSprintStartStatus(UpdateSprintStartStatusRequest request) {
        LOG.info("开始执行方法：updateSprintStartStatus");
        SprintInfo sprintInfo = new SprintInfo();
        try {
            String url = webConfig.getUpdateSprintStartStatusUrl();
            sprintInfo = this.restTemplate.postForObject(url, request, SprintInfo.class);
        } catch (Exception ex) {
            throw new BusinessException(ExceptionEnum.DASHBOARD_STATUS_START_NOT_UPDATE);
        }
        restResponse.setStatusCode(String.valueOf(HttpStatus.OK));
        restResponse.setResponseMsg("请求成功");
        restResponse.setResponseCode("000");
        restResponse.setResponseBody(sprintInfo);
        LOG.info("结束执行方法：updateSprintStartStatus");
        return restResponse;
    }

    @Override
    public RestResponse updateSprintCloseStatus(UpdateSprintCloseStatusRequest request) {
        LOG.info("开始执行方法：updateSprintCloseStatus");
        SprintInfo sprintInfo = new SprintInfo();
        try {
            String url = webConfig.getUpdateSprintCloseStatusUrl();
            sprintInfo = this.restTemplate.postForObject(url, request, SprintInfo.class);
        } catch (Exception ex) {
            throw new BusinessException(ExceptionEnum.DASHBOARD_STATUS_CLOSE_NOT_UPDATE);
        }
        String projId = request.getProjId();
        String creatorId = request.getCreatorId();
        RtrvDashboardRequest rtrvAllStoryListRequest = new RtrvDashboardRequest();
        rtrvAllStoryListRequest.setProjId(projId);
        rtrvAllStoryListRequest.setModleId(request.getModelId());
        rtrvAllStoryListRequest.setCreatorId(creatorId);
        restResponse.setStatusCode(String.valueOf(HttpStatus.OK));
        restResponse.setResponseMsg("请求成功");
        restResponse.setResponseCode("000");
        restResponse.setResponseBody(rtrvDashboardByModel(rtrvAllStoryListRequest));
        LOG.info("结束执行方法：updateSprintCloseStatus");
        return restResponse;
    }

    /**
     * 获取下两个SprintInfo
     *
     * @param request
     * @return
     */
    @Override
    public RestResponse itSprints(RtrvItSprintsRequest request) {
        LOG.info("开始执行方法：itSprints");
        List<SprintInfo> sprintInfos = new ArrayList<>();
        /*获取具体的uuid*/
        String name = request.getName();
        FindUuIdRequest findUuIdRequest = new FindUuIdRequest();
        findUuIdRequest.setName(name);
        findUuIdRequest.setCreatorId(request.getCreatorId());
        findUuIdRequest.setModelId(request.getModelId());
        findUuIdRequest.setProjId(request.getProjId());
        Integer uuId = restTemplate.postForObject(webConfig.getFindUuIdByIdsUrl(), findUuIdRequest, Integer.class);
        try {
            String url = webConfig.getItSprintUrl();
            sprintInfos = this.restTemplate.postForObject(url, uuId, List.class);
        } catch (Exception ex) {
            throw new BusinessException(ExceptionEnum.DASHBOARD_NEXT_SPRINT_NOT_RTRV);
        }
        restResponse.setStatusCode(String.valueOf(HttpStatus.OK));
        restResponse.setResponseMsg("请求成功");
        restResponse.setResponseCode("000");
        restResponse.setResponseBody(sprintInfos);
        LOG.info("结束执行方法：itSprints");
        return restResponse;
    }

    /**
     * 获取所有负责人的看板信息（只可以浏览，不可以修改）（后来加的Dashboard,现在不再用）
     *
     * @param request
     * @return
     */
    @Override
    public RestResponse rtrvAllDashboard(RtrvAllStoryListRequest request) {
        RtrvProjectDetailRequest rtrvProjectDetailRequest = new RtrvProjectDetailRequest();
        String projId = request.getProjId();
        rtrvProjectDetailRequest.setProjId(projId);
        List<RtrvDashboardResponse> responses = rtrvAllDashboardById(request);
        restResponse.setStatusCode(String.valueOf(HttpStatus.OK));
        restResponse.setResponseCode("000");
        restResponse.setResponseMsg("请求成功");
        restResponse.setResponseBody(responses);
        return restResponse;
    }

    private List<RtrvDashboardResponse> rtrvAllDashboardById(RtrvAllStoryListRequest request) {
        LOG.info("开始执行方法：rtrvAllDashboardById");
        List<RtrvDashboardResponse> rtrvDashboardResponses = new ArrayList<>();
        //首先判断是否是第一次创建sprintInfo
        String findDashboardInfoByIdUrl = webConfig.getFindAllDashboardByIdUrl();
        ParameterizedTypeReference parameTypeReference = new ParameterizedTypeReference<List<SprintInfo>>() {
        };
        List<SprintInfo> sprintInfoList = FetchListUtil.fetch(restTemplate, findDashboardInfoByIdUrl, request, parameTypeReference);
        //选出不同的模块
        List<String> models = new ArrayList<>();
        for (int i = 0; i < sprintInfoList.size(); i++) {
            String id = sprintInfoList.get(i).getModelId();
            if (!models.isEmpty() && models.contains(id)) {
                continue;
            }
            models.add(id);
        }
        for (int i = 0; i < models.size(); i++) {
            RtrvDashboardResponse rtrvDashboardResponse = new RtrvDashboardResponse();
            //模型对象
            String modelId = models.get(i);
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
            List<SprintInfo> spInfos = FetchListUtil.fetch(restTemplate, webConfig.getFindAllDashboardInfoByIdsUrl(), rtrvDashboardRequest, pTypeReference);
            //选出不同的name
            List<String> names = new ArrayList<>();
            for (int j = 0; j < spInfos.size(); j++) {
                String name = spInfos.get(j).getName();
                if (!names.isEmpty() && names.contains(name)) {
                    continue;
                }
                names.add(name);
            }
            List<SprintInfo> sprintInfos = new ArrayList<>();
            for (int k = 0; k < names.size(); k++) {
                SprintInfo sprintInfo = new SprintInfo();
                String name = names.get(k);
                List<String> itemIdList = new ArrayList<>();
                for (int j = 0; j < spInfos.size(); j++) {
                    String storyIds = spInfos.get(j).getItemIds();
                    String sprintName = spInfos.get(j).getName();
                    String[] sprintStoryIds = storyIds.split(",");
                    List<String> sprintStoryIdList = Arrays.asList(sprintStoryIds);
                    if (sprintName.equals(name) && !StringUtils.isEmpty(sprintName)) {
                        sprintInfo = spInfos.get(j);
                        itemIdList.addAll(sprintStoryIdList);
                    }
                }
                String stoIds = MdvnStringUtil.join(itemIdList, ",");
                sprintInfo.setUuId(null);
                sprintInfo.setCreatorId("");
                sprintInfo.setCreateTime(null);
                sprintInfo.setUpdateTime(null);
                sprintInfo.setName(name);
                sprintInfo.setItemIds(stoIds);
                sprintInfos.add(sprintInfo);
            }
            List<SprintStoryListAndLabelId> sprintStoryLists = new ArrayList<>();
            for (int j = 0; j < sprintInfos.size(); j++) {
                if (j > 2) {
                    break;
                }
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
                    List<StoryAndLabelId> storyAndLabelIds = new ArrayList<>();
                    for (int k = 0; k < storyList.size(); k++) {
                        StoryAndLabelId storyAndLabelId = new StoryAndLabelId();
                        String storyId = storyList.get(k).getStoryId();
                        RequirementInfo requirementInfo = this.restTemplate.postForObject(webConfig.getRtrvlabelIdBystoryIdUrl(), storyId, RequirementInfo.class);
                        String labelId = requirementInfo.getFunctionLabelId();
                        //获取story对应的reqmnt的labelId
                        storyAndLabelId.setLabelId(labelId);
                        //添加是否可以移动的标识
                        storyAndLabelId.setIsRemove(0);
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
        LOG.info("结束执行方法：rtrvAllDashboardById");
        return rtrvDashboardResponses;
    }

    /**
     * 获取所有负责人的看板信息(只有负责人可以更改各自的看板信息)
     *
     * @param request
     * @return
     */
    @Override
    public RestResponse rtrvAllMVPDashboard(RtrvAllStoryListRequest request) {
        LOG.info("开始执行方法：rtrvAllMVPDashboard");
        //1.查询登录者是否是第一次创建看板，如果是项目创建者即可创建看板（只有项目负责人可以创建看板）
        List<RtrvDashboardResponse> rtrvDashboard = (List<RtrvDashboardResponse>) this.rtrvStoryList(request).getResponseBody();
        List<RtrvDashboardResponse> rtrvDashboardResponses = new ArrayList<>();
        String findDashboardInfoByIdUrl = webConfig.getFindAllDashboardByIdUrl();
        ParameterizedTypeReference parameTypeReference = new ParameterizedTypeReference<List<SprintInfo>>() {
        };
        List<SprintInfo> sprintInfoList = FetchListUtil.fetch(restTemplate, findDashboardInfoByIdUrl, request, parameTypeReference);
        //选出不同的模块
        List<String> models = new ArrayList<>();
        for (int i = 0; i < sprintInfoList.size(); i++) {
            String id = sprintInfoList.get(i).getModelId();
            if (!models.isEmpty() && models.contains(id)) {
                continue;
            }
            models.add(id);
        }
        for (int i = 0; i < models.size(); i++) {
            //获取每个model下面的dashboard
            RtrvDashboardResponse rtrvDashboardResponse = new RtrvDashboardResponse();
            String creatorId = request.getCreatorId();
            String projId = request.getProjId();
            RtrvDashboardRequest rtrvAllStoryListRequest = new RtrvDashboardRequest();
            rtrvAllStoryListRequest.setProjId(projId);
            rtrvAllStoryListRequest.setModleId(models.get(i));
            rtrvAllStoryListRequest.setCreatorId(creatorId);
            rtrvDashboardResponse = rtrvDashboardByModel(rtrvAllStoryListRequest);
            rtrvDashboardResponses.add(rtrvDashboardResponse);
        }
        LOG.info("结束执行方法：rtrvAllMVPDashboard");
        restResponse.setStatusCode(String.valueOf(HttpStatus.OK));
        restResponse.setResponseCode("000");
        restResponse.setResponseMsg("请求成功");
        restResponse.setResponseBody(rtrvDashboardResponses);
        return restResponse;
    }

    /**
     * 关闭sprint之后返回这个模板下的dashboard
     *
     * @param request
     * @return
     */
    private RtrvDashboardResponse rtrvDashboardByModel(RtrvDashboardRequest request) {
        LOG.info("开始执行方法：rtrvDashboardByModel");
        //获取用户在项目中的权限信息
        String projId = request.getProjId();
        List<StaffAuthInfo> staffAuthInfos = StaffAuthUtil.rtrvStaffAuthInfo(this.restTemplate, projId, projId, request.getCreatorId());
        RtrvAllStoryListRequest req = new RtrvAllStoryListRequest();
        req.setCreatorId(request.getCreatorId());
        req.setProjId(request.getProjId());
        List<RtrvDashboardResponse> rtrvDashboard = (List<RtrvDashboardResponse>) this.rtrvStoryList(req).getResponseBody();
        RtrvDashboardResponse rtrvDashboardResponse = new RtrvDashboardResponse();
        //添加权限
        rtrvDashboardResponse.setStaffAuthInfo(staffAuthInfos);
        //模型对象
        String modelId = request.getModleId();
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
        List<SprintInfo> spInfos = FetchListUtil.fetch(restTemplate, webConfig.getFindAllDashboardInfoByIdsUrl(), rtrvDashboardRequest, pTypeReference);
        //选出不同的name
        List<String> names = new ArrayList<>();
        for (int j = 0; j < spInfos.size(); j++) {
            String name = spInfos.get(j).getName();
            if (!names.isEmpty() && names.contains(name)) {
                continue;
            }
            names.add(name);
        }
        List<SprintInfo> sprintInfos = new ArrayList<>();
        for (int k = 0; k < names.size(); k++) {
            SprintInfo sprintInfo = new SprintInfo();
            String name = names.get(k);
            List<String> itemIdList = new ArrayList<>();
            for (int j = 0; j < spInfos.size(); j++) {
                String storyIds = spInfos.get(j).getItemIds();
                String sprintName = spInfos.get(j).getName();
                String[] sprintStoryIds = storyIds.split(",");
                List<String> sprintStoryIdList = Arrays.asList(sprintStoryIds);
                if (sprintName.equals(name) && !StringUtils.isEmpty(sprintName)) {
                    sprintInfo = spInfos.get(j);
                    itemIdList.addAll(sprintStoryIdList);
                }
            }
            String stoIds = MdvnStringUtil.join(itemIdList, ",");
            sprintInfo.setUuId(null);
            sprintInfo.setCreatorId("");
            sprintInfo.setCreateTime(null);
            sprintInfo.setUpdateTime(null);
            sprintInfo.setName(name);
            sprintInfo.setItemIds(stoIds);
            sprintInfos.add(sprintInfo);
        }
        List<SprintStoryListAndLabelId> sprintStoryLists = new ArrayList<>();
        for (int j = 0; j < sprintInfos.size(); j++) {
            if (j > 2) {
                break;
            }
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
                List<StoryAndLabelId> storyAndLabelIds = new ArrayList<>();
                for (int k = 0; k < storyList.size(); k++) {
                    StoryAndLabelId storyAndLabelId = new StoryAndLabelId();
                    String storyId = storyList.get(k).getStoryId();
                    RequirementInfo requirementInfo = this.restTemplate.postForObject(webConfig.getRtrvlabelIdBystoryIdUrl(), storyId, RequirementInfo.class);
                    String labelId = requirementInfo.getFunctionLabelId();
                    //获取story对应的reqmnt的labelId
                    storyAndLabelId.setLabelId(labelId);
                    storyAndLabelId.setStory(storyList.get(k));
                    storyAndLabelId.setIsRemove(0);
                    //添加是否可以移动的标识
                    String storId = storyAndLabelId.getStory().getStoryId();
                    for (int l = 0; l < rtrvDashboard.size(); l++) {
                        List<SprintStoryListAndLabelId> sprintStoryListAndLabelIds = rtrvDashboard.get(l).getSprintStoryLists();
                        for (int m = 0; m < sprintStoryListAndLabelIds.size(); m++) {
                            List<StoryAndLabelId> storyAndLabelIdList = sprintStoryListAndLabelIds.get(m).getStories();
                            for (int n = 0; n < storyAndLabelIdList.size(); n++) {
                                String stoId = storyAndLabelIdList.get(n).getStory().getStoryId();
                                if (stoId.equals(storId)) {
                                    storyAndLabelId.setIsRemove(1);
                                }
                            }
                        }
                    }
                    storyAndLabelId.setStory(storyList.get(k));
                    storyAndLabelIds.add(storyAndLabelId);
                }
                sprintStoryList.setStories(this.sortStories(storyAndLabelIds));
//                sprintStoryList.setStories(storyAndLabelIds);
                sprintStoryList.setTotalElements(response.getBody().getTotalElements());
                sprintStoryList.setRemarks(response.getBody().getRemarks());
            } catch (Exception ex) {
                throw new BusinessException(ExceptionEnum.DASHBOARD_DETAIL_STORY_NOT_RTRV);
            }
            sprintStoryLists.add(sprintStoryList);
        }
        rtrvDashboardResponse.setSprintStoryLists(sprintStoryLists);
        LOG.info("结束执行方法：rtrvDashboardByModel");
        return rtrvDashboardResponse;
    }

    /**
     * storyList排序（先按优先级分类，再把各个优先级按进度排序）
     */
    private List<StoryAndLabelId> sortStories(List<StoryAndLabelId> storyAndLabelIds) {
        /**
         * 先按优先级分类
         */
        List<StoryAndLabelId> highStoryAndLabelIds = new ArrayList<>();
        List<StoryAndLabelId> middleStoryAndLabelIds = new ArrayList<>();
        List<StoryAndLabelId> lowStoryAndLabelIds = new ArrayList<>();
        List<StoryAndLabelId> sortStoryAndLabelIds = new ArrayList<>();
        for (int i = 0; i < storyAndLabelIds.size(); i++) {
            Integer priority = storyAndLabelIds.get(i).getStory().getPriority();
            if (priority == 3) {
                highStoryAndLabelIds.add(storyAndLabelIds.get(i));
            }
            if (priority == 2) {
                middleStoryAndLabelIds.add(storyAndLabelIds.get(i));
            }
            if (priority == 1) {
                lowStoryAndLabelIds.add(storyAndLabelIds.get(i));
            }
        }
        /**
         * 再按进度排序
         */
        Collections.sort(highStoryAndLabelIds);
        Collections.sort(middleStoryAndLabelIds);
        Collections.sort(lowStoryAndLabelIds);
        sortStoryAndLabelIds.addAll(highStoryAndLabelIds);
        sortStoryAndLabelIds.addAll(middleStoryAndLabelIds);
        sortStoryAndLabelIds.addAll(lowStoryAndLabelIds);
        return sortStoryAndLabelIds;
    }


}
