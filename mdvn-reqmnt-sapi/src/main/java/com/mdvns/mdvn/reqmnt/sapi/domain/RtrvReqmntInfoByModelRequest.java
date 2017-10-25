package com.mdvns.mdvn.reqmnt.sapi.domain;

import org.springframework.stereotype.Component;

@Component
public class RtrvReqmntInfoByModelRequest {

    private String projId;
    private String creatorId;

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }
}
