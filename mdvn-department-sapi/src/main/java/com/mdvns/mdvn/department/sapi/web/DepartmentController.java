package com.mdvns.mdvn.department.sapi.web;

import com.mdvns.mdvn.department.sapi.domain.*;
import com.mdvns.mdvn.department.sapi.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping(value = "/createDepartment")
    private DepartmentDetail createDepartment(@RequestBody CreateOrUpdateDepartmentRequest request) {
        return departmentService.createDepartment(request);
    }

    @PostMapping(value = "/deleteDepartment")
    private Boolean deleteDepartment(@RequestBody QueryDepartmentRequest request) {
        return departmentService.deleteDepartment(request);
    }

    @PostMapping(value = "/rtrvDepartment")
    private DepartmentDetail getDepartment(@RequestBody QueryDepartmentRequest request) {
        return departmentService.getDepartment(request);
    }

    @PostMapping(value = "/rtrvDepartmentList")
    private RtrvDepartmentListResponse getAllDepartment(@RequestBody RtrvDepartmentListRequest request) {
        return departmentService.getAllDepartment(request);
    }

    @PostMapping(value = "/updateDepartment")
    private DepartmentDetail updateDepartment(@RequestBody CreateOrUpdateDepartmentRequest request) {
        return departmentService.updateDepartment(request);
    }

}
