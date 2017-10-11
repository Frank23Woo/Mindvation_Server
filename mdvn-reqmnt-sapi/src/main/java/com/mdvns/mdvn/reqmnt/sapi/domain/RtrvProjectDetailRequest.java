package com.mdvns.mdvn.reqmnt.sapi.domain;

import org.springframework.stereotype.Component;

@Component
public class RtrvProjectDetailRequest {
    private String projId;

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }
}
