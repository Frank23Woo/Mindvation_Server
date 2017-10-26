package com.mdvns.mdvn.dashboard.sapi.domain;


import org.springframework.stereotype.Component;

@Component
public class UpdateDashboardRequest {

    private String projId;
    private String modelId;
    private String creatorId;
    private String storyId;
    private Integer beforeMoving;
    private Integer afterMoving;

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }

    public Integer getBeforeMoving() {
        return beforeMoving;
    }

    public void setBeforeMoving(Integer beforeMoving) {
        this.beforeMoving = beforeMoving;
    }

    public Integer getAfterMoving() {
        return afterMoving;
    }

    public void setAfterMoving(Integer afterMoving) {
        this.afterMoving = afterMoving;
    }

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
}
