package com.mdvns.mdvn.project.papi.service.impl;

import com.mdvns.mdvn.common.beans.RestDefaultResponse;
import com.mdvns.mdvn.common.beans.exception.BusinessException;
import com.mdvns.mdvn.project.papi.config.ProjConfig;
import com.mdvns.mdvn.project.papi.domain.*;
import com.mdvns.mdvn.project.papi.service.IProjService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.http.HttpHeaders;
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
    private RestDefaultResponse restDefaultResponse;

    @Autowired
    private RestTemplate restTemplate;



    /**
     * 获取project列表详细信息
     *
     * @param rtrvProjectRequest
     * @return
     */
    public ResponseEntity<?> rtrvProjInfoList(RtrvProjectRequest rtrvProjectRequest) {
        rtrvProjectRequest.setPage(rtrvProjectRequest.getPage()-1);
        String projInfoListUrl = config.getRtrvProjInfoListUrl();
        restDefaultResponse = this.restTemplate.postForObject(projInfoListUrl, rtrvProjectRequest, RestDefaultResponse.class);
        ResponseEntity<RestDefaultResponse> responseEntity = null;
        if (restDefaultResponse.getStatusCode().equals(HttpStatus.OK.toString())) {
//            HttpHeaders httpHeaders = new HttpHeaders();
//            httpHeaders.setAccessControlAllowOrigin("*");
            responseEntity = new ResponseEntity<RestDefaultResponse>(restDefaultResponse,HttpStatus.OK);
            return responseEntity;
        }
        throw new BusinessException(restDefaultResponse.getResponseCode(), restDefaultResponse.getResponseBody().toString());
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
    public RestDefaultResponse createProject(CreateProjectRequest createProjectRequest) {
        CreateProjectResponse createProjectResponse = new CreateProjectResponse();

        //1.先保存项目基本信息（获取projId）
        if (createProjectRequest == null || createProjectRequest.getStaffId() == null ||
                createProjectRequest.getName() == null || createProjectRequest.getDescription() == null ||
                createProjectRequest.getStartDate() ==null || createProjectRequest.getEndDate() ==null) {
            throw new NullPointerException("createProjectRequest 或员工Id不能为空 或项目名称不能为空 或项目描述不能为空 或者项目开始结束时间不能为空");
        }
        String saveProjectBaseUrl = config.getSaveProjectBaseUrl();
//        proj = restTemplate.postForObject(saveProjectBaseUrl, createProjectRequest, Project.class);
        ResponseEntity<Project> responseEntity = null;
        responseEntity = restTemplate.postForEntity(saveProjectBaseUrl,createProjectRequest, Project.class);
        restDefaultResponse.setResponseBody(responseEntity.getBody());
        restDefaultResponse.setStatusCode("200");
        restDefaultResponse.setResponseMsg("请求成功");
        restDefaultResponse.setResponseCode("000");
        proj = responseEntity.getBody();
        Integer uuId = proj.getUuId();
        //通过uuID查询projId返回整个基本信息
        String getProjIdByUuIdUrl = config.getGetProjIdByUuIdUrl();
        Project pro = restTemplate.postForObject(getProjIdByUuIdUrl, proj, Project.class);
        String projId = pro.getProjId();
        proj.setProjId(projId);

//        createProjectResponse.setProject(pro);
        //2.保存项目负责人信息
        if (createProjectRequest.getLeaders()!=null && !createProjectRequest.getLeaders().isEmpty()) {
            List<ProjLeaders> projLeaders = createProjectRequest.getLeaders();
            for (int i = 0; i < projLeaders.size(); i++) {
                projLeaders.get(i).setProjId(projId);
            }
            String savePLeadersUrl = config.getSavePLeadersUrl();
            try {
                List<ProjLeaders> pLeaders = restTemplate.postForObject(savePLeadersUrl, projLeaders, List.class);
            } catch (Exception ex) {
                throw new RuntimeException("调用SAPI获取项目负责人信息保存数据失败.");
            }
        }
        //3.保存项目标签信息
        if (createProjectRequest.getTags()!=null && !createProjectRequest.getTags().isEmpty()) {
            List<ProjTags> projTags = createProjectRequest.getTags();
            for (int i = 0; i < projTags.size(); i++) {
                projTags.get(i).setProjId(projId);
            }
            String savePTagsUrl = config.getSavePTagsUrl();
            try {
                List<ProjTags> pTags = restTemplate.postForObject(savePTagsUrl, projTags, List.class);
            } catch (Exception ex) {
                throw new RuntimeException("调用SAPI获取项目标签信息保存数据失败.");
            }
        }
        //4.保存项目模块信息
        if (createProjectRequest.getModels()!=null && !createProjectRequest.getModels().isEmpty()) {
            List<ProjModels> projModels = createProjectRequest.getModels();
            for (int i = 0; i < projModels.size(); i++) {
                projModels.get(i).setProjId(projId);
            }
            String savePModelsUrl = config.getSavePModelsUrl();
            try {
                List<ProjTags> pModels = restTemplate.postForObject(savePModelsUrl, projModels, List.class);
            } catch (Exception ex) {
                throw new RuntimeException("调用SAPI获取项目模型信息保存数据失败.");
            }
        }
        //5.保存项目checklist信息
        if (createProjectRequest.getCheckLists()!=null && !createProjectRequest.getCheckLists().isEmpty()) {
            List<ProjChecklists> projChecklists = createProjectRequest.getCheckLists();
            for (int i = 0; i < projChecklists.size(); i++) {
                projChecklists.get(i).setProjId(projId);
                projChecklists.get(i).setAssignerId(createProjectRequest.getStaffId());
            }
            String saveCheckListsUrl = config.getSaveCheckListsUrl();
            try {
                List<ProjChecklists> checklistsList = restTemplate.postForObject(saveCheckListsUrl, projChecklists, List.class);
                //通过UUid遍历保存checklistId
                String getChecklistsListByUuIdUrl = config.getGetChecklistsListByUuIdUrl();
                try {
                    List<ProjChecklists> checklists = restTemplate.postForObject(getChecklistsListByUuIdUrl, checklistsList, List.class);
                } catch (Exception ex) {
                    throw new RuntimeException("调用SAPI获取项目checklistId信息保存数据失败.");
                }
            } catch (Exception ex) {
                throw new RuntimeException("调用SAPI获取项目checklist信息保存数据失败.");
            }
        }
        //6.保存项目附件信息
        if (createProjectRequest.getAttchUrls()!=null && !createProjectRequest.getAttchUrls().isEmpty()) {
            List<ProjAttchUrls> projAttchUrls = createProjectRequest.getAttchUrls();
            for (int i = 0; i < projAttchUrls.size(); i++) {
                projAttchUrls.get(i).setProjId(projId);
//                projAttchUrls.get(i).setYxbz("Y");
            }
            String savePAttchUrlsUrl = config.getSavePAttchUrlsUrl();
            try {
                List<ProjAttchUrls> pAttchUrls = restTemplate.postForObject(savePAttchUrlsUrl, projAttchUrls, List.class);
            } catch (Exception ex) {
                throw new RuntimeException("调用SAPI获取项目附件信息保存数据失败.");
            }
        }
        //response
//        if (restDefaultResponse.getStatusCode().equals(HttpStatus.OK.toString())) {
            return restDefaultResponse;
//        }
//        throw new BusinessException(restDefaultResponse.getResponseCode(), restDefaultResponse.getResponseBody().toString());
    }

    /**
     * 调用sapi更改project
     *
     * @param updateProjectRequest
     * @return
     */
    @Override
    public UpdateProjectResponse updateProject(UpdateProjectRequest updateProjectRequest) {
        RestTemplate restTemplate = new RestTemplate();
        UpdateProjectResponse updateProjectResponse = new UpdateProjectResponse();
//        String projId = updateProjectRequest.getProjId();
        if (updateProjectRequest == null || updateProjectRequest.getProjId() == null) {
            throw new NullPointerException("updateProjectRequest 或项目Id不能为空");
        }
        //1.先判断是否更改项目基本信息
        proj.setProjId(updateProjectRequest.getProjId());
        if (!StringUtils.isEmpty(updateProjectRequest.getName()) ) {
            proj.setName(updateProjectRequest.getName());
        }
        if (!StringUtils.isEmpty(updateProjectRequest.getDescription())) {
            proj.setDescription(updateProjectRequest.getDescription());
        }
        if (!StringUtils.isEmpty(updateProjectRequest.getStartDate())) {
            proj.setStartDate(updateProjectRequest.getStartDate());

        }
        if (!StringUtils.isEmpty(updateProjectRequest.getEndDate())) {
            proj.setEndDate(updateProjectRequest.getEndDate());

        }
        if (!StringUtils.isEmpty(updateProjectRequest.getPriority())) {
            proj.setPriority(updateProjectRequest.getPriority());

        }
        if (!StringUtils.isEmpty(updateProjectRequest.getContingency())) {
            proj.setContingency(updateProjectRequest.getContingency());
        }
        String updateProjBaseInfoUrl = config.getUpdateProjBaseInfoUrl();
        Project pro = restTemplate.postForObject(updateProjBaseInfoUrl, proj, Project.class);
        updateProjectResponse.setProjId(pro.getProjId());
        updateProjectResponse.setName(pro.getName());
        updateProjectResponse.setDescription(pro.getDescription());
        updateProjectResponse.setStartDate(pro.getStartDate());
        updateProjectResponse.setEndDate(pro.getEndDate());
        updateProjectResponse.setPriority(pro.getPriority());
        updateProjectResponse.setContingency(pro.getContingency());
        //2.判断是否更改项目负责人信息
        if (updateProjectRequest.getLeaders()!=null && !updateProjectRequest.getLeaders().isEmpty()) {
            UpdatePLeadersRequest updatePLeadersRequest = new UpdatePLeadersRequest();
            updatePLeadersRequest.setProjId(updateProjectRequest.getProjId());
            updatePLeadersRequest.setLeaders(updateProjectRequest.getLeaders());
            String updateProjLeadersUrl = config.getUpdateProjLeadersUrl();
            try {
                List<ProjLeaders> pLeaders = restTemplate.postForObject(updateProjLeadersUrl, updatePLeadersRequest, List.class);
                updateProjectResponse.setLeaders(pLeaders);
            } catch (Exception ex) {
                throw new RuntimeException("调用SAPI更改项目负责人信息保存数据失败.");
            }
        }
        //3.判断是否更改项目标签信息
        if (updateProjectRequest.getTags()!=null && !updateProjectRequest.getTags().isEmpty()) {
            List<ProjTags> projTags = updateProjectRequest.getTags();

        }

        //4.判断是否更改项目模块信息
        if (updateProjectRequest.getModels()!=null && !updateProjectRequest.getModels().isEmpty()) {
            List<ProjModels> projModels = updateProjectRequest.getModels();

        }

        //5.判断是否更改项目checklist信息
        if (updateProjectRequest.getCheckLists()!=null && !updateProjectRequest.getCheckLists().isEmpty()) {
            List<ProjChecklists> projChecklists = updateProjectRequest.getCheckLists();


        }

        //6.判断是否更改项目附件信息
        if (updateProjectRequest.getAttchUrls()!=null && !updateProjectRequest.getAttchUrls().isEmpty()) {
            UpdatePAttchUrlsRequest updatePAttchUrlsRequest = new UpdatePAttchUrlsRequest();
            updatePAttchUrlsRequest.setProjId(updateProjectRequest.getProjId());
            updatePAttchUrlsRequest.setAttchUrls(updateProjectRequest.getAttchUrls());
            String updateProjAttchUrlsUrl = config.getUpdateProjAttchUrlsUrl();
            try {
                List<ProjAttchUrls> pAttchUrls = restTemplate.postForObject(updateProjAttchUrlsUrl, updatePAttchUrlsRequest, List.class);
                updateProjectResponse.setAttchUrls(pAttchUrls);
            } catch (Exception ex) {
                throw new RuntimeException("调用SAPI更改项目附件信息保存数据失败.");
            }

        }
        return updateProjectResponse;
    }
}
