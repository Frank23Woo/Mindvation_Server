package com.mdvns.mdvn.reqmnt.sapi.domain;

import com.mdvns.mdvn.common.beans.Tag;
import com.mdvns.mdvn.reqmnt.sapi.domain.entity.ReqmntAttchUrl;
import com.mdvns.mdvn.reqmnt.sapi.domain.entity.ReqmntCheckList;
import com.mdvns.mdvn.reqmnt.sapi.domain.entity.ReqmntMember;
import com.mdvns.mdvn.reqmnt.sapi.domain.entity.RequirementInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdateReqmntInfoRequest {
    private String staffId;
    private RequirementInfo reqmntInfo;
    private List<Tag> tags;
    private List<RoleMember> members;
    private List<ReqmntCheckList> checkLists;
    private List<ReqmntAttchUrl> attchUrls;
    private List<String> remarks;

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


    public List<RoleMember> getMembers() {
        return members;
    }

    public void setMembers(List<RoleMember> members) {
        this.members = members;
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
}
