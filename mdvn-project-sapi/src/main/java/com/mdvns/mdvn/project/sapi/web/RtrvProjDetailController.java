package com.mdvns.mdvn.project.sapi.web;

import com.mdvns.mdvn.project.sapi.domain.ProjChecklistsDetail;
import com.mdvns.mdvn.project.sapi.domain.RtrvProjectDetailRequest;
import com.mdvns.mdvn.project.sapi.domain.entity.*;
import com.mdvns.mdvn.project.sapi.service.IRtrvProjDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(value= {"/projDetail", "/v1.0/project"})
public class RtrvProjDetailController {

    @Autowired
    private IRtrvProjDetailService rtrvProjDetailService;

    /**
     * 获得某个项目基础信息
     * @param request
     * @return
     * @throws SQLException
     */
    @PostMapping(value="/rtrvProjBaseInfo")
    public ResponseEntity<?> rtrvProjBaseInfo(@RequestBody RtrvProjectDetailRequest request) throws SQLException {
        return rtrvProjDetailService.rtrvProjBaseInfo(request);
    }

    /**
     * 获得某个项目负责人信息
     * @param request
     * @return
     * @throws SQLException
     */
    @PostMapping(value="/rtrvProjLeders")
    public List<Staff> rtrvProjLeders(@RequestBody RtrvProjectDetailRequest request) throws SQLException {
        return rtrvProjDetailService.rtrvProjLeders(request);
    }

    /**
     * 获得某个项目标签信息
     * @param request
     * @return
     * @throws SQLException
     */
    @PostMapping(value="/rtrvProjTags")
    public List<Tag> rtrvProjTags(@RequestBody RtrvProjectDetailRequest request) throws SQLException {
        return rtrvProjDetailService.rtrvProjTags(request);
    }

    /**
     * 获得某个项目模型信息
     * @param request
     * @return
     * @throws SQLException
     */
    @PostMapping(value="/rtrvProjModels")
    public List<Model> rtrvProjModels(@RequestBody RtrvProjectDetailRequest request) throws SQLException {
        return rtrvProjDetailService.rtrvProjModels(request);
    }

    /**
     * 获得某个项目checkList信息
     * @param request
     * @return
     * @throws SQLException
     */
    @PostMapping(value="/rtrvProjCheckLists")
    public List<ProjChecklistsDetail> rtrvProjCheckLists(@RequestBody RtrvProjectDetailRequest request) throws SQLException {
        return rtrvProjDetailService.rtrvProjCheckLists(request);
    }

    /**
     * 获得某个项目附件信息
     * @param request
     * @return
     * @throws SQLException
     */
    @PostMapping(value="/rtrvProjAttUrls")
    public List<ProjAttchUrls> rtrvProjAttUrls(@RequestBody RtrvProjectDetailRequest request) throws SQLException {
        return rtrvProjDetailService.rtrvProjAttUrls(request);
    }
}
