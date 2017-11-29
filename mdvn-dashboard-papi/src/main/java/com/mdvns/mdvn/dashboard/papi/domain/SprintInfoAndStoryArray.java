package com.mdvns.mdvn.dashboard.papi.domain;

import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class SprintInfoAndStoryArray {
//    private Integer uuId;
    private String name;
    private List<String> stories;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getStories() {
        return stories;
    }

    public void setStories(List<String> stories) {
        this.stories = stories;
    }
}
