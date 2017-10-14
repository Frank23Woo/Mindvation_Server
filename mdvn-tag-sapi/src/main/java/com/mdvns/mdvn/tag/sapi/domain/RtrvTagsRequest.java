package com.mdvns.mdvn.tag.sapi.domain;

import java.util.List;

public class RtrvTagsRequest {

    private List<String> tagIds;

    private List<String> remarks;

    public List<String> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<String> tagIds) {
        this.tagIds = tagIds;
    }

    public List<String> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<String> remarks) {
        this.remarks = remarks;
    }
}
