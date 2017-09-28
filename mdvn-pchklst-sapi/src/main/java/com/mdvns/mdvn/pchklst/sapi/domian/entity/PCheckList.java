package com.mdvns.mdvn.pchklst.sapi.domian.entity;


/*
 Project Check List Table
 */

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Component
@Table(name = "proj_chklst")
public class PCheckList {

    @Id
    @GeneratedValue
    private Integer UUID;

    @Column(name = "chklst_id")
    private String pCheckListId;

    @Column(name = "proj_id", nullable = false)
    private String projectId;

    @Column(name = "chklst_desc", nullable = false)
    private String description;

    @Column(name = "start_date")
    private Timestamp startDate;

    @Column(name = "end_date")
    private Timestamp endDate;

    @Column(name = "assigner_id")
    private String assignerId;

    @Column(name = "assignee_id")
    private String assigneeId;

    @Column(name = "status")
    private Integer status;

    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "last_update_time")
    private Timestamp lastUpdatTime;

    @Column(name = "close_time")
    private Timestamp closeTime;

    /* this flag is to indicate whether this record is deleted or not.
     0: not deleted
     1: deleted
     */
    @Column(name = "is_deleted", columnDefinition = "INT default 0")
    private Integer isDeleted;

    public Integer getUUID() {
        return UUID;
    }

    public void setUUID(Integer UUID) {
        this.UUID = UUID;
    }

    public String getpCheckListId() {
        return pCheckListId;
    }

    public void setpCheckListId(String pCheckListId) {
        this.pCheckListId = pCheckListId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssignerId() {
        return assignerId;
    }

    public void setAssignerId(String assignerId) {
        this.assignerId = assignerId;
    }

    public String getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(String assigneeId) {
        this.assigneeId = assigneeId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getLastUpdatTime() {
        return lastUpdatTime;
    }

    public void setLastUpdatTime(Timestamp lastUpdatTime) {
        this.lastUpdatTime = lastUpdatTime;
    }

    public Timestamp getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Timestamp closeTime) {
        this.closeTime = closeTime;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PCheckList that = (PCheckList) o;

        if (UUID != null ? !UUID.equals(that.UUID) : that.UUID != null) return false;
        if (pCheckListId != null ? !pCheckListId.equals(that.pCheckListId) : that.pCheckListId != null) return false;
        if (projectId != null ? !projectId.equals(that.projectId) : that.projectId != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (assignerId != null ? !assignerId.equals(that.assignerId) : that.assignerId != null) return false;
        if (assigneeId != null ? !assigneeId.equals(that.assigneeId) : that.assigneeId != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (lastUpdatTime != null ? !lastUpdatTime.equals(that.lastUpdatTime) : that.lastUpdatTime != null)
            return false;
        if (closeTime != null ? !closeTime.equals(that.closeTime) : that.closeTime != null) return false;
        return isDeleted != null ? isDeleted.equals(that.isDeleted) : that.isDeleted == null;
    }

    @Override
    public int hashCode() {
        int result = UUID != null ? UUID.hashCode() : 0;
        result = 31 * result + (pCheckListId != null ? pCheckListId.hashCode() : 0);
        result = 31 * result + (projectId != null ? projectId.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (assignerId != null ? assignerId.hashCode() : 0);
        result = 31 * result + (assigneeId != null ? assigneeId.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (lastUpdatTime != null ? lastUpdatTime.hashCode() : 0);
        result = 31 * result + (closeTime != null ? closeTime.hashCode() : 0);
        result = 31 * result + (isDeleted != null ? isDeleted.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PCheckList{" +
                "UUID=" + UUID +
                ", pCheckListId='" + pCheckListId + '\'' +
                ", projectId='" + projectId + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", assignerId='" + assignerId + '\'' +
                ", assigneeId='" + assigneeId + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", lastUpdatTime=" + lastUpdatTime +
                ", closeTime=" + closeTime +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
