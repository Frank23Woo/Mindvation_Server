package com.mdvns.mdvn.reqmnt.sapi.domain;

import com.mdvns.mdvn.reqmnt.sapi.domain.entity.ReqmntAttchUrl;
import com.mdvns.mdvn.reqmnt.sapi.domain.entity.ReqmntCheckList;
import com.mdvns.mdvn.reqmnt.sapi.domain.entity.ReqmntMember;
import com.mdvns.mdvn.reqmnt.sapi.domain.entity.ReqmntTag;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreateReqmntRequest {

    /* project id*/
    private String projId;
    /* staff id of creator*/
    private String creatorId;
    /* reqirement summary*/
    private String summary;
    /* requirement description */
    private String description;
    /* requirement priority*/
    private Integer priority;
    /* requirement function label id*/
    private String functionLabelId;
    /* requirement start date*/
    private Long startDate;
    /* requirement end date*/
    private Long endDate;
    /* staff id of Leader*/
    private String leaderId;
    /* members of requirement*/
    private List<ReqmntMember> members;
    /* tags of requirement*/
    private List<ReqmntTag> tags;
    /* requirement checklist */
    private List<ReqmntCheckList> rCheckLists;
    /* requirement attachment url*/
    private List<ReqmntAttchUrl> attchUrls;
    /* remarks*/
    private List<String> remarks;

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getFunctionLabelId() {
        return functionLabelId;
    }

    public void setFunctionLabelId(String functionLabelId) {
        this.functionLabelId = functionLabelId;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    public String getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(String leaderId) {
        this.leaderId = leaderId;
    }

    public List<ReqmntMember> getMembers() {
        return members;
    }

    public void setMembers(List<ReqmntMember> members) {
        this.members = members;
    }

    public List<ReqmntTag> getTags() {
        return tags;
    }

    public void setTags(List<ReqmntTag> tags) {
        this.tags = tags;
    }

    public List<ReqmntCheckList> getrCheckLists() {
        return rCheckLists;
    }

    public void setrCheckLists(List<ReqmntCheckList> rCheckLists) {
        this.rCheckLists = rCheckLists;
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

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreateReqmntRequest that = (CreateReqmntRequest) o;

        if (projId != null ? !projId.equals(that.projId) : that.projId != null) return false;
        if (creatorId != null ? !creatorId.equals(that.creatorId) : that.creatorId != null) return false;
        if (summary != null ? !summary.equals(that.summary) : that.summary != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (priority != null ? !priority.equals(that.priority) : that.priority != null) return false;
        if (functionLabelId != null ? !functionLabelId.equals(that.functionLabelId) : that.functionLabelId != null)
            return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (leaderId != null ? !leaderId.equals(that.leaderId) : that.leaderId != null) return false;
        if (members != null ? !members.equals(that.members) : that.members != null) return false;
        if (tags != null ? !tags.equals(that.tags) : that.tags != null) return false;
        if (rCheckLists != null ? !rCheckLists.equals(that.rCheckLists) : that.rCheckLists != null) return false;
        if (attchUrls != null ? !attchUrls.equals(that.attchUrls) : that.attchUrls != null) return false;
        return remarks != null ? remarks.equals(that.remarks) : that.remarks == null;
    }

    @Override
    public int hashCode() {
        int result = projId != null ? projId.hashCode() : 0;
        result = 31 * result + (creatorId != null ? creatorId.hashCode() : 0);
        result = 31 * result + (summary != null ? summary.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (priority != null ? priority.hashCode() : 0);
        result = 31 * result + (functionLabelId != null ? functionLabelId.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (leaderId != null ? leaderId.hashCode() : 0);
        result = 31 * result + (members != null ? members.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + (rCheckLists != null ? rCheckLists.hashCode() : 0);
        result = 31 * result + (attchUrls != null ? attchUrls.hashCode() : 0);
        result = 31 * result + (remarks != null ? remarks.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CreateReqmntRequest{" +
                "projId='" + projId + '\'' +
                ", creatorId='" + creatorId + '\'' +
                ", summary='" + summary + '\'' +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                ", functionLabelId='" + functionLabelId + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", leaderId='" + leaderId + '\'' +
                ", members=" + members +
                ", tags=" + tags +
                ", rCheckLists=" + rCheckLists +
                ", attchUrls=" + attchUrls +
                ", remarks=" + remarks +
                '}';
    }
}
