package com.mdvns.mdvn.mdvnwebsocketpapi.service.impl;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.common.beans.ServerPush;
import com.mdvns.mdvn.common.beans.Staff;
import com.mdvns.mdvn.common.beans.exception.ExceptionEnum;
import com.mdvns.mdvn.common.utils.RestResponseUtil;
import com.mdvns.mdvn.mdvnwebsocketpapi.config.WebConfig;
import com.mdvns.mdvn.mdvnwebsocketpapi.domain.RtrvServerPushListRequest;
import com.mdvns.mdvn.mdvnwebsocketpapi.domain.RtrvServerPushResponse;
import com.mdvns.mdvn.mdvnwebsocketpapi.service.WebSocketService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@Service
public class WebSocketServiceImpl implements WebSocketService {

    private static final Logger LOG = LoggerFactory.getLogger(WebSocketServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebConfig webConfig;

    @Autowired
    private RestResponse restResponse;

    /**
     * 调用SAPI获取推送信息列表
     *
     * @param request
     * @return
     */
    public RestResponse rtrvServerPushInfoList(RtrvServerPushListRequest request) {
        RtrvServerPushResponse rtrvServerPushResponse = new RtrvServerPushResponse();
        ResponseEntity<Object> responseEntity;
        String url = webConfig.getRtrvServerPushInfoListUrl();
        rtrvServerPushResponse = this.restTemplate.postForObject(url, request, RtrvServerPushResponse.class);
        if (rtrvServerPushResponse.getServerPushes() != null) {
            List<ServerPush> serverPushes = rtrvServerPushResponse.getServerPushes();
            for (int i = 0; i < serverPushes.size(); i++) {
                String initiatorId = serverPushes.get(i).getInitiatorId();//发起人
                Staff initiator = this.restTemplate.postForObject(webConfig.getRtrvStaffInfoUrl(), initiatorId, Staff.class);
                serverPushes.get(i).setInitiator(initiator);
            }
        }
        restResponse.setResponseBody(rtrvServerPushResponse);
        restResponse.setResponseCode("000");
        restResponse.setResponseMsg("请求成功");
        restResponse.setStatusCode("200");
        return restResponse;
    }


    /**
     * 删除某个推送的信息
     *
     * @param uuId
     * @return
     */
    @Override
    public ResponseEntity<?> deleteServerPushInfo(Integer uuId) {
        String url = webConfig.getDeleteServerPushInfoUrl();
        Boolean flag = this.restTemplate.postForObject(url, uuId, Boolean.class);
        if (flag) {
            restResponse.setResponseBody(true);
            restResponse.setResponseCode("000");
            restResponse.setResponseMsg("请求成功");
            restResponse.setStatusCode("200");
            ResponseEntity<?> responseEntity = new ResponseEntity<RestResponse>(restResponse, HttpStatus.OK);
            return responseEntity;
        } else {
            return ResponseEntity.ok(RestResponseUtil.error(HttpStatus.NOT_MODIFIED, ExceptionEnum.DELETE_SERVERPUSH_FAIL + "", "Fail to delete server"));
        }

    }


}
