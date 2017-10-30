package com.mdvns.mdvn.department.papi.web;

import com.mdvns.mdvn.common.beans.DepartmentDetail;
import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.department.papi.domain.CreateOrUpdateDepartmentRequest;
import com.mdvns.mdvn.department.papi.domain.QueryDepartmentRequest;
import com.mdvns.mdvn.department.papi.domain.RtrvDepartmentListRequest;
import com.mdvns.mdvn.department.papi.domain.RtrvDepartmentListResponse;
import com.mdvns.mdvn.department.papi.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = {"/department", "/v1/department"})
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping(value = "/createDepartment")
    private RestResponse<DepartmentDetail> createDepartment(@RequestBody CreateOrUpdateDepartmentRequest request) {
        return departmentService.createDepartment(request);
    }

    @PostMapping(value = "/deleteDepartment")
    private RestResponse<Boolean> deleteDepartment(@RequestBody QueryDepartmentRequest request) {
        return departmentService.deleteDepartment(request);
    }

    @PostMapping(value = "/rtrvDepartment")
    private RestResponse<DepartmentDetail> getDepartment(@RequestBody QueryDepartmentRequest request) {
        return departmentService.getDepartment(request);
    }

    @PostMapping(value = "/rtrvDepartmentList")
    private RestResponse<RtrvDepartmentListResponse> getAllDepartment(@RequestBody RtrvDepartmentListRequest request) {
        return departmentService.getAllDepartment(request);
    }

    @PostMapping(value = "/updateDepartment")
    private RestResponse<DepartmentDetail> updateDepartment(@RequestBody CreateOrUpdateDepartmentRequest request) {
        return departmentService.updateDepartment(request);
    }


}
