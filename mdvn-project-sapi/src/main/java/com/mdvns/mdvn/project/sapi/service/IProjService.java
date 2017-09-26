package com.mdvns.mdvn.project.sapi.service;

import com.mdvns.mdvn.project.sapi.domain.CreateProjectRequest;
import com.mdvns.mdvn.project.sapi.domain.CreateProjectResponse;
import com.mdvns.mdvn.project.sapi.domain.RtrvProjectRequest;
import com.mdvns.mdvn.project.sapi.domain.entity.ProjChecklists;
import com.mdvns.mdvn.project.sapi.domain.entity.ProjLeaders;
import com.mdvns.mdvn.project.sapi.domain.entity.Project;


import java.sql.SQLException;
import java.util.List;

public interface IProjService {

    //获取project整个列表
    List<Project> rtrvProjInfoList(RtrvProjectRequest request) throws SQLException;
    //创建project(整个信息)
    Project createProject(CreateProjectRequest request);
    //保存project(基本信息)
    Project saveProject(CreateProjectRequest request);
    //保存project任务（checkLists）
    List<ProjChecklists> saveCheckLists(List<ProjChecklists> projChecklists);
    Project getProject(Integer uuId);
    //创建project时保存leaders信息
    List<ProjLeaders> saveProjLeaders(List<ProjLeaders> leaders);
}
