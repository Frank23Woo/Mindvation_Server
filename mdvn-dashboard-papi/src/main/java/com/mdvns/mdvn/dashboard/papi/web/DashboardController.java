package com.mdvns.mdvn.dashboard.papi.web;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.dashboard.papi.domain.*;
import com.mdvns.mdvn.dashboard.papi.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping(value= {"/dashboard", "/v1.0/dashboard"})
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    /**
     * 进入dashboard,首先获取product backlogs信息（story列表）
     * @param request
     * @return
     */
    @PostMapping(value = "/rtrvStoryList")
    public RestResponse rtrvStoryList(@RequestBody RtrvAllStoryListRequest request) {
        return this.dashboardService.rtrvStoryList(request);
    }

    /**
     * 进入dashboard,首先获取product backlogs信息（story列表）
     * @param request
     * @return
     */
    @PostMapping(value = "/updateDashboard")
    public RestResponse updateDashboard(@RequestBody UpdateDashboardRequest request) {
        return this.dashboardService.updateDashboard(request);
    }

    /**
     * 更改个人dashboard
     * @param request
     * @return
     */
    @PostMapping(value = "/updateMyDashboard")
    public RestResponse updateMyDashboard(@RequestBody UpdateMyDashboardRequest request) {
        return this.dashboardService.updateMyDashboard(request);
    }
    /**
     * 获取个人dashboard
     * @param request
     * @return
     */
    @PostMapping(value = "/getMyDashboardInfos")
    public RestResponse getMyDashboardInfos(@RequestBody RtrvMyDashboardInfoRequest request) {
        return this.dashboardService.getMyDashboardInfos(request);
    }

    /**
     * 进入dashboard,点击按钮，按迭代计划模板分storylist
     * @param request
     * @return
     */
    @PostMapping(value = "/assignSprint")
    public RestResponse assignSprint(@RequestBody AssignStoryListByItRequest request) {
        return this.dashboardService.assignSprint(request);
    }

    /**
     * 根据Id删除Dashboard
     *
     * @param dashboardId
     * @return
     */
    @PostMapping(value = "/deleteDashboard")
    public Integer deleteDashboard(String dashboardId) {
        return null;
    }

}
