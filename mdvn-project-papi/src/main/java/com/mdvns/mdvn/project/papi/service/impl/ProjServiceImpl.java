package com.mdvns.mdvn.project.papi.service.impl;

import com.mdvns.mdvn.common.beans.*;
import com.mdvns.mdvn.common.beans.exception.BusinessException;
import com.mdvns.mdvn.common.beans.exception.ExceptionEnum;
import com.mdvns.mdvn.common.enums.AuthEnum;
import com.mdvns.mdvn.common.utils.FetchListUtil;
import com.mdvns.mdvn.common.utils.StaffAuthUtil;
import com.mdvns.mdvn.project.papi.config.ProjConfig;
import com.mdvns.mdvn.project.papi.domain.*;
import com.mdvns.mdvn.project.papi.domain.Model;
import com.mdvns.mdvn.project.papi.domain.Staff;
import com.mdvns.mdvn.project.papi.domain.Tag;
import com.mdvns.mdvn.project.papi.service.IProjService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjServiceImpl implements IProjService {

    private static final Logger LOG = LoggerFactory.getLogger(ProjServiceImpl.class);

    @Autowired
    private ProjConfig config;

    @Autowired
    private Project proj;

    @Autowired
    private RestResponse restResponse;

    @Autowired
    private RestTemplate restTemplate;



    /**
     * 获取project列表信息
     *
     * @param rtrvProjectListRequest
     * @return
     */
    public ResponseEntity<?> rtrvProjInfoList(RtrvProjectListRequest rtrvProjectListRequest) {
        rtrvProjectListRequest.setPage(rtrvProjectListRequest.getPage() - 1);
        String projInfoListUrl = config.getRtrvProjInfoListUrl();
        restResponse = this.restTemplate.postForObject(projInfoListUrl, rtrvProjectListRequest, RestResponse.class);
        ResponseEntity<RestResponse> responseEntity = null;
        if (!restResponse.getStatusCode().equals(HttpStatus.OK.toString())) {
            throw new BusinessException(ExceptionEnum.SAPI_EXCEPTION);
        }
        responseEntity = new ResponseEntity<RestResponse>(restResponse, HttpStatus.OK);
        return responseEntity;
    }


    /**
     * 调用sapi创建project
     * 1.创建保存project信息
     * 2.返回Project整个信息
     *
     * @param createProjectRequest
     * @return
     */

    @Override
    public RestResponse createProject(CreateProjectRequest createProjectRequest) {
        //1.先保存项目基本信息（获取projId）
        String saveProjectBaseUrl = config.getSaveProjectBaseUrl();
        ResponseEntity<Project> responseEntity = null;
        try {
            responseEntity = restTemplate.postForEntity(saveProjectBaseUrl, createProjectRequest, Project.class);
        } catch (Exception ex) {
            throw new BusinessException(ExceptionEnum.PROJECT_BASEINFO_NOT_CREATE);
        }
        restResponse.setResponseBody(responseEntity.getBody());
        restResponse.setStatusCode("200");
        restResponse.setResponseMsg("请求成功");
        restResponse.setResponseCode("000");
        proj = responseEntity.getBody();
        String projId = proj.getProjId();
        //1.1 给项目创建人赋权限
        String creatorId = createProjectRequest.getStaffId();
        List<String> asignee = new ArrayList<String>();
        asignee.add(creatorId);

        StaffAuthUtil.assignAuth(this.restTemplate,new AssignAuthRequest(projId, creatorId, asignee, projId, AuthEnum.BOSS.getCode()));


        //2.保存项目负责人信息
        if (createProjectRequest.getLeaders() != null && !createProjectRequest.getLeaders().isEmpty()) {
            List<ProjLeaders> projLeaders = createProjectRequest.getLeaders();
            for (int i = 0; i < projLeaders.size(); i++) {
                projLeaders.get(i).setProjId(projId);
            }
            String savePLeadersUrl = config.getSavePLeadersUrl();
            try {
                List<ProjLeaders> pLeaders = restTemplate.postForObject(savePLeadersUrl, projLeaders, List.class);
            } catch (Exception ex) {
                throw new BusinessException(ExceptionEnum.PROJECT_STAFF_NOT_CREATE);
            }

            //2.1 给项目leader分配权限
            List<String> leaders = new ArrayList<String>();
            for (int i = 0; i <projLeaders.size() ; i++) {
                leaders.add(projLeaders.get(i).getStaffId());
            }
            StaffAuthUtil.assignAuth(this.restTemplate, new AssignAuthRequest(projId, createProjectRequest.getStaffId(), leaders, projId, AuthEnum.Leader.getCode()));
            LOG.info("创建项目，给leader分配权限成功!");
        }
        //3.保存项目标签信息
        if (createProjectRequest.getTags() != null && !createProjectRequest.getTags().isEmpty()) {
            List<ProjTags> projTags = createProjectRequest.getTags();
            for (int i = 0; i < projTags.size(); i++) {
                projTags.get(i).setProjId(projId);
            }
            String savePTagsUrl = config.getSavePTagsUrl();
            try {
                List<ProjTags> pTags = restTemplate.postForObject(savePTagsUrl, projTags, List.class);
            } catch (Exception ex) {
                throw new BusinessException(ExceptionEnum.PROJECT_TAG_NOT_CREATE);
            }
        }
        //4.保存项目模块信息
        if (createProjectRequest.getModels() != null && !createProjectRequest.getModels().isEmpty()) {
            List<ProjModels> projModels = createProjectRequest.getModels();
            for (int i = 0; i < projModels.size(); i++) {
                projModels.get(i).setProjId(projId);
            }
            String savePModelsUrl = config.getSavePModelsUrl();
            try {
                List<ProjTags> pModels = restTemplate.postForObject(savePModelsUrl, projModels, List.class);
            } catch (Exception ex) {
                throw new BusinessException(ExceptionEnum.PROJECT_MODEL_NOT_CREATE);
            }
        }
        //5.保存项目checklist信息
        if (createProjectRequest.getCheckLists() != null && !createProjectRequest.getCheckLists().isEmpty()) {
            SavePCheckListsRequest savePCheckListsRequest = new SavePCheckListsRequest();
            List<ProjChecklists> projChecklists = createProjectRequest.getCheckLists();
            for (int i = 0; i < projChecklists.size(); i++) {
                projChecklists.get(i).setProjId(projId);
                projChecklists.get(i).setAssignerId(createProjectRequest.getStaffId());
            }
            savePCheckListsRequest.setStaffId(createProjectRequest.getStaffId());
            savePCheckListsRequest.setCheckLists(projChecklists);
            String saveCheckListsUrl = config.getSaveCheckListsUrl();
            try {
                List<ProjChecklists> checklistsList = restTemplate.postForObject(saveCheckListsUrl, savePCheckListsRequest, List.class);
            } catch (Exception ex) {
                throw new BusinessException(ExceptionEnum.PROJECT_CHECKLIST_NOT_CREATE);
            }
        }
        //6.保存项目附件信息
        if (createProjectRequest.getAttchUrls() != null && !createProjectRequest.getAttchUrls().isEmpty()) {
            List<ProjAttchUrls> projAttchUrls = createProjectRequest.getAttchUrls();
            for (int i = 0; i < projAttchUrls.size(); i++) {
                projAttchUrls.get(i).setProjId(projId);
//                projAttchUrls.get(i).setYxbz("Y");
            }
            String savePAttchUrlsUrl = config.getSavePAttchUrlsUrl();
            try {
                List<ProjAttchUrls> pAttchUrls = restTemplate.postForObject(savePAttchUrlsUrl, projAttchUrls, List.class);
            } catch (Exception ex) {
                throw new BusinessException(ExceptionEnum.PROJECT_ATTCHURL_NOT_CREATE);
            }
        }

        return restResponse;
    }

    /**
     * 分配权限
     * @param assignAuthRequest
     */
   /* private void assignAuth(AssignAuthRequest assignAuthRequest) {
        String assignAuthUrl = "http://localhost:10014/mdvn-staff-papi/staff/assignAuth";
        ResponseEntity<StaffAuthInfo[]> responseEntity = null;
        try {
            responseEntity = this.restTemplate.postForEntity(assignAuthUrl, assignAuthRequest, StaffAuthInfo[].class);
        } catch (Exception ex) {
            LOG.error("添加权限失败:{}", ex.getLocalizedMessage());
            throw new BusinessException(ExceptionEnum.UNKNOW_EXCEPTION);
        }

        LOG.info("添加权限完成：{}",responseEntity.getBody().toString());

    }*/

    /**
     * 调用sapi更改project
     * (更新那一块儿，就把这一块儿所有信息传过来，没更新就不传)
     *
     * @param updateProjectDetailRequest
     * @return
     */
    @Override
    public RestResponse updateProject(UpdateProjectDetailRequest updateProjectDetailRequest) {
        UpdateProjectDetailResponse updateProjectDetailResponse = new UpdateProjectDetailResponse();
        ProjectDetail projectDetail = new ProjectDetail();
        RestTemplate restTemplate = new RestTemplate();
//        String projId = updateProjectRequest.getProjId();
        if (updateProjectDetailRequest == null || updateProjectDetailRequest.getProjId() == null) {
            throw new NullPointerException("updateProjectRequest 或项目Id不能为空");
        }
        //1.先判断是否更改项目基本信息
        proj.setProjId(updateProjectDetailRequest.getProjId());
        if (!StringUtils.isEmpty(updateProjectDetailRequest.getName())) {
            proj.setName(updateProjectDetailRequest.getName());
        }
        if (!StringUtils.isEmpty(updateProjectDetailRequest.getDescription())) {
            proj.setDescription(updateProjectDetailRequest.getDescription());
        }
        if (!StringUtils.isEmpty(updateProjectDetailRequest.getStartDate())) {
            proj.setStartDate(updateProjectDetailRequest.getStartDate());

        }
        if (!StringUtils.isEmpty(updateProjectDetailRequest.getEndDate())) {
            proj.setEndDate(updateProjectDetailRequest.getEndDate());

        }
        if (!StringUtils.isEmpty(updateProjectDetailRequest.getPriority())) {
            proj.setPriority(updateProjectDetailRequest.getPriority());

        }
        if (!StringUtils.isEmpty(updateProjectDetailRequest.getContingency())) {
            proj.setContingency(updateProjectDetailRequest.getContingency());
        }
        if (!StringUtils.isEmpty(updateProjectDetailRequest.getStatus())) {
            proj.setStatus(updateProjectDetailRequest.getStatus());

        }
        //之后ragStatus需要后台计算以后传给前台
//        if (!StringUtils.isEmpty(updateProjectDetailRequest.getRagStatus())) {
//            proj.setRagStatus(updateProjectDetailRequest.getRagStatus());
//        }
        String updateProjBaseInfoUrl = config.getUpdateProjBaseInfoUrl();
        try {
            Project pro = restTemplate.postForObject(updateProjBaseInfoUrl, proj, Project.class);
            projectDetail.setProject(pro);
        } catch (Exception ex) {
            throw new BusinessException(ExceptionEnum.PROJECT_BASEINFO_NOT_UPDATE);
        }
        //2.判断是否更改项目负责人信息
        if (updateProjectDetailRequest.getLeaders() != null && !updateProjectDetailRequest.getLeaders().isEmpty()) {
            UpdatePLeadersRequest updatePLeadersRequest = new UpdatePLeadersRequest();
            updatePLeadersRequest.setProjId(updateProjectDetailRequest.getProjId());
            updatePLeadersRequest.setLeaders(updateProjectDetailRequest.getLeaders());
            String updateProjLeadersUrl = config.getUpdateProjLeadersUrl();
            try {
                List<Staff> pLeaders = restTemplate.postForObject(updateProjLeadersUrl, updatePLeadersRequest, List.class);
                projectDetail.setLeaders(pLeaders);
            } catch (Exception ex) {
                throw new BusinessException(ExceptionEnum.PROJECT_STAFF_NOT_UPDATE);
            }
        }
        //3.判断是否更改项目标签信息
        if (updateProjectDetailRequest.getTags() != null && !updateProjectDetailRequest.getTags().isEmpty()) {
            UpdatePTagsRequest updatePTagsRequest = new UpdatePTagsRequest();
            updatePTagsRequest.setProjId(updateProjectDetailRequest.getProjId());
            updatePTagsRequest.setTags(updateProjectDetailRequest.getTags());
            String updateProjTagsUrl = config.getUpdateProjTagsUrl();
            try {
                List<Tag> pTags = restTemplate.postForObject(updateProjTagsUrl, updatePTagsRequest, List.class);
                projectDetail.setTags(pTags);
            } catch (Exception ex) {
                throw new BusinessException(ExceptionEnum.PROJECT_TAG_NOT_UPDATE);
            }
        }

        //4.判断是否更改项目模块信息
        if (updateProjectDetailRequest.getModels() != null && !updateProjectDetailRequest.getModels().isEmpty()) {
            UpdatePModelsRequest updatePModelsRequest = new UpdatePModelsRequest();
            updatePModelsRequest.setProjId(updateProjectDetailRequest.getProjId());
            updatePModelsRequest.setModels(updateProjectDetailRequest.getModels());
            String updateProjModelsUrl = config.getUpdateProjModelsUrl();
            try {
                List<Model> pModels = restTemplate.postForObject(updateProjModelsUrl, updatePModelsRequest, List.class);
                projectDetail.setModels(pModels);
            } catch (Exception ex) {
                throw new BusinessException(ExceptionEnum.PROJECT_MODEL_NOT_UPDATE);
            }
        }
        //5.判断是否更改项目checklist信息
        if (updateProjectDetailRequest.getCheckLists() != null && !updateProjectDetailRequest.getCheckLists().isEmpty()) {
            UpdatePCheckListsRequest updatePCheckListsRequest = new UpdatePCheckListsRequest();
            updatePCheckListsRequest.setProjId(updateProjectDetailRequest.getProjId());
            updatePCheckListsRequest.setStaffId(updateProjectDetailRequest.getStaffId());
            updatePCheckListsRequest.setCheckLists(updateProjectDetailRequest.getCheckLists());
            String updateProjChecklistsUrl = config.getUpdateProjChecklistsUrl();
            try {
                List<ProjChecklists> pChecklists = restTemplate.postForObject(updateProjChecklistsUrl, updatePCheckListsRequest, List.class);
                //获取checklist里面成员的对象信息
                UpdatePCheckListsRequest pCheckListsRequest = new UpdatePCheckListsRequest();
                pCheckListsRequest.setProjId(updateProjectDetailRequest.getProjId());
                pCheckListsRequest.setCheckLists(pChecklists);
                String checklistsListByUuIdUrl = config.getChecklistsListByUuIdUrl();
                try {
                    List<ProjChecklistsDetail> checklists = restTemplate.postForObject(checklistsListByUuIdUrl, pCheckListsRequest, List.class);
                    projectDetail.setCheckLists(checklists);
                } catch (Exception ex) {
                    throw new BusinessException(ExceptionEnum.PROJECT_CHECKLIST_STAFFDETAIL_NOT_RTRV);
                }
            } catch (Exception ex) {
                throw new BusinessException(ExceptionEnum.PROJECT_CHECKLIST_NOT_UPDATE);
            }
        }
        //6.判断是否更改项目附件信息
        if (updateProjectDetailRequest.getAttchUrls() != null && !updateProjectDetailRequest.getAttchUrls().isEmpty()) {
            UpdatePAttchUrlsRequest updatePAttchUrlsRequest = new UpdatePAttchUrlsRequest();
            updatePAttchUrlsRequest.setProjId(updateProjectDetailRequest.getProjId());
            updatePAttchUrlsRequest.setAttchUrls(updateProjectDetailRequest.getAttchUrls());
            String updateProjAttchUrlsUrl = config.getUpdateProjAttchUrlsUrl();
            try {
                ParameterizedTypeReference typeReference = new ParameterizedTypeReference<List<ProjAttchUrls>>() {
                };
                List<ProjAttchUrls> pAttchUrls = FetchListUtil.fetch(restTemplate, updateProjAttchUrlsUrl, updatePAttchUrlsRequest, typeReference);
//                List<ProjAttchUrls> pAttchUrls = restTemplate.postForObject(updateProjAttchUrlsUrl, updatePAttchUrlsRequest, List.class);
                List<String> idList = new ArrayList<String>();
                for (int i = 0; i < pAttchUrls.size(); i++) {
                    Integer attachmentId = pAttchUrls.get(i).getAttachmentId();
                    idList.add(attachmentId.toString());
                }
                String attachmentIds = com.sun.deploy.util.StringUtils.join(idList, ",");
                if (pAttchUrls.size() != 0) {
                    ResponseEntity<RestResponse> responseEntity = restTemplate.getForEntity(config.getRtrvAttchListUrl() + attachmentIds, RestResponse.class);
                    projectDetail.setAttchInfos((List<AttchInfo>)responseEntity.getBody().getResponseBody());
//                projectDetail.setAttchUrls(pAttchUrls);
                }

            } catch (Exception ex) {
                throw new BusinessException(ExceptionEnum.PROJECT_ATTCHURL_NOT_UPDATE);
            }
        }
        updateProjectDetailResponse.setProjectDetail(projectDetail);
        restResponse.setResponseBody(updateProjectDetailResponse);
        restResponse.setStatusCode("200");
        restResponse.setResponseMsg("请求成功");
        restResponse.setResponseCode("000");

        return restResponse;
    }

    /**
     * 获取某个项目详细信息
     *
     * @param rtrvProjectDetailRequest
     * @return
     */
    @Override
    public RestResponse rtrvProjectInfo(RtrvProjectDetailRequest rtrvProjectDetailRequest) {
        RtrvProjectDetailResponse rtrvProjectDetailResponse = new RtrvProjectDetailResponse();
        ProjectDetail projectDetail = new ProjectDetail();
        if (rtrvProjectDetailRequest == null || rtrvProjectDetailRequest.getProjId() == null) {
            throw new NullPointerException("rtrvProjectDetailRequest 或项目Id不能为空");
        }
        //1.先获取项目基本信息
        String rtrvProjBaseInfoUrl = config.getRtrvProjBaseInfoUrl();
        proj = restTemplate.postForObject(rtrvProjBaseInfoUrl, rtrvProjectDetailRequest, Project.class);
        if (null == proj) {
            LOG.error("获取项目基本信息不存在.");
            throw new BusinessException(ExceptionEnum.PROJECT_DETAIL_BASEINFO_NOT_RTRV);
        }
        projectDetail.setProject(proj);
        //2.获取项目负责人信息
        String rtrvProjLedersUrl = config.getRtrvProjLedersUrl();
        try {
            List<Staff> projLeaders = restTemplate.postForObject(rtrvProjLedersUrl, rtrvProjectDetailRequest, List.class);
            projectDetail.setLeaders(projLeaders);
        } catch (Exception ex) {
            throw new BusinessException(ExceptionEnum.PROJECT_DETAIL_STAFF_NOT_RTRV);
        }
        //3.获取项目标签信息
        String rtrvProjTagsUrl = config.getRtrvProjTagsUrl();
        try {
            List<Tag> projTags = restTemplate.postForObject(rtrvProjTagsUrl, rtrvProjectDetailRequest, List.class);
            projectDetail.setTags(projTags);
        } catch (Exception ex) {
            throw new BusinessException(ExceptionEnum.PROJECT_DETAIL_TAG_NOT_RTRV);
        }
        //4.获取项目模型信息
        String rtrvProjModelsUrl = config.getRtrvProjModelsUrl();
        try {
            List<Model> projModels = restTemplate.postForObject(rtrvProjModelsUrl, rtrvProjectDetailRequest, List.class);
            projectDetail.setModels(projModels);
        } catch (Exception ex) {
            throw new BusinessException(ExceptionEnum.PROJECT_DETAIL_MODEL_NOT_RTRV);
        }
        //5.获取项目checklist信息
        String rtrvProjCheckListsUrl = config.getRtrvProjCheckListsUrl();
        try {
            List<ProjChecklistsDetail> projChecklists = restTemplate.postForObject(rtrvProjCheckListsUrl, rtrvProjectDetailRequest, List.class);
            projectDetail.setCheckLists(projChecklists);
        } catch (Exception ex) {
            throw new BusinessException(ExceptionEnum.PROJECT_DETAIL_CHECKLIST_NOT_RTRV);
        }
        //6.获取项目附件信息
        String rtrvProjAttUrlsUrl = config.getRtrvProjAttUrlsUrl();
        try {
            ParameterizedTypeReference typeReference = new ParameterizedTypeReference<List<ProjAttchUrls>>() {
            };
            List<ProjAttchUrls> projAttchUrls = FetchListUtil.fetch(restTemplate, rtrvProjAttUrlsUrl, rtrvProjectDetailRequest, typeReference);
//            List<ProjAttchUrls> projAttchUrls = restTemplate.postForObject(rtrvProjAttUrlsUrl, rtrvProjectDetailRequest, List.class);
            List<String> idList = new ArrayList<String>();
            for (int i = 0; i < projAttchUrls.size(); i++) {
                Integer attachmentId = projAttchUrls.get(i).getAttachmentId();
//                List<Integer> idList = new ArrayList<Integer>();
//                for (String id:ids.split(",")) {
//                    idList.add(Integer.valueOf(id));
//                }
                idList.add(attachmentId.toString());
            }
            String attachmentIds = com.sun.deploy.util.StringUtils.join(idList, ",");
            if (projAttchUrls.size() != 0) {
                ResponseEntity<RestResponse> responseEntity = restTemplate.getForEntity(config.getRtrvAttchListUrl() + attachmentIds, RestResponse.class);
                projectDetail.setAttchInfos((List<AttchInfo>)responseEntity.getBody().getResponseBody());
            }
        } catch (Exception ex) {
            throw new BusinessException(ExceptionEnum.PROJECT_DETAIL_ATTCHURL_NOT_RTRV);
        }
        //7.获取requirment列表信息
        String rtrvReqmntListUrl = config.getRtrvReqmntListUrl();
        RtrvReqmntListRequest rtrvReqmntListRequest = new RtrvReqmntListRequest();
        String porjId = rtrvProjectDetailRequest.getProjId();
        //项目需求列表分页信息按默认值处理
        Integer page = Integer.parseInt(config.getReqmntListPage());
        LOG.info("需求分页参数, page:{}, pageSize:{}", page, config.getReqmntListPageSize());
        Integer pageSize = Integer.parseInt(config.getReqmntListPageSize());

        rtrvReqmntListRequest.setPage(page);
        rtrvReqmntListRequest.setPageSize(pageSize);
        rtrvReqmntListRequest.setProjId(rtrvProjectDetailRequest.getProjId());
        try {
            RtrvReqmntListResponse rtrvReqmntListResponse = restTemplate.postForObject(rtrvReqmntListUrl, rtrvReqmntListRequest, RtrvReqmntListResponse.class);
            projectDetail.setReqmntListResponse(rtrvReqmntListResponse);
        } catch (Exception ex) {
            throw new BusinessException(ExceptionEnum.PROJECT_DETAIL_REQMNT_NOT_RTRV);
        }


        //获取用户在项目中的权限信息
<<<<<<< HEAD
        RtrvStaffAuthInfoRequest rtrvAuthInfoRequest = new RtrvStaffAuthInfoRequest();
        String projId = rtrvProjectDetailRequest.getProjId();
        StaffAuthInfo staffAuthInfo = StaffAuthUtil.rtrvStaffAuthInfo(this.restTemplate, projId, projId, rtrvProjectDetailRequest.getStaffId());
        rtrvProjectDetailResponse.setStaffAuthInfo(staffAuthInfo);
=======
//        StaffAuthInfo staffAuthInfo = rtrvStaffAuthInfo(rtrvProjectDetailRequest.getProjId(), rtrvProjectDetailRequest.getStaffId());
//        rtrvProjectDetailResponse.setStaffAuthInfo(staffAuthInfo);
>>>>>>> 02032cc46be60f31b668c8b41786eea6d8917a4a

        rtrvProjectDetailResponse.setProjectDetail(projectDetail);
        restResponse.setResponseBody(rtrvProjectDetailResponse);
        restResponse.setStatusCode("200");
        restResponse.setResponseMsg("请求成功");
        restResponse.setResponseCode("000");

        return restResponse;
    }

    /**
     * 获取用户在项目中的权限
     *
     * @param projId
     * @param staffId
     * @return
     */
    /*private StaffAuthInfo rtrvStaffAuthInfo(String projId, String staffId) {

        String rtrvStaffAuthUrl = "http://localhost:10014/mdvn-staff-papi/staff/rtrvAuth";
        RtrvAuthRequest rtrvAuthRequest = new RtrvAuthRequest();
        rtrvAuthRequest.setProjId(projId);
        rtrvAuthRequest.setStaffId(staffId);
        rtrvAuthRequest.setHierarchyId(projId);
        ResponseEntity<StaffAuthInfo> responseEntity = this.restTemplate.postForEntity(rtrvStaffAuthUrl, rtrvAuthRequest, StaffAuthInfo.class);

        StaffAuthInfo staffAuthInfo = null;
        if (HttpStatus.OK.equals(responseEntity.getStatusCode())) {
            staffAuthInfo = responseEntity.getBody();
        }
        return staffAuthInfo;
    }*/

}
