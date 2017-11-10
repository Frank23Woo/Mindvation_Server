package com.mdvns.mdvn.dashboard.sapi.domain;

import org.springframework.stereotype.Component;

@Component
public class RtrvDashboardRequest {
    private String projId;
    private String modleId;
    private String creatorId;

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public String getModleId() {
        return modleId;
    }

    public void setModleId(String modleId) {
        this.modleId = modleId;
    }
}
