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

    @Autowired
    private RequirementInfo requirementInfo;

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
        CreateReqmntResponse createReqmntResponse = new CreateReqmntResponse();

        //1.先保存requirement基本信息（获取reqmntId）

        if (createReqmntRequest == null || StringUtils.isEmpty(createReqmntRequest.getCreatorId()) ||
                StringUtils.isEmpty(createReqmntRequest.getSummary()) || StringUtils.isEmpty(createReqmntRequest.getDescription()) || StringUtils.isEmpty(createReqmntRequest.getFunctionLabelId())
                || StringUtils.isEmpty(createReqmntRequest.getProjId()) || StringUtils.isEmpty(createReqmntRequest.getModelId())) {
            throw new NullPointerException("Mandatory fields should not be empty for createReqmntRequest");
        }


        String saveReqmntUrl = config.getSaveReqmntUrl();
        ResponseEntity<RequirementInfo> responseEntity = null;
        responseEntity = restTemplate.postForEntity(saveReqmntUrl, createReqmntRequest, RequirementInfo.class);
        restResponse.setResponseBody(responseEntity.getBody());
        restResponse.setStatusCode(String.valueOf(HttpStatus.OK));
        restResponse.setResponseMsg("请求成功");
        restResponse.setResponseCode("000");
        requirementInfo = responseEntity.getBody();

        //1.1 给需求创建者分配权限
        StaffAuthUtil.assignAuthForCreator(this.restTemplate, createReqmntRequest.getProjId(), requirementInfo.getReqmntId(), createReqmntRequest.getCreatorId(), AuthEnum.Leader.getCode() );
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

        requirementInfo = responseEntity.getBody();
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
        rtrvReqmntInfoResponse.setLabelDetail(funcLabel);


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

                for (int i = 0; i < checkLists.size(); i++) {
                    checkLists.get(i).setAssigner(assignerdStaffList.get(i));
                    checkLists.get(i).setAssignee(assigneedStaffList.get(i));
                }

                rtrvReqmntInfoResponse.setCheckLists(checkLists);
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
            ResponseEntity<RtrvStoryListResponse> rtrvStoryListResponse = this.restTemplate.postForEntity(storyInfoListUrl, rtrvStoryListRequest, RtrvStoryListResponse.class);
//            restResponse = this.restTemplate.postForObject(storyInfoListUrl, rtrvStoryListRequest, RestResponse.class);
            rtrvReqmntInfoResponse.setRtrvStoryListResponse(rtrvStoryListResponse.getBody());
        } catch (Exception ex) {
            throw new BusinessException(ExceptionEnum.PROJECT_DETAIL_STORY_NOT_RTRV);
        }

        //获取用户权限信息
        List<StaffAuthInfo> staffAuthInfos = StaffAuthUtil.rtrvStaffAuthInfo(this.restTemplate, requirementInfo.getProjId(), requirementInfo.getReqmntId(), request.getStaffId());
        rtrvReqmntInfoResponse.setStaffAuthInfos(staffAuthInfos);
        LOG.info("获取需求中用户权限成功" + "projId:" + requirementInfo.getProjId() + "reqmntId:" + requirementInfo.getReqmntId() + "staffId:" + request.getStaffId());
        restResponse.setResponseBody(rtrvReqmntInfoResponse);

        return restResponse;
    }

    @Override
    public RestResponse updateReqmntInfo(UpdateReqmntInfoRequest request) {
        // 参数检查
        if (request == null) {
            throw new BusinessException(ExceptionEnum.PARAMS_EXCEPTION);
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


//    /**
//     * 调用sapi更改project
//     *
//     * @param updateProjectDetailRequest
//     * @return
//     */
//    @Override
//    public restResponse updateProject(UpdateProjectDetailRequest updateProjectDetailRequest) {
//        UpdateProjectDetailResponse updateProjectDetailResponse = new UpdateProjectDetailResponse();
//        ProjectDetail projectDetail = new ProjectDetail();
//        RestTemplate restTemplate = new RestTemplate();
////        String projId = updateProjectRequest.getProjId();
//        if (updateProjectDetailRequest == null || updateProjectDetailRequest.getProjId() == null) {
//            throw new NullPointerException("updateProjectRequest 或项目Id不能为空");
//        }
//        //1.先判断是否更改项目基本信息
//        proj.setProjId(updateProjectDetailRequest.getProjId());
//        if (!StringUtils.isEmpty(updateProjectDetailRequest.getName()) ) {
//            proj.setName(updateProjectDetailRequest.getName());
//        }
//        if (!StringUtils.isEmpty(updateProjectDetailRequest.getDescription())) {
//            proj.setDescription(updateProjectDetailRequest.getDescription());
//        }
//        if (!StringUtils.isEmpty(updateProjectDetailRequest.getStartDate())) {
//            proj.setStartDate(updateProjectDetailRequest.getStartDate());
//
//        }
//        if (!StringUtils.isEmpty(updateProjectDetailRequest.getEndDate())) {
//            proj.setEndDate(updateProjectDetailRequest.getEndDate());
//
//        }
//        if (!StringUtils.isEmpty(updateProjectDetailRequest.getPriority())) {
//            proj.setPriority(updateProjectDetailRequest.getPriority());
//
//        }
//        if (!StringUtils.isEmpty(updateProjectDetailRequest.getContingency())) {
//            proj.setContingency(updateProjectDetailRequest.getContingency());
//        }
//        if (!StringUtils.isEmpty(updateProjectDetailRequest.getStatus())) {
//            proj.setStatus(updateProjectDetailRequest.getStatus());
//
//        }
//        //之后ragStatus需要后台计算以后传给前台
////        if (!StringUtils.isEmpty(updateProjectDetailRequest.getRagStatus())) {
////            proj.setRagStatus(updateProjectDetailRequest.getRagStatus());
////        }
//        String updateProjBaseInfoUrl = config.getUpdateProjBaseInfoUrl();
//        ReqirementInfo pro = restTemplate.postForObject(updateProjBaseInfoUrl, proj, ReqirementInfo.class);
//        projectDetail.setProject(pro);
//        //2.判断是否更改项目负责人信息
//        if (updateProjectDetailRequest.getLeaders()!=null && !updateProjectDetailRequest.getLeaders().isEmpty()) {
//            UpdatePLeadersRequest updatePLeadersRequest = new UpdatePLeadersRequest();
//            updatePLeadersRequest.setProjId(updateProjectDetailRequest.getProjId());
//            updatePLeadersRequest.setLeaders(updateProjectDetailRequest.getLeaders());
//            String updateProjLeadersUrl = config.getUpdateProjLeadersUrl();
//            try {
//                List<Staff> pLeaders = restTemplate.postForObject(updateProjLeadersUrl, updatePLeadersRequest, List.class);
//                projectDetail.setLeaders(pLeaders);
//            } catch (Exception ex) {
//                throw new RuntimeException("调用SAPI更改项目负责人信息保存数据失败.");
//            }
//        }
//        //3.判断是否更改项目标签信息
//        if (updateProjectDetailRequest.getTags()!=null && !updateProjectDetailRequest.getTags().isEmpty()) {
//            UpdatePTagsRequest updatePTagsRequest = new UpdatePTagsRequest();
//            updatePTagsRequest.setProjId(updateProjectDetailRequest.getProjId());
//            updatePTagsRequest.setTags(updateProjectDetailRequest.getTags());
//            String updateProjTagsUrl = config.getUpdateProjTagsUrl();
//            try {
//                List<Tag> pTags = restTemplate.postForObject(updateProjTagsUrl, updatePTagsRequest, List.class);
//                projectDetail.setTags(pTags);
//            } catch (Exception ex) {
//                throw new RuntimeException("调用SAPI更改项目标签信息保存数据失败.");
//            }
//        }
//
//        //4.判断是否更改项目模块信息
//        if (updateProjectDetailRequest.getModels()!=null && !updateProjectDetailRequest.getModels().isEmpty()) {
//            UpdatePModelsRequest updatePModelsRequest = new UpdatePModelsRequest();
//            updatePModelsRequest.setProjId(updateProjectDetailRequest.getProjId());
//            updatePModelsRequest.setModels(updateProjectDetailRequest.getModels());
//            String updateProjModelsUrl = config.getUpdateProjModelsUrl();
//            try {
//                List<Model> pModels = restTemplate.postForObject(updateProjModelsUrl, updatePModelsRequest, List.class);
//                projectDetail.setModels(pModels);
//            } catch (Exception ex) {
//                throw new RuntimeException("调用SAPI更改项目模块信息保存数据失败.");
//            }
//
//        }
//
//        //5.判断是否更改项目checklist信息
//        if (updateProjectDetailRequest.getCheckLists()!=null && !updateProjectDetailRequest.getCheckLists().isEmpty()) {
//            UpdatePCheckListsRequest updatePCheckListsRequest = new UpdatePCheckListsRequest();
//            updatePCheckListsRequest.setProjId(updateProjectDetailRequest.getProjId());
//            updatePCheckListsRequest.setStaffId(updateProjectDetailRequest.getStaffId());
//            updatePCheckListsRequest.setCheckLists(updateProjectDetailRequest.getCheckLists());
//            String updateProjChecklistsUrl = config.getUpdateProjChecklistsUrl();
//            try {
//                List<ReqmntChecklist> pChecklists = restTemplate.postForObject(updateProjChecklistsUrl, updatePCheckListsRequest, List.class);
//                //通过UUid遍历保存checklistId
//                UpdatePCheckListsRequest pCheckListsRequest = new UpdatePCheckListsRequest();
//                pCheckListsRequest.setProjId(updateProjectDetailRequest.getProjId());
//                pCheckListsRequest.setCheckLists(pChecklists);
//                String checklistsListByUuIdUrl = config.getChecklistsListByUuIdUrl();
//                try {
//                    List<ProjChecklistsDetail> checklists = restTemplate.postForObject(checklistsListByUuIdUrl, pCheckListsRequest, List.class);
//                    projectDetail.setCheckLists(checklists);
//                } catch (Exception ex) {
//                    throw new RuntimeException("调用SAPI获取项目checklistId信息保存数据失败.");
//                }
//            } catch (Exception ex) {
//                throw new RuntimeException("调用SAPI更改项目checklist信息保存数据失败.");
//            }
//        }
//
//        //6.判断是否更改项目附件信息
//        if (updateProjectDetailRequest.getAttchUrls()!=null && !updateProjectDetailRequest.getAttchUrls().isEmpty()) {
//            UpdatePAttchUrlsRequest updatePAttchUrlsRequest = new UpdatePAttchUrlsRequest();
//            updatePAttchUrlsRequest.setProjId(updateProjectDetailRequest.getProjId());
//            updatePAttchUrlsRequest.setAttchUrls(updateProjectDetailRequest.getAttchUrls());
//            String updateProjAttchUrlsUrl = config.getUpdateProjAttchUrlsUrl();
//            try {
//                List<ReqmntAttchUrl> pAttchUrls = restTemplate.postForObject(updateProjAttchUrlsUrl, updatePAttchUrlsRequest, List.class);
//                projectDetail.setAttchUrls(pAttchUrls);
//            } catch (Exception ex) {
//                throw new RuntimeException("调用SAPI更改项目附件信息保存数据失败.");
//            }
//        }
//
//        updateProjectDetailResponse.setProjectDetail(projectDetail);
//        restResponse.setResponseBody(updateProjectDetailResponse);
//        restResponse.setStatusCode("200");
//        restResponse.setResponseMsg("请求成功");
//        restResponse.setResponseCode("000");
//
//        return restResponse;
//    }

//    /**
//     * 获取某个项目详细信息
//     * @param rtrvProjectDetailRequest
//     * @return
//     */
//    @Override
//    public restResponse rtrvProjectInfo(RtrvProjectDetailRequest rtrvProjectDetailRequest) {
//        RtrvProjectDetailResponse rtrvProjectDetailResponse = new RtrvProjectDetailResponse();
//        ProjectDetail projectDetail = new ProjectDetail();
//        if (rtrvProjectDetailRequest == null || rtrvProjectDetailRequest.getProjId() == null) {
//            throw new NullPointerException("rtrvProjectDetailRequest 或项目Id不能为空");
//        }
//        //1.先获取项目基本信息
//        String rtrvProjBaseInfoUrl = config.getRtrvProjBaseInfoUrl();
//        proj = restTemplate.postForObject(rtrvProjBaseInfoUrl, rtrvProjectDetailRequest, ReqirementInfo.class);
//        if (null == proj) {
//            LOG.error("获取项目基本信息不存在.");
//            throw new BusinessException("获取项目基本信息不存在.");
//        }
//        projectDetail.setProject(proj);
//        //2.获取项目负责人信息
//        String rtrvProjLedersUrl = config.getRtrvProjLedersUrl();
//        List<Staff> projLeaders = restTemplate.postForObject(rtrvProjLedersUrl, rtrvProjectDetailRequest, List.class);
//        projectDetail.setLeaders(projLeaders);
//        //3.获取项目标签信息
//        String rtrvProjTagsUrl = config.getRtrvProjTagsUrl();
//        List<Tag> projTags = restTemplate.postForObject(rtrvProjTagsUrl, rtrvProjectDetailRequest, List.class);
//        projectDetail.setTags(projTags);
//        //4.获取项目模型信息
//        String rtrvProjModelsUrl = config.getRtrvProjModelsUrl();
//        List<Model> projModels = restTemplate.postForObject(rtrvProjModelsUrl, rtrvProjectDetailRequest, List.class);
//        projectDetail.setModels(projModels);
//        //5.获取项目checklist信息
//        String rtrvProjCheckListsUrl = config.getRtrvProjCheckListsUrl();
//        List<ProjChecklistsDetail> projChecklists = restTemplate.postForObject(rtrvProjCheckListsUrl, rtrvProjectDetailRequest, List.class);
//        projectDetail.setCheckLists(projChecklists);
//        //6.获取项目附件信息
//        String rtrvProjAttUrlsUrl = config.getRtrvProjAttUrlsUrl();
//        List<ReqmntAttchUrl> reqmntAttchUrls = restTemplate.postForObject(rtrvProjAttUrlsUrl, rtrvProjectDetailRequest, List.class);
//        projectDetail.setAttchUrls(reqmntAttchUrls);
//
//        rtrvProjectDetailResponse.setProjectDetail(projectDetail);
//        restResponse.setResponseBody(rtrvProjectDetailResponse);
//        restResponse.setStatusCode("200");
//        restResponse.setResponseMsg("请求成功");
//        restResponse.setResponseCode("000");
//
//        return restResponse;
//    }
}
