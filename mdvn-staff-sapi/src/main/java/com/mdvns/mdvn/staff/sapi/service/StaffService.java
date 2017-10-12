package com.mdvns.mdvn.staff.sapi.service;


import com.mdvns.mdvn.staff.sapi.domain.RetrieveStaffListResponse;
import com.mdvns.mdvn.staff.sapi.domain.RtrvStaffListByStaffIbListRequest;
import com.mdvns.mdvn.staff.sapi.domain.entity.Staff;

import java.util.List;

public interface StaffService {

    RetrieveStaffListResponse rtrvStaffList();

    List<Staff> rtrvStaffListByStaffIdList(RtrvStaffListByStaffIbListRequest request);

    Staff rtrvStaffInfo(String staffId);

}
