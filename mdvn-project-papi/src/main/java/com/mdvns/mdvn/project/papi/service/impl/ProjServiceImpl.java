package com.mdvns.mdvn.project.papi.service.impl;

import com.mdvns.mdvn.project.papi.config.ProjConfig;
import com.mdvns.mdvn.project.papi.domain.*;
import com.mdvns.mdvn.project.papi.service.IProjService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ProjServiceImpl implements IProjService {

    private static final Logger LOG = LoggerFactory.getLogger(ProjServiceImpl.class);

    @Autowired
    private ProjConfig config;

    @Autowired
    private Project proj;

    @Autowired
    private RtrvProjectResponse rtrvProjectResponse;

    /**
     * 获取project列表详细信息
     *
     * @param rtrvProjectRequest
     * @return
     */
    public RtrvProjectResponse rtrvProjInfoList(RtrvProjectRequest rtrvProjectRequest) {
        RestTemplate restTemplate = new RestTemplate();
        String projInfoListUrl = config.getRtrvProjInfoListUrl();
        List<Project> projects = restTemplate.postForObject(projInfoListUrl, rtrvProjectRequest, List.class);
        rtrvProjectResponse.setProjects(projects);
        return rtrvProjectResponse;
    }

    /**
     * 调用sapi创建project
     * 1.创建保存project信息
     * 2.返回Project整个信息
     *
     * @param createProjectRequest
     * @return
     */
//    @Override
//    public CreateProjectResponse createProject(CreateProjectRequest createProjectRequest) {
//        //创建保存project信息
//        RestTemplate restTemplate = new RestTemplate();
//        CreateProjectResponse createProjectResponse = new CreateProjectResponse();
//        String createProjectUrl = config.getCreateProjectUrl();
//        try {
//            proj = restTemplate.postForObject(createProjectUrl, createProjectRequest, Project.class);
//        }catch (Exception ex){
//            throw new RuntimeException("调用SAPI保存数据失败.");
//        }
//        createProjectResponse.setProject(proj);
//
//        return createProjectResponse;
//    }
    @Override
    public CreateProjectResponse createProject(CreateProjectRequest createProjectRequest) {
        RestTemplate restTemplate = new RestTemplate();
        CreateProjectResponse createProjectResponse = new CreateProjectResponse();
        //1.先保存项目基本信息（获取projId）
        if (createProjectRequest == null || createProjectRequest.getStaffId() == null ||
                createProjectRequest.getName() == null || createProjectRequest.getDescription() == null) {
            throw new NullPointerException("createProjectRequest 或员工Id不能为空 或项目名称不能为空 或项目描述不能为空");
        }
        String saveProjectBaseUrl = config.getSaveProjectBaseUrl();

        proj = restTemplate.postForObject(saveProjectBaseUrl, createProjectRequest, Project.class);
        //通过uuID查询projId返回整个基本信息
        String getProjIdByUuIdUrl = config.getGetProjIdByUuIdUrl();
        Project pro = restTemplate.postForObject(getProjIdByUuIdUrl, proj, Project.class);
        String projId = pro.getProjId();
        createProjectResponse.setProject(pro);
        //2.保存项目负责人信息
        if (!StringUtils.isEmpty(createProjectRequest.getLeaders())) {
            List<ProjLeaders> projLeaders = createProjectRequest.getLeaders();
            for (int i = 0; i < projLeaders.size(); i++) {
                projLeaders.get(i).setProjId(projId);
                projLeaders.get(i).setYxbz("Y");
            }
            String savePLeadersUrl = config.getSavePLeadersUrl();
            try {
                List<ProjLeaders> pLeaders = restTemplate.postForObject(savePLeadersUrl, projLeaders, List.class);
            } catch (Exception ex) {
                throw new RuntimeException("调用SAPI获取项目负责人信息保存数据失败.");
            }
        }
        //3.保存项目标签信息
        if (!StringUtils.isEmpty(createProjectRequest.getTags())) {
            List<ProjTags> projTags = createProjectRequest.getTags();
            for (int i = 0; i < projTags.size(); i++) {
                projTags.get(i).setProjId(projId);
                projTags.get(i).setYxbz("Y");
            }
            String savePTagsUrl = config.getSavePTagsUrl();
            try {
                List<ProjTags> pTags = restTemplate.postForObject(savePTagsUrl, projTags, List.class);
            } catch (Exception ex) {
                throw new RuntimeException("调用SAPI获取项目标签信息保存数据失败.");
            }
        }
        //4.保存项目模块信息
        if (!StringUtils.isEmpty(createProjectRequest.getModels())) {
            List<ProjModels> projModels = createProjectRequest.getModels();
            for (int i = 0; i < projModels.size(); i++) {
                projModels.get(i).setProjId(projId);
                projModels.get(i).setYxbz("Y");
            }
            String savePModelsUrl = config.getSavePModelsUrl();
            try {
                List<ProjTags> pModels = restTemplate.postForObject(savePModelsUrl, projModels, List.class);
            } catch (Exception ex) {
                throw new RuntimeException("调用SAPI获取项目模型信息保存数据失败.");
            }
        }
        //5.保存项目checklist信息
        if (!StringUtils.isEmpty(createProjectRequest.getCheckLists())) {
            List<ProjChecklists> projChecklists = createProjectRequest.getCheckLists();
            for (int i = 0; i < projChecklists.size(); i++) {
                projChecklists.get(i).setProjId(projId);
                projChecklists.get(i).setAssignerId(createProjectRequest.getStaffId());
                projChecklists.get(i).setYxbz("Y");
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
        if (!StringUtils.isEmpty(createProjectRequest.getAttchUrls())) {
            List<ProjAttchUrls> projAttchUrls = createProjectRequest.getAttchUrls();
            for (int i = 0; i < projAttchUrls.size(); i++) {
                projAttchUrls.get(i).setProjId(projId);
                projAttchUrls.get(i).setYxbz("Y");
            }
            String savePAttchUrlsUrl = config.getSavePAttchUrlsUrl();
            try {
                List<ProjAttchUrls> pAttchUrls = restTemplate.postForObject(savePAttchUrlsUrl, projAttchUrls, List.class);
            } catch (Exception ex) {
                throw new RuntimeException("调用SAPI获取项目附件信息保存数据失败.");
            }
        }
        return createProjectResponse;
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
        String projId = updateProjectRequest.getProjId();
        //1.先判断是否更改项目基本信息
        if (updateProjectRequest == null || updateProjectRequest.getProjId() == null) {
            throw new NullPointerException("updateProjectRequest 或项目Id不能为空");

        }

        //2.判断是否更改项目负责人信息
        if (!StringUtils.isEmpty(updateProjectRequest.getLeaders())) {

        }

        //3.判断是否更改项目标签信息
        if (!StringUtils.isEmpty(updateProjectRequest.getTags())) {

        }

        //4.判断是否更改项目模块信息
        if (!StringUtils.isEmpty(updateProjectRequest.getModels())) {

        }

        //5.判断是否更改项目checklist信息
        if (!StringUtils.isEmpty(updateProjectRequest.getCheckLists())) {

        }


        //6.判断是否更改项目附件信息
        if (!StringUtils.isEmpty(updateProjectRequest.getAttchUrls())) {

        }


        return null;
    }
}
