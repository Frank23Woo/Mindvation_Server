package com.mdvns.mdvn.reqmnt.papi.service.impl;

import com.mdvns.mdvn.common.beans.*;
import com.mdvns.mdvn.common.beans.exception.BusinessException;
import com.mdvns.mdvn.common.beans.exception.ExceptionEnum;
import com.mdvns.mdvn.common.enums.AuthEnum;
import com.mdvns.mdvn.common.utils.FetchListUtil;
import com.mdvns.mdvn.common.utils.StaffAuthUtil;
import com.mdvns.mdvn.reqmnt.papi.config.ReqmntConfig;
import com.mdvns.mdvn.reqmnt.papi.domain.*;
import com.mdvns.mdvn.reqmnt.papi.domain.ReqmntMember;
import com.mdvns.mdvn.reqmnt.papi.domain.RequirementInfo;
import com.mdvns.mdvn.reqmnt.papi.service.IReqmntService;
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
public class ReqmntServiceImpl implements IReqmntService {

    private static final Logger LOG = LoggerFactory.getLogger(ReqmntServiceImpl.class);

    @Autowired
    private ReqmntConfig config;

//    @Autowired
//    private RequirementInfo requirementInfo;

    @Autowired
    private RestResponse restResponse;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RtrvReqmntInfoResponse rtrvReqmntInfoResponse;

//    /**
//     * 获取project列表详细信息
//     *
//     * @param rtrvProjectListRequest
//     * @return
//     */
//    public ResponseEntity<?> rtrvProjInfoList(RtrvProjectListRequest rtrvProjectListRequest) {
//        rtrvProjectListRequest.setPage(rtrvProjectListRequest.getPage()-1);
//        String projInfoListUrl = config.getRtrvProjInfoListUrl();
//        restResponse = this.restTemplate.postForObject(projInfoListUrl, rtrvProjectListRequest, restResponse.class);
//        ResponseEntity<restResponse> responseEntity = null;
//        if (restResponse.getStatusCode().equals(HttpStatus.OK.toString())) {
////            HttpHeaders httpHeaders = new HttpHeaders();
////            httpHeaders.setAccessControlAllowOrigin("*");
//            responseEntity = new ResponseEntity<restResponse>(restResponse,HttpStatus.OK);
//            return responseEntity;
//        }
//        throw new BusinessException(restResponse.getResponseCode(), restResponse.getResponseBody().toString());
//    }

    @Override
    public RestResponse rtrvReqmntList(RtrvReqmntListRequest rtrvReqmntListRequest) {
        String reqmntListUrl = config.getRtrvReqmntListUrl();
        RtrvReqmntListResponse rtrvReqmntListResponse = new RtrvReqmntListResponse();
        ResponseEntity<RtrvReqmntListResponse> respEntity = this.restTemplate.postForEntity(reqmntListUrl, rtrvReqmntListRequest, RtrvReqmntListResponse.class);
        restResponse.setStatusCode(String.valueOf(HttpStatus.OK));
        restResponse.setResponseCode("000");
        restResponse.setResponseBody(respEntity.getBody());
        return restResponse;
    }

    /**
     * 调用sapi创建project
     * 1.创建保存project信息
     * 2.返回Project整个信息
     *
     * @param createReqmntRequest
     * @return
     */

    @Override
    public RestResponse createReqmnt(CreateReqmntRequest createReqmntRequest) {
        if (createReqmntRequest == null || createReqmntRequest.getPriority() == null) {
            throw new NullPointerException("createStoryRequest 或需求优先级不能为空");
        }
        CreateReqmntResponse createReqmntResponse = new CreateReqmntResponse();
        //先判断过程方法子模块是新建还是选取（访问model模块）
        JudgeLabelIdRequest judgeLabelIdRequest = new JudgeLabelIdRequest();
        judgeLabelIdRequest.setCreatorId(createReqmntRequest.getCreatorId());
        judgeLabelIdRequest.setFunctionLabel(createReqmntRequest.getFunctionLabel());
        String judgeLabelIdUrl = config.getJudgeLabelIdUrl();
        SubFunctionLabel funcLabel = new SubFunctionLabel();
        try {
            funcLabel = restTemplate.postForObject(judgeLabelIdUrl, judgeLabelIdRequest, SubFunctionLabel.class);
        } catch (Exception ex) {
            throw new BusinessException(ExceptionEnum.SAPI_EXCEPTION);
        }
        createReqmntRequest.setFunctionLabel(funcLabel);
        //1.先保存requirement基本信息（获取reqmntId）
        if (createReqmntRequest == null || StringUtils.isEmpty(createReqmntRequest.getCreatorId()) ||
                StringUtils.isEmpty(createReqmntRequest.getSummary()) || StringUtils.isEmpty(createReqmntRequest.getDescription())
                || StringUtils.isEmpty(createReqmntRequest.getProjId()) || StringUtils.isEmpty(createReqmntRequest.getModelId())) {
            throw new NullPointerException("Mandatory fields should not be empty for createReqmntRequest");
        }

        String saveReqmntUrl = config.getSaveReqmntUrl();
        ResponseEntity<RequirementInfo> responseEntity = null;
        responseEntity = restTemplate.postForEntity(saveReqmntUrl, createReqmntRequest, RequirementInfo.class);
        RequirementInfo requirementInfo = responseEntity.getBody();

        //1.1 给需求创建者分配权限
        StaffAuthUtil.assignAuthForCreator(this.restTemplate, createReqmntRequest.getProjId(), requirementInfo.getReqmntId(), createReqmntRequest.getCreatorId(), AuthEnum.Leader.getCode());
        LOG.info("给创建需求者：{}，分配权限：{}成功", createReqmntRequest.getCreatorId(), AuthEnum.Leader.getCode());

        //2.保存requirement member信息
        if (createReqmntRequest.getMembers() != null && !createReqmntRequest.getMembers().isEmpty()) {
            List<RoleMember> roleMembers = createReqmntRequest.getMembers();
            List<ReqmntMember> reqmntMembers = new ArrayList<>();
            ReqmntMember reqmntMember = null;

            String roleId = "";
            for (int i = 0; i < roleMembers.size(); i++) {
                roleId = roleMembers.get(i).getRoleId();
                List<String> memberIds = roleMembers.get(i).getMemberIds();
                for (int j = 0; j < memberIds.size(); j++) {
                    reqmntMember = new ReqmntMember();
                    reqmntMember.setStaffId(memberIds.get(j));
                    reqmntMember.setRoleId(roleId);
                    reqmntMember.setReqmntId(requirementInfo.getReqmntId());
                    reqmntMembers.add(reqmntMember);
                }

            }
            String saveRMembersUrl = config.getSaveRMembersUrl();
            try {
                reqmntMembers = restTemplate.postForObject(saveRMembersUrl, reqmntMembers, List.class);
            } catch (Exception ex) {
                throw new RuntimeException("调用SAPI获取项目负责人信息保存数据失败.");
            }

            //取每条reqmnt的人数和创建者信息
            requirementInfo = responseEntity.getBody();
            String creatorId = requirementInfo.getCreatorId();
            // call staff sapi
            List staffs = new ArrayList();
            staffs.add(creatorId);
            Map<String, Object> prams = new HashMap<>();
            prams.put("staffIdList", staffs);
            ParameterizedTypeReference tReference = new ParameterizedTypeReference<List<Staff>>() {
            };
            LOG.info("获取创建者信息的url为：" + config.getRtrvStaffsByIdsUrl());
            List<Staff> staffList = (List<Staff>) FetchListUtil.fetch(restTemplate, config.getRtrvStaffsByIdsUrl(), prams, tReference);
            responseEntity.getBody().setCreatorInfo(staffList.get(0));

            // 查询members(不重复的个数)
            try {
                // call reqmnt sapi
                ParameterizedTypeReference parameterizedTypeReference = new ParameterizedTypeReference<List<ReqmntMember>>() {
                };
                List<ReqmntMember> data = FetchListUtil.fetch(restTemplate, config.getRtrvReqmntMembersUrl(), requirementInfo.getReqmntId(), parameterizedTypeReference);
                List<ReqmntMember> reqMembers = data;
                List<String> memberIds = new ArrayList<>();
                for (int i = 0; i < reqMembers.size(); i++) {
                    String id = reqMembers.get(i).getStaffId();
                    if (!memberIds.isEmpty() && memberIds.contains(id)) {
                        continue;
                    }
                    memberIds.add(id);
                }
                responseEntity.getBody().setMemberCunt(memberIds.size());
            } catch (Exception e) {
                e.printStackTrace();
                throw new BusinessException(ExceptionEnum.REQMNT_QUERY_MEMBER_FAIELD);
            }

            restResponse.setResponseBody(responseEntity.getBody());
            restResponse.setStatusCode(String.valueOf(HttpStatus.OK));
            restResponse.setResponseMsg("请求成功");
            restResponse.setResponseCode("000");
            requirementInfo = responseEntity.getBody();

            //2.1 给需求中的Member分配权限
            List<String> members = new ArrayList<String>();
            for (int i = 0; i < roleMembers.size(); i++) {
                members.addAll(roleMembers.get(i).getMemberIds());

            }
            LOG.info("需求中将要被分配的memberIds:" + members.toString());
            StaffAuthUtil.assignAuth(this.restTemplate, new AssignAuthRequest(createReqmntRequest.getProjId(), createReqmntRequest.getCreatorId(), members, requirementInfo.getReqmntId(), AuthEnum.RMEMBER.getCode()));
            LOG.info("新建需求：{}并给成员分配权限成功!", requirementInfo.getReqmntId());
        }


        //3.保存requirement标签信息
        if (createReqmntRequest.getTags() != null && !createReqmntRequest.getTags().isEmpty()) {
            List<ReqmntTag> reqmntTags = createReqmntRequest.getTags();
            for (int i = 0; i < reqmntTags.size(); i++) {
                reqmntTags.get(i).setReqmntId(requirementInfo.getReqmntId());
            }
            String saveRTagsUrl = config.getSaveRTagsUrl();
            try {
                List<ReqmntTag> rTags = restTemplate.postForObject(saveRTagsUrl, reqmntTags, List.class);
            } catch (Exception ex) {
                throw new RuntimeException("调用SAPI获取项目标签信息保存数据失败.");
            }
        }
        //4.保存项目模块信息

        //5.保存项目checklist信息
        if (createReqmntRequest.getrCheckLists() != null && !createReqmntRequest.getrCheckLists().isEmpty()) {
            SaveRCheckListRequest saveRCheckListRequest = new SaveRCheckListRequest();
            List<ReqmntCheckList> reqmntCheckLists = createReqmntRequest.getrCheckLists();
            for (int i = 0; i < reqmntCheckLists.size(); i++) {
                reqmntCheckLists.get(i).setReqmntId(requirementInfo.getReqmntId());
                reqmntCheckLists.get(i).setAssignerId(createReqmntRequest.getCreatorId());
            }
            saveRCheckListRequest.setStaffId(createReqmntRequest.getCreatorId());
            saveRCheckListRequest.setCheckLists(reqmntCheckLists);
            String saveCheckListsUrl = config.getSaveRCheckListUrl();
            try {
                reqmntCheckLists = restTemplate.postForObject(saveCheckListsUrl, saveRCheckListRequest, List.class);
            } catch (Exception ex) {
                throw new RuntimeException("调用SAPI获取项目checklist信息保存数据失败.");
            }
        }
        //6.保存requirement附件信息
        if (createReqmntRequest.getAttchUrls() != null && !createReqmntRequest.getAttchUrls().isEmpty()) {
            List<ReqmntAttchUrl> reqmntAttchUrls = createReqmntRequest.getAttchUrls();
            for (int i = 0; i < reqmntAttchUrls.size(); i++) {
                reqmntAttchUrls.get(i).setReqmntId(requirementInfo.getReqmntId());
            }
            String saveRAttchUrl = config.getSaveRAttchUrl();
            try {
                List<ReqmntAttchUrl> rAttchUrls = restTemplate.postForObject(saveRAttchUrl, reqmntAttchUrls, List.class);
            } catch (Exception ex) {
                throw new RuntimeException("调用SAPI获取项目附件信息保存数据失败.");
            }
        }
        //response
//        if (restResponse.getStatusCode().equals(HttpStatus.OK.toString())) {
        return restResponse;
//        }
//        throw new BusinessException(restResponse.getResponseCode(), restResponse.getResponseBody().toString());
    }

    @Override
    public RestResponse rtrvReqmntInfo(RtrvReqmntInfoRequest request) {
        RtrvReqmntInfoResponse rtrvReqmntInfoResponse = new RtrvReqmntInfoResponse();
        if (request == null || StringUtils.isEmpty(request.getReqmntId())) {
            restResponse.setStatusCode(String.valueOf(HttpStatus.BAD_REQUEST));
            restResponse.setResponseMsg("Requirement ID should not be empty");
            return restResponse;
        }

        String reqmntInfoUrl = config.getRtrvReqmntInfoUrl();
        ResponseEntity<RequirementInfo> responseEntity = this.restTemplate.postForEntity(reqmntInfoUrl, request, RequirementInfo.class);
        if (responseEntity.getStatusCode() == HttpStatus.NO_CONTENT) {
            // does not exists
            throw new BusinessException(ExceptionEnum.REQMNT_DOES_NOT_EXIST);
        }

        RequirementInfo requirementInfo = responseEntity.getBody();
        restResponse.setStatusCode(String.valueOf(HttpStatus.OK));
        restResponse.setResponseBody(requirementInfo);
        restResponse.setResponseCode("000");
        restResponse.setResponseMsg("Success");
        rtrvReqmntInfoResponse.setReqmntInfo(requirementInfo);

        //retriveFunctionLable

        ParameterizedTypeReference funcLabelTypeReference = new ParameterizedTypeReference<FunctionLabel>() {
        };

        Map mapLabel = new HashMap();
        mapLabel.put("labelId", requirementInfo.getFunctionLabelId());

        FunctionLabel funcLabel = this.restTemplate.postForObject(config.getRtrvFuncLabelUrl(), mapLabel, FunctionLabel.class);
//        funcLabel.setSubFunctionLabels();
        rtrvReqmntInfoResponse.setLabelDetail(funcLabel);
        //retriveSubFunctionLable
        List<SubFunctionLabel> subFunctionLabels = this.restTemplate.postForObject(config.getFindSubFuncListByIdUrl(), mapLabel, List.class);
//        funcLabel.setSubFunctionLabels();
        rtrvReqmntInfoResponse.setSubFunctionLabels(subFunctionLabels);

        // 查询tag
        ParameterizedTypeReference reqmntTagTypeReference = new ParameterizedTypeReference<List<ReqmntTag>>() {
        };
        List<ReqmntTag> reqmntTags = FetchListUtil.fetch(restTemplate, config.getRtrvReqmntTagsUrl(), requirementInfo.getReqmntId(), reqmntTagTypeReference);

        List<String> tagIdList = new ArrayList<>();
        if (reqmntTags != null && !reqmntTags.isEmpty()) {
            for (int i = 0; i < reqmntTags.size(); i++) {
                tagIdList.add(reqmntTags.get(i).getTagId());
            }
        }


        ParameterizedTypeReference tagTypeReference = new ParameterizedTypeReference<List<Tag>>() {
        };
        Map<String, List<String>> idListMap = new HashMap<String, List<String>>();
        idListMap.put("tagIds", tagIdList);

        List<Tag> tagList = FetchListUtil.fetch(restTemplate, config.getRtrvTagsByIdsUrl(), idListMap, tagTypeReference);
        rtrvReqmntInfoResponse.setTagList(tagList);

        // 查询members
        try {

            String modelId = requirementInfo.getModelId();
            ParameterizedTypeReference modelRoleRef = new ParameterizedTypeReference<List<ModelRole>>() {
            };
            Map modelIdMap = new HashMap();
            modelIdMap.put("modelId", modelId);
            RtrvModelByIdResponse MRresp = restTemplate.postForObject(config.getRtrvModelRoleByModelIdUrl(), modelIdMap, RtrvModelByIdResponse.class);
            List<ModelRole> modelRoleList = MRresp.getModelRoles();


            // call reqmnt sapi
            ParameterizedTypeReference parameterizedTypeReference = new ParameterizedTypeReference<List<ReqmntMember>>() {
            };
            List<ReqmntMember> data = FetchListUtil.fetch(restTemplate, config.getRtrvReqmntMembersUrl(), requirementInfo.getReqmntId(), parameterizedTypeReference);
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

            rtrvReqmntInfoResponse.setMembers(roleAndMembers);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ExceptionEnum.REQMNT_QUERY_MEMBER_FAIELD);
        }

        // query CheckList
        try {
            ParameterizedTypeReference typeReference = new ParameterizedTypeReference<List<ReqmntCheckList>>() {
            };
            List<ReqmntCheckList> checkLists = FetchListUtil.fetch(restTemplate, config.getRtrvReqmntCheckListUrl(), requirementInfo.getReqmntId(), typeReference);
            if (checkLists != null || !checkLists.isEmpty()) {
                List<String> assigneeIds = new ArrayList<>();
                List<String> assignerIds = new ArrayList<>();
                for (ReqmntCheckList checklist : checkLists) {
                    assigneeIds.add(checklist.getAssigneeId());
                    assignerIds.add(checklist.getAssignerId());
                }
                Map<String, Object> params1 = new HashMap<>();
                params1.put("staffIdList", assigneeIds);

                typeReference = new ParameterizedTypeReference<List<Staff>>() {
                };
                List<Staff> assigneedStaffList = FetchListUtil.fetch(restTemplate, config.getRtrvStaffsByIdsUrl(), params1, typeReference);
                params1.put("staffIdList", assignerIds);
                List<Staff> assignerdStaffList = FetchListUtil.fetch(restTemplate, config.getRtrvStaffsByIdsUrl(), params1, typeReference);
                if (assignerdStaffList.size() != 0) {
                    for (int i = 0; i < checkLists.size(); i++) {
                        checkLists.get(i).setAssigner(assignerdStaffList.get(i));
                        checkLists.get(i).setAssignee(assigneedStaffList.get(i));
                    }
                    rtrvReqmntInfoResponse.setCheckLists(checkLists);
                } else {
                    rtrvReqmntInfoResponse.setCheckLists(new ArrayList<>());
                }
            } else {
                rtrvReqmntInfoResponse.setCheckLists(new ArrayList<>());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ExceptionEnum.REQMNT_QUERY_CHECKLIST_FAIELD);
        }

        // query attchs
        ParameterizedTypeReference typeReference = new ParameterizedTypeReference<List<ReqmntAttchUrl>>() {
        };
        List<ReqmntAttchUrl> attchUrls = FetchListUtil.fetch(restTemplate, config.getRtrvReqmntAttchsUrl(), requirementInfo.getReqmntId(), typeReference);
        StringBuilder stringBuilder = new StringBuilder("");

        if (attchUrls.size() != 0) {
            for (int i = 0; i < attchUrls.size(); i++) {
                stringBuilder.append(attchUrls.get(i).getAttachmentId());
                stringBuilder.append(",");
            }
            String ids = stringBuilder.toString();
            ids = ids.substring(0, ids.length() - 1);

            ParameterizedTypeReference attchInfotTypeReference = new ParameterizedTypeReference<List<AttchInfo>>() {
            };
            String attchInfoUrl = config.getRtrvAttchInfoListUrl();
            System.out.println(attchInfoUrl);
            ResponseEntity<RestResponse> attchInfos = restTemplate.getForEntity(attchInfoUrl + ids, RestResponse.class);
//        ResponseEntity<List> attchInfos = restTemplate.postForEntity(attchInfoUrl,null,List.class);
            rtrvReqmntInfoResponse.setAttchInfos((List<AttchInfo>) attchInfos.getBody().getResponseBody());
        }
        //查询story列表
        RtrvStoryListRequest rtrvStoryListRequest = new RtrvStoryListRequest();
        //项目需求列表分页信息按默认值处理
        Integer page = Integer.parseInt(config.getStoryListPage());
        LOG.info("需求分页参数, page:{}, pageSize:{}", page, config.getStoryListPageSize());
        Integer pageSize = Integer.parseInt(config.getStoryListPageSize());

        rtrvStoryListRequest.setPage(page);
        rtrvStoryListRequest.setPageSize(pageSize);
        rtrvStoryListRequest.setReqmntId(request.getReqmntId());
        String storyInfoListUrl = config.getRtrvStoryInfoListUrl();
        try {
            LOG.info("storyInfoListUrl为：" + storyInfoListUrl);
            LOG.info("rtrvStoryListRequest(ReqmntId)：" + rtrvStoryListRequest.getReqmntId());
            ResponseEntity<RtrvStoryListResponse> rtrvStoryListResponse = this.restTemplate.postForEntity(storyInfoListUrl, rtrvStoryListRequest, RtrvStoryListResponse.class);
//            restResponse = this.restTemplate.postForObject(storyInfoListUrl, rtrvStoryListRequest, RestResponse.class);
            //加上评论list
            String rtrvCommentInfosUrl = config.getRtrvCommentInfosUrl();
            RtrvCommentInfosRequest rtrvCommentInfosRequest = new RtrvCommentInfosRequest();
            for (int i = 0; i < rtrvStoryListResponse.getBody().getStories().size(); i++) {
                Story story = rtrvStoryListResponse.getBody().getStories().get(i);
                rtrvCommentInfosRequest.setProjId(story.getProjId());
                rtrvCommentInfosRequest.setSubjectId(story.getStoryId());
                ParameterizedTypeReference tReference = new ParameterizedTypeReference<List<CommentDetail>>() {
                };
                List<CommentDetail> commentDetails = FetchListUtil.fetch(restTemplate, rtrvCommentInfosUrl, rtrvCommentInfosRequest, tReference);
                for (int j = 0; j < commentDetails.size(); j++) {
                    //创建者返回对象
                    String staffUrl = config.getRtrvStaffInfoUrl();
                    String creatorId = commentDetails.get(j).getComment().getCreatorId();
                    com.mdvns.mdvn.common.beans.Staff staff = restTemplate.postForObject(staffUrl, creatorId, com.mdvns.mdvn.common.beans.Staff.class);
                    commentDetails.get(j).getComment().setCreatorInfo(staff);
                    //被@的人返回对象
                    if (commentDetails.get(j).getComment().getReplyId() != null) {
                        String passiveAt = commentDetails.get(j).getReplyDetail().getCreatorId();
                        com.mdvns.mdvn.common.beans.Staff passiveAtInfo = restTemplate.postForObject(staffUrl, passiveAt, com.mdvns.mdvn.common.beans.Staff.class);
                        commentDetails.get(j).getReplyDetail().setCreatorInfo(passiveAtInfo);
                    }
                }
                story.setCommentDetails(commentDetails);
            }

            LOG.info("rtrvStoryListResponse为：" + rtrvStoryListResponse);
            LOG.info("rtrvStoryListResponse.getBody()为：" + rtrvStoryListResponse.getBody());

            for (int j = 0; j < rtrvStoryListResponse.getBody().getStories().size(); j++) {
                Story storyInfo = rtrvStoryListResponse.getBody().getStories().get(j);
                String creatorId = storyInfo.getCreatorId();
                // call staff sapi
                List staffs = new ArrayList();
                staffs.add(creatorId);
                Map<String, Object> prams = new HashMap<>();
                prams.put("staffIdList", staffs);
                ParameterizedTypeReference tReference = new ParameterizedTypeReference<List<Staff>>() {
                };
                LOG.info("获取创建者信息的url为：" + config.getRtrvStaffsByIdsUrl());
                List<Staff> staffList = (List<Staff>) FetchListUtil.fetch(restTemplate, config.getRtrvStaffsByIdsUrl(), prams, tReference);
                storyInfo.setCreatorInfo(staffList.get(0));

                // 查询members(不重复的个数)
                try {
                    // call story sapi
                    RtrvStoryDetailRequest rtrvStoryDetailRequest = new RtrvStoryDetailRequest();
                    rtrvStoryDetailRequest.setStaffId(request.getStaffId());
                    rtrvStoryDetailRequest.setStoryId(storyInfo.getStoryId());
                    ParameterizedTypeReference storyReference = new ParameterizedTypeReference<List<StoryRoleMember>>() {
                    };
                    List<StoryRoleMember> storyRoleMembers = FetchListUtil.fetch(restTemplate, config.getRtrvSRoleMembersUrl(), rtrvStoryDetailRequest, storyReference);
                    //选出不同的角色
                    List<String> memberIds = new ArrayList<String>();
                    for (int i = 0; i < storyRoleMembers.size(); i++) {
                        String id = storyRoleMembers.get(i).getStaffId();
                        if (!memberIds.isEmpty() && memberIds.contains(id)) {
                            continue;
                        }
                        memberIds.add(id);
                    }
                    storyInfo.setMemberCunt(memberIds.size());
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new BusinessException(ExceptionEnum.REQMNT_QUERY_MEMBER_FAIELD);
                }
            }
            rtrvReqmntInfoResponse.setRtrvStoryListResponse(rtrvStoryListResponse.getBody());
        } catch (Exception ex) {
            throw new BusinessException(ExceptionEnum.PROJECT_DETAIL_STORY_NOT_RTRV);
        }

        //获取用户权限信息
        List<StaffAuthInfo> staffAuthInfos = StaffAuthUtil.rtrvStaffAuthInfo(this.restTemplate, requirementInfo.getProjId(), requirementInfo.getReqmntId(), request.getStaffId());
        rtrvReqmntInfoResponse.setStaffAuthInfo(staffAuthInfos);
        LOG.info("获取需求中用户权限成功" + "projId:" + requirementInfo.getProjId() + "reqmntId:" + requirementInfo.getReqmntId() + "staffId:" + request.getStaffId());
        if (staffAuthInfos != null) {
            for (int i = 0; i < staffAuthInfos.size(); i++) {
                LOG.info("用户在需求中的权限为：{}", staffAuthInfos.get(i).toString());
            }
        }
        restResponse.setResponseBody(rtrvReqmntInfoResponse);

        return restResponse;
    }

    @Override
    public RestResponse updateReqmntInfo(UpdateReqmntInfoRequest request) {
        // 参数检查
        if (request == null) {
            throw new BusinessException(ExceptionEnum.PARAMS_EXCEPTION);
        }
        //先判断过程方法子模块是新建还是选取（访问model模块）
        if (request.getFunctionLabel() != null) {
            LOG.info("更改reqmnt的时候传入的过程方法模块Id："+request.getFunctionLabel().getLabelId());
            LOG.info("更改reqmnt的时候传入的过程方法模块Name："+request.getFunctionLabel().getName());
            JudgeLabelIdRequest judgeLabelIdRequest = new JudgeLabelIdRequest();
            judgeLabelIdRequest.setCreatorId(request.getReqmntInfo().getCreatorId());
            judgeLabelIdRequest.setFunctionLabel(request.getFunctionLabel());
            String judgeLabelIdUrl = config.getJudgeLabelIdUrl();
            SubFunctionLabel subFuncLabel = new SubFunctionLabel();
            try {
                subFuncLabel = restTemplate.postForObject(judgeLabelIdUrl, judgeLabelIdRequest, SubFunctionLabel.class);
            } catch (Exception ex) {
                throw new BusinessException(ExceptionEnum.SAPI_EXCEPTION);
            }
            request.setFunctionLabel(subFuncLabel);
            request.getReqmntInfo().setFunctionLabelId(subFuncLabel.getLabelId());
        }

        final String url = config.getUpdateReqmntInfoUrl();
        ResponseEntity<Boolean> responseEntity = restTemplate.postForEntity(url, request, Boolean.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return rtrvReqmntInfo(new RtrvReqmntInfoRequest(request.getReqmntInfo().getReqmntId()));
        } else {
            throw new BusinessException(ExceptionEnum.SAPI_EXCEPTION);
        }
    }

    /**
     * saff list query by id
     *
     * @param staffs
     * @param id
     * @return
     */
    private static Staff getById(List<Staff> staffs, String id) {
        for (Staff staff : staffs) {
            if (staff.getStaffId().equals(id)) {
                return staff;
            }
        }

        return null;
    }


}
