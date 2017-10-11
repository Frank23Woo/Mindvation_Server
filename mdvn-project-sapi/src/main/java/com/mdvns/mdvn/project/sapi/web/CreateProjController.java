package com.mdvns.mdvn.project.sapi.web;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.project.sapi.domain.*;
import com.mdvns.mdvn.project.sapi.domain.entity.*;
import com.mdvns.mdvn.project.sapi.service.ICreateProjService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(value= {"/project", "/v1.0/project"})
public class CreateProjController {
    private Logger LOG = LoggerFactory.getLogger(CreateProjController.class);

    @Autowired
    private ICreateProjService projService;


    /**
     * 获取project整个列表
     * @return
     */
    @PostMapping(value="/rtrvProjInfoList")
    public RestResponse rtrvProjInfoList(@RequestBody RtrvProjectListRequest request) throws SQLException{
        return projService.rtrvProjInfoList(request);
    }


    /**
     *保存project（基本信息）
     */
    @PostMapping(value="/saveProject")
    public ResponseEntity<?> saveProject(@RequestBody CreateProjectRequest request){
        return projService.saveProject(request);
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
     * 创建project时保存project任务（checkLists）(多了一个保存创建者信息的动作)
     * @param request
     * @return
     */
    @PostMapping(value="/savePCheckLists")
    public List<ProjChecklists> saveCheckLists(@RequestBody SavePCheckListsRequest request){
        List<ProjChecklists> projChecklists = projService.saveCheckLists(request);
        return projChecklists;
    }

    /**
     * 通过checklist的uuid查询它的checklistId(详细staff)
     * @param request
     * @return
     */
    @PostMapping(value="/checklistsListByUuId")
    public List<ProjChecklistsDetail> getChecklistsListByUuId(@RequestBody UpdatePCheckListsRequest request){
        return this.projService.getChecklistIdByUuId(request);

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
