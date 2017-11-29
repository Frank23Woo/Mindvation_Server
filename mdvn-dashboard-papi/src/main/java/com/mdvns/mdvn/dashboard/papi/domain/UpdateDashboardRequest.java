package com.mdvns.mdvn.dashboard.papi.domain;


import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdateDashboardRequest {
    private String projId;
    private String creatorId;
    private String modelId;
    private List<SprintInfoAndStoryArray> sprintAndStoryArrays;

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

    public List<SprintInfoAndStoryArray> getSprintAndStoryArrays() {
        return sprintAndStoryArrays;
    }

    public void setSprintAndStoryArrays(List<SprintInfoAndStoryArray> sprintAndStoryArrays) {
        this.sprintAndStoryArrays = sprintAndStoryArrays;
    }
}
