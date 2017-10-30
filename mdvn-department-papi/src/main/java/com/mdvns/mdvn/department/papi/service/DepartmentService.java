package com.mdvns.mdvn.department.papi.service;

import com.mdvns.mdvn.common.beans.DepartmentDetail;
import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.department.papi.domain.CreateOrUpdateDepartmentRequest;
import com.mdvns.mdvn.department.papi.domain.QueryDepartmentRequest;
import com.mdvns.mdvn.department.papi.domain.RtrvDepartmentListRequest;
import com.mdvns.mdvn.department.papi.domain.RtrvDepartmentListResponse;

import java.util.List;


public interface DepartmentService {
    RestResponse<DepartmentDetail> createDepartment(CreateOrUpdateDepartmentRequest request);

    RestResponse<Boolean> deleteDepartment(QueryDepartmentRequest request);

    RestResponse<RtrvDepartmentListResponse> getAllDepartment(RtrvDepartmentListRequest request);

    RestResponse<DepartmentDetail> updateDepartment(CreateOrUpdateDepartmentRequest request);

    RestResponse<DepartmentDetail> getDepartment(QueryDepartmentRequest request);

    RestResponse<List<DepartmentDetail>> getAllDepartment();

}
