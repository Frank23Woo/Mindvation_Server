package com.mdvns.mdvn.reqmnt.papi.domain;

import com.mdvns.mdvn.common.beans.Staff;
import org.springframework.stereotype.Component;

@Component
public class RequirementInfo {

    /* project ID */
    private String projId;
    /* model ID */
    private String modelId;

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    private String reqmntId;
    /* unique id in db */
    private Integer uuId;
    /* requirement summary */
    private String summary;
    /* staff id of creator */
    private String creatorId;
    /* requirement description*/
    private String description;
    /* requirement priority*/
    private Integer priority;
    private String functionLabelId;
    /* start date of this requirement*/
    private Long startDate;
    /* end date of this requirement*/
    private Long endDate;
    /* create time of this requirement*/
    private Long createTime;
    /* requirement status, eg. New, Open, In progess, Closed .etc*/
    private String status;
    /* requirement RAG status, ie. Red, Amber, Green*/
    private String ragStatus;
    /* requirement progress*/
    private Double progress;
    /* total amount of story point and CR story point*/
    private Integer totalStoryPoint;
    //备注
    private String remarks;
    //是否被删除
    private Integer isDeleted;
    //成员数
    private Integer memberCunt;
    private Staff creatorInfo;

    public Integer getMemberCunt() {
        return memberCunt;
    }

    public void setMemberCunt(Integer memberCunt) {
        this.memberCunt = memberCunt;
    }

    public Staff getCreatorInfo() {
        return creatorInfo;
    }

    public void setCreatorInfo(Staff creatorInfo) {
        this.creatorInfo = creatorInfo;
    }

    public String getReqmntId() {
        return reqmntId;
    }

    public void setReqmntId(String reqmntId) {
        this.reqmntId = reqmntId;
    }

    public Integer getUuId() {
        return uuId;
    }

    public void setUuId(Integer uuId) {
        this.uuId = uuId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
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

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRagStatus() {
        return ragStatus;
    }

    public void setRagStatus(String ragStatus) {
        this.ragStatus = ragStatus;
    }

    public Double getProgress() {
        return progress;
    }

    public void setProgress(Double progress) {
        this.progress = progress;
    }

    public Integer getTotalStoryPoint() {
        return totalStoryPoint;
    }

    public void setTotalStoryPoint(Integer totalStoryPoint) {
        this.totalStoryPoint = totalStoryPoint;
    }


    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getFunctionLabelId() {
        return functionLabelId;
    }

    public void setFunctionLabelId(String functionLabelId) {
        this.functionLabelId = functionLabelId;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RequirementInfo that = (RequirementInfo) o;

        if (projId != null ? !projId.equals(that.projId) : that.projId != null) return false;
        if (reqmntId != null ? !reqmntId.equals(that.reqmntId) : that.reqmntId != null) return false;
        if (uuId != null ? !uuId.equals(that.uuId) : that.uuId != null) return false;
        if (summary != null ? !summary.equals(that.summary) : that.summary != null) return false;
        if (creatorId != null ? !creatorId.equals(that.creatorId) : that.creatorId != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (priority != null ? !priority.equals(that.priority) : that.priority != null) return false;
        if (functionLabelId != null ? !functionLabelId.equals(that.functionLabelId) : that.functionLabelId != null)
            return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (ragStatus != null ? !ragStatus.equals(that.ragStatus) : that.ragStatus != null) return false;
        if (progress != null ? !progress.equals(that.progress) : that.progress != null) return false;
        if (totalStoryPoint != null ? !totalStoryPoint.equals(that.totalStoryPoint) : that.totalStoryPoint != null)
            return false;
        if (remarks != null ? !remarks.equals(that.remarks) : that.remarks != null) return false;
        return isDeleted != null ? isDeleted.equals(that.isDeleted) : that.isDeleted == null;
    }

    @Override
    public int hashCode() {
        int result = projId != null ? projId.hashCode() : 0;
        result = 31 * result + (reqmntId != null ? reqmntId.hashCode() : 0);
        result = 31 * result + (uuId != null ? uuId.hashCode() : 0);
        result = 31 * result + (summary != null ? summary.hashCode() : 0);
        result = 31 * result + (creatorId != null ? creatorId.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (priority != null ? priority.hashCode() : 0);
        result = 31 * result + (functionLabelId != null ? functionLabelId.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (ragStatus != null ? ragStatus.hashCode() : 0);
        result = 31 * result + (progress != null ? progress.hashCode() : 0);
        result = 31 * result + (totalStoryPoint != null ? totalStoryPoint.hashCode() : 0);
        result = 31 * result + (remarks != null ? remarks.hashCode() : 0);
        result = 31 * result + (isDeleted != null ? isDeleted.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RequirementInfo{" +
                "projId='" + projId + '\'' +
                ", reqmntId='" + reqmntId + '\'' +
                ", uuId=" + uuId +
                ", summary='" + summary + '\'' +
                ", creatorId='" + creatorId + '\'' +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                ", functionLabelId='" + functionLabelId + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", createTime=" + createTime +
                ", status='" + status + '\'' +
                ", ragStatus='" + ragStatus + '\'' +
                ", progress=" + progress +
                ", totalStoryPoint=" + totalStoryPoint +
                ", remarks='" + remarks + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
