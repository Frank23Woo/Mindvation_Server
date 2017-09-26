package com.mdvns.mdvn.project.papi.web;

import com.mdvns.mdvn.project.papi.domain.*;
import com.mdvns.mdvn.project.papi.service.IProjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value= {"/project", "/v1.0/project"})
public class ProjController {

    @Autowired
    private IProjService projService;
    /**
     * 获取project整个列表
     * @return
     */
    @PostMapping(value="/rtrvProjInfoList")
    public RtrvProjectResponse rtrvProjInfoList(@RequestBody RtrvProjectRequest rtrvProjectRequest){
        return this.projService.rtrvProjInfoList(rtrvProjectRequest);
    }

    /**
     * 创建项目
     * @param createProjectRequest
     * @return
     */
    @PostMapping(value="/createProject")
    public CreateProjectResponse createProject(@RequestBody CreateProjectRequest createProjectRequest){
        return projService.createProject(createProjectRequest);
    }

}
