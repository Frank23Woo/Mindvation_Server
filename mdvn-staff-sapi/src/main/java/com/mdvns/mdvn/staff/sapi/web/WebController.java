package com.mdvns.mdvn.staff.sapi.web;


import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.common.beans.exception.BusinessException;
import com.mdvns.mdvn.staff.sapi.domain.RetrieveStaffListResponse;
import com.mdvns.mdvn.staff.sapi.domain.RtrvStaffListByNameRequest;
import com.mdvns.mdvn.staff.sapi.domain.RtrvStaffListByStaffIbListRequest;
import com.mdvns.mdvn.staff.sapi.domain.entity.Staff;
import com.mdvns.mdvn.staff.sapi.service.StaffService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;


@RestController
public class WebController {
    private Logger LOG = LoggerFactory.getLogger(WebController.class);
    private final String CLASS = this.getClass().getName();
    @Autowired
    private StaffService staffService;

    /**
     * 获取全部成员
     * @return
     */
    @PostMapping(value = "/staffList")
    public RetrieveStaffListResponse  rtrvStaffList() {
        return this.staffService.rtrvStaffList();
    }

    /**
     * 通过staffId的list获取他们的对象列表
     */
    @PostMapping(value = "/rtrvStaffListByStaffIdList")
    public List<Staff> rtrvStaffListByStaffIdList(@RequestBody RtrvStaffListByStaffIbListRequest request) {
        return this.staffService.rtrvStaffListByStaffIdList(request);
    }

    /**
     * 通过staffId获取单条staff对象
     * @param staffId
     * @return
     */
    @PostMapping(value = "/rtrvStaffInfo")
    public Staff rtrvStaffInfo(@RequestBody String staffId) {
        return this.staffService.rtrvStaffInfo(staffId);
    }


    /**
     * 通过staff的name获取他们的对象列表(模糊查询)(分页查询)
     * @param request
     * @return
     * @throws SQLException
     * @throws BusinessException
     */
    @PostMapping(value = "/rtrvStaffListByName")
    public ResponseEntity<?> rtrvStaffListByName(@RequestBody RtrvStaffListByNameRequest request) throws SQLException, BusinessException {
        LOG.info("开始执行{} rtrvStaffListByName()方法.", this.CLASS);
        Integer page = request.getPage();
        Integer pageSize = request.getPageSize();
        if (null==page||pageSize==null) {
            return this.staffService.rtrvStaffListByStaffName(request.getName());
        }
//        page, pageSize不能小于1
        if (page<1||pageSize<1) {
            LOG.error("获取name列表，分页参数不正确!");
            throw new IllegalArgumentException("分页参数不正确.");
        }
        return this.staffService.rtrvStaffListByStaffName((page-1), pageSize,request.getName(), request.getSortBy());
    }


    /**
     * 根据指定Id获取Staff信息
     * @param id
     * @return
     */
    @GetMapping(value = "/staff/{id}")
    public RestResponse<?> findById(@PathVariable String id) {

        return this.staffService.findById(id);
    }

}
