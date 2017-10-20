package com.mdvns.mdvn.reqmnt.papi.domain;

import com.mdvns.mdvn.common.beans.FunctionLabel;
import com.mdvns.mdvn.common.beans.Story;
import com.mdvns.mdvn.common.beans.Tag;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RtrvReqmntInfoResponse{

    private RequirementInfo reqmntInfo;

    private FunctionLabel labelDetail;

    private List<Tag> tagList;

    private List<RoleAndMember> members;

    private List<ReqmntCheckList> checkLists;

    private List<ReqmntAttchUrl> attchUrls;

    private RtrvStoryListResponse rtrvStoryListResponse;

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

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public FunctionLabel getLabelDetail() {
        return labelDetail;
    }

    public void setLabelDetail(FunctionLabel labelDetail) {
        this.labelDetail = labelDetail;
    }

    public RtrvStoryListResponse getRtrvStoryListResponse() {
        return rtrvStoryListResponse;
    }

    public void setRtrvStoryListResponse(RtrvStoryListResponse rtrvStoryListResponse) {
        this.rtrvStoryListResponse = rtrvStoryListResponse;
    }
}
