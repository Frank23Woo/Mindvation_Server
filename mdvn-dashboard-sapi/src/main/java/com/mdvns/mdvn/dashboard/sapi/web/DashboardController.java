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
     * @param request
     * @return
     */
    @PostMapping(value = "/dashboardInfos")
    public List<SprintInfo> findDashboardInfoById(@RequestBody RtrvAllStoryListRequest request) {
        LOG.info("开始执行项目findDashboardInfoById：");
        return this.dashboardService.findDashboardInfoById(request);
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
     * 成员查询所有dashboard信息
     * @param request
     * @return
     */
    @PostMapping(value = "/findAllDashboardById")
    public List<SprintInfo> findAllDashboardById(@RequestBody RtrvAllStoryListRequest request) {
        LOG.info("开始执行findAllDashboardById：");
        return this.dashboardService.findAllDashboardById(request);
    }

    /**
     * 根据projId和modelId查询SprintInfo对象(成员查询所有dashboard信息)
     * @param request
     * @return
     */
    @PostMapping(value = "/findAllDashboardInfoByIds")
    public List<SprintInfo> findAllDashboardInfoByIds(@RequestBody RtrvDashboardRequest request) {
        LOG.info("findAllDashboardInfoByIds 开始执行:{}");
        return this.dashboardService.findAllDashboardInfoByIds(request);
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
     * 更改看板（移动端）
     * @param request
     * @return
     */
    @PostMapping(value = "/updateDashboardForAndroid")
    public SprintInfo updateDashboardForAndroid(@RequestBody UpdateDashboardForAndroidRequest request) {
        return this.dashboardService.updateDashboardForAndroid(request);
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

    /**
     * 更改SprintInfo(start)
     * @param request
     * @return
     */
    @PostMapping(value = "/updateSprintStartStatus")
    public SprintInfo updateSprintStartStatus(@RequestBody UpdateSprintStartStatusRequest request) {
        return this.dashboardService.updateSprintStartStatus(request);
    }

    /**
     * 更改SprintInfo(close)
     * @param request
     * @return
     */
    @PostMapping(value = "/updateSprintCloseStatus")
    public SprintInfo updateSprintCloseStatus(@RequestBody UpdateSprintCloseStatusRequest request) {
        return this.dashboardService.updateSprintCloseStatus(request);
    }

    /**
     * 更改SprintInfo（创建story时的一个动作·）
     * @param request
     * @return
     */
    @PostMapping(value = "/addStory")
    public SprintInfo addStory(@RequestBody AddStoryRequest request) {
        return this.dashboardService.addStory(request);
    }

    /**
     * 获取下两个SprintInfo
     * @param uuId
     * @return
     */
    @PostMapping(value = "/itSprint")
    public List<SprintInfo> itSprint(@RequestBody Integer uuId) {
        return this.dashboardService.itSprint(uuId);
    }

    /**
     * 通过projId和creatorId和name查询sprint的UUid
     * @param request
     * @return
     */
    @PostMapping(value = "/findUuIdByIds")
    public Integer findUuIdByIds(@RequestBody FindUuIdRequest request) {
        return this.dashboardService.findUuIdByIds(request);
    }
}
