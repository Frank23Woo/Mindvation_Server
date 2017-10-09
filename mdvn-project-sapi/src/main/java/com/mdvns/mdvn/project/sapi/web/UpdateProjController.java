package com.mdvns.mdvn.project.sapi.web;


import com.mdvns.mdvn.project.sapi.domain.*;
import com.mdvns.mdvn.project.sapi.domain.entity.*;
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
    public List<Staff> updateProjLeaders(@RequestBody UpdatePLeadersRequest leders){
        return this.updateProjService.updateProjLeaders(leders);
    }

    /**
     * 更改项目附件信息
     * @param attchUrls
     * @return
     */
    @PostMapping(value="/updateProjAttchUrls")
    public List<ProjAttchUrls> updateProjAttchUrls(@RequestBody UpdatePAttchUrlsRequest attchUrls){
        List<ProjAttchUrls> projAttchUrls = this.updateProjService.updateProjAttchUrls(attchUrls);
        return projAttchUrls;
    }

    /**
     * 更改项目模型信息
     * @param models
     * @return
     */
    @PostMapping(value="/updateProjModels")
    public List<Model> updateProjModels(@RequestBody UpdatePModelsRequest models){
        return this.updateProjService.updateProjModels(models);
    }

    /**
     * 更改项目标签信息
     * @param tags
     * @return
     */
    @PostMapping(value="/updateProjTags")
    public List<Tag> updateProjTags(@RequestBody UpdatePTagsRequest tags){
        return this.updateProjService.updateProjTags(tags);
    }
    /**
     * 更改项目checklist信息
     * @param checkLists
     * @return
     */
    @PostMapping(value="/updateProjChecklists")
    public List<ProjChecklists> updateProjChecklists(@RequestBody UpdatePCheckListsRequest checkLists){
        return this.updateProjService.updateProjChecklists(checkLists);
    }

}
