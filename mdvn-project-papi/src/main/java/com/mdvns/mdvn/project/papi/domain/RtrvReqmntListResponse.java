package com.mdvns.mdvn.project.papi.domain;

import com.mdvns.mdvn.common.beans.RequirementInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RtrvReqmntListResponse {
    //总记录数
    private Long totalElements;

    private List<RequirementInfo> requirementInfos;

    private List<String> remarks;

    public List<RequirementInfo> getRequirementInfos() {
        return requirementInfos;
    }

    public void setRequirementInfos(List<RequirementInfo> requirementInfos) {
        this.requirementInfos = requirementInfos;
    }

    public List<String> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<String> remarks) {
        this.remarks = remarks;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }
}
