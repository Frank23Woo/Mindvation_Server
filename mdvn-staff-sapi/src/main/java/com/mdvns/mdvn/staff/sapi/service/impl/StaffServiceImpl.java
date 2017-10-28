package com.mdvns.mdvn.staff.sapi.service.impl;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.common.beans.exception.BusinessException;
import com.mdvns.mdvn.common.utils.RestResponseUtil;
import com.mdvns.mdvn.staff.sapi.domain.RetrieveStaffListResponse;
import com.mdvns.mdvn.staff.sapi.domain.RtrvStaffListByNameResponse;
import com.mdvns.mdvn.staff.sapi.domain.RtrvStaffListByStaffIbListRequest;
import com.mdvns.mdvn.staff.sapi.domain.entity.Staff;
import com.mdvns.mdvn.staff.sapi.repository.StaffRepository;
import com.mdvns.mdvn.staff.sapi.service.StaffService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
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

    /**
     * 简单模糊查询staff(不分页不存在，后续删除)
     * @param name
     * @return
     */
    @Override
    public ResponseEntity<?> rtrvStaffListByStaffName(String name) {
        String flag = "%"+name+"%";

        RtrvStaffListByNameResponse rtrvStaffListByNameResponse = new RtrvStaffListByNameResponse();
        List<Staff> staffList = this.staffRepository.rtrvStaffListByStaffName(flag);
        Long count = this.staffRepository.getStaffCount();
        rtrvStaffListByNameResponse.setStaffs(staffList);
        rtrvStaffListByNameResponse.setTotalNumber(count);
        return ResponseEntity.ok(rtrvStaffListByNameResponse);
    }

    /**
     * 姓名分页查询（模糊）
     * @param page
     * @param pageSize
     * @param sortBy
     * @return
     * @throws SQLException
     */
    @Override
    public ResponseEntity<?> rtrvStaffListByStaffName(Integer page, Integer pageSize, String name, String sortBy) throws SQLException {
        RtrvStaffListByNameResponse rtrvStaffListByNameResponse = new RtrvStaffListByNameResponse();
        sortBy = (sortBy == null) ? "staffId" : sortBy;
        PageRequest pageable = new PageRequest(page, pageSize,Sort.Direction.DESC, sortBy);
        Page<Staff> staffPage = null;
        String flag = "%"+name+"%";
        List<Staff> staffList =  this.staffRepository.rtrvStaffListByStaffName(flag);
        staffPage = this.staffRepository.findByNameLike(flag,pageable);
//        staffPage = this.staffRepository.findAll(pageable);
        rtrvStaffListByNameResponse.setStaffs(staffPage.getContent());
        rtrvStaffListByNameResponse.setTotalNumber(staffPage.getTotalElements());
        return ResponseEntity.ok(rtrvStaffListByNameResponse);
    }

    /**
     * 根据指定id查询staff
     * @param id
     * @return
     */
    @Override
    public RestResponse<?> findById(String id) {
        Staff staff = this.staffRepository.findByStaffId(id);
        return RestResponseUtil.success(staff);
    }

}
