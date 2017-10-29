package com.mdvns.mdvn.staff.papi.service.impl;

import com.mdvns.mdvn.common.beans.AssignAuthRequest;
import com.mdvns.mdvn.common.beans.StaffAuthInfo;
import com.mdvns.mdvn.common.beans.exception.BusinessException;
import com.mdvns.mdvn.common.beans.exception.ExceptionEnum;
import com.mdvns.mdvn.staff.papi.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class AuthServiceImpl implements AuthService {

    private static final Logger LOG = LoggerFactory.getLogger(StaffServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResponseEntity<?> assignAuth(AssignAuthRequest assignAuthRequest) {
        String assignAuthUrl = "";

        ResponseEntity responseEntity = this.restTemplate.postForEntity(assignAuthUrl, assignAuthRequest, StaffAuthInfo.class);
        if (HttpStatus.OK.equals(responseEntity.getStatusCode())) {
            return responseEntity;
        }
        throw new BusinessException(ExceptionEnum.SAPI_EXCEPTION);
    }
}
