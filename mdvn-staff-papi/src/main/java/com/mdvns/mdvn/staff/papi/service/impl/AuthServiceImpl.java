package com.mdvns.mdvn.staff.papi.service.impl;

import com.mdvns.mdvn.common.beans.*;
import com.mdvns.mdvn.common.beans.exception.BusinessException;
import com.mdvns.mdvn.common.beans.exception.ExceptionEnum;
import com.mdvns.mdvn.staff.papi.config.WebConfig;
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

    @Autowired
    private WebConfig webConfig;

    /**
     * 添加权限
     * @param assignAuthRequest
     * @return
     */
    @Override
    public ResponseEntity<?> assignAuth(AssignAuthRequest assignAuthRequest) {
        String assignAuthUrl = webConfig.getAssignAuthUrl();
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
    public ResponseEntity<?> rtrvAuth(RtrvStaffAuthInfoRequest rtrvAuthRequest) {
        String rtrvAuthUrl = webConfig.getRtrvAuthUrl();
        ResponseEntity<StaffAuthInfo[]> responseEntity = this.restTemplate.postForEntity(rtrvAuthUrl, rtrvAuthRequest, StaffAuthInfo[].class);
        LOG.info("调用SAPI结束....{}", responseEntity.getStatusCode());
        if (HttpStatus.OK.equals(responseEntity.getStatusCode())) {
            return responseEntity;
        }
        LOG.error("调用SAPI添加权限失败！");
        throw new BusinessException(ExceptionEnum.SAPI_EXCEPTION);
    }

    /**
     * 更新权限
     * @param removeAuthRequest
     * @return
     */
    @Override
    public ResponseEntity<?> removeAuth(RemoveAuthRequest removeAuthRequest) {
        String removeAuthUrl = "";

        if (removeAuthRequest.getStaffAuthInfo() == null) {
            LOG.error("删除权限时，权限信息不能为空!");
            throw new IllegalArgumentException("staffAuth 不能为空!");
        }
        //根据id更新staffAuthInfo 的isDelete = 1
        String deleteAutInfoUrl = "";

        StaffAuthInfo  staffAuthInfo = this.restTemplate.getForObject(deleteAutInfoUrl, StaffAuthInfo.class);
        if (null==staffAuthInfo) {
            LOG.error("权限信息不存在");
            throw new BusinessException(ExceptionEnum.AUTH_INFO_NOT_FOUND);
        }

        return ResponseEntity.ok(staffAuthInfo);
    }

    public ResponseEntity<?> removeAllAuth(String projId, String hierarchyId) {
        String removeAllAuthUrl = webConfig.getRemoveAllAuthUrl() + "/" + projId + "/" + hierarchyId;
        this.restTemplate.delete(removeAllAuthUrl);
        return new ResponseEntity(HttpStatus.OK);
    }

}
