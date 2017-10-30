package com.mdvns.mdvn.staff.sapi.service.impl;

import com.mdvns.mdvn.common.beans.AssignAuthRequest;
import com.mdvns.mdvn.common.beans.RtrvAuthRequest;
import com.mdvns.mdvn.staff.sapi.domain.entity.StaffAuthInfo;
import com.mdvns.mdvn.staff.sapi.repository.StaffAuthInfoRepository;
import com.mdvns.mdvn.staff.sapi.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger LOG = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    private StaffAuthInfoRepository authInfoRepository;

    @Autowired
    private StaffAuthInfo staffAuthInfo;



    @Override
    public ResponseEntity<?> assignAuth(AssignAuthRequest assignAuthRequest) {

        List<String> assignees = assignAuthRequest.getAssignees();
        String assignerId = assignAuthRequest.getAssignerId();
        if (assignees.size() == 1 && assignerId.equals(assignees.get(0))) {
            assignAuth(assignAuthRequest.getProjId(), assignerId, assignees.get(0), assignAuthRequest.getHierarchyId());
        }

        List<StaffAuthInfo> staffAuthInfos = new ArrayList<StaffAuthInfo>();
        for (String assigneeId : assignees) {
            staffAuthInfo = new StaffAuthInfo();
            staffAuthInfo.setProjId(assignAuthRequest.getProjId());
            staffAuthInfo.setStaffId(assigneeId);
            staffAuthInfo.setAuthCode(assignAuthRequest.getAuthCode());
            staffAuthInfo.setHierarchyId(assignAuthRequest.getHierarchyId());
            staffAuthInfo.setAssignerId(assignAuthRequest.getAssignerId());
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            staffAuthInfo.setCreateTime(timestamp);
            staffAuthInfo.setIsDeleted(0);
            staffAuthInfo = this.authInfoRepository.save(staffAuthInfo);
            staffAuthInfos.add(staffAuthInfo);
        }

        LOG.info("添加权限的request为：{}", assignAuthRequest.toString());

        LOG.info("SAPI添加权限结束：{}", staffAuthInfo.toString());
        LOG.info("SAPI添加权限结束, staffAuthInfo 的值是:{}", staffAuthInfo.toString());
        return ResponseEntity.ok(staffAuthInfos);
    }

    public void assignAuth(String projId, String assignerId, String assigneeId, String hierarchyId) {

    }
    /**
     * 获取权限信息
     * @param rtrvAuthRequest
     * @return
     */
    @Override
    public ResponseEntity<?> rtrvAuth(RtrvAuthRequest rtrvAuthRequest) {
        String projId = rtrvAuthRequest.getProjId();
        String staffId = rtrvAuthRequest.getStaffId();
        String hierarchyId = rtrvAuthRequest.getHierarchyId();
        StaffAuthInfo staffAuthInfo = this.authInfoRepository.findByProjIdAndStaffIdAndHierarchyId(projId, staffId, hierarchyId);

        return ResponseEntity.ok(staffAuthInfo);
    }
}
