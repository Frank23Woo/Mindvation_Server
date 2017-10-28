package com.mdvns.mdvn.story.papi.domain;


import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdateAttchUrlsRequest {

    //用户故事ID
    private String storyId;
    //用户故事附件（集合）
    private List<StoryAttchUrl> attchUrls;

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }

    public List<StoryAttchUrl> getAttchUrls() {
        return attchUrls;
    }

    public void setAttchUrls(List<StoryAttchUrl> attchUrls) {
        this.attchUrls = attchUrls;
    }
}
