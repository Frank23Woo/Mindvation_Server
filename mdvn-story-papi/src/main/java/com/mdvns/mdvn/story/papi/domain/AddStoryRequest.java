package com.mdvns.mdvn.story.papi.domain;

import org.springframework.stereotype.Component;

@Component
public class AddStoryRequest {
    private String storyId;
    private String projId;
    private String creatorId;

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

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public String getProjId() {
        return projId;
    }
}
