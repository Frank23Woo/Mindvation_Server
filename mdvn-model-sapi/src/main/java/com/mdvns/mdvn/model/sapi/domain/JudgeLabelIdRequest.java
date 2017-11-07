package com.mdvns.mdvn.model.sapi.domain;


import com.mdvns.mdvn.model.sapi.domain.entity.SubFunctionLabel;
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
