package com.mdvns.mdvn.reqmnt.papi.domain;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RtrvReqmntResponse {
    private List<ReqirementInfo> reqirementInfos;

    private List<String> remarks;

    public List<ReqirementInfo> getReqirementInfos() {
        return reqirementInfos;
    }

    public void setReqirementInfos(List<ReqirementInfo> reqirementInfos) {
        this.reqirementInfos = reqirementInfos;
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

        RtrvReqmntResponse that = (RtrvReqmntResponse) o;

        if (reqirementInfos != null ? !reqirementInfos.equals(that.reqirementInfos) : that.reqirementInfos != null)
            return false;
        return remarks != null ? remarks.equals(that.remarks) : that.remarks == null;
    }

    @Override
    public int hashCode() {
        int result = reqirementInfos != null ? reqirementInfos.hashCode() : 0;
        result = 31 * result + (remarks != null ? remarks.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RtrvReqmntResponse{" +
                "reqirementInfos=" + reqirementInfos +
                ", remarks=" + remarks +
                '}';
    }
}
