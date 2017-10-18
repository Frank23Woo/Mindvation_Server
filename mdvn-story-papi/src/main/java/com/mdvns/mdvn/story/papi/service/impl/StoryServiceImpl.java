package com.mdvns.mdvn.story.papi.service.impl;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.common.beans.SubFunctionLabel;
import com.mdvns.mdvn.common.beans.exception.BusinessException;
import com.mdvns.mdvn.common.beans.exception.ExceptionEnum;
import com.mdvns.mdvn.story.papi.config.StoryConfig;
import com.mdvns.mdvn.story.papi.domain.*;
import com.mdvns.mdvn.story.papi.service.IStoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class StoryServiceImpl implements IStoryService {

    private static final Logger LOG = LoggerFactory.getLogger(StoryServiceImpl.class);

    @Autowired
    private StoryConfig config;

    @Autowired
    private Story story;

    @Autowired
    private RestResponse restResponse;

    @Autowired
    private RestTemplate restTemplate;


    /**
     * 获取story列表详细信息
     *
     * @param rtrvStoryListRequest
     * @return
     */
    public ResponseEntity<?> rtrvStoryInfoList(RtrvStoryListRequest rtrvStoryListRequest) {
        rtrvStoryListRequest.setPage(rtrvStoryListRequest.getPage() - 1);
        String storyInfoListUrl = config.getRtrvStoryInfoListUrl();
        restResponse = this.restTemplate.postForObject(storyInfoListUrl, rtrvStoryListRequest, RestResponse.class);
        ResponseEntity<RestResponse> responseEntity = null;
        if (!restResponse.getStatusCode().equals(HttpStatus.OK.toString())) {
            throw new BusinessException(ExceptionEnum.SAPI_EXCEPTION);
        }
        responseEntity = new ResponseEntity<RestResponse>(restResponse, HttpStatus.OK);
        return responseEntity;
    }

    /**
     * 调用sapi创建story
     * 1.创建保存story信息
     * 2.返回Story整个信息
     *
     * @param createStoryRequest
     * @return
     */

    @Override
    public RestResponse createStory(CreateStoryRequest createStoryRequest) {
        //先判断过程方法子模块是新建还是选取（访问model模块）
        JudgeSubLabelIdRequest judgeSubLabelIdRequest = new JudgeSubLabelIdRequest();
        judgeSubLabelIdRequest.setCreatorId(createStoryRequest.getCreatorId());
        judgeSubLabelIdRequest.setSubFunctionLabel(createStoryRequest.getSubFunctionLabel());
        String judgeSubLabelIdUrl = config.getJudgeSubLabelIdUrl();
        try {
            SubFunctionLabel subFuncLabel = restTemplate.postForObject(judgeSubLabelIdUrl, judgeSubLabelIdRequest, SubFunctionLabel.class);
        } catch (Exception ex) {
            throw new BusinessException(ExceptionEnum.SAPI_EXCEPTION);
        }
        //1.先保存用户故事基本信息（获取storyId）
        String saveStoryBaseUrl = config.getSaveStoryBaseUrl();
        ResponseEntity<Story> responseEntity = null;
        try {
            responseEntity = restTemplate.postForEntity(saveStoryBaseUrl, createStoryRequest, Story.class);
        } catch (Exception ex) {
            throw new BusinessException(ExceptionEnum.STORY_BASEINFO_NOT_CREATE);
        }
        restResponse.setResponseBody(responseEntity.getBody());
        restResponse.setStatusCode("200");
        restResponse.setResponseMsg("请求成功");
        restResponse.setResponseCode("000");
        story = responseEntity.getBody();
        String storyId = story.getStoryId();
        //2.保存用户故事成员信息
        if (createStoryRequest.getsMembers() != null && !createStoryRequest.getsMembers().isEmpty()) {
            List<StoryRoleMember> storyLeaders = createStoryRequest.getsMembers();
            for (int i = 0; i < storyLeaders.size(); i++) {
                storyLeaders.get(i).setStoryId(storyId);
            }
            String savePLeadersUrl = config.getSavePLeadersUrl();
            try {
                List<StoryRoleMember> sRoleMembers = restTemplate.postForObject(savePLeadersUrl, storyLeaders, List.class);
            } catch (Exception ex) {
                throw new BusinessException(ExceptionEnum.STORY_STAFF_NOT_CREATE);
            }
        }
        //3.保存用户故事标签信息
        if (createStoryRequest.getsTags() != null && !createStoryRequest.getsTags().isEmpty()) {
            List<StoryTag> storyTags = createStoryRequest.getsTags();
            for (int i = 0; i < storyTags.size(); i++) {
                storyTags.get(i).setStoryId(storyId);
            }
            String savePTagsUrl = config.getSavePTagsUrl();
            try {
                List<StoryTag> sTags = restTemplate.postForObject(savePTagsUrl, storyTags, List.class);
            } catch (Exception ex) {
                throw new BusinessException(ExceptionEnum.STORY_TAG_NOT_CREATE);
            }
        }
//        //4.保存用户故事模块信息
//        if (createStoryRequest.getsModels() != null && !createStoryRequest.getsModels().isEmpty()) {
//            List<StoryModel> storyModels = createStoryRequest.getsModels();
//            for (int i = 0; i < storyModels.size(); i++) {
//                storyModels.get(i).setStoryId(storyId);
//            }
//            String savePModelsUrl = config.getSavePModelsUrl();
//            try {
//                List<StoryTag> sModels = restTemplate.postForObject(savePModelsUrl, storyModels, List.class);
//            } catch (Exception ex) {
//                throw new BusinessException(ExceptionEnum.STORY_MODEL_NOT_CREATE);
//            }
//        }
//        //5.保存用户故事checklist信息
//        if (createStoryRequest.getCheckLists() != null && !createStoryRequest.getCheckLists().isEmpty()) {
//            SavePCheckListsRequest savePCheckListsRequest = new SavePCheckListsRequest();
//            List<StoryChecklists> storyChecklists = createStoryRequest.getCheckLists();
//            for (int i = 0; i < storyChecklists.size(); i++) {
//                storyChecklists.get(i).setStoryId(storyId);
//                storyChecklists.get(i).setAssignerId(createStoryRequest.getStaffId());
//            }
//            savePCheckListsRequest.setStaffId(createStoryRequest.getStaffId());
//            savePCheckListsRequest.setCheckLists(storyChecklists);
//            String saveCheckListsUrl = config.getSaveCheckListsUrl();
//            try {
//                List<StoryChecklists> checklistsList = restTemplate.postForObject(saveCheckListsUrl, savePCheckListsRequest, List.class);
//            } catch (Exception ex) {
//                throw new BusinessException(ExceptionEnum.STORY_CHECKLIST_NOT_CREATE);
//            }
//        }
//        //6.保存用户故事附件信息
//        if (createStoryRequest.getsAttchUrls() != null && !createStoryRequest.getsAttchUrls().isEmpty()) {
//            List<StoryAttchUrls> storyAttchUrls = createStoryRequest.getsAttchUrls();
//            for (int i = 0; i < storyAttchUrls.size(); i++) {
//                storyAttchUrls.get(i).setStoryId(storyId);
////                storyAttchUrls.get(i).setYxbz("Y");
//            }
//            String savePAttchUrlsUrl = config.getSavePAttchUrlsUrl();
//            try {
//                List<StoryAttchUrls> pAttchUrls = restTemplate.postForObject(savePAttchUrlsUrl, storyAttchUrls, List.class);
//            } catch (Exception ex) {
//                throw new BusinessException(ExceptionEnum.STORY_ATTCHURL_NOT_CREATE);
//            }
//        }
        return restResponse;
    }

    @Override
    public RestResponse updateStory(UpdateStoryDetailRequest updateStoryDetailRequest) {
        return null;
    }

    @Override
    public RestResponse rtrvStoryInfo(RtrvStoryDetailRequest rtrvStoryDetailRequest) {
        return null;
    }

    /**
     * 调用sapi更改story
     *(更新那一块儿，就把这一块儿所有信息传过来，没更新就不传)
     * @param updateStoryDetailRequest
     * @return
     */
//    @Override
//    public RestResponse updateStory(UpdateStoryDetailRequest updateStoryDetailRequest) {
//        UpdateStoryDetailResponse updateStoryDetailResponse = new UpdateStoryDetailResponse();
//        StoryDetail storyDetail = new StoryDetail();
//        RestTemplate restTemplate = new RestTemplate();
////        String storyId = updateStoryRequest.getStoryId();
//        if (updateStoryDetailRequest == null || updateStoryDetailRequest.getStoryInfo().getStoryId() == null) {
//            throw new NullPointerException("updateStoryRequest 或用户故事Id不能为空");
//        }
//        //1.先判断是否更改用户故事基本信息
//        story.setStoryId(updateStoryDetailRequest.getStoryInfo().getStoryId());
//        if (!StringUtils.isEmpty(updateStoryDetailRequest.getStoryInfo().getSummary())) {
//            story.setSummary(updateStoryDetailRequest.getStoryInfo().getSummary());
//        }
//        if (!StringUtils.isEmpty(updateStoryDetailRequest.getStoryInfo().getDescription())) {
//            story.setDescription(updateStoryDetailRequest.getStoryInfo().getDescription());
//        }
//        if (!StringUtils.isEmpty(updateStoryDetailRequest.getStoryInfo().getStartDate())) {
//            story.setStartDate(updateStoryDetailRequest.getStoryInfo().getStartDate());
//
//        }
//        if (!StringUtils.isEmpty(updateStoryDetailRequest.getStoryInfo().getEndDate())) {
//            story.setEndDate(updateStoryDetailRequest.getStoryInfo().getEndDate());
//
//        }
//        if (!StringUtils.isEmpty(updateStoryDetailRequest.getStoryInfo().getPriority())) {
//            story.setPriority(updateStoryDetailRequest.getStoryInfo().getPriority());
//
//        }
//        if (!StringUtils.isEmpty(updateStoryDetailRequest.getStoryInfo().getStatus())) {
//            story.setStatus(updateStoryDetailRequest.getStoryInfo().getStatus());
//
//        }
//        //之后ragStatus需要后台计算以后传给前台
////        if (!StringUtils.isEmpty(updateStoryDetailRequest.getRagStatus())) {
////            story.setRagStatus(updateStoryDetailRequest.getRagStatus());
////        }
//        String updateStoryBaseInfoUrl = config.getUpdateStoryBaseInfoUrl();
//        try {
//            Story st = restTemplate.postForObject(updateStoryBaseInfoUrl, story, Story.class);
//            storyDetail.setStoryInfo(st);
//        } catch (Exception ex) {
//            throw new BusinessException(ExceptionEnum.STORY_BASEINFO_NOT_UPDATE);
//        }
//        //2.判断是否更改用户故事负责人信息
//        if (updateStoryDetailRequest.getsRoleMembers() != null && !updateStoryDetailRequest.getsRoleMembers().isEmpty()) {
//            UpdateSMembersRequest updateSMembersRequest = new UpdateSMembersRequest();
//            updateSMembersRequest.setStoryId(updateStoryDetailRequest.getStoryInfo().getStoryId());
//            updateSMembersRequest.setsRoleMembers(updateStoryDetailRequest.getsRoleMembers());
//            String updateStoryLeadersUrl = config.getUpdateStoryLeadersUrl();
////            try {
////                List<Staff> pLeaders = restTemplate.postForObject(updateStoryLeadersUrl, updateSMembersRequest, List.class);
////                storyDetail.setLeaders(pLeaders);
////            } catch (Exception ex) {
////                throw new BusinessException(ExceptionEnum.STORY_STAFF_NOT_UPDATE);
////            }
//        }
//        //3.判断是否更改用户故事标签信息
////        if (updateStoryDetailRequest.getTags() != null && !updateStoryDetailRequest.getTags().isEmpty()) {
////            UpdatePTagsRequest updatePTagsRequest = new UpdatePTagsRequest();
////            updatePTagsRequest.setStoryId(updateStoryDetailRequest.getStoryId());
////            updatePTagsRequest.setTags(updateStoryDetailRequest.getTags());
////            String updateStoryTagsUrl = config.getUpdateStoryTagsUrl();
////            try {
////                List<Tag> pTags = restTemplate.postForObject(updateStoryTagsUrl, updatePTagsRequest, List.class);
////                storyDetail.setTags(pTags);
////            } catch (Exception ex) {
////                throw new BusinessException(ExceptionEnum.STORY_TAG_NOT_UPDATE);
////            }
//        }
//
//        //4.判断是否更改用户故事模块信息
//        if (updateStoryDetailRequest.getModels() != null && !updateStoryDetailRequest.getModels().isEmpty()) {
//            UpdatePModelsRequest updatePModelsRequest = new UpdatePModelsRequest();
//            updatePModelsRequest.setStoryId(updateStoryDetailRequest.getStoryId());
//            updatePModelsRequest.setModels(updateStoryDetailRequest.getModels());
//            String updateStoryModelsUrl = config.getUpdateStoryModelsUrl();
//            try {
//                List<Model> pModels = restTemplate.postForObject(updateStoryModelsUrl, updatePModelsRequest, List.class);
//                storyDetail.setModels(pModels);
//            } catch (Exception ex) {
//                throw new BusinessException(ExceptionEnum.STORY_MODEL_NOT_UPDATE);
//            }
//        }
//        //5.判断是否更改用户故事checklist信息
//        if (updateStoryDetailRequest.getCheckLists() != null && !updateStoryDetailRequest.getCheckLists().isEmpty()) {
//            UpdatePCheckListsRequest updatePCheckListsRequest = new UpdatePCheckListsRequest();
//            updatePCheckListsRequest.setStoryId(updateStoryDetailRequest.getStoryId());
//            updatePCheckListsRequest.setStaffId(updateStoryDetailRequest.getStaffId());
//            updatePCheckListsRequest.setCheckLists(updateStoryDetailRequest.getCheckLists());
//            String updateStoryChecklistsUrl = config.getUpdateStoryChecklistsUrl();
//            try {
//                List<StoryChecklists> pChecklists = restTemplate.postForObject(updateStoryChecklistsUrl, updatePCheckListsRequest, List.class);
//                //获取checklist里面成员的对象信息
//                UpdatePCheckListsRequest pCheckListsRequest = new UpdatePCheckListsRequest();
//                pCheckListsRequest.setStoryId(updateStoryDetailRequest.getStoryId());
//                pCheckListsRequest.setCheckLists(pChecklists);
//                String checklistsListByUuIdUrl = config.getChecklistsListByUuIdUrl();
//                try {
//                    List<StoryChecklistsDetail> checklists = restTemplate.postForObject(checklistsListByUuIdUrl, pCheckListsRequest, List.class);
//                    storyDetail.setCheckLists(checklists);
//                } catch (Exception ex) {
//                    throw new BusinessException(ExceptionEnum.STORY_CHECKLIST_STAFFDETAIL_NOT_RTRV);
//                }
//            } catch (Exception ex) {
//                throw new BusinessException(ExceptionEnum.STORY_CHECKLIST_NOT_UPDATE);
//            }
//        }
//        //6.判断是否更改用户故事附件信息
//        if (updateStoryDetailRequest.getAttchUrls() != null && !updateStoryDetailRequest.getAttchUrls().isEmpty()) {
//            UpdatePAttchUrlsRequest updatePAttchUrlsRequest = new UpdatePAttchUrlsRequest();
//            updatePAttchUrlsRequest.setStoryId(updateStoryDetailRequest.getStoryId());
//            updatePAttchUrlsRequest.setAttchUrls(updateStoryDetailRequest.getAttchUrls());
//            String updateStoryAttchUrlsUrl = config.getUpdateStoryAttchUrlsUrl();
//            try {
//                List<StoryAttchUrls> pAttchUrls = restTemplate.postForObject(updateStoryAttchUrlsUrl, updatePAttchUrlsRequest, List.class);
//                storyDetail.setAttchUrls(pAttchUrls);
//            } catch (Exception ex) {
//                throw new BusinessException(ExceptionEnum.STORY_ATTCHURL_NOT_UPDATE);
//            }
//        }
//        updateStoryDetailResponse.setStoryDetail(storyDetail);
//        restResponse.setResponseBody(updateStoryDetailResponse);
//        restResponse.setStatusCode("200");
//        restResponse.setResponseMsg("请求成功");
//        restResponse.setResponseCode("000");
//
//        return restResponse;
//    }
//
//    /**
//     * 获取某个用户故事详细信息
//     *
//     * @param rtrvStoryDetailRequest
//     * @return
//     */
//    @Override
//    public RestResponse rtrvStoryInfo(RtrvStoryDetailRequest rtrvStoryDetailRequest) {
//        RtrvStoryDetailResponse rtrvStoryDetailResponse = new RtrvStoryDetailResponse();
//        StoryDetail storyDetail = new StoryDetail();
//        if (rtrvStoryDetailRequest == null || rtrvStoryDetailRequest.getStoryId() == null) {
//            throw new NullPointerException("rtrvStoryDetailRequest 或用户故事Id不能为空");
//        }
//        //1.先获取用户故事基本信息
//        String rtrvStoryBaseInfoUrl = config.getRtrvStoryBaseInfoUrl();
//        story = restTemplate.postForObject(rtrvStoryBaseInfoUrl, rtrvStoryDetailRequest, Story.class);
//        if (null == story) {
//            LOG.error("获取用户故事基本信息不存在.");
//            throw new BusinessException(ExceptionEnum.STORY_DETAIL_BASEINFO_NOT_RTRV);
//        }
//        storyDetail.setStory(story);
//        //2.获取用户故事负责人信息
//        String rtrvStoryLedersUrl = config.getRtrvStoryLedersUrl();
//        try {
//            List<Staff> storyLeaders = restTemplate.postForObject(rtrvStoryLedersUrl, rtrvStoryDetailRequest, List.class);
//            storyDetail.setLeaders(storyLeaders);
//        } catch (Exception ex) {
//            throw new BusinessException(ExceptionEnum.STORY_DETAIL_STAFF_NOT_RTRV);
//        }
//        //3.获取用户故事标签信息
//        String rtrvStoryTagsUrl = config.getRtrvStoryTagsUrl();
//        try {
//            List<Tag> storyTags = restTemplate.postForObject(rtrvStoryTagsUrl, rtrvStoryDetailRequest, List.class);
//            storyDetail.setTags(storyTags);
//        } catch (Exception ex) {
//            throw new BusinessException(ExceptionEnum.STORY_DETAIL_TAG_NOT_RTRV);
//        }
//        //4.获取用户故事模型信息
//        String rtrvStoryModelsUrl = config.getRtrvStoryModelsUrl();
//        try {
//            List<Model> storyModels = restTemplate.postForObject(rtrvStoryModelsUrl, rtrvStoryDetailRequest, List.class);
//            storyDetail.setModels(storyModels);
//        } catch (Exception ex) {
//            throw new BusinessException(ExceptionEnum.STORY_DETAIL_MODEL_NOT_RTRV);
//        }
//        //5.获取用户故事checklist信息
//        String rtrvStoryCheckListsUrl = config.getRtrvStoryCheckListsUrl();
//        try {
//            List<StoryChecklistsDetail> storyChecklists = restTemplate.postForObject(rtrvStoryCheckListsUrl, rtrvStoryDetailRequest, List.class);
//            storyDetail.setCheckLists(storyChecklists);
//        } catch (Exception ex) {
//            throw new BusinessException(ExceptionEnum.STORY_DETAIL_CHECKLIST_NOT_RTRV);
//        }
//        //6.获取用户故事附件信息
//        String rtrvStoryAttUrlsUrl = config.getRtrvStoryAttUrlsUrl();
//        try {
//            List<StoryAttchUrls> storyAttchUrls = restTemplate.postForObject(rtrvStoryAttUrlsUrl, rtrvStoryDetailRequest, List.class);
//            storyDetail.setAttchUrls(storyAttchUrls);
//        } catch (Exception ex) {
//            throw new BusinessException(ExceptionEnum.STORY_DETAIL_ATTCHURL_NOT_RTRV);
//        }
//        //7.获取requirment列表信息
//        String rtrvReqmntListUrl = config.getRtrvReqmntListUrl();
//        try {
//            restResponse = restTemplate.postForObject(rtrvReqmntListUrl, rtrvStoryDetailRequest, RestResponse.class);
////            storyDetail.setRequirementInfos(restResponse.getResponseBody());
//        } catch (Exception ex) {
//            throw new BusinessException(ExceptionEnum.STORY_DETAIL_ATTCHURL_NOT_RTRV);
//        }
//
//        rtrvStoryDetailResponse.setStoryDetail(storyDetail);
//        restResponse.setResponseBody(rtrvStoryDetailResponse);
//        restResponse.setStatusCode("200");
//        restResponse.setResponseMsg("请求成功");
//        restResponse.setResponseCode("000");
//
//        return restResponse;
//    }
}
