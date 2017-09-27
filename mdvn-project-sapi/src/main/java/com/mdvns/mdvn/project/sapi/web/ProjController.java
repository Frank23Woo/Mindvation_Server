package com.mdvns.mdvn.project.sapi.web;

import com.mdvns.mdvn.project.sapi.domain.CreateProjectRequest;
import com.mdvns.mdvn.project.sapi.domain.CreateProjectResponse;
import com.mdvns.mdvn.project.sapi.domain.RtrvProjectRequest;
import com.mdvns.mdvn.project.sapi.domain.entity.*;
import com.mdvns.mdvn.project.sapi.service.IProjService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(value= {"/project", "/v1.0/project"})
public class ProjController {
    private Logger LOG = LoggerFactory.getLogger(ProjController.class);

    @Autowired
    private IProjService projService;


    /**
     * 获取project整个列表
     * @return
     */
    @PostMapping(value="/rtrvProjInfoList")
    public List<Project> rtrvProjInfoList(@RequestBody RtrvProjectRequest request) throws SQLException{
        return projService.rtrvProjInfoList(request);
    }

    /**
     * 创建project(详细信息)
     * @param request
     * @return
     */
    @PostMapping(value="/createProject")
    public Project createProject(@RequestBody CreateProjectRequest request){
        Project project = projService.createProject(request);
        return project;
    }

    /**
     *保存project（基本信息）
     */
    @PostMapping(value="/saveProject")
    public Project saveProject(@RequestBody CreateProjectRequest request){
        Project project = projService.saveProject(request);
        return project;
    }

    /**
     * 通过uuId获取项目的projId(触发器引发的问题)
     * @param proj
     * @return
     */
    @PostMapping(value="/getProjIdByUuId")
    public Project getProjIdByUuId(@RequestBody Project proj){
        Project pro = projService.getProjIdByUuId(proj);
        return pro;
    }



    /**
     * 创建project时保存负责人信息
     * @param leaders
     * @return
     */
    @PostMapping(value="/savePLeaders")
    public List<ProjLeaders> saveProjLeaders(@RequestBody List<ProjLeaders> leaders){
        List<ProjLeaders> projLeaders = projService.saveProjLeaders(leaders);
        return projLeaders;
    }

    /**
     * 创建project时保存标签信息
     * @param request
     * @return
     */
    @PostMapping(value="/savePTags")
    public List<ProjTags> savePTags(@RequestBody List<ProjTags>  request){
        List<ProjTags> projTags = projService.savePTags(request);
        return projTags;
    }

    /**
     * 创建project时保存模型信息
     * @param request
     * @return
     */
    @PostMapping(value="/savePModels")
    public List<ProjModels> savePModels(@RequestBody List<ProjModels> request){
        List<ProjModels> projModels = projService.savePModels(request);
        return projModels;
    }
    /**
     * 创建project时保存project任务（checkLists）
     * @param pChecklists
     * @return
     */
    @PostMapping(value="/savePCheckLists")
    public List<ProjChecklists> saveCheckLists(@RequestBody List<ProjChecklists> pChecklists){
        List<ProjChecklists> projChecklists = projService.saveCheckLists(pChecklists);
        return projChecklists;
    }
    /**
     * 通过checklist的uuid查询它的checklistId
     * @param pChecklists
     * @return
     */
    @PostMapping(value="/getChecklistsListByUuId")
    public List<ProjChecklists> getChecklistsListByUuId(@RequestBody List<ProjChecklists> pChecklists){
        List<ProjChecklists> checklistsList = projService.getChecklistIdByUuId(pChecklists);
        return checklistsList;
    }

    /**
     * 创建project时保存附件信息
     * @param request
     * @return
     */
    @PostMapping(value="/savePAttchUrls")
    public List<ProjAttchUrls> savePAttchUrls(@RequestBody List<ProjAttchUrls> request){
        List<ProjAttchUrls> projAttchUrls = projService.savePAttchUrls(request);
        return projAttchUrls;
    }




    /**
     * 通过主键查询单条project数据
     * @param request
     * @return
     */
    @PostMapping(value="/getProject")
    public Project getProject(@RequestBody Project request){
        Project project = projService.getProject(request.getUuId());
        return project;
    }

}
