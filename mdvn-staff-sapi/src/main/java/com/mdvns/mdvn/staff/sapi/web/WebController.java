package com.mdvns.mdvn.staff.sapi.web;


import com.mdvns.mdvn.common.beans.*;
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
    public List<Staff> rtrvStaffListById(@RequestBody RtrvStaffListByIdRequest request) {
        return this.staffService.rtrvStaffListById(request);
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
     * @throws
     */
    @PostMapping(value = "/rtrvStaffListByName")
    public ResponseEntity<?> rtrvStaffListByName(@RequestBody RtrvStaffListByNameRequest request) throws SQLException, BusinessException {
        LOG.info("开始执行{} rtrvStaffListByName()方法.", this.CLASS);
        Integer page = request.getPage();
        Integer pageSize = request.getPageSize();
        if (null==page||pageSize==null) {
            return this.staffService.rtrvStaffListByStaffName(request);
        }
//        page, pageSize不能小于1
        if (page<1||pageSize<1) {
            LOG.error("获取name列表，分页参数不正确!");
            throw new IllegalArgumentException("分页参数不正确.");
        }
        return this.staffService.rtrvStaffListByStaffName(request);
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

    /**
     * 获取员工权限
     * @param rtrvAuthRequest
     * @return
     */
    @PostMapping(value = "/rtrvAuth")
    public ResponseEntity<?> assignAuth(@RequestBody RtrvStaffAuthInfoRequest rtrvAuthRequest) {

        return this.authService.rtrvAuth(rtrvAuthRequest);
    }

    /**
     * 添加员工
     * @param request
     * @return
     */
    @PostMapping(value = "/createStaff")
    public CreateStaffResponse createStaff(@RequestBody CreateStaffRequest request) {
        return this.staffService.createStaff(request);
    }

    /**
     *查询所有StaffID为指定id的StaffTag对象
     * @param staffId
     * @return
     */
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

    /**
     * 取消指定项目的指定模块所有的权限
     * @param projId
     * @param hierarchyId
     * @return
     */
    @RequestMapping(value = "/removeAllAuth/{projId}/{hierarchyId}")
    public ResponseEntity<?> removeAllAuth(@PathVariable String projId, @PathVariable String hierarchyId) {
        return this.authService.removeAllAuth(projId, hierarchyId);
    }

    /**
     * 获取拥有指定标签集中任意标签的所有StaffTag
     * @param tags
     * @return
     */
    @PostMapping(value = "/rtrvStaffByTags")
    public ResponseEntity<?> getStaffByTags(@RequestBody List<String> tags) {
        return this.staffService.getStaffByTags(tags);
    }

    /**
     * 查询name以指定字符串开始的所有Staff
     * @param startingStr
     * @return
     */
    @PostMapping(value = "/rtrvStaffByNameStarting/{startingStr}")
    public ResponseEntity<?> findByNameStartingWith(@PathVariable String startingStr) {

        return this.staffService.findByNameStartingWith(startingStr);
    }

    /**
     * 查询staffId为指定id的所有tagId集合
     * @param staffId
     * @return
     */
    @PostMapping(value = "/rtrvTagsByStaffId/{staffId}")
    public List<String> rtrvTagsByStaffId(@PathVariable String staffId) {
        return this.staffService.rtrvTagsByStaffId(staffId);
    }
    @PostMapping(value = "/updateStaffPassword")
    public Boolean updateStaffPassword(@RequestBody UpdatePasswordRequest request) {
        return this.staffService.updateStaffPassword(request);
    }
}

