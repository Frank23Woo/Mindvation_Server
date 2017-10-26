package com.mdvns.mdvn.dashboard.papi.domain;

import org.springframework.stereotype.Component;

@Component
public class RtrvDashboardRequest {
    private String projId;
    private String modleId;

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
