package com.mdvns.mdvn.dashboard.sapi.domain;

import org.springframework.stereotype.Component;

@Component
public class AddStoryRequest {
    private String storyId;
    private String projId;

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
