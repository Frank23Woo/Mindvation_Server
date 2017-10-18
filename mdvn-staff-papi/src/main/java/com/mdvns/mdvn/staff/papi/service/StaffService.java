package com.mdvns.mdvn.staff.papi.service;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.staff.papi.domain.RetrieveStaffListRequest;
import com.mdvns.mdvn.staff.papi.domain.RtrvStaffInfoRequest;
import com.mdvns.mdvn.staff.papi.domain.RtrvStaffListByNameRequest;
import com.mdvns.mdvn.staff.papi.domain.RtrvStaffListByStaffIbListRequest;
import org.springframework.http.ResponseEntity;


public interface StaffService {

    RestResponse rtrvStaffList(RetrieveStaffListRequest request);

    RestResponse rtrvStaffListByStaffIdList(RtrvStaffListByStaffIbListRequest request);

    RestResponse rtrvStaffInfo(RtrvStaffInfoRequest request);

    ResponseEntity<?> rtrvStaffListByName(RtrvStaffListByNameRequest request);
}
