package com.mdvns.mdvn.story.papi.domain;

import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class CreateStoryResponse {
    private Story story;
    private List<String> remarks;
    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    public List<String> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<String> remarks) {
        this.remarks = remarks;
    }
}
