package com.mdvns.mdvn.websocket.service;



import com.mdvns.mdvn.common.beans.SendMessageRequest;
import com.mdvns.mdvn.websocket.domain.RtrvServerPushListRequest;
import com.mdvns.mdvn.websocket.domain.RtrvServerPushResponse;

import java.io.IOException;

public interface WebSocketService {
    Boolean sendMessage(SendMessageRequest request) throws IOException;

    RtrvServerPushResponse rtrvServerPushInfoList(RtrvServerPushListRequest request);

    Boolean deleteServerPushInfo(Integer uuId);
}
