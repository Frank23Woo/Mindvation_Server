package com.mdvns.mdvn.tag.papi.domain;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RetrieveTagListResponse {

    private List<Tag> tags;

    private Integer totalNumber;

    private List<String> remarks;

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<String> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<String> remarks) {
        this.remarks = remarks;
    }
}
