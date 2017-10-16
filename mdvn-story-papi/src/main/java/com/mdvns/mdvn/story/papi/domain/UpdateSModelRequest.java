package com.mdvns.mdvn.story.papi.domain;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdateSModelRequest {
    //用户故事ID
    private String storyId;
    //用户故事模块
    private StoryModel sModel;

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }

    public StoryModel getsModel() {
        return sModel;
    }

    public void setsModel(StoryModel sModel) {
        this.sModel = sModel;
    }
}
