package com.mdvns.mdvn.tag.papi.domain;

import java.util.List;

public class UpdateQuoteCntRequest {

    private String tagId;

    private List<String> remarks;

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public List<String> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<String> remarks) {
        this.remarks = remarks;
    }
}
