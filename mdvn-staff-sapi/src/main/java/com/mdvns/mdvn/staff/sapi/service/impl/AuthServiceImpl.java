package com.mdvns.mdvn.staff.sapi.service.impl;

import com.mdvns.mdvn.common.beans.AssignAuthRequest;
import com.mdvns.mdvn.staff.sapi.domain.entity.StaffAuthInfo;
import com.mdvns.mdvn.staff.sapi.repository.StaffAuthInfoRepository;
import com.mdvns.mdvn.staff.sapi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;

public class AuthServiceImpl implements AuthService {

    @Autowired
    private StaffAuthInfoRepository authInfoRepository;

    @Autowired
    private StaffAuthInfo staffAuthInfo;



    @Override
    public ResponseEntity<?> assignAuth(AssignAuthRequest assignAuthRequest) {
        staffAuthInfo.setProjId(assignAuthRequest.getProjId());
        staffAuthInfo.setStaffId(assignAuthRequest.getAssigneeId());
        staffAuthInfo.setAuthCode(assignAuthRequest.getAuthCode());
        staffAuthInfo.setModuleId(assignAuthRequest.getModuleId());
        staffAuthInfo.setAssignerId(assignAuthRequest.getAssignerId());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        staffAuthInfo.setCreateTime(timestamp);
        StaffAuthInfo authInfo = this.authInfoRepository.save(staffAuthInfo);
        return ResponseEntity.ok(authInfo);
    }
}
