package com.mdvns.mdvn.staff.sapi.web;


import com.mdvns.mdvn.common.beans.AssignAuthRequest;
import com.mdvns.mdvn.common.beans.LoginRequest;
import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.common.beans.RtrvStaffAuthInfoRequest;
import com.mdvns.mdvn.common.beans.exception.BusinessException;
import com.mdvns.mdvn.staff.sapi.domain.*;
import com.mdvns.mdvn.staff.sapi.domain.entity.Staff;
import com.mdvns.mdvn.staff.sapi.domain.entity.StaffTag;
import com.mdvns.mdvn.staff.sapi.service.AuthService;
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

    @Autowired
    private AuthService authService;

    /**
     * 获取全部成员
     * @return
     */
    @PostMapping(value = "/staffList")
    public RetrieveStaffListResponse  rtrvStaffList(@RequestBody RetrieveStaffListRequest request) {
        return this.staffService.rtrvStaffList(request);
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
     * 根据指定account获取Staff信息
     *
     * @param loginRequest
     * @return
     */
    @PostMapping(value = "/staff")
    public ResponseEntity<?> findByAccountAndPassword(@RequestBody LoginRequest loginRequest) {

        return this.staffService.findByAccountAndPassword(loginRequest.getAccount(), loginRequest.getPassword());
    }

    /**
     * 添加权限
     * @param assignAuthRequest
     * @return
     */
    @PostMapping(value = "/staffAuth")
    public ResponseEntity<?> assignAuth(@RequestBody AssignAuthRequest assignAuthRequest) {

        return this.authService.assignAuth(assignAuthRequest);
    }

    @PostMapping(value = "/rtrvAuth")
    public ResponseEntity<?> assignAuth(@RequestBody RtrvStaffAuthInfoRequest rtrvAuthRequest) {

        return this.authService.rtrvAuth(rtrvAuthRequest);
    }

    @PostMapping(value = "/createStaff")
    public CreateStaffResponse createStaff(@RequestBody CreateStaffRequest request) {
        return this.staffService.createStaff(request);
    }


    @PostMapping(value = "/rtrvStaffTagList")
    public List<StaffTag> rtrvStaffTagList(@RequestBody String staffId) {
        return this.staffService.rtrvStaffTagList(staffId);
    }


    @PostMapping(value = "/updateStaffDetail")
    public Boolean updateStaffDetail(@RequestBody UpdateStaffDetailRequest request) {
        return this.staffService.updateStaffDetail(request);
    }

    @PostMapping(value = "/deleteStaff/{staffId}")
    public Boolean deleteStaff(@PathVariable String staffId) {
        return this.staffService.deleteStaff(staffId);
    }
}
