package com.mdvns.mdvn.project.papi.service.impl;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.common.beans.exception.BusinessException;
import com.mdvns.mdvn.common.beans.exception.ExceptionEnum;
import com.mdvns.mdvn.common.utils.RestResponseUtil;
import com.mdvns.mdvn.project.papi.config.ProjConfig;
import com.mdvns.mdvn.project.papi.domain.*;
import com.mdvns.mdvn.project.papi.service.IProjService;
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
     * 获取project列表详细信息
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
        responseEntity = restTemplate.postForEntity(saveProjectBaseUrl, createProjectRequest, Project.class);
        if (!responseEntity.getStatusCode().equals(HttpStatus.OK.toString())) {
            throw new BusinessException(ExceptionEnum.PROJECT_BASEINFO_NOT_CREATE);
        }
        restResponse.setResponseBody(responseEntity.getBody());
        restResponse.setStatusCode("200");
        restResponse.setResponseMsg("请求成功");
        restResponse.setResponseCode("000");
        proj = responseEntity.getBody();
        String projId = proj.getProjId();
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
     * 调用sapi更改project
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
                List<ProjAttchUrls> pAttchUrls = restTemplate.postForObject(updateProjAttchUrlsUrl, updatePAttchUrlsRequest, List.class);
                projectDetail.setAttchUrls(pAttchUrls);
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
            List<ProjAttchUrls> projAttchUrls = restTemplate.postForObject(rtrvProjAttUrlsUrl, rtrvProjectDetailRequest, List.class);
            projectDetail.setAttchUrls(projAttchUrls);
        } catch (Exception ex) {
            throw new BusinessException(ExceptionEnum.PROJECT_DETAIL_ATTCHURL_NOT_RTRV);
        }
        rtrvProjectDetailResponse.setProjectDetail(projectDetail);
        restResponse.setResponseBody(rtrvProjectDetailResponse);
        restResponse.setStatusCode("200");
        restResponse.setResponseMsg("请求成功");
        restResponse.setResponseCode("000");

        return restResponse;
    }
}
