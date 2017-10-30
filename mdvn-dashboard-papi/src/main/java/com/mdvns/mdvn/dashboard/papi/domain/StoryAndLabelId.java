package com.mdvns.mdvn.dashboard.papi.domain;


import com.mdvns.mdvn.common.beans.Story;
import org.springframework.stereotype.Component;

@Component
public class StoryAndLabelId {
    private Story story;
    private String labelId;

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    public String getLabelId() {
        return labelId;
    }

    public void setLabelId(String labelId) {
        this.labelId = labelId;
    }
}
