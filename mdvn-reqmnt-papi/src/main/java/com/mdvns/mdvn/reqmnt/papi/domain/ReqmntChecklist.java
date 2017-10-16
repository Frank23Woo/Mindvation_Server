package com.mdvns.mdvn.reqmnt.papi.domain;


import com.mdvns.mdvn.common.beans.Staff;
import org.springframework.stereotype.Component;

@Component
public class ReqmntCheckList {

    private Integer uuId;
    //任务ID
    private String checkListId;
    //projectID
    private String reqmntId;
    //任务描述
    private String description;
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
    private String checkListStatus;
    //有效标志
    private Integer isDeleted;
    //更新时间
    private Long updateTime;
    //任务状态
    private String status;

    //执行者ID
    private Staff assignee;
    //设计者ID
    private Staff assigner;


    public Integer getUuId() {
        return uuId;
    }

    public void setUuId(Integer uuId) {
        this.uuId = uuId;
    }

    public String getCheckListId() {
        return checkListId;
    }

    public void setCheckListId(String checkListId) {
        this.checkListId = checkListId;
    }

    public String getReqmntId() {
        return reqmntId;
    }

    public void setReqmntId(String reqmntId) {
        this.reqmntId = reqmntId;
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

    public String getCheckListStatus() {
        return checkListStatus;
    }

    public void setCheckListStatus(String checkListStatus) {
        this.checkListStatus = checkListStatus;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Staff getAssignee() {
        return assignee;
    }

    public void setAssignee(Staff assignee) {
        this.assignee = assignee;
    }

    public Staff getAssigner() {
        return assigner;
    }

    public void setAssigner(Staff assigner) {
        this.assigner = assigner;
    }

    @Override
    public String toString() {
        return "ReqmntCheckList{" +
                "uuId=" + uuId +
                ", checkListId='" + checkListId + '\'' +
                ", reqmntId='" + reqmntId + '\'' +
                ", description='" + description + '\'' +
                ", assigneeId='" + assigneeId + '\'' +
                ", assignerId='" + assignerId + '\'' +
                ", createTime=" + createTime +
                ", lastUpdateTime=" + lastUpdateTime +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", checkListStatus='" + checkListStatus + '\'' +
                ", isDeleted=" + isDeleted +
                ", updateTime=" + updateTime +
                ", status='" + status + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReqmntCheckList that = (ReqmntCheckList) o;

        if (uuId != null ? !uuId.equals(that.uuId) : that.uuId != null) return false;
        if (checkListId != null ? !checkListId.equals(that.checkListId) : that.checkListId != null) return false;
        if (reqmntId != null ? !reqmntId.equals(that.reqmntId) : that.reqmntId != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null)
            return false;
        if (assigneeId != null ? !assigneeId.equals(that.assigneeId) : that.assigneeId != null) return false;
        if (assignerId != null ? !assignerId.equals(that.assignerId) : that.assignerId != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (lastUpdateTime != null ? !lastUpdateTime.equals(that.lastUpdateTime) : that.lastUpdateTime != null)
            return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (checkListStatus != null ? !checkListStatus.equals(that.checkListStatus) : that.checkListStatus != null)
            return false;
        if (isDeleted != null ? !isDeleted.equals(that.isDeleted) : that.isDeleted != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        return status != null ? status.equals(that.status) : that.status == null;
    }

    @Override
    public int hashCode() {
        int result = uuId != null ? uuId.hashCode() : 0;
        result = 31 * result + (checkListId != null ? checkListId.hashCode() : 0);
        result = 31 * result + (reqmntId != null ? reqmntId.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (assigneeId != null ? assigneeId.hashCode() : 0);
        result = 31 * result + (assignerId != null ? assignerId.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (lastUpdateTime != null ? lastUpdateTime.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (checkListStatus != null ? checkListStatus.hashCode() : 0);
        result = 31 * result + (isDeleted != null ? isDeleted.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
