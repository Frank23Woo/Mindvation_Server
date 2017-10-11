package com.mdvns.mdvn.reqmnt.papi.domain;

import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class CreateReqmntResponse {
    private RequirementInfo requirementInfo;

    private List<String> remarks;

    public RequirementInfo getRequirementInfo() {
        return requirementInfo;
    }

    public void setRequirementInfo(RequirementInfo requirementInfo) {
        this.requirementInfo = requirementInfo;
    }

    public List<String> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<String> remarks) {
        this.remarks = remarks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreateReqmntResponse that = (CreateReqmntResponse) o;

        if (requirementInfo != null ? !requirementInfo.equals(that.requirementInfo) : that.requirementInfo != null)
            return false;
        return remarks != null ? remarks.equals(that.remarks) : that.remarks == null;
    }

    @Override
    public int hashCode() {
        int result = requirementInfo != null ? requirementInfo.hashCode() : 0;
        result = 31 * result + (remarks != null ? remarks.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CreateReqmntResponse{" +
                "reqirementInfo=" + requirementInfo +
                ", remarks=" + remarks +
                '}';
    }
}
