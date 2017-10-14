package com.mdvns.mdvn.story.sapi.domain;

import com.mdvns.mdvn.story.sapi.domain.entity.StoryTag;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdateSTagsRequest {
    //用户故事ID
    private String storyId;
    //用户故事标签（集合）
    private List<StoryTag> sTags;

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }

    public List<StoryTag> getsTags() {
        return sTags;
    }

    public void setsTags(List<StoryTag> sTags) {
        this.sTags = sTags;
    }
}
