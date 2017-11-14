package com.mdvns.mdvn.story.sapi.domain;

import org.springframework.stereotype.Component;

@Component
public class RtrvAverageReqmProgress {
    private String projId;
    private String reqmId;

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public String getReqmId() {
        return reqmId;
    }

    public void setReqmId(String reqmId) {
        this.reqmId = reqmId;
    }
}
