package com.mdvns.mdvn.dashboard.papi.service;


import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.dashboard.papi.domain.*;

public interface DashboardService {

    RestResponse rtrvStoryList(RtrvAllStoryListRequest request);

    RestResponse updateDashboard(UpdateDashboardRequest request);

    RestResponse updateMyDashboard(UpdateMyDashboardRequest request);

    RestResponse assignSprint(AssignStoryListByItRequest request);
    RestResponse getMyDashboardInfos(RtrvMyDashboardInfoRequest request);
    RestResponse updateSprintStartStatus(UpdateSprintStartStatusRequest request);
    RestResponse updateSprintCloseStatus(UpdateSprintCloseStatusRequest request);
    RestResponse itSprints(RtrvItSprintsRequest request);

//    ResponseEntity<?> createDashboard(CreateDashboardRequest request);
//
//    ResponseEntity<?> rtrvDashboardList(RetrieveDashboardListRequest request);
//
//    ResponseEntity<?> updateQuoteCnt(String dashboardId);
//
//    ResponseEntity<?> findByName(String name);
//
//    ResponseEntity<?> findById(String dashboardId);
}
