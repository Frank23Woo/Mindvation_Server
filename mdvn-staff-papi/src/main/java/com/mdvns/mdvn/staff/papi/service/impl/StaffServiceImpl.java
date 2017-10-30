package com.mdvns.mdvn.staff.papi.service.impl;

import com.mdvns.mdvn.common.beans.DepartmentDetail;
import com.mdvns.mdvn.common.beans.Position;
import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.common.beans.Tag;
import com.mdvns.mdvn.common.beans.exception.BusinessException;
import com.mdvns.mdvn.common.beans.exception.ExceptionEnum;
import com.mdvns.mdvn.common.utils.FetchListUtil;
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

import java.util.ArrayList;
import java.util.List;


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
        String findByIdUrl = webConfig.getFindByIdUrl()+"/" + id;
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

    @Override
    public ResponseEntity<?> createStaff(CreateStaffRequest request) {
        String url = webConfig.getCreateStaffUrl();
        CreateStaffResponse response = this.restTemplate.postForObject(url, request, CreateStaffResponse.class);
//        String positionUrl = webConfig.getRtrvPostionDetailUrl();
//        Position position = this.restTemplate.postForObject(positionUrl+"/"+request.getPositionId(), "", Position.class);
//        if(position!=null){
//            response.setPositionDetail(position);
//        }
//        String deptUrl = webConfig.getRtrvDepartmentDetailUrl();
//        DepartmentDetail departmentDetail = this.restTemplate.postForObject(deptUrl+"/"+request.getDeptId(), "", DepartmentDetail.class);
//        if(departmentDetail!=null){
//            response.setDeptDetail(departmentDetail);
//        }
        restResponse = RestResponseUtil.success(response);
        return ResponseEntity.ok(restResponse);
    }

    @Override
    public ResponseEntity<?> deleteStaff(String staffId) {
        String url = webConfig.getDeleteStaffUrl();
        Boolean flag = this.restTemplate.postForObject(url+"/"+staffId,"",Boolean.class);
        if(flag){
            return ResponseEntity.ok(RestResponseUtil.success());
        }else{
            return ResponseEntity.ok(RestResponseUtil.error(HttpStatus.NOT_MODIFIED,ExceptionEnum.DELETE_STAFF_FAIL+"","Fail to delete staff"));
        }

    }

    @Override
    public ResponseEntity<?> rtrvStaffDetail(String staffId) {
        String url = webConfig.getRtrvStaffInfoUrl();
        RtrvStaffDetailResponse response = new RtrvStaffDetailResponse();
        Staff staff = this.restTemplate.postForObject(url,staffId,Staff.class);
        response.setStaffInfo(staff);

        String staffTagUrl = webConfig.getRtrvStaffTagListUrl();
//        List<StaffTag> staffTagList = this.restTemplate.postForObject(staffTagUrl,staffId,List.class);
        ParameterizedTypeReference<List<StaffTag>> parameterizedTypeReference = new ParameterizedTypeReference<List<StaffTag>>(){};
        List<StaffTag> staffTagList = FetchListUtil.fetch(this.restTemplate,staffTagUrl,staffId,parameterizedTypeReference);

        List<String> tagIds= new ArrayList<>();
        for (int i = 0; i < staffTagList.size(); i++) {
            tagIds.add(staffTagList.get(i).getTagId());
        }
        String tagUrl = webConfig.getRtrvTagsUrl();

        RtrvTagsRequest rtrvTagsRequest = new RtrvTagsRequest();
        rtrvTagsRequest.setTagIds(tagIds);

//        List<Tag> tagList = this.restTemplate.postForObject(tagUrl,params, List.class);
        ParameterizedTypeReference<List<Tag>> tagRefer = new ParameterizedTypeReference<List<Tag>>() {};
        List<Tag> tagList = FetchListUtil.fetch(this.restTemplate,tagUrl,rtrvTagsRequest,tagRefer);
        response.setTags(tagList);
        return ResponseEntity.ok(RestResponseUtil.success(response));
    }

    @Override
    public ResponseEntity<?> updateStaffDetail(UpdateStaffDetailRequest request) {
        Boolean flag = this.restTemplate.postForObject(webConfig.getUpdateStaffDetailUrl(), request, Boolean.class);
        if(flag){
            return rtrvStaffDetail(request.getStaffInfo().getStaffId());
        }else{
            return  ResponseEntity.ok(RestResponseUtil.error(HttpStatus.NOT_MODIFIED,ExceptionEnum.UPDATE_STAFF_FAIL+"","Fail to update staff info"));
        }


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
