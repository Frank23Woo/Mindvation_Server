package com.mdvns.mdvn.reqmnt.sapi.domain;

import com.mdvns.mdvn.reqmnt.sapi.domain.entity.ReqmntTag;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdatePTagsRequest {
    //项目ID
    private String projId;
    //项目标签（集合）
    private List<ReqmntTag> tags;

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public List<ReqmntTag> getTags() {
        return tags;
    }

    public void setTags(List<ReqmntTag> tags) {
        this.tags = tags;
    }
}
