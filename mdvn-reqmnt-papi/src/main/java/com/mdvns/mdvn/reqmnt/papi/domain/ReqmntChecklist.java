package com.mdvns.mdvn.reqmnt.papi.domain;


import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class ReqmntChecklist {

    private Integer uuId;
    //任务ID
    private String checklistId;
    //projectID
    private String reqmntId;
    //任务描述
    private String checklistDesc;
    //执行者ID
    private String assigneeId;
    //设计者ID
    private String assignerId;
    //任务创建时间
    private Long createTime;
    //最后一次更改时间
    private Long lastUpdateTime;
    //开始日期
    private Long startDate;
    //结束日期
    private Long endDate;
    //任务状态
    private String checklistStatus;
    //有效标志
    private Integer isDeleted;
    //更新时间
    private Long updateTime;

    public Integer getUuId() {
        return uuId;
    }

    public void setUuId(Integer uuId) {
        this.uuId = uuId;
    }

    public String getChecklistId() {
        return checklistId;
    }

    public void setChecklistId(String checklistId) {
        this.checklistId = checklistId;
    }

    public String getReqmntId() {
        return reqmntId;
    }

    public void setReqmntId(String reqmntId) {
        this.reqmntId = reqmntId;
    }

    public String getChecklistDesc() {
        return checklistDesc;
    }

    public void setChecklistDesc(String checklistDesc) {
        this.checklistDesc = checklistDesc;
    }

    public String getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(String assigneeId) {
        this.assigneeId = assigneeId;
    }

    public String getAssignerId() {
        return assignerId;
    }

    public void setAssignerId(String assignerId) {
        this.assignerId = assignerId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
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

    public String getChecklistStatus() {
        return checklistStatus;
    }

    public void setChecklistStatus(String checklistStatus) {
        this.checklistStatus = checklistStatus;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReqmntChecklist that = (ReqmntChecklist) o;

        if (uuId != null ? !uuId.equals(that.uuId) : that.uuId != null) return false;
        if (checklistId != null ? !checklistId.equals(that.checklistId) : that.checklistId != null) return false;
        if (reqmntId != null ? !reqmntId.equals(that.reqmntId) : that.reqmntId != null) return false;
        if (checklistDesc != null ? !checklistDesc.equals(that.checklistDesc) : that.checklistDesc != null)
            return false;
        if (assigneeId != null ? !assigneeId.equals(that.assigneeId) : that.assigneeId != null) return false;
        if (assignerId != null ? !assignerId.equals(that.assignerId) : that.assignerId != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (lastUpdateTime != null ? !lastUpdateTime.equals(that.lastUpdateTime) : that.lastUpdateTime != null)
            return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (checklistStatus != null ? !checklistStatus.equals(that.checklistStatus) : that.checklistStatus != null)
            return false;
        if (isDeleted != null ? !isDeleted.equals(that.isDeleted) : that.isDeleted != null) return false;
        return updateTime != null ? updateTime.equals(that.updateTime) : that.updateTime == null;
    }

    @Override
    public int hashCode() {
        int result = uuId != null ? uuId.hashCode() : 0;
        result = 31 * result + (checklistId != null ? checklistId.hashCode() : 0);
        result = 31 * result + (reqmntId != null ? reqmntId.hashCode() : 0);
        result = 31 * result + (checklistDesc != null ? checklistDesc.hashCode() : 0);
        result = 31 * result + (assigneeId != null ? assigneeId.hashCode() : 0);
        result = 31 * result + (assignerId != null ? assignerId.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (lastUpdateTime != null ? lastUpdateTime.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (checklistStatus != null ? checklistStatus.hashCode() : 0);
        result = 31 * result + (isDeleted != null ? isDeleted.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ReqmntChecklist{" +
                "uuId=" + uuId +
                ", checklistId='" + checklistId + '\'' +
                ", reqmntId='" + reqmntId + '\'' +
                ", checklistDesc='" + checklistDesc + '\'' +
                ", assigneeId='" + assigneeId + '\'' +
                ", assignerId='" + assignerId + '\'' +
                ", createTime=" + createTime +
                ", lastUpdateTime=" + lastUpdateTime +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", checklistStatus='" + checklistStatus + '\'' +
                ", isDeleted=" + isDeleted +
                ", updateTime=" + updateTime +
                '}';
    }
}
