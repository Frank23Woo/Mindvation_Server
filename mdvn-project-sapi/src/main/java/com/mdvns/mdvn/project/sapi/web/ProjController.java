package com.mdvns.mdvn.project.sapi.web;

import com.mdvns.mdvn.project.sapi.domain.entity.Project;
import com.mdvns.mdvn.project.sapi.service.IProjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
    @PostMapping(value="/rtrvProjBaseInfoList")
    public List<Project> rtrvProjBaseInfoList(){
        return projService.rtrvProjBaseInfoList();
    }

}
