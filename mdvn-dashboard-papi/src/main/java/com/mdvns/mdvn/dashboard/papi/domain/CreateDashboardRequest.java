package com.mdvns.mdvn.dashboard.papi.domain;


import org.springframework.stereotype.Component;

@Component
public class CreateDashboardRequest {

    private String projId;
    private String creatorId;
    private String modelId;
    private String productBacklogs;

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

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getProductBacklogs() {
        return productBacklogs;
    }

    public void setProductBacklogs(String productBacklogs) {
        this.productBacklogs = productBacklogs;
    }
}
