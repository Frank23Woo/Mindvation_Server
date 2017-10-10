package com.mdvns.mdvn.staff.sapi.service.impl;

import com.mdvns.mdvn.staff.sapi.domain.RetrieveStaffListResponse;
import com.mdvns.mdvn.staff.sapi.domain.entity.Staff;
import com.mdvns.mdvn.staff.sapi.repository.StaffRepository;
import com.mdvns.mdvn.staff.sapi.service.StaffService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {
    /* 日志常量 */
    private static final Logger LOG = LoggerFactory.getLogger(StaffServiceImpl.class);

    private final String CLASS = this.getClass().getName();

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private Staff staff;

    /**
     * 获取全部模块
     * @return
     */
    @Override
    public RetrieveStaffListResponse rtrvStaffList() {
        RetrieveStaffListResponse retrieveStaffListResponse =new RetrieveStaffListResponse();
        List<Staff> staffList = this.staffRepository.findAll();
        Long count = this.staffRepository.getStaffCount();
        retrieveStaffListResponse.setStaffs(staffList);
        retrieveStaffListResponse.setTotalNumber(count);
        return retrieveStaffListResponse;
    }

}
