package com.mdvns.mdvn.staff.sapi.web;


import com.mdvns.mdvn.staff.sapi.domain.RetrieveStaffListResponse;
import com.mdvns.mdvn.staff.sapi.service.StaffService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;



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


}
