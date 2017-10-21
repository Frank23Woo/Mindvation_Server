package com.mdvns.mdvn.story.papi.service.impl;

import com.mdvns.mdvn.common.beans.*;
import com.mdvns.mdvn.common.beans.exception.BusinessException;
import com.mdvns.mdvn.common.beans.exception.ExceptionEnum;
import com.mdvns.mdvn.common.utils.FetchListUtil;
import com.mdvns.mdvn.story.papi.config.StoryConfig;
import com.mdvns.mdvn.story.papi.domain.*;
import com.mdvns.mdvn.story.papi.domain.Story;
import com.mdvns.mdvn.story.papi.service.IStoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public RestResponse rtrvStoryInfoList(RtrvStoryListRequest rtrvStoryListRequest) {
        String storyInfoListUrl = config.getRtrvStoryInfoListUrl();
        ResponseEntity<RtrvStoryListResponse> rtrvStoryListResponse = this.restTemplate.postForEntity(storyInfoListUrl, rtrvStoryListRequest, RtrvStoryListResponse.class);
        restResponse.setStatusCode(String.valueOf(HttpStatus.OK));
        restResponse.setResponseCode("000");
        restResponse.setResponseBody(rtrvStoryListResponse.getBody());
        return restResponse;
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
        SubFunctionLabel subFuncLabel = new SubFunctionLabel();
        try {
            subFuncLabel = restTemplate.postForObject(judgeSubLabelIdUrl, judgeSubLabelIdRequest, SubFunctionLabel.class);
        } catch (Exception ex) {
            throw new BusinessException(ExceptionEnum.SAPI_EXCEPTION);
        }
        //1.先保存用户故事基本信息（获取storyId）
        String saveStoryBaseUrl = config.getSaveStoryBaseUrl();
        ResponseEntity<Story> responseEntity = null;
//        createStoryRequest.getStoryInfo().setLabelId(subFuncLabel.getLabelId());
        createStoryRequest.setSubFunctionLabel(subFuncLabel);
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
        if (createStoryRequest.getMembers() != null && !createStoryRequest.getMembers().isEmpty()) {
            List<RoleMember> members = createStoryRequest.getMembers();
            List<StoryRoleMember> list = new ArrayList<>();
            for (int i = 0; i < members.size(); i++) {
                String roleId = members.get(i).getRoleId();
                for (int j = 0; j < members.get(i).getMemberIds().size(); j++) {
                    StoryRoleMember storyRoleMember = new StoryRoleMember();
                    storyRoleMember.setRoleId(roleId);
                    storyRoleMember.setStaffId(members.get(i).getMemberIds().get(j));
                    list.add(storyRoleMember);
                }
            }
            List<StoryRoleMember> storyLeaders = list;
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


    /**
     * 调用sapi更改story
     * (更新那一块儿，就把这一块儿所有信息传过来，没更新就不传)
     *
     * @param updateStoryDetailRequest
     * @return
     */
    @Override
    public RestResponse updateStory(UpdateStoryDetailRequest updateStoryDetailRequest) {
        UpdateStoryDetailResponse updateStoryDetailResponse = new UpdateStoryDetailResponse();
        StoryDetail storyDetail = new StoryDetail();
        RestTemplate restTemplate = new RestTemplate();
        if (updateStoryDetailRequest == null || updateStoryDetailRequest.getStoryInfo().getStoryId() == null) {
            throw new NullPointerException("updateStoryRequest 或用户故事Id不能为空");
        }
        //1.先判断是否更改用户故事基本信息
        if (updateStoryDetailRequest.getStoryInfo() != null) {
            //先判断过程方法子模块是新建还是选取（访问model模块）
            if (updateStoryDetailRequest.getSubFunctionLabel() != null) {
                JudgeSubLabelIdRequest judgeSubLabelIdRequest = new JudgeSubLabelIdRequest();
                judgeSubLabelIdRequest.setCreatorId(updateStoryDetailRequest.getCreatorId());
                judgeSubLabelIdRequest.setSubFunctionLabel(updateStoryDetailRequest.getSubFunctionLabel());
                String judgeSubLabelIdUrl = config.getJudgeSubLabelIdUrl();
                SubFunctionLabel subFuncLabel = new SubFunctionLabel();
                try {
                    subFuncLabel = restTemplate.postForObject(judgeSubLabelIdUrl, judgeSubLabelIdRequest, SubFunctionLabel.class);
                } catch (Exception ex) {
                    throw new BusinessException(ExceptionEnum.SAPI_EXCEPTION);
                }
                updateStoryDetailRequest.setSubFunctionLabel(subFuncLabel);
                updateStoryDetailRequest.getStoryInfo().setLabelId(subFuncLabel.getLabelId());
            }
            String updateStoryBaseInfoUrl = config.getUpdateStoryBaseInfoUrl();
            story = updateStoryDetailRequest.getStoryInfo();
            try {
                Story st = restTemplate.postForObject(updateStoryBaseInfoUrl, story, Story.class);
                storyDetail.setStoryInfo(st);
            } catch (Exception ex) {
                throw new BusinessException(ExceptionEnum.STORY_BASEINFO_NOT_UPDATE);
            }
        }
        //之后ragStatus需要后台计算以后传给前台
//        if (!StringUtils.isEmpty(updateStoryDetailRequest.getRagStatus())) {
//            story.setRagStatus(updateStoryDetailRequest.getRagStatus());
//        }
        //2.判断是否更改用户故事下的角色下的成员信息
        if (updateStoryDetailRequest.getMembers() != null && !updateStoryDetailRequest.getMembers().isEmpty()) {
            UpdateSMembersRequest updateSMembersRequest = new UpdateSMembersRequest();
            //角色集合转为对象
            List<RoleMember> members = updateStoryDetailRequest.getMembers();
            List<StoryRoleMember> list = new ArrayList<>();
            for (int i = 0; i < members.size(); i++) {
                String roleId = members.get(i).getRoleId();
                for (int j = 0; j < members.get(i).getMemberIds().size(); j++) {
                    StoryRoleMember storyRoleMember = new StoryRoleMember();
                    storyRoleMember.setRoleId(roleId);
                    storyRoleMember.setStaffId(members.get(i).getMemberIds().get(j));
                    list.add(storyRoleMember);
                }
            }
            updateSMembersRequest.setStoryId(updateStoryDetailRequest.getStoryInfo().getStoryId());
            updateSMembersRequest.setsRoleMembers(list);
            String updateStoryMembersUrl = config.getUpdateStoryMembersUrl();
            try {
                ParameterizedTypeReference reqmntTagTypeReference = new ParameterizedTypeReference<List<StoryRoleMember>>() {
                };
                List<StoryRoleMember> storyRoleMembers = FetchListUtil.fetch(restTemplate, updateStoryMembersUrl, updateSMembersRequest, reqmntTagTypeReference);
                //选出不同的角色
                List<String> roleIds = new ArrayList<>();
                for (int i = 0; i < storyRoleMembers.size(); i++) {
                    String id = storyRoleMembers.get(i).getRoleId();
                    if (!roleIds.isEmpty() && roleIds.contains(id)) {
                        continue;
                    }
                    roleIds.add(storyRoleMembers.get(i).getRoleId());
                }
                //选出角色中不同的成员
                List<RoleAndMember> roleAndMembers = new ArrayList<>();
                for (int i = 0; i < roleIds.size(); i++) {
                    RoleAndMember roleAndMember = new RoleAndMember();
                    String roleId = roleIds.get(i);
                    ModelRole modelRole = restTemplate.postForObject(config.getRtrvRoleByRoleIdUrl(), roleId, ModelRole.class);
                    roleAndMember.setRoleDetail(modelRole);
                    RtrvMembersByRoleIdRequest rtrvMembersByRoleIdRequest = new RtrvMembersByRoleIdRequest();
                    rtrvMembersByRoleIdRequest.setRoleId(roleId);
                    rtrvMembersByRoleIdRequest.setStoryId(updateStoryDetailRequest.getStoryInfo().getStoryId());
                    List<StoryRoleMember> memberList = FetchListUtil.fetch(restTemplate, config.getRtrvMembersByRoleIdUrl(), rtrvMembersByRoleIdRequest, reqmntTagTypeReference);
                    List staffIds = new ArrayList();
                    for (int j = 0; j < memberList.size(); j++) {
                        staffIds.add(memberList.get(j).getStaffId());
                    }
                    RtrvStaffListByStaffIbListRequest rtrvStaffListRequest = new RtrvStaffListByStaffIbListRequest();
                    rtrvStaffListRequest.setStaffIdList(staffIds);
                    List<Staff> staffList = restTemplate.postForObject(config.getRtrvStaffsByIdsUrl(), rtrvStaffListRequest, List.class);
                    roleAndMember.setMemberDetails(staffList);
                    roleAndMembers.add(roleAndMember);
                }
                storyDetail.setMembers(roleAndMembers);
            } catch (Exception ex) {
                throw new BusinessException(ExceptionEnum.STORY_STAFF_NOT_UPDATE);
            }
        }
        //3.判断是否更改用户故事标签信息
        if (updateStoryDetailRequest.getsTags() != null && !updateStoryDetailRequest.getsTags().isEmpty()) {
            UpdateSTagsRequest updateSTagsRequest = new UpdateSTagsRequest();
            updateSTagsRequest.setStoryId(updateStoryDetailRequest.getStoryInfo().getStoryId());
            updateSTagsRequest.setsTags(updateStoryDetailRequest.getsTags());
            String updateStoryTagsUrl = config.getUpdateStoryTagsUrl();
            try {
                ParameterizedTypeReference reqmntTagTypeReference = new ParameterizedTypeReference<List<StoryTag>>() {
                };
                List<StoryTag> storyTags = FetchListUtil.fetch(restTemplate, updateStoryTagsUrl, updateSTagsRequest, reqmntTagTypeReference);
                List tagIds = new ArrayList();
                for (int i = 0; i < storyTags.size(); i++) {
                    tagIds.add(storyTags.get(i).getTagId());
                }
                RtrvTagsRequest rtrvTagsRequest = new RtrvTagsRequest();
                rtrvTagsRequest.setTagIds(tagIds);
                List<Tag> tagList = restTemplate.postForObject(config.getRtrvTagsByIdsUrl(), rtrvTagsRequest, List.class);
                storyDetail.setTags(tagList);
            } catch (Exception ex) {
                throw new BusinessException(ExceptionEnum.STORY_TAG_NOT_UPDATE);
            }
        }
        //4.判断是否更改用户故事模块信息
        if (updateStoryDetailRequest.getSubFunctionLabel() != null) {
            try {
                String labelId = storyDetail.getStoryInfo().getLabelId();
                Map mapLabel = new HashMap();
                mapLabel.put("labelId", labelId);
                SubFunctionLabel storyModel = restTemplate.postForObject(config.getRtrvFuncLabelUrl(), mapLabel, SubFunctionLabel.class);
                storyDetail.setSubFunctionLabel(storyModel);
            } catch (Exception ex) {
                throw new BusinessException(ExceptionEnum.STORY_DETAIL_MODEL_NOT_RTRV);
            }
        }
        updateStoryDetailResponse.setStoryDetail(storyDetail);
        restResponse.setResponseBody(updateStoryDetailResponse);
        restResponse.setStatusCode("200");
        restResponse.setResponseMsg("请求成功");
        restResponse.setResponseCode("000");

        return restResponse;
    }


    /**
     * 获取某个用户故事详细信息
     *
     * @param rtrvStoryDetailRequest
     * @return
     */
    @Override
    public RestResponse rtrvStoryInfo(RtrvStoryDetailRequest rtrvStoryDetailRequest) {
        RtrvStoryDetailResponse rtrvStoryDetailResponse = new RtrvStoryDetailResponse();
        StoryDetail storyDetail = new StoryDetail();
        if (rtrvStoryDetailRequest == null || rtrvStoryDetailRequest.getStoryId() == null) {
            throw new NullPointerException("rtrvStoryDetailRequest 或用户故事Id不能为空");
        }
        //1.先获取用户故事基本信息
        String rtrvStoryBaseInfoUrl = config.getRtrvStoryBaseInfoUrl();
        story = restTemplate.postForObject(rtrvStoryBaseInfoUrl, rtrvStoryDetailRequest, Story.class);
        if (null == story) {
            LOG.error("获取用户故事基本信息不存在.");
            throw new BusinessException(ExceptionEnum.STORY_DETAIL_BASEINFO_NOT_RTRV);
        }
        storyDetail.setStoryInfo(story);
        //2.获取用户故事角色成员信息
        String rtrvSRoleMembersUrl = config.getRtrvSRoleMembersUrl();
        try {
            ParameterizedTypeReference reqmntTagTypeReference = new ParameterizedTypeReference<List<StoryRoleMember>>() {
            };
            List<StoryRoleMember> storyRoleMembers = FetchListUtil.fetch(restTemplate, rtrvSRoleMembersUrl, rtrvStoryDetailRequest, reqmntTagTypeReference);
//            List<StoryRoleMember> storyRoleMembers = restTemplate.postForObject(rtrvSRoleMembersUrl, rtrvStoryDetailRequest, List.class);
            List<RoleAndMember> roleAndMembers = new ArrayList<>();
            //选出不同的角色
            List<String> roleIds = new ArrayList<>();
            for (int i = 0; i < storyRoleMembers.size(); i++) {
                String id = storyRoleMembers.get(i).getRoleId();
                if (!roleIds.isEmpty() && roleIds.contains(id)) {
                    continue;
                }
                roleIds.add(storyRoleMembers.get(i).getRoleId());
            }
            //选出角色中不同的成员
            for (int i = 0; i < roleIds.size(); i++) {
                RoleAndMember roleAndMember = new RoleAndMember();
                String roleId = storyRoleMembers.get(i).getRoleId();
                ModelRole modelRole = restTemplate.postForObject(config.getRtrvRoleByRoleIdUrl(), roleId, ModelRole.class);
                roleAndMember.setRoleDetail(modelRole);
                RtrvMembersByRoleIdRequest rtrvMembersByRoleIdRequest = new RtrvMembersByRoleIdRequest();
                rtrvMembersByRoleIdRequest.setRoleId(roleId);
                rtrvMembersByRoleIdRequest.setStoryId(rtrvStoryDetailRequest.getStoryId());
                List<StoryRoleMember> members = FetchListUtil.fetch(restTemplate, config.getRtrvMembersByRoleIdUrl(), rtrvMembersByRoleIdRequest, reqmntTagTypeReference);
                List staffIds = new ArrayList();
                for (int j = 0; j < members.size(); j++) {
                    staffIds.add(members.get(j).getStaffId());
                }
                RtrvStaffListByStaffIbListRequest rtrvStaffListRequest = new RtrvStaffListByStaffIbListRequest();
                rtrvStaffListRequest.setStaffIdList(staffIds);
                List<Staff> staffList = restTemplate.postForObject(config.getRtrvStaffsByIdsUrl(), rtrvStaffListRequest, List.class);
                roleAndMember.setMemberDetails(staffList);
                roleAndMembers.add(roleAndMember);
            }
            storyDetail.setMembers(roleAndMembers);
        } catch (Exception ex) {
            throw new BusinessException(ExceptionEnum.STORY_DETAIL_STAFF_NOT_RTRV);
        }
        //3.获取用户故事上一层reqmnt所有角色成员信息
        // 查询members
        try {
            //查询reqmnt下的modelId
            String storyId = rtrvStoryDetailRequest.getStoryId();
            Map mapStory = new HashMap();
            mapStory.put("storyId", storyId);
            String  modelId = restTemplate.postForObject(config.getModelIdByStoryIdUrl(), storyId, String.class);
            ParameterizedTypeReference modelRoleRef = new ParameterizedTypeReference<List<ModelRole>>() {
            };
            Map modelIdMap = new HashMap();
            modelIdMap.put("modelId", modelId);
            RtrvModelByIdResponse MRresp = restTemplate.postForObject(config.getRtrvModelRoleByModelIdUrl(), modelIdMap, RtrvModelByIdResponse.class);
            List<ModelRole> modelRoleList = MRresp.getModelRoles();

            // call reqmnt sapi
            //获取reqmntId
            String reqmntId = restTemplate.postForObject(config.getReqmntIdByStoryIdUrl(), storyId, String.class);
            ParameterizedTypeReference parameterizedTypeReference = new ParameterizedTypeReference<List<ReqmntMember>>() {
            };
            List<ReqmntMember> data = FetchListUtil.fetch(restTemplate, config.getRtrvReqmntMembersUrl(), reqmntId, parameterizedTypeReference);
            List<ReqmntMember> reqmntMembers = data;
            List<String> memberIds = new ArrayList<>();
            for (int i = 0; i < reqmntMembers.size(); i++) {
                String id = reqmntMembers.get(i).getReqmntId();
                if (!memberIds.isEmpty() && memberIds.contains(id)) {
                    continue;
                }
                memberIds.add(reqmntMembers.get(i).getStaffId());
            }

            // call staff sapi
            Map<String, Object> params = new HashMap<>();
            params.put("staffIdList", memberIds);
            ParameterizedTypeReference typeReference = new ParameterizedTypeReference<List<Staff>>() {
            };
            List<Staff> stafList = (List<Staff>) FetchListUtil.fetch(restTemplate, config.getRtrvStaffsByIdsUrl(), params, typeReference);

            List<RoleAndMember> roleAndMembers = new ArrayList<>();
            for (int i = 0; i < modelRoleList.size(); i++) {
                RoleAndMember roleAndMember = new RoleAndMember();
                roleAndMember.setRoleDetail(modelRoleList.get(i));
                List<Staff> stafs = new ArrayList<>();
                roleAndMember.setMemberDetails(stafs);

                for (int j = 0; j < reqmntMembers.size(); j++) {
                    if (modelRoleList.get(i).getRoleId().equals(reqmntMembers.get(j).getRoleId())) {
                        stafs.add(stafList.get(j));
                    }
                }
                roleAndMembers.add(roleAndMember);
            }
            storyDetail.setrMembers(roleAndMembers);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ExceptionEnum.REQMNT_QUERY_MEMBER_FAIELD);
        }

        //4.获取用户故事标签信息
        String rtrvStoryTagsUrl = config.getRtrvStoryTagsUrl();
        try {
            ParameterizedTypeReference reqmntTagTypeReference = new ParameterizedTypeReference<List<StoryTag>>() {
            };
            List<StoryTag> storyTags = FetchListUtil.fetch(restTemplate, rtrvStoryTagsUrl, rtrvStoryDetailRequest, reqmntTagTypeReference);
            List tagIds = new ArrayList();
            for (int i = 0; i < storyTags.size(); i++) {
                tagIds.add(storyTags.get(i).getTagId());
            }
            RtrvTagsRequest rtrvTagsRequest = new RtrvTagsRequest();
            rtrvTagsRequest.setTagIds(tagIds);
            List<Tag> tagList = restTemplate.postForObject(config.getRtrvTagsByIdsUrl(), rtrvTagsRequest, List.class);
            storyDetail.setTags(tagList);
        } catch (Exception ex) {
            throw new BusinessException(ExceptionEnum.STORY_DETAIL_TAG_NOT_RTRV);
        }
        //5.获取用户故事过程方法子模块信息
        try {
            String labelId = storyDetail.getStoryInfo().getLabelId();
            Map mapLabel = new HashMap();
            mapLabel.put("labelId", labelId);
            SubFunctionLabel storyModel = restTemplate.postForObject(config.getRtrvFuncLabelUrl(), mapLabel, SubFunctionLabel.class);
            storyDetail.setSubFunctionLabel(storyModel);
        } catch (Exception ex) {
            throw new BusinessException(ExceptionEnum.STORY_DETAIL_MODEL_NOT_RTRV);
        }
        //retriveFunctionLable
        ParameterizedTypeReference funcLabelTypeReference = new ParameterizedTypeReference<FunctionLabel>() {
        };
        Map mapLabel = new HashMap();
        //查询reqmnt下的labelid
        String storyId = rtrvStoryDetailRequest.getStoryId();
        Map mapStory = new HashMap();
        mapStory.put("storyId", storyId);
        String labelId = restTemplate.postForObject(config.getLabelIdByStoryIdUrl(), storyId, String.class);
        mapLabel.put("labelId", labelId);
        FunctionLabel funcLabel = this.restTemplate.postForObject(config.getRtrvFuncLabelUrl(), mapLabel, FunctionLabel.class);
        storyDetail.setLabelDetail(funcLabel);
        //6.获取用户故事list<task>信息
        String url = config.getRtrvTaskListByStoryIdUrl();
        RtrvTaskListRequest rtrvTaskListRequest = new RtrvTaskListRequest();
        rtrvTaskListRequest.setPage(0);
        rtrvTaskListRequest.setPageSize(Integer.MAX_VALUE);
        rtrvTaskListRequest.setStoryId(rtrvStoryDetailRequest.getStoryId());
        RestResponse<List<TaskDetail>> response = restTemplate.postForObject(url,rtrvTaskListRequest,RestResponse.class);
        storyDetail.setsTasks(response.getResponseBody());

        rtrvStoryDetailResponse.setStoryDetail(storyDetail);
        restResponse.setResponseBody(rtrvStoryDetailResponse);
        restResponse.setStatusCode("200");
        restResponse.setResponseMsg("请求成功");
        restResponse.setResponseCode("000");
        return restResponse;
    }
}
