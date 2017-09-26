package com.mdvns.mdvn.project.sapi.web;

import com.mdvns.mdvn.project.sapi.domain.CreateProjectRequest;
import com.mdvns.mdvn.project.sapi.domain.CreateProjectResponse;
import com.mdvns.mdvn.project.sapi.domain.RtrvProjectRequest;
import com.mdvns.mdvn.project.sapi.domain.entity.ProjChecklists;
import com.mdvns.mdvn.project.sapi.domain.entity.ProjLeaders;
import com.mdvns.mdvn.project.sapi.domain.entity.Project;
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
     * 保存project任务（checkLists）
     * @param PChecklists
     * @return
     */
    @PostMapping(value="/saveCheckLists")
    public List<ProjChecklists> saveCheckLists(List<ProjChecklists> PChecklists){
        List<ProjChecklists> projChecklists = projService.saveCheckLists(PChecklists);
        return projChecklists;
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

    /**
     * 创建project时保存负责人信息
     * @param leaders
     * @return
     */
    @PostMapping(value="/saveProjLeaders")
    public List<ProjLeaders> saveProjLeaders(@RequestBody List<ProjLeaders> leaders){
        List<ProjLeaders> projLeaders = projService.saveProjLeaders(leaders);
        return projLeaders;
    }


}
