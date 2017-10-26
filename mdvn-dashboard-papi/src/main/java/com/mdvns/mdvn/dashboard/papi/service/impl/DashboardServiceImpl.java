package com.mdvns.mdvn.dashboard.papi.service.impl;

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
        //首先判断是否是第一次创建dashboard
        String findDashboardInfoByIdUrl = webConfig.getFindDashboardInfoByIdUrl();
        ParameterizedTypeReference parameTypeReference = new ParameterizedTypeReference<List<Dashboard>>() {
        };
        List<Dashboard> dashboards = FetchListUtil.fetch(restTemplate, findDashboardInfoByIdUrl, request.getProjId(), parameTypeReference);
//         List<Dashboard> dashboards = restTemplate.postForObject(findDashboardInfoByIdUrl, request.getProjId(), List.class);
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
                //通过storyIdList查询storyList
                String productBacklogs = dashboards.get(i).getProductBacklogs();
                String currentSprint = dashboards.get(i).getCurrentSprint();
                String nextSprint = dashboards.get(i).getNextSprint();

                RtrvStoryListByStoryIdsRequest rtrvStoryListByStoryIdsRequest = new RtrvStoryListByStoryIdsRequest();
                rtrvStoryListByStoryIdsRequest.setPage(1);
                rtrvStoryListByStoryIdsRequest.setPageSize(Integer.MAX_VALUE);
                //productBacklogs
                if(productBacklogs !=null){
                    ProductStoryList productStoryList = new ProductStoryList();
                    String[] productStoryIds = productBacklogs.split(",");
                    List<String> productStoryIdList = Arrays.asList(productStoryIds);
                    rtrvStoryListByStoryIdsRequest.setStoryIds(productStoryIdList);
                    try {
                        String url = webConfig.getRtrvStoryInfoListByStoryIdsUrl();
                        ResponseEntity<RtrvStoryListByStoryIdsResponse> response = this.restTemplate.postForEntity(url, rtrvStoryListByStoryIdsRequest, RtrvStoryListByStoryIdsResponse.class);
                        productStoryList.setStories(response.getBody().getStories());
                        productStoryList.setTotalElements(response.getBody().getTotalElements());
                        productStoryList.setRemarks(response.getBody().getRemarks());
                        rtrvDashboardResponse.setProductStoryList(productStoryList);
                    } catch (Exception ex) {
                        throw new BusinessException(ExceptionEnum.DASHBOARD_DETAIL_STORY_NOT_RTRV);
                    }
                }
                //currentSprint
                if(currentSprint !=null){
                    CurrentSprintStoryList currentSprintStoryList = new CurrentSprintStoryList();
                    String[] currentStoryIds = currentSprint.split(",");
                    List<String> currentStoryIdList = Arrays.asList(currentStoryIds);
                    rtrvStoryListByStoryIdsRequest.setStoryIds(currentStoryIdList);
                    try {
                        String url = webConfig.getRtrvStoryInfoListByStoryIdsUrl();
                        ResponseEntity<RtrvStoryListByStoryIdsResponse> response = this.restTemplate.postForEntity(url, rtrvStoryListByStoryIdsRequest, RtrvStoryListByStoryIdsResponse.class);
                        currentSprintStoryList.setStories(response.getBody().getStories());
                        currentSprintStoryList.setTotalElements(response.getBody().getTotalElements());
                        currentSprintStoryList.setRemarks(response.getBody().getRemarks());
                        rtrvDashboardResponse.setCurrentSprintStoryList(currentSprintStoryList);
                    } catch (Exception ex) {
                        throw new BusinessException(ExceptionEnum.DASHBOARD_DETAIL_STORY_NOT_RTRV);
                    }
                }
                //nextSprint
                if (nextSprint !=null){
                    NextSprintSrotyList nextSprintSrotyList = new NextSprintSrotyList();
                    String[] nextStoryIds = nextSprint.split(",");
                    List<String> nextStoryIdList = Arrays.asList(nextStoryIds);
                    rtrvStoryListByStoryIdsRequest.setStoryIds(nextStoryIdList);
                    try {
                        String url = webConfig.getRtrvStoryInfoListByStoryIdsUrl();
                        ResponseEntity<RtrvStoryListByStoryIdsResponse> response = this.restTemplate.postForEntity(url, rtrvStoryListByStoryIdsRequest, RtrvStoryListByStoryIdsResponse.class);
                        nextSprintSrotyList.setStories(response.getBody().getStories());
                        nextSprintSrotyList.setTotalElements(response.getBody().getTotalElements());
                        nextSprintSrotyList.setRemarks(response.getBody().getRemarks());
                        rtrvDashboardResponse.setNextSprintSrotyList(nextSprintSrotyList);
                    } catch (Exception ex) {
                        throw new BusinessException(ExceptionEnum.DASHBOARD_DETAIL_STORY_NOT_RTRV);
                    }
                }
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
                String stoIds = null;
                Map modelIdMap = new HashMap();
                modelIdMap.put("modelId", modelId);
                Model model = restTemplate.postForObject(webConfig.getFindModelByIdUrl(), modelId, Model.class);
                rtrvAllStoryListResponse.setModel(model);
                //每个model下的reqmnt
                for (int j = 0; j < responseList.get(i).getRequirementInfos().size(); j++) {
                    List<RequirementInfo> reqmntInfos = responseList.get(i).getRequirementInfos();
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
                    } catch (Exception ex) {
                        throw new BusinessException(ExceptionEnum.DASHBOARD_DETAIL_REQMNTINFO_NOT_RTRV);
                    }
                }
                storyListResponses.add(rtrvAllStoryListResponse);
                //创建看板
                CreateDashboardRequest createDashboardRequest = new CreateDashboardRequest();
                createDashboardRequest.setCreatorId(request.getCreatorId());
                createDashboardRequest.setModelId(modelId);
                createDashboardRequest.setProductBacklogs(stoIds);
                createDashboardRequest.setProjId(request.getProjId());
                try {
                    String url = webConfig.getCreateDashboardUrl();
                    Dashboard board = this.restTemplate.postForObject(url, createDashboardRequest, Dashboard.class);
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
     * @param request
     * @return
     */
    @Override
    public RestResponse updateDashboard(UpdateDashboardRequest request) {
        //首先更改dashboard
        Dashboard dashboard = new Dashboard();
        try {
            String url = webConfig.getUpdateDashboardUrl();
            dashboard = this.restTemplate.postForObject(url, request, Dashboard.class);
        } catch (Exception ex) {
            throw new BusinessException(ExceptionEnum.DASHBOARD_NOT_UPDATE);
        }
        //再查询该dashboard
        String modelId = request.getModelId();

        RtrvDashboardResponse rtrvDashboardResponse = new RtrvDashboardResponse();
        Map modelIdMap = new HashMap();
        modelIdMap.put("modelId", modelId);
        Model model = restTemplate.postForObject(webConfig.getFindModelByIdUrl(), modelId, Model.class);
        rtrvDashboardResponse.setModel(model);
        //通过storyIdList查询storyList
        String productBacklogs = dashboard.getProductBacklogs();
        String currentSprint = dashboard.getCurrentSprint();
        String nextSprint = dashboard.getNextSprint();
        RtrvStoryListByStoryIdsRequest rtrvStoryListByStoryIdsRequest = new RtrvStoryListByStoryIdsRequest();
        rtrvStoryListByStoryIdsRequest.setPage(1);
        rtrvStoryListByStoryIdsRequest.setPageSize(Integer.MAX_VALUE);
        //productBacklogs
        ProductStoryList productStoryList = new ProductStoryList();
        String[] productStoryIds = productBacklogs.split(",");
        List<String> productStoryIdList = Arrays.asList(productStoryIds);
        rtrvStoryListByStoryIdsRequest.setStoryIds(productStoryIdList);
        try {
            String url = webConfig.getRtrvStoryInfoListByStoryIdsUrl();
            ResponseEntity<RtrvStoryListByStoryIdsResponse> response = this.restTemplate.postForEntity(url, rtrvStoryListByStoryIdsRequest, RtrvStoryListByStoryIdsResponse.class);
            productStoryList.setStories(response.getBody().getStories());
            productStoryList.setTotalElements(response.getBody().getTotalElements());
            productStoryList.setRemarks(response.getBody().getRemarks());
            rtrvDashboardResponse.setProductStoryList(productStoryList);
        } catch (Exception ex) {
            throw new BusinessException(ExceptionEnum.DASHBOARD_DETAIL_STORY_NOT_RTRV);
        }
        //currentSprint
        CurrentSprintStoryList currentSprintStoryList = new CurrentSprintStoryList();
        String[] currentStoryIds = currentSprint.split(",");
        List<String> currentStoryIdList = Arrays.asList(currentStoryIds);
        rtrvStoryListByStoryIdsRequest.setStoryIds(currentStoryIdList);
        try {
            String url = webConfig.getRtrvStoryInfoListByStoryIdsUrl();
            ResponseEntity<RtrvStoryListByStoryIdsResponse> response = this.restTemplate.postForEntity(url, rtrvStoryListByStoryIdsRequest, RtrvStoryListByStoryIdsResponse.class);
            currentSprintStoryList.setStories(response.getBody().getStories());
            currentSprintStoryList.setTotalElements(response.getBody().getTotalElements());
            currentSprintStoryList.setRemarks(response.getBody().getRemarks());
            rtrvDashboardResponse.setCurrentSprintStoryList(currentSprintStoryList);
        } catch (Exception ex) {
            throw new BusinessException(ExceptionEnum.DASHBOARD_DETAIL_STORY_NOT_RTRV);
        }
        //nextSprint
        NextSprintSrotyList nextSprintSrotyList = new NextSprintSrotyList();
        String[] nextStoryIds = nextSprint.split(",");
        List<String> nextStoryIdList = Arrays.asList(nextStoryIds);
        rtrvStoryListByStoryIdsRequest.setStoryIds(nextStoryIdList);
        try {
            String url = webConfig.getRtrvStoryInfoListByStoryIdsUrl();
            ResponseEntity<RtrvStoryListByStoryIdsResponse> response = this.restTemplate.postForEntity(url, rtrvStoryListByStoryIdsRequest, RtrvStoryListByStoryIdsResponse.class);
            nextSprintSrotyList.setStories(response.getBody().getStories());
            nextSprintSrotyList.setTotalElements(response.getBody().getTotalElements());
            nextSprintSrotyList.setRemarks(response.getBody().getRemarks());
            rtrvDashboardResponse.setNextSprintSrotyList(nextSprintSrotyList);
        } catch (Exception ex) {
            throw new BusinessException(ExceptionEnum.DASHBOARD_DETAIL_STORY_NOT_RTRV);
        }

        restResponse.setStatusCode(String.valueOf(HttpStatus.OK));
        restResponse.setResponseCode("000");
        restResponse.setResponseBody(rtrvDashboardResponse);
        return restResponse;
}

    @Override
    public RestResponse assignSprint(UpdateDashboardRequest request) {
        return null;
    }

}
