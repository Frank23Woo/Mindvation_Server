package com.mdvns.mdvn.mdvnwebsocketpapi.service;

import com.mdvns.mdvn.common.beans.LoginRequest;
import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.mdvnwebsocketpapi.domain.RtrvServerPushListRequest;
import org.springframework.http.ResponseEntity;


public interface WebSocketService {

    RestResponse rtrvServerPushInfoList(RtrvServerPushListRequest request);

    ResponseEntity<?> deleteServerPushInfo(Integer uuId);
}
