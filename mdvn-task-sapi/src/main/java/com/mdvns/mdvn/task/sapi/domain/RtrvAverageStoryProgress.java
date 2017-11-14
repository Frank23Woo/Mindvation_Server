package com.mdvns.mdvn.task.sapi.domain;

import org.springframework.stereotype.Component;

@Component
public class RtrvAverageStoryProgress {
    private String projId;
    private String reqmId;
    private String storyId;

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public String getReqmId() {
        return reqmId;
    }

    public void setReqmId(String reqmId) {
        this.reqmId = reqmId;
    }

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }
}
