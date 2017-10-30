package com.mdvns.mdvn.dashboard.sapi.web;

import com.mdvns.mdvn.dashboard.sapi.domain.*;
import com.mdvns.mdvn.dashboard.sapi.domain.entity.SprintInfo;
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
     * 根据projId查询SprintInfo对象(判断是不是新建)
     * @param projId
     * @return
     */
    @PostMapping(value = "/dashboardInfos")
    public List<SprintInfo> findDashboardInfoById(@RequestBody String projId) {
        LOG.info("项目编号："+projId);
        return this.dashboardService.findDashboardInfoById(projId);
    }

    /**
     * 根据projId和modelId查询SprintInfo对象(更改时查询)
     * @param request
     * @return
     */
    @PostMapping(value = "/dashboardInfo")
    public List<SprintInfo> findDashboardInfoByIds(@RequestBody RtrvDashboardRequest request) {
        LOG.info("findDashboardInfoByIds 开始执行:{}");
        return this.dashboardService.findDashboardInfoByIds(request);
    }

    /**
     * 创建SprintInfo
     * @param request
     * @return
     */
    @PostMapping(value = "/createSprintInfo")
    public SprintInfo createSprintInfo(@RequestBody CreateSprintInfoRequest request) {
        return this.dashboardService.createSprintInfo(request);
    }

    /**
     * 更改List<SprintInfo>
     * @param request
     * @return
     */
    @PostMapping(value = "/updateDashboard")
    public List<SprintInfo> updateDashboard(@RequestBody UpdateSprintRequest request) {
        return this.dashboardService.updateDashboard(request);
    }

    /**
     * 更改SprintInfo
     * @param request
     * @return
     */
    @PostMapping(value = "/updateSprintInfo")
    public SprintInfo updateSprintInfo(@RequestBody UpdateSprintInfoRequest request) {
        return this.dashboardService.updateSprintInfo(request);
    }





}
