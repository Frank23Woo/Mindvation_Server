package com.mdvns.mdvn.model.sapi.domain;


import org.springframework.stereotype.Component;

@Component
public class RtrvModelByIdRequest {
    private  String modelId;

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }
}
