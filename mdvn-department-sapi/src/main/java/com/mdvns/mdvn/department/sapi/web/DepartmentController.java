package com.mdvns.mdvn.department.sapi.web;

import com.mdvns.mdvn.department.sapi.domain.*;
import com.mdvns.mdvn.department.sapi.domain.entity.Department;
import com.mdvns.mdvn.department.sapi.domain.entity.Position;
import com.mdvns.mdvn.department.sapi.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/findDepartmentListByIds")
    private List<Department> findDepartmentListByIds(@RequestBody String ids){
        return departmentService.findDepartmentListByIds(ids);
    }

    @PostMapping(value = "/findPositionListByIds")
    private List<Position> findPositionListByIds(@RequestBody String ids) {
        return departmentService.findPositionListByIds(ids);
    }


    @PostMapping(value = "/findPositionById/{id}")
    private Position findPostionById(@PathVariable Integer id){
        return departmentService.findPositionById(id);
    }

//    @PostMapping(value = "/findPositionListByIds")
//    private List<Position> findPositionListByIds(@RequestBody String ids) {
//        return departmentService.findPositionListByIds(ids);
//    }
}
