package com.mdvns.mdvn.dashboard.sapi.domain;

import org.springframework.stereotype.Component;

@Component
public class RtrvSprintRequest {
    private String reqmntId;
    private String projectId;

    public String getReqmntId() {
        return reqmntId;
    }

    public void setReqmntId(String reqmntId) {
        this.reqmntId = reqmntId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
