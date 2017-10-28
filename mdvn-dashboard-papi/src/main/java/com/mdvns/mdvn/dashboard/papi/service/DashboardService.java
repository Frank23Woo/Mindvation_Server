package com.mdvns.mdvn.dashboard.papi.service;


import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.dashboard.papi.domain.AssignStoryListByItRequest;
import com.mdvns.mdvn.dashboard.papi.domain.RtrvAllStoryListRequest;
import com.mdvns.mdvn.dashboard.papi.domain.UpdateDashboardRequest;
import org.springframework.http.ResponseEntity;

public interface DashboardService {

    RestResponse rtrvStoryList(RtrvAllStoryListRequest request);

    RestResponse updateDashboard(UpdateDashboardRequest request);

    RestResponse assignSprint(AssignStoryListByItRequest request);

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
