package com.mdvns.mdvn.user.papi.service.impl;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.common.beans.Staff;
import com.mdvns.mdvn.common.beans.exception.BusinessException;
import com.mdvns.mdvn.common.beans.exception.ExceptionEnum;
import com.mdvns.mdvn.common.utils.RestResponseUtil;
import com.mdvns.mdvn.user.papi.domain.LonginRequest;
import com.mdvns.mdvn.user.papi.domain.User;
import com.mdvns.mdvn.user.papi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class UserServiceImpl implements UserService {

    /*初始化*/
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    private RestTemplate restTemplate;

    public UserServiceImpl(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }
    /**
     * 用户登录
     * 1. 通过account调用SAPI查询User
     * 2. 如果user 为空，抛出异常： 用户不存在
     * 3. user不为空，校验密码
     * 3. 密码校验通过 返回user对应的staff信息，否则抛出异常：密码错误
     * @param loginRequest
     * @return
     */
    @Override
    public ResponseEntity<?> login(LonginRequest loginRequest) {

        LOG.info("开始执行UserPapi Service Login:{}", loginRequest.getAccount());
        //调用SAPI根据account查询用户
        User user = getUserByAccount(loginRequest.getAccount());
        if (user == null) {
            throw new BusinessException(ExceptionEnum.USER_NOT_FOUND);
        }
        //登录校验
        Boolean passed = userCheck(user, loginRequest.getPassword());
        if (!passed) {
            throw new BusinessException(ExceptionEnum.PASSWORD_INCORRECT);
        }
        //调用SAPI获取staff信息
        return getStaffById(user.getStaffId());
    }

    /**
     * 调用SAPI获取staff信息
     * @param staffId
     * @return
     */
    private ResponseEntity<?> getStaffById(String staffId) {
        String rtrvStaffByIdUrl = "http://localhost:10014/mdvn-staff-papi/staff/" + staffId;
        
        RestResponse<Staff> restResponse = this.restTemplate.postForObject(rtrvStaffByIdUrl, null, RestResponse.class);
        if ("000".equals(restResponse.getResponseCode())) {
            return ResponseEntity.ok(restResponse);
        }
        throw new BusinessException(restResponse.getResponseCode());
    }

    /**
     * 调用SAPI根据account查询用户
     * @param account
     * @return
     */
    private User getUserByAccount(String account) {
        String findByAccountUrl = "http://localhost:10022/mdvn-user-sapi/users/user/"+account;

        ResponseEntity<RestResponse<User>> responseEntity = null;
        ParameterizedTypeReference parameterizedTypeReference = new ParameterizedTypeReference<RestResponse<User>>() {
        };
        try{
        	responseEntity = restTemplate.exchange(findByAccountUrl, HttpMethod.GET, new HttpEntity<>(account), parameterizedTypeReference);
        }catch(Exception ex){
            LOG.error("调用SAPI查询用户：{} 失败: {}", account, ex.getLocalizedMessage());
        }
        User user = null;
        if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            user = responseEntity.getBody().getResponseBody();
        }
        return user;
    }

    /**
     * 登录校验
     * @param user
     * @param password
     */
    private Boolean userCheck(User user, String password) {
        Boolean passed = false;
        if (password.equals(user.getPassword())) {
            passed = true;
        }
        return passed;
    }
}
