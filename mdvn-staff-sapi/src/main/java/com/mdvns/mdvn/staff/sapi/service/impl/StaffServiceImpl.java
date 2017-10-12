package com.mdvns.mdvn.staff.sapi.service.impl;

import com.mdvns.mdvn.common.beans.exception.BusinessException;
import com.mdvns.mdvn.staff.sapi.domain.RetrieveStaffListResponse;
import com.mdvns.mdvn.staff.sapi.domain.RtrvStaffListByStaffIbListRequest;
import com.mdvns.mdvn.staff.sapi.domain.entity.Staff;
import com.mdvns.mdvn.staff.sapi.repository.StaffRepository;
import com.mdvns.mdvn.staff.sapi.service.StaffService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    /**
     * 通过staffIdList获取staff对象列表
     * @param request
     * @return
     */
    @Override
    public List<Staff> rtrvStaffListByStaffIdList(RtrvStaffListByStaffIbListRequest request) {
        List<Staff> list = new ArrayList<>();
        for (int i = 0; i < request.getStaffIdList().size(); i++) {
            System.out.println(request.getStaffIdList().get(i));
            Staff staffInfo = this.staffRepository.findByStaffId(request.getStaffIdList().get(i));
            if (null == staffInfo) {
                LOG.error("该id的员工在员工库中不存在.", staffInfo);
                throw new BusinessException(staffInfo + "该id的员工在员工库中不存在.");
            }else{
                list.add(staffInfo);
            }
        }
        return list;
    }

    /**
     * 通过staffId获取单条staff对象
     * @param staffId
     * @return
     */
    @Override
    public Staff rtrvStaffInfo(String staffId) {
        return this.staffRepository.findByStaffId(staffId);
    }

}
