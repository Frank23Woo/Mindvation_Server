package com.mdvns.mdvn.dashboard.papi.domain;

import org.springframework.stereotype.Component;

@Component
public class AssignStoryListByItRequest {
    private String projId;
    private String modelId;
    private String creatorId;

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }
}
