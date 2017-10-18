package com.mdvns.mdvn.story.papi.domain;

import com.mdvns.mdvn.common.beans.SubFunctionLabel;
import org.springframework.stereotype.Component;

@Component
public class JudgeSubLabelIdRequest {

    private String creatorId;
    private SubFunctionLabel subFunctionLabel;

    public SubFunctionLabel getSubFunctionLabel() {
        return subFunctionLabel;
    }

    public void setSubFunctionLabel(SubFunctionLabel subFunctionLabel) {
        this.subFunctionLabel = subFunctionLabel;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }
}
