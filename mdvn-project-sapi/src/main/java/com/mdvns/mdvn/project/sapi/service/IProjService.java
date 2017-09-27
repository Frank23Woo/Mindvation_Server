package com.mdvns.mdvn.project.sapi.service;

import com.mdvns.mdvn.project.sapi.domain.CreateProjectRequest;
import com.mdvns.mdvn.project.sapi.domain.CreateProjectResponse;
import com.mdvns.mdvn.project.sapi.domain.RtrvProjectRequest;
import com.mdvns.mdvn.project.sapi.domain.entity.*;


import java.sql.SQLException;
import java.util.List;

public interface IProjService {

    //获取project整个列表
    List<Project> rtrvProjInfoList(RtrvProjectRequest request) throws SQLException;
    //创建project(整个信息)
    Project createProject(CreateProjectRequest request);
    //创建project时保存project(基本信息)
    Project saveProject(CreateProjectRequest request);
    //通过uuId获取项目的projId(触发器引发的问题)
    Project getProjIdByUuId(Project proj);
    //创建project时保存leaders信息
    List<ProjLeaders> saveProjLeaders(List<ProjLeaders> leaders);
    //创建project时保存标签信息
    List<ProjTags> savePTags(List<ProjTags> request);
    //创建project时保存project项目模型
    List<ProjModels> savePModels(List<ProjModels> request);
    //创建project时保存project任务（checkLists）
    List<ProjChecklists> saveCheckLists(List<ProjChecklists> projChecklists);
    //通过checklist的uuid查询它的checklistId
    List<ProjChecklists> getChecklistIdByUuId(List<ProjChecklists> pChecklists);
    //创建project时保存附件信息
    List<ProjAttchUrls> savePAttchUrls(List<ProjAttchUrls>  request);




    Project getProject(Integer uuId);
}
