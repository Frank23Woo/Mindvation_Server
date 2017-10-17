package com.mdvns.mdvn.reqmnt.papi.domain;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class CreateReqmntRequest {
    /* project ID */
    private String projId;

    /* staff id of creator*/
    private String creatorId;
    /* model id */
    private String modelId;
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
    private List<RoleMember> members;
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

    public List<RoleMember> getMembers() {
        return members;
    }

    public void setMembers(List<RoleMember> members) {
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

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }
}
