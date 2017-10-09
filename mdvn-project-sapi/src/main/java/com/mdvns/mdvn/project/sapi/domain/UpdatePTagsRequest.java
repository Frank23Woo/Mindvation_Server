package com.mdvns.mdvn.project.sapi.domain;

import com.mdvns.mdvn.project.sapi.domain.entity.ProjTags;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdatePTagsRequest {
    //项目ID
    private String projId;
    //项目标签（集合）
    private List<ProjTags> tags;

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public List<ProjTags> getTags() {
        return tags;
    }

    public void setTags(List<ProjTags> tags) {
        this.tags = tags;
    }
}
