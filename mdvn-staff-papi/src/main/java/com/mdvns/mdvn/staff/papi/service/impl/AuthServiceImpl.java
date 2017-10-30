package com.mdvns.mdvn.staff.papi.service.impl;

import com.mdvns.mdvn.common.beans.AssignAuthRequest;
import com.mdvns.mdvn.common.beans.RtrvAuthRequest;
import com.mdvns.mdvn.common.beans.StaffAuthInfo;
import com.mdvns.mdvn.common.beans.exception.BusinessException;
import com.mdvns.mdvn.common.beans.exception.ExceptionEnum;
import com.mdvns.mdvn.staff.papi.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger LOG = LoggerFactory.getLogger(StaffServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 添加权限
     * @param assignAuthRequest
     * @return
     */
    @Override
    public ResponseEntity<?> assignAuth(AssignAuthRequest assignAuthRequest) {
        String assignAuthUrl = "http://localhost:10013/mdvn-staff-sapi/staffAuth";

        ResponseEntity<StaffAuthInfo[]> responseEntity = this.restTemplate.postForEntity(assignAuthUrl, assignAuthRequest, StaffAuthInfo[].class);
        LOG.info("调用SAPI结束....{}", responseEntity.getStatusCode());
        if (HttpStatus.OK.equals(responseEntity.getStatusCode())) {
            LOG.info("添加权限response是：{}", responseEntity.getBody().toString());
            return responseEntity;
        }
        LOG.error("调用SAPI添加权限失败！");
        throw new BusinessException(ExceptionEnum.SAPI_EXCEPTION);
    }

    /**
     *获取权限
     * @param rtrvAuthRequest
     * @return
     */
    @Override
    public ResponseEntity<?> rtrvAuth(RtrvAuthRequest rtrvAuthRequest) {
        String rtrvAuthUrl = "http://localhost:10013/mdvn-staff-sapi/rtrvAuth";

        ResponseEntity<StaffAuthInfo> responseEntity = this.restTemplate.postForEntity(rtrvAuthUrl, rtrvAuthRequest, StaffAuthInfo.class);
        LOG.info("调用SAPI结束....{}", responseEntity.getStatusCode());
        if (HttpStatus.OK.equals(responseEntity.getStatusCode())) {
            return responseEntity;
        }
        LOG.error("调用SAPI添加权限失败！");
        throw new BusinessException(ExceptionEnum.SAPI_EXCEPTION);
    }
}
