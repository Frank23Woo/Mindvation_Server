package com.mdvns.mdvn.mdvnwebsocketpapi.web;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.mdvnwebsocketpapi.domain.RtrvServerPushListRequest;
import com.mdvns.mdvn.mdvnwebsocketpapi.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = {"/websocket", "/v1.0/websocket"})
public class WebSocketController {

    @Autowired
    private WebSocketService webSocketService;


    /**
     * 获取推送信息列表信息
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/rtrvServerPushInfoList")
    public RestResponse rtrvServerPushInfoList(@RequestBody RtrvServerPushListRequest request) {
        return this.webSocketService.rtrvServerPushInfoList(request);
    }


    /**
     * 删除推送的某个信息
     * @param uuId
     * @return
     */
    @PostMapping(value = "/deleteServerPushInfo/{uuId}")
    public ResponseEntity<?> deleteServerPushInfo(@PathVariable Integer uuId) {
        return this.webSocketService.deleteServerPushInfo(uuId);
    }



}
