package com.mdvns.mdvn.reqmnt.papi.domain;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdateReqmntInfoResponse {

    private RequirementInfo reqmntInfo;

    private List<RoleAndMember> members;

    private List<ReqmntCheckList> checkLists;

    private List<ReqmntAttchUrl> attchUrls;

    public void setReqmntInfo(RequirementInfo reqmntInfo) {
        this.reqmntInfo = reqmntInfo;
    }

    public RequirementInfo getReqmntInfo() {
        return reqmntInfo;
    }

    public List<RoleAndMember> getMembers() {
        return members;
    }

    public void setMembers(List<RoleAndMember> members) {
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
}
