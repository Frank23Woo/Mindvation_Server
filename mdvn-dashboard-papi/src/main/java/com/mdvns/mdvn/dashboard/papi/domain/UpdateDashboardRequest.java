package com.mdvns.mdvn.dashboard.papi.domain;


import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdateDashboardRequest {


    private String modelId;
    private List<SprintInfoAndStoryArray> sprintAndStoryArrays;

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
