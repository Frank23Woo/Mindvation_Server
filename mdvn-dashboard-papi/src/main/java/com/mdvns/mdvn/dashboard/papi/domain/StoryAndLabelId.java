package com.mdvns.mdvn.dashboard.papi.domain;


import com.mdvns.mdvn.common.beans.Story;
import org.springframework.stereotype.Component;

@Component
public class StoryAndLabelId {
    private Integer isRemove;
    private Story story;
    private String labelId;
    //是否可以移动（可以1，不可以0）


    public Integer getIsRemove() {
        return isRemove;
    }

    public void setIsRemove(Integer isRemove) {
        this.isRemove = isRemove;
    }

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
