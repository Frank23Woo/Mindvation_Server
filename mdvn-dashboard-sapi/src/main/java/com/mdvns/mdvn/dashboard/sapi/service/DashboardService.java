package com.mdvns.mdvn.dashboard.sapi.service;

import com.mdvns.mdvn.dashboard.sapi.domain.*;
import com.mdvns.mdvn.dashboard.sapi.domain.entity.SprintInfo;

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
    List<SprintInfo> findDashboardInfoById(String projId);

    /*根据projId和modelId查询dashboard对象(更改时查询)*/
    List<SprintInfo> findDashboardInfoByIds(RtrvDashboardRequest request);

    /*创建看板*/
    SprintInfo createSprintInfo(CreateSprintInfoRequest request);

    /*更改看板*/
    List<SprintInfo> updateDashboard(UpdateSprintRequest request);

    SprintInfo updateSprintInfo(UpdateSprintInfoRequest request);

    SprintInfo addStory(AddStoryRequest request);

    SprintInfo updateSprintStartStatus(UpdateSprintStartStatusRequest request);

    SprintInfo updateSprintCloseStatus(UpdateSprintCloseStatusRequest request);

    List<SprintInfo> itSprint(Integer uuId);

}
