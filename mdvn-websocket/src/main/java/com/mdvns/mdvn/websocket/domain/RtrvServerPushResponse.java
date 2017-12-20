package com.mdvns.mdvn.websocket.domain;


import com.mdvns.mdvn.websocket.domain.entity.ServerPush;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class RtrvServerPushResponse {

    private List<ServerPush> serverPushes;

    private Integer totalNumbers;

    public List<ServerPush> getServerPushes() {
        return serverPushes;
    }

    public void setServerPushes(List<ServerPush> serverPushes) {
        this.serverPushes = serverPushes;
    }

    public Integer getTotalNumbers() {
        return totalNumbers;
    }

    public void setTotalNumbers(Integer totalNumbers) {
        this.totalNumbers = totalNumbers;
    }
}