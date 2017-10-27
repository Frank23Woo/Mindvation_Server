package com.mdvns.mdvn.dashboard.papi.domain;

import com.mdvns.mdvn.common.beans.Model;
import com.mdvns.mdvn.common.beans.Story;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class RtrvAllStoryListResponse {
    private Model model;

    //总记录数
    private Long totalElements;

    private List<Story> stories;

    private List<String> remarks;

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public List<Story> getStories() {
        return stories;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public List<String> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<String> remarks) {
        this.remarks = remarks;
    }
}
