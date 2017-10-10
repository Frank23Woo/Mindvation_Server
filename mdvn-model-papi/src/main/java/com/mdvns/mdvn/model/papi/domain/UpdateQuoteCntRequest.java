package com.mdvns.mdvn.model.papi.domain;

import java.util.List;

public class UpdateQuoteCntRequest {

    private String modelId;

    private List<String> remarks;

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public List<String> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<String> remarks) {
        this.remarks = remarks;
    }
}
