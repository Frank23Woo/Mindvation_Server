package com.mdvns.mdvn.story.sapi.domain;

import com.mdvns.mdvn.story.sapi.domain.entity.Story;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RtrvStoryListResponse {
    //总记录数
    private Long totalElements;

    private List<Story> stories;

    private List<String> remarks;

    public List<Story> getStories() {
        return stories;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }

    public List<String> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<String> remarks) {
        this.remarks = remarks;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }
}
