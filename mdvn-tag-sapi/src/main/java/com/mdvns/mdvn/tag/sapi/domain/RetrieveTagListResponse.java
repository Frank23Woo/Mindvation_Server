package com.mdvns.mdvn.tag.sapi.domain;

import com.mdvns.mdvn.tag.sapi.domain.entity.Tag;

import java.util.List;

public class RetrieveTagListResponse {

    private List<Tag> tags;

    private Long totalNumber;

    private List<String> remarks;

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Long getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Long totalNumber) {
        this.totalNumber = totalNumber;
    }

    public List<String> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<String> remarks) {
        this.remarks = remarks;
    }
}
