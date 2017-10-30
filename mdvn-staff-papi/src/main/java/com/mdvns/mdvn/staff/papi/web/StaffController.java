package com.mdvns.mdvn.staff.papi.web;

import com.mdvns.mdvn.common.beans.*;
import com.mdvns.mdvn.common.beans.LoginRequest;
import com.mdvns.mdvn.staff.papi.domain.*;
import com.mdvns.mdvn.staff.papi.domain.Staff;
import com.mdvns.mdvn.staff.papi.service.AuthService;
import com.mdvns.mdvn.staff.papi.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = {"/staff", "/v1.0/staff"})
public class StaffController {

    @Autowired
    private StaffService staffService;

    @Autowired
    private AuthService authService;

    /**
     * 登录
     * @param loginRequest
     * @return
     */
    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        System.out.print(loginRequest);
        return this.staffService.login(loginRequest);
    }

    /**
     * 获取staff列表信息
     *
     * @param retrieveStaffListRequest
     * @return
     */
    @PostMapping(value = "/rtrvStaffList")
    public RestResponse rtrvStaffList(@RequestBody RetrieveStaffListRequest retrieveStaffListRequest) {
        return this.staffService.rtrvStaffList(retrieveStaffListRequest);
    }

    /**
     * 通过staffID的list获取staff对象列表信息
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/rtrvStaffListByStaffIdList")
    public RestResponse rtrvStaffListByStaffIdList(@RequestBody RtrvStaffListByStaffIbListRequest request) {
        return this.staffService.rtrvStaffListByStaffIdList(request);
    }

    /**
     * 通过staffID获取staff对象信息
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/rtrvStaffInfo")
    public RestResponse rtrvStaffInfo(@RequestBody RtrvStaffInfoRequest request) {
        return this.staffService.rtrvStaffInfo(request);
    }

    /**
     * 模糊查询
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/rtrvStaffListByName")
    public ResponseEntity<?> rtrvStaffListByName(@RequestBody RtrvStaffListByNameRequest request) {
        return this.staffService.rtrvStaffListByName(request);
    }

    /**
     * 根据指定Id获取Staff信息
     * @param id
     * @return
     */
    @PostMapping(value = "/{id}")
    public RestResponse<Staff> retrieve(@PathVariable String id) {
        return this.staffService.retrieve(id);
    }

    /**
     * 给项目相关员工添加权限
     * @param authRequest
     * @return
     */
    @PostMapping(value = "/assignAuth")
    public ResponseEntity<?> assignAuth(@RequestBody AssignAuthRequest authRequest) {
        ResponseEntity<?> responseEntity = this.authService.assignAuth(authRequest);
        return responseEntity;
    }


    @PostMapping(value = "/rtrvAuth")
    public ResponseEntity<?> rtrvAuth(@RequestBody RtrvStaffAuthInfoRequest rtrvAuthRequest) {

        return this.authService.rtrvAuth(rtrvAuthRequest);
    }

    @PostMapping(value = "/removeAuth")
    public ResponseEntity<?> removeAuth(@RequestBody RemoveAuthRequest removeAuthRequest) {
        return this.authService.removeAuth(removeAuthRequest);
    }

    @PostMapping(value = "/createStaff")
    public ResponseEntity<?> createStaff(@RequestBody CreateStaffRequest request) {
        return this.staffService.createStaff(request);
    }

    @PostMapping(value = "/deleteStaff/{staffId}")
    public ResponseEntity<?> deleteStaff(@PathVariable String staffId) {
        return this.staffService.deleteStaff(staffId);
    }

    @PostMapping(value = "/rtrvStaffDetail/{staffId}")
    public ResponseEntity<?> rtrvStaffDetail(@PathVariable String staffId) {
        return this.staffService.rtrvStaffDetail(staffId);
    }

    @PostMapping(value = "/updateStaffDetail")
    public ResponseEntity<?> updateStaffDetail(@RequestBody UpdateStaffDetailRequest request) {
        return this.staffService.updateStaffDetail(request);
    }

}
