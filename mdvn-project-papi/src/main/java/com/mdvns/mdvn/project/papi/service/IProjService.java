package com.mdvns.mdvn.project.papi.service;

import com.mdvns.mdvn.project.papi.domain.*;


public interface IProjService {
    /**
     * 获取project整个列表
     * @param rtrvProjectRequest
     * @return
     */
    RtrvProjectResponse rtrvProjInfoList(RtrvProjectRequest rtrvProjectRequest);

    /**
     * 创建project
     * @param createProjectRequest
     * @return
     */
    CreateProjectResponse createProject(CreateProjectRequest createProjectRequest);
}
