package com.mdvns.mdvn.department.papi.service.impl;

import com.mdvns.mdvn.common.beans.DepartmentDetail;
import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.common.beans.exception.ExceptionEnum;
import com.mdvns.mdvn.common.utils.RestResponseUtil;
import com.mdvns.mdvn.department.papi.config.UrlConfig;
import com.mdvns.mdvn.department.papi.domain.CreateOrUpdateDepartmentRequest;
import com.mdvns.mdvn.department.papi.domain.QueryDepartmentRequest;
import com.mdvns.mdvn.department.papi.domain.RtrvDepartmentListRequest;
import com.mdvns.mdvn.department.papi.domain.RtrvDepartmentListResponse;
import com.mdvns.mdvn.department.papi.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private RestResponse restResponse;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UrlConfig urlConfig;

    @Override
    public RestResponse<DepartmentDetail> createDepartment(CreateOrUpdateDepartmentRequest request) {
        if (request == null || StringUtils.isEmpty(request.getName())) {
            restResponse.setResponseCode(ExceptionEnum.PARAMS_EXCEPTION.getErroCode());
            restResponse.setResponseMsg("need department name");
            restResponse.setResponseMsg("ok");
            return restResponse;
        }

        DepartmentDetail departmentDetail = restTemplate.postForObject(urlConfig.getCreateDepartmentUrl(), request, DepartmentDetail.class);
        return RestResponseUtil.success(departmentDetail);
    }

    @Override
    public RestResponse<Boolean> deleteDepartment(QueryDepartmentRequest request) {
        if (request == null || StringUtils.isEmpty(request.getDepartmentId())) {
            restResponse.setResponseCode(ExceptionEnum.PARAMS_EXCEPTION.getErroCode());
            restResponse.setResponseMsg("need department id");
            return restResponse;
        }

        Boolean result = restTemplate.postForObject(urlConfig.getDeleteDepartmentUrl(), request, Boolean.class);

        return RestResponseUtil.success(result);
    }

    @Override
    public RestResponse<RtrvDepartmentListResponse> getAllDepartment(RtrvDepartmentListRequest request) {
        if (request == null) {
            restResponse.setResponseCode(ExceptionEnum.PARAMS_EXCEPTION.getErroCode());
            restResponse.setResponseMsg("need request body");
            return restResponse;
        }
        RtrvDepartmentListResponse result = restTemplate.postForObject(urlConfig.getRtrvDepartmentListUrl(), request, RtrvDepartmentListResponse.class);
        return RestResponseUtil.success(result);
    }

    @Override
    public RestResponse<DepartmentDetail> updateDepartment(CreateOrUpdateDepartmentRequest request) {
        if (request == null || StringUtils.isEmpty(request.getId()) || StringUtils.isEmpty(request.getName())) {
            restResponse.setResponseCode(ExceptionEnum.PARAMS_EXCEPTION.getErroCode());
            restResponse.setResponseMsg(ExceptionEnum.PARAMS_EXCEPTION.getErrorMsg());
            return restResponse;
        }

        DepartmentDetail departmentDetail = restTemplate.postForObject(urlConfig.getUpdateDepartmentUrl(), request, DepartmentDetail.class);
        if (departmentDetail == null) {
            return RestResponseUtil.error(HttpStatus.OK, ExceptionEnum.OBJECT_DOES_NOT_EXIST);
        } else {
            return RestResponseUtil.success(departmentDetail);
        }
    }

    @Override
    public RestResponse<DepartmentDetail> getDepartment(QueryDepartmentRequest request) {
        if (request == null || StringUtils.isEmpty(request.getDepartmentId())) {
            restResponse.setResponseCode(ExceptionEnum.PARAMS_EXCEPTION.getErroCode());
            restResponse.setResponseMsg(ExceptionEnum.PARAMS_EXCEPTION.getErrorMsg());
            return restResponse;
        }

        DepartmentDetail departmentDetail = restTemplate.postForObject(urlConfig.getRtrvDepartmentUrl(), request, DepartmentDetail.class);
        if (departmentDetail == null) {
            return RestResponseUtil.error(HttpStatus.OK, ExceptionEnum.OBJECT_DOES_NOT_EXIST);
        } else {
            return RestResponseUtil.success(departmentDetail);
        }
    }

    @Override
    public RestResponse<List<DepartmentDetail>> getAllDepartment() {
        RtrvDepartmentListRequest request = new RtrvDepartmentListRequest();
        request.setPage(0);
        request.setPageSize(Integer.MAX_VALUE);
        RtrvDepartmentListResponse result = restTemplate.postForObject(urlConfig.getRtrvDepartmentListUrl(), request, RtrvDepartmentListResponse.class);
        return RestResponseUtil.success(result.getDepartmentList());
    }
}
