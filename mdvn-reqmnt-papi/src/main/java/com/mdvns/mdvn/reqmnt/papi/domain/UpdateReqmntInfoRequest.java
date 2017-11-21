package com.mdvns.mdvn.reqmnt.papi.domain;

import com.mdvns.mdvn.common.beans.SubFunctionLabel;
import com.mdvns.mdvn.common.beans.Tag;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdateReqmntInfoRequest {
    private String staffId;
    private RequirementInfo reqmntInfo;
    private SubFunctionLabel functionLabel;
    private List<Tag> tags;

        /* members of requirement*/
    private List<RoleMember> members;
    private List<ReqmntCheckList> checkLists;
    private List<ReqmntAttchUrl> attchUrls;
    private List<String> remarks;

    public SubFunctionLabel getFunctionLabel() {
        return functionLabel;
    }

    public void setFunctionLabel(SubFunctionLabel functionLabel) {
        this.functionLabel = functionLabel;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public RequirementInfo getReqmntInfo() {
        return reqmntInfo;
    }

    public void setReqmntInfo(RequirementInfo reqmntInfo) {
        this.reqmntInfo = reqmntInfo;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }




    public List<ReqmntCheckList> getCheckLists() {
        return checkLists;
    }

    public void setCheckLists(List<ReqmntCheckList> checkLists) {
        this.checkLists = checkLists;
    }

    public List<ReqmntAttchUrl> getAttchUrls() {
        return attchUrls;
    }

    public void setAttchUrls(List<ReqmntAttchUrl> attchUrls) {
        this.attchUrls = attchUrls;
    }

    public List<String> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<String> remarks) {
        this.remarks = remarks;
    }

    public List<RoleMember> getMembers() {
        return members;
    }

    public void setMembers(List<RoleMember> members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return "UpdateReqmntInfoRequest{" +
                "staffId='" + staffId + '\'' +
                ", reqmntInfo=" + reqmntInfo +
                ", functionLabel=" + functionLabel +
                ", tags=" + tags +
                ", members=" + members +
                ", checkLists=" + checkLists +
                ", attchUrls=" + attchUrls +
                ", remarks=" + remarks +
                '}';
    }
}
