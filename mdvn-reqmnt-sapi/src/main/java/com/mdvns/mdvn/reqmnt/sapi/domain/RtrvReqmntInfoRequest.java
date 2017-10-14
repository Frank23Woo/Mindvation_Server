package com.mdvns.mdvn.reqmnt.sapi.domain;

import org.springframework.stereotype.Component;

@Component
public class RtrvReqmntInfoRequest {
    private String reqmntId;

    public String getReqmntId() {
        return reqmntId;
    }

    public void setReqmntId(String reqmntId) {
        this.reqmntId = reqmntId;
    }
}
