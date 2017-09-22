package com.mdvns.mdvn.project.sapi.service.impl;

import com.mdvns.mdvn.project.sapi.domain.entity.Project;
import com.mdvns.mdvn.project.sapi.repository.ProjectRepository;
import com.mdvns.mdvn.project.sapi.service.IProjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProjServiceImpl implements IProjService {

    @Autowired
    private ProjectRepository projectRepository;
    public List<Project> rtrvProjBaseInfoList() {
        List<Project> project = new ArrayList<Project>();
        try {
            project = this.projectRepository.rtrvProjBaseInfoList();
        } catch (Exception e) {
            try {
                throw new SQLException("查询数据库异常");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return project;
    }
}
