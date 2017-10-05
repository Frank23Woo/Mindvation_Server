package com.mdvns.mdvn.project.sapi.web;


import com.mdvns.mdvn.project.sapi.domain.UpdatePAttchUrlsRequest;
import com.mdvns.mdvn.project.sapi.domain.UpdatePLeadersRequest;
import com.mdvns.mdvn.project.sapi.domain.entity.ProjAttchUrls;
import com.mdvns.mdvn.project.sapi.domain.entity.ProjLeaders;
import com.mdvns.mdvn.project.sapi.domain.entity.Project;
import com.mdvns.mdvn.project.sapi.service.IUpdateProjService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value= {"/updateProject", "/v1.0/project"})
public class UpdateProjController {
    private Logger LOG = LoggerFactory.getLogger(CreateProjController.class);
    @Autowired
    private IUpdateProjService updateProjService;

    /**
     * 更改项目基本信息
     * @param proj
     * @return
     */
    @PostMapping(value="/updateProjBaseInfo")
    public Project updateProjBaseInfo(@RequestBody Project proj){
        Project project = this.updateProjService.updateProjBaseInfo(proj);
        return project;
    }

    /**
     * 更改项目负责人信息
     * @param leders
     * @return
     */
    @PostMapping(value="/updateProjLeaders")
    public List<ProjLeaders> updateProjLeaders(@RequestBody UpdatePLeadersRequest leders){
        List<ProjLeaders> projLeaders = this.updateProjService.updateProjLeaders(leders);
        return projLeaders;
    }

    /**
     * 更改项目附件信息
     * @param AttchUrls
     * @return
     */
    @PostMapping(value="/updateProjAttchUrls")
    public List<ProjAttchUrls> updateProjAttchUrls(@RequestBody UpdatePAttchUrlsRequest AttchUrls){
        List<ProjAttchUrls> projAttchUrls = this.updateProjService.updateProjAttchUrls(AttchUrls);
        return projAttchUrls;
    }



}
