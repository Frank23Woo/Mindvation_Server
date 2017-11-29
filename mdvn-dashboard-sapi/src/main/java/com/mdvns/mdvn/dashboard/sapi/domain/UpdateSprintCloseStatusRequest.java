package com.mdvns.mdvn.dashboard.sapi.domain;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdateSprintCloseStatusRequest {
    private String projId;
    private String creatorId;
    private String modelId;
    private String beforeName;
    private String afterName;
    private List<String> stories;
//    private Integer beforUuId;
//    private Integer afterUuId;

    public List<String> getStories() {
        return stories;
    }
    public void setStories(List<String> stories) {
        this.stories = stories;
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

    public String getBeforeName() {
        return beforeName;
    }

    public void setBeforeName(String beforeName) {
        this.beforeName = beforeName;
    }

    public String getAfterName() {
        return afterName;
    }

    public void setAfterName(String afterName) {
        this.afterName = afterName;
    }
}
