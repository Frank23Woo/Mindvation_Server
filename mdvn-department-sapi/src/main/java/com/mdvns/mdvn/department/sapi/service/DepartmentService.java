package com.mdvns.mdvn.department.sapi.service;

import com.mdvns.mdvn.department.sapi.domain.*;

import java.util.List;

public interface DepartmentService {

    DepartmentDetail createDepartment(CreateOrUpdateDepartmentRequest request);

    Boolean deleteDepartment(QueryDepartmentRequest request);

    DepartmentDetail getDepartment(QueryDepartmentRequest request);

    RtrvDepartmentListResponse getAllDepartment(RtrvDepartmentListRequest request);

    DepartmentDetail updateDepartment(CreateOrUpdateDepartmentRequest request);

}
