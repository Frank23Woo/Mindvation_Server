package com.mdvns.mdvn.dashboard.sapi.web;

import com.mdvns.mdvn.dashboard.sapi.domain.*;
import com.mdvns.mdvn.dashboard.sapi.domain.entity.Dashboard;
import com.mdvns.mdvn.dashboard.sapi.service.DashboardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 任务列表SAPI控制层
 */

@RestController
@RequestMapping(value = {"/dashboards", "/v1.0/dashboards"})
public class DashboardController {

    private Logger LOG = LoggerFactory.getLogger(DashboardController.class);
    private final String CLASS = this.getClass().getName();

    /*任务列表service注入*/
    @Autowired
    private DashboardService dashboardService;

    /**
     * 根据projId查询dashboard对象(判断是不是新建)
     * @param projId
     * @return
     */
    @PostMapping(value = "/dashboardInfos")
    public List<Dashboard> findDashboardInfoById(@RequestBody String projId) {
        LOG.info("项目编号："+projId);
        return this.dashboardService.findDashboardInfoById(projId);
    }

    /**
     * 根据projId和modelId查询dashboard对象(更改时查询)
     * @param request
     * @return
     */
    @PostMapping(value = "/dashboardInfo")
    public Dashboard findDashboardInfoByIds(@RequestBody RtrvDashboardRequest request) {
        LOG.info("findDashboardInfoByIds 开始执行:{}");
        return this.dashboardService.findDashboardInfoByIds(request);
    }

    /**
     * 创建看板
     * @param request
     * @return
     */
    @PostMapping(value = "/createDashboard")
    public Dashboard createDashboard(@RequestBody CreateDashboardRequest request) {
        return this.dashboardService.createDashboard(request);
    }

    /**
     * 更改看板
     * @param request
     * @return
     */
    @PostMapping(value = "/updateDashboard")
    public Dashboard updateDashboard(@RequestBody UpdateDashboardRequest request) {
        return this.dashboardService.updateDashboard(request);
    }




//    /**
//     * 获取sprint
//     *
//     * @param request
//     * @return
//     */
//    @PostMapping("/rtrvSprint")
//    public ResponseEntity<?> rtrvSprint(@RequestBody RtrvSprintRequest request) throws SQLException {
//        LOG.info("开始执行 updateSprint 方法.");
//        return this.dashboardService.rtrvSprint(request);
//    }
//    /**
//     * 更改sprint
//     *
//     * @param request
//     * @return
//     */
//    @PostMapping("/updateSprint")
//    public ResponseEntity<?> updateSprint(@RequestBody UpdateSprintRequest request) throws SQLException {
//        LOG.info("开始执行 updateSprint 方法.");
//        return this.dashboardService.updateSprint(request);
//    }



}
