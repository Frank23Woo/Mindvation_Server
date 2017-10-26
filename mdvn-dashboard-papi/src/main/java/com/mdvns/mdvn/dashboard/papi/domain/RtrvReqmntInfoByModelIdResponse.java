package com.mdvns.mdvn.dashboard.papi.domain;


import com.mdvns.mdvn.common.beans.RequirementInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RtrvReqmntInfoByModelIdResponse {
    private String modelId;
    private List<RequirementInfo> requirementInfos;

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public List<RequirementInfo> getRequirementInfos() {
        return requirementInfos;
    }

    public void setRequirementInfos(List<RequirementInfo> requirementInfos) {
        this.requirementInfos = requirementInfos;
    }
}
