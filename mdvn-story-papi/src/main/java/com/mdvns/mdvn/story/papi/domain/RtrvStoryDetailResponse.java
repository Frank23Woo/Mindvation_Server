package com.mdvns.mdvn.story.papi.domain;

import org.springframework.stereotype.Component;

@Component
public class RtrvStoryDetailResponse {
    private StoryDetail storyDetail;

    public StoryDetail getStoryDetail() {
        return storyDetail;
    }

    public void setStoryDetail(StoryDetail storyDetail) {
        this.storyDetail = storyDetail;
    }
}
