package com.mdvns.mdvn.staff.papi.web;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.staff.papi.domain.RetrieveStaffListRequest;
import com.mdvns.mdvn.staff.papi.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value= {"/staff", "/v1.0/staff"})
public class StaffController {

    @Autowired
    private StaffService staffService;

    @PostMapping(value = "/rtrvStaffList")
    public RestResponse rtrvStaffList(@RequestBody RetrieveStaffListRequest retrieveStaffListRequest) {
        return this.staffService.rtrvStaffList(retrieveStaffListRequest);
    }

}
