package com.mdvns.mdvn.dashboard.sapi.domain;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdateSprintInfoRequest {
    private Integer uuId;
    private List<String> stories;

    public Integer getUuId() {
        return uuId;
    }

    public void setUuId(Integer uuId) {
        this.uuId = uuId;
    }

    public List<String> getStories() {
        return stories;
    }

    public void setStories(List<String> stories) {
        this.stories = stories;
    }
}
