package com.mdvns.mdvn.dashboard.sapi.service;

import com.mdvns.mdvn.dashboard.sapi.domain.*;
import com.mdvns.mdvn.dashboard.sapi.domain.entity.Dashboard;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.SQLException;
import java.util.List;

/**
 * 標簽模塊接口
 */

public interface DashboardService {

//    /*新建任务列表保存*/
//    ResponseEntity<?> updateSprint(UpdateSprintRequest request) throws SQLException;
//
//    /*新建任务列表保存*/
//    ResponseEntity<?> rtrvSprint(RtrvSprintRequest request) throws SQLException;

    /*根据projId查询dashboard对象(判断是不是新建)*/
    List<Dashboard> findDashboardInfoById(String projId);

    /*根据projId和modelId查询dashboard对象(更改时查询)*/
    Dashboard findDashboardInfoByIds(RtrvDashboardRequest request);

    /*创建看板*/
    Dashboard createDashboard(CreateDashboardRequest request);

    /*更改看板*/
    Dashboard updateDashboard(UpdateDashboardRequest request);

}
