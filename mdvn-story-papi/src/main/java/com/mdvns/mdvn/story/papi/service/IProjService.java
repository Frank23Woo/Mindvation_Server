package com.mdvns.mdvn.story.papi.service;

import com.mdvns.mdvn.common.beans.RestResponse;
import org.springframework.http.ResponseEntity;


public interface IProjService {
    /**
     * 获取project整个列表
     * @param rtrvProjectListRequest
     * @return
     */
    ResponseEntity<?> rtrvProjInfoList(RtrvProjectListRequest rtrvProjectListRequest);

    /**
     * 创建project
     * @param createProjectRequest
     * @return
     */
    RestResponse createProject(CreateProjectRequest createProjectRequest);

    /**
     * 更改project
     * @param updateProjectDetailRequest
     * @return
     */
    RestResponse updateProject(UpdateProjectDetailRequest updateProjectDetailRequest);

    /**
     * 获取某个项目详细信息
     * @param rtrvProjectDetailRequest
     * @return
     */
    RestResponse rtrvProjectInfo(RtrvProjectDetailRequest rtrvProjectDetailRequest);
}
