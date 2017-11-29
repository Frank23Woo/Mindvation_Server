package com.mdvns.mdvn.dashboard.sapi.domain;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdateSprintStartStatusRequest {
    private String projId;
    private String creatorId;
    private String modelId;
    private String name;
//    private Integer uuId;
    private Integer iterationCycle;

    public Integer getIterationCycle() {
        return iterationCycle;
    }
    public void setIterationCycle(Integer iterationCycle) {
        this.iterationCycle = iterationCycle;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
