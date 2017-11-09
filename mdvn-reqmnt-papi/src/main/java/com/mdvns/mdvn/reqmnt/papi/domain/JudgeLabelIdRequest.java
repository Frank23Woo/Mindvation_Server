package com.mdvns.mdvn.reqmnt.papi.domain;


import com.mdvns.mdvn.common.beans.SubFunctionLabel;
import org.springframework.stereotype.Component;

@Component
public class JudgeLabelIdRequest {

    private String creatorId;
    private SubFunctionLabel functionLabel;

    public SubFunctionLabel getFunctionLabel() {
        return functionLabel;
    }

    public void setFunctionLabel(SubFunctionLabel functionLabel) {
        this.functionLabel = functionLabel;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }
}
