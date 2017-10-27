package com.mdvns.mdvn.dashboard.sapi.domain;

import org.springframework.stereotype.Component;

@Component
public class UpdateSprintRequest {

    private String creatorId;
    private String sprintId;
    private String sprintIndex;
    private String storyId;
    private String reqmntId;

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getSprintId() {
        return sprintId;
    }

    public void setSprintId(String sprintId) {
        this.sprintId = sprintId;
    }

    public String getSprintIndex() {
        return sprintIndex;
    }

    public void setSprintIndex(String sprintIndex) {
        this.sprintIndex = sprintIndex;
    }

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }

    public String getReqmntId() {
        return reqmntId;
    }

    public void setReqmntId(String reqmntId) {
        this.reqmntId = reqmntId;
    }
}
