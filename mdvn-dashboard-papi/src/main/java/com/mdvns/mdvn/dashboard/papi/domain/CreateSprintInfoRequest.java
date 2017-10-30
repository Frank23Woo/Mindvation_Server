package com.mdvns.mdvn.dashboard.papi.domain;


import org.springframework.stereotype.Component;

@Component
public class CreateSprintInfoRequest {

    private String projId;
    private String creatorId;
    private String modelId;
    private Integer sprintIndex;
    private String itemIds;
    private String name;
    private String labelIds;

    public String getLabelIds() {
        return labelIds;
    }

    public void setLabelIds(String labelIds) {
        this.labelIds = labelIds;
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

    public Integer getSprintIndex() {
        return sprintIndex;
    }

    public void setSprintIndex(Integer sprintIndex) {
        this.sprintIndex = sprintIndex;
    }

    public String getItemIds() {
        return itemIds;
    }

    public void setItemIds(String itemIds) {
        this.itemIds = itemIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
