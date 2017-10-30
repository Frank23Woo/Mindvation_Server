package com.mdvns.mdvn.dashboard.sapi.domain;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdateSprintCloseStatusRequest {
    private Integer beforUuId;
    private List<String> stories;
    private Integer afterUuId;

    public List<String> getStories() {
        return stories;
    }
    public void setStories(List<String> stories) {
        this.stories = stories;
    }

    public Integer getBeforUuId() {
        return beforUuId;
    }

    public void setBeforUuId(Integer beforUuId) {
        this.beforUuId = beforUuId;
    }

    public Integer getAfterUuId() {
        return afterUuId;
    }

    public void setAfterUuId(Integer afterUuId) {
        this.afterUuId = afterUuId;
    }
}
