package com.mdvns.mdvn.department.sapi.service;

import com.mdvns.mdvn.department.sapi.domain.*;
import com.mdvns.mdvn.department.sapi.domain.entity.Department;
import com.mdvns.mdvn.department.sapi.domain.entity.Position;

import java.util.List;

public interface DepartmentService {

    DepartmentDetail createDepartment(CreateOrUpdateDepartmentRequest request);

    Boolean deleteDepartment(QueryDepartmentRequest request);

    DepartmentDetail getDepartment(QueryDepartmentRequest request);

    RtrvDepartmentListResponse getAllDepartment(RtrvDepartmentListRequest request);

    DepartmentDetail updateDepartment(CreateOrUpdateDepartmentRequest request);

    List<Department> findDepartmentListByIds(String ids);

    List<Position> findPositionListByIds(String ids);

    Position findPositionById(Integer id);

    DepartmentDetail findDepartmentById(String departmentId);
}
