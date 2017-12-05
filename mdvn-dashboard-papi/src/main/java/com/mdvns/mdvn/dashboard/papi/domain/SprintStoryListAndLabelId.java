package com.mdvns.mdvn.dashboard.papi.domain;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SprintStoryListAndLabelId {

    private List<String> labelIds;

    private SprintInfo sprintInfo;

    private Long totalElements;

    private List<StoryAndLabelId> stories;

    private List<String> remarks;

    public List<String> getLabelIds() {
        return labelIds;
    }

    public void setLabelIds(List<String> labelIds) {
        this.labelIds = labelIds;
    }

    public SprintInfo getSprintInfo() {
        return sprintInfo;
    }

    public void setSprintInfo(SprintInfo sprintInfo) {
        this.sprintInfo = sprintInfo;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public List<StoryAndLabelId> getStories() {
        return stories;
    }

    public void setStories(List<StoryAndLabelId> stories) {
        this.stories = stories;
    }

    public List<String> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<String> remarks) {
        this.remarks = remarks;
    }
}
