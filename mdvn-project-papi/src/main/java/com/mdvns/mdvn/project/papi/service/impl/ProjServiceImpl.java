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
     *  1.创建保存project信息
     *  2.返回Project整个信息
     * @param createProjectRequest
     * @return
     */
    @Override
    public CreateProjectResponse createProject(CreateProjectRequest createProjectRequest) {
        //创建保存project信息
        RestTemplate restTemplate = new RestTemplate();
        CreateProjectResponse createProjectResponse = new CreateProjectResponse();
        String createProjectUrl = config.getCreateProjectUrl();
        try {
            proj = restTemplate.postForObject(createProjectUrl, createProjectRequest, Project.class);
        }catch (Exception ex){
            throw new RuntimeException("调用SAPI保存数据失败.");
        }
        createProjectResponse.setProject(proj);

        return createProjectResponse;
    }
}
