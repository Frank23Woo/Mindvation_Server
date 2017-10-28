package com.mdvns.mdvn.staff.papi.service.impl;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.common.beans.exception.BusinessException;
import com.mdvns.mdvn.common.beans.exception.ExceptionEnum;
import com.mdvns.mdvn.common.utils.RestResponseUtil;
import com.mdvns.mdvn.staff.papi.config.WebConfig;
import com.mdvns.mdvn.staff.papi.domain.*;
import com.mdvns.mdvn.staff.papi.service.StaffService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


@Service
public class StaffServiceImpl implements StaffService {

    private static final Logger LOG = LoggerFactory.getLogger(StaffServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebConfig webConfig;

    @Autowired
    private Staff staff;

    @Autowired
    private RestResponse restResponse;

    /**
     * 调用SAPI获取Staff列表
     *
     * @param retrieveStaffListRequest
     * @return
     */
    public RestResponse rtrvStaffList(RetrieveStaffListRequest retrieveStaffListRequest) {
        RetrieveStaffListResponse retrieveStaffListResponse = new RetrieveStaffListResponse();
        ResponseEntity<Object> responseEntity;
        String url = webConfig.getRtrvStaffListUrl();
        retrieveStaffListResponse = this.restTemplate.postForObject(url, retrieveStaffListRequest, RetrieveStaffListResponse.class);
//        restResponse = RestResponseUtil.success(responseEntity.getBody());
        restResponse.setResponseBody(retrieveStaffListResponse);
        restResponse.setResponseCode("000");
        restResponse.setResponseMsg("请求成功");
        restResponse.setStatusCode("200");
        return restResponse;
    }

    /**
     * 通过staffId的list集合获取staff的对象列表
     *
     * @param request
     * @return
     */
    @Override
    public RestResponse rtrvStaffListByStaffIdList(RtrvStaffListByStaffIbListRequest request) {
        List<Staff> list = new ArrayList<>();
        ResponseEntity<Object> responseEntity;
        String url = webConfig.getRtrvStaffListByStaffIdListUrl();
        list = this.restTemplate.postForObject(url, request, List.class);
//        restResponse = RestResponseUtil.success(responseEntity.getBody());
        restResponse.setResponseBody(list);
        restResponse.setResponseCode("000");
        restResponse.setResponseMsg("请求成功");
        restResponse.setStatusCode("200");
        return restResponse;
    }

    /**
     * 通过staffId获取staff对象信息
     *
     * @param request
     * @return
     */
    @Override
    public RestResponse rtrvStaffInfo(RtrvStaffInfoRequest request) {
        String staffId = request.getStaffId();
        String url = webConfig.getRtrvStaffInfoUrl();
        staff = this.restTemplate.postForObject(url, staffId, Staff.class);
//        restResponse = RestResponseUtil.success(responseEntity.getBody());
        restResponse.setResponseBody(staff);
        restResponse.setResponseCode("000");
        restResponse.setResponseMsg("请求成功");
        restResponse.setStatusCode("200");
        return restResponse;
    }

    /**
     * 调用SAPI获取Tag列表
     *
     * @param request
     * @return
     */
    @Override
    public ResponseEntity<?> rtrvStaffListByName(RtrvStaffListByNameRequest request) {
        String url = webConfig.getRtrvStaffListByNameUrl();
        RtrvStaffListByNameResponse rtrvStaffListByNameResponse = this.restTemplate.postForObject(url, request, RtrvStaffListByNameResponse.class);
        restResponse = RestResponseUtil.success(rtrvStaffListByNameResponse);
        return ResponseEntity.ok(restResponse);
    }

    /**
     * 根据指定Id获取Staff信息
     *
     * @param id
     * @return
     */
    @Override
    public RestResponse<Staff> retrieve(String id) {
        //1.初始化URL
        String findByIdUrl = "http://localhost:10013/mdvn-staff-sapi/staff/" + id;
        RestResponse<Staff> restResponse = null;
        try {
            restResponse = this.restTemplate.getForObject(findByIdUrl, RestResponse.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new BusinessException(ExceptionEnum.SAPI_EXCEPTION);
        }

        if ("000".equals(restResponse.getResponseCode())) {
            return restResponse;
        }
        throw new BusinessException(restResponse.getStatusCode());
    }

    /**
     * 登录
     *
     * @param loginRequest
     * @return
     */
    @Override
    public ResponseEntity<?> login(LonginRequest loginRequest) {

        LOG.info("开始执行StaffPapi Service Login:{}", loginRequest.getAccount());
        //调用SAPI根据account查询用户
        Staff staff = getStaffByAccount(loginRequest.getAccount());
        if (staff == null) {
            throw new BusinessException(ExceptionEnum.USER_NOT_FOUND);
        }
        //登录校验
        if (!loginRequest.getPassword().equals(staff.getPassword())) {
            throw new BusinessException(ExceptionEnum.PASSWORD_INCORRECT);
        }
        return RestResponseUtil.successResponseEntity(staff);
    }



    /**
     * 调用SAPI根据account查询用户
     *
     * @param account
     * @return
     */
    private Staff getStaffByAccount(String account) {
        String findByAccountUrl = "http://localhost:10013/mdvn-staff-sapi/staff/" + account;

        ResponseEntity<RestResponse<Staff>> responseEntity = null;
        ParameterizedTypeReference parameterizedTypeReference = new ParameterizedTypeReference<RestResponse<Staff>>() {
        };
        try {
            responseEntity = restTemplate.exchange(findByAccountUrl, HttpMethod.GET, new HttpEntity<>(account), parameterizedTypeReference);
        } catch (Exception ex) {
            LOG.error("调用SAPI查询用户：{} 失败: {}", account, ex.getLocalizedMessage());
        }
        Staff staff = null;
        if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            staff = responseEntity.getBody().getResponseBody();
        }
        return staff;
    }
}
