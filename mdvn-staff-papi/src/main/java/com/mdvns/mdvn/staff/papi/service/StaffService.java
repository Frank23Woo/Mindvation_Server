package com.mdvns.mdvn.staff.papi.service;

import com.mdvns.mdvn.common.beans.LoginRequest;
import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.staff.papi.domain.*;
import org.springframework.http.ResponseEntity;


public interface StaffService {

    RestResponse rtrvStaffList(RetrieveStaffListRequest request);

    RestResponse rtrvStaffListByStaffIdList(RtrvStaffListByStaffIbListRequest request);

    RestResponse rtrvStaffInfo(RtrvStaffInfoRequest request);

    ResponseEntity<?> rtrvStaffListByName(RtrvStaffListByNameRequest request);

    RestResponse<Staff> retrieve(String id);

    ResponseEntity<?> login(LoginRequest loginRequest);

    ResponseEntity<?> createStaff(CreateStaffRequest request);

    ResponseEntity<?> rtrvStaffDetail(String staffId);

    ResponseEntity<?> updateStaffDetail(UpdateStaffDetailRequest request);

    ResponseEntity<?> deleteStaff(String staffId);



}
