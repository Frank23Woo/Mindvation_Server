package com.mdvns.mdvn.common.beans;


import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SendMessageRequest {
    private ServerPush serverPushResponse;
    private List<String> staffIds;
    private String initiatorId;//发起人

    public String getInitiatorId() {
        return initiatorId;
    }

    public void setInitiatorId(String initiatorId) {
        this.initiatorId = initiatorId;
    }

    public ServerPush getServerPushResponse() {
        return serverPushResponse;
    }

    public void setServerPushResponse(ServerPush serverPushResponse) {
        this.serverPushResponse = serverPushResponse;
    }

    public List<String> getStaffIds() {
        return staffIds;
    }

    public void setStaffIds(List<String> staffIds) {
        this.staffIds = staffIds;
    }
}
