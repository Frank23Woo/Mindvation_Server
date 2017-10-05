package com.mdvns.mdvn.project.papi.service;

import com.mdvns.mdvn.common.beans.RestDefaultResponse;
import com.mdvns.mdvn.project.papi.domain.*;
import org.springframework.http.ResponseEntity;


public interface IProjService {
    /**
     * 获取project整个列表
     * @param rtrvProjectRequest
     * @return
     */
    ResponseEntity<?> rtrvProjInfoList(RtrvProjectRequest rtrvProjectRequest);

    /**
     * 创建project
     * @param createProjectRequest
     * @return
     */
    RestDefaultResponse createProject(CreateProjectRequest createProjectRequest);

    /**
     * 更改project
     * @param updateProjectRequest
     * @return
     */
    UpdateProjectResponse updateProject(UpdateProjectRequest updateProjectRequest);
}
