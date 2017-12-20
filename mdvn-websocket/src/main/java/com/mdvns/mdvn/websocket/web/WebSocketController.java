package com.mdvns.mdvn.websocket.web;


import com.mdvns.mdvn.common.beans.SendMessageRequest;
import com.mdvns.mdvn.websocket.domain.RtrvServerPushListRequest;
import com.mdvns.mdvn.websocket.domain.RtrvServerPushResponse;
import com.mdvns.mdvn.websocket.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping(value = {"/websocket", "/v1.0/websocket"})
public class WebSocketController {

    @Autowired
    private WebSocketService webSocketService;


    /**
     * 推送信息
     * @param request
     * @return
     */
    @PostMapping(value = "/sendMessage")
    public Boolean sendMessage(@RequestBody SendMessageRequest request) throws IOException {
        return this.webSocketService.sendMessage(request);
    }

    /**
     * 获取不在线时的推送信息
     * @param request
     * @return
     */
    @PostMapping(value = "/rtrvServerPushInfoList")
    public RtrvServerPushResponse rtrvServerPushInfoList(@RequestBody RtrvServerPushListRequest request) throws IOException {
        return this.webSocketService.rtrvServerPushInfoList(request);
    }


    /**
     * 删除推送信息
     * @param uuId
     * @return
     */
    @PostMapping(value = "/deleteServerPushInfo")
    public Boolean deleteServerPushInfo(@RequestBody Integer uuId) throws IOException {
        return this.webSocketService.deleteServerPushInfo(uuId);
    }


}
