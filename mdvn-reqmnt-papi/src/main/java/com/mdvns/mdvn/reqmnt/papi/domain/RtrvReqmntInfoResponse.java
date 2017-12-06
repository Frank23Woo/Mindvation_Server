package com.mdvns.mdvn.reqmnt.papi.domain;

import com.mdvns.mdvn.common.beans.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RtrvReqmntInfoResponse{

    private RequirementInfo reqmntInfo;

    private List<CommentDetail> commentDetails;

    private FunctionLabel labelDetail;

    private List<SubFunctionLabel> subFunctionLabels;

    private List<Tag> tagList;

    private List<RoleAndMember> members;

    private List<ReqmntCheckList> checkLists;

//    private List<ReqmntAttchUrl> attchUrls;
    private List<AttchInfo> attchInfos;

    private RtrvStoryListResponse rtrvStoryListResponse;

    public List<CommentDetail> getCommentDetails() {
        return commentDetails;
    }

    public void setCommentDetails(List<CommentDetail> commentDetails) {
        this.commentDetails = commentDetails;
    }

    private List<StaffAuthInfo> staffAuthInfo;

    public List<SubFunctionLabel> getSubFunctionLabels() {
        return subFunctionLabels;
    }

    public void setSubFunctionLabels(List<SubFunctionLabel> subFunctionLabels) {
        this.subFunctionLabels = subFunctionLabels;
    }

    public List<StaffAuthInfo> getStaffAuthInfo() {
        return staffAuthInfo;
    }

    public void setStaffAuthInfo(List<StaffAuthInfo> staffAuthInfo) {
        this.staffAuthInfo = staffAuthInfo;
    }

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

    public List<AttchInfo> getAttchInfos() {
        return attchInfos;
    }

    public void setAttchInfos(List<AttchInfo> attchInfos) {
        this.attchInfos = attchInfos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RtrvReqmntInfoResponse)) return false;

        RtrvReqmntInfoResponse that = (RtrvReqmntInfoResponse) o;

        if (getReqmntInfo() != null ? !getReqmntInfo().equals(that.getReqmntInfo()) : that.getReqmntInfo() != null)
            return false;
        if (getLabelDetail() != null ? !getLabelDetail().equals(that.getLabelDetail()) : that.getLabelDetail() != null)
            return false;
        if (getTagList() != null ? !getTagList().equals(that.getTagList()) : that.getTagList() != null) return false;
        if (getMembers() != null ? !getMembers().equals(that.getMembers()) : that.getMembers() != null) return false;
        if (getCheckLists() != null ? !getCheckLists().equals(that.getCheckLists()) : that.getCheckLists() != null)
            return false;
        if (getAttchInfos() != null ? !getAttchInfos().equals(that.getAttchInfos()) : that.getAttchInfos() != null)
            return false;
        if (getRtrvStoryListResponse() != null ? !getRtrvStoryListResponse().equals(that.getRtrvStoryListResponse()) : that.getRtrvStoryListResponse() != null)
            return false;
        return getStaffAuthInfo() != null ? getStaffAuthInfo().equals(that.getStaffAuthInfo()) : that.getStaffAuthInfo() == null;
    }

    @Override
    public int hashCode() {
        int result = getReqmntInfo() != null ? getReqmntInfo().hashCode() : 0;
        result = 31 * result + (getLabelDetail() != null ? getLabelDetail().hashCode() : 0);
        result = 31 * result + (getTagList() != null ? getTagList().hashCode() : 0);
        result = 31 * result + (getMembers() != null ? getMembers().hashCode() : 0);
        result = 31 * result + (getCheckLists() != null ? getCheckLists().hashCode() : 0);
        result = 31 * result + (getAttchInfos() != null ? getAttchInfos().hashCode() : 0);
        result = 31 * result + (getRtrvStoryListResponse() != null ? getRtrvStoryListResponse().hashCode() : 0);
        result = 31 * result + (getStaffAuthInfo() != null ? getStaffAuthInfo().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RtrvReqmntInfoResponse{" +
                "reqmntInfo=" + reqmntInfo +
                ", labelDetail=" + labelDetail +
                ", tagList=" + tagList +
                ", members=" + members +
                ", checkLists=" + checkLists +
                ", attchInfos=" + attchInfos +
                ", rtrvStoryListResponse=" + rtrvStoryListResponse +
                ", staffAuthInfo=" + staffAuthInfo +
                '}';
    }
}
