package com.mdvns.mdvn.staff.sapi.service;


import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.staff.sapi.domain.RetrieveStaffListResponse;
import com.mdvns.mdvn.staff.sapi.domain.RtrvStaffListByStaffIbListRequest;
import com.mdvns.mdvn.staff.sapi.domain.entity.Staff;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;
import java.util.List;

public interface StaffService {

    RetrieveStaffListResponse rtrvStaffList();

    List<Staff> rtrvStaffListByStaffIdList(RtrvStaffListByStaffIbListRequest request);

    Staff rtrvStaffInfo(String staffId);

    ResponseEntity<?> rtrvStaffListByStaffName(String name);

    ResponseEntity<?> rtrvStaffListByStaffName(Integer page, Integer pageSize, String name, String sortBy) throws SQLException;

    RestResponse<?> findById(String id);
}
