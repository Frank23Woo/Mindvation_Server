package com.mdvns.mdvn.dashboard.sapi.domain;


import com.mdvns.mdvn.dashboard.sapi.domain.entity.SprintInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdateSprintRequest {

    private String projId;
    private String modelId;
    private String creatorId;
    private List<SprintInfo> sprintInfos;

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

    public List<SprintInfo> getSprintInfos() {
        return sprintInfos;
    }

    public void setSprintInfos(List<SprintInfo> sprintInfos) {
        this.sprintInfos = sprintInfos;
    }
}
