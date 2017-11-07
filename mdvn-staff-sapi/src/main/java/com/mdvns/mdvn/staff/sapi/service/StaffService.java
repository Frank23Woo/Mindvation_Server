package com.mdvns.mdvn.staff.sapi.service;



import com.mdvns.mdvn.staff.sapi.domain.*;
import com.mdvns.mdvn.staff.sapi.domain.entity.Staff;
import com.mdvns.mdvn.staff.sapi.domain.entity.StaffTag;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;
import java.util.List;

public interface StaffService {

    RetrieveStaffListResponse rtrvStaffList(RetrieveStaffListRequest request);

    List<Staff> rtrvStaffListById(RtrvStaffListByIdRequest request);

    Staff rtrvStaffInfo(String staffId);


    ResponseEntity<?> rtrvStaffListByStaffName(RtrvStaffListByNameRequest request) throws SQLException;


    CreateStaffResponse createStaff(CreateStaffRequest request);

    List<StaffTag> rtrvStaffTagList(String staffId);

    Boolean updateStaffDetail(UpdateStaffDetailRequest request);
    Boolean deleteStaff(String staffId);


    ResponseEntity<?> findByAccountAndPassword(String account, String password);

}
