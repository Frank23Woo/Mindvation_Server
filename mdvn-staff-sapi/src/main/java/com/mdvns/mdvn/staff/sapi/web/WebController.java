package com.mdvns.mdvn.staff.sapi.web;


import com.mdvns.mdvn.staff.sapi.domain.RetrieveStaffListResponse;
import com.mdvns.mdvn.staff.sapi.domain.RtrvStaffListByStaffIbListRequest;
import com.mdvns.mdvn.staff.sapi.domain.entity.Staff;
import com.mdvns.mdvn.staff.sapi.service.StaffService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class WebController {
    private Logger LOG = LoggerFactory.getLogger(WebController.class);

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



}
