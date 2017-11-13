package com.mdvns.mdvn.task.papi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mdvns.mdvn.common.beans.AttchInfo;
import com.mdvns.mdvn.common.beans.Staff;
import com.mdvns.mdvn.common.beans.StaffAuthInfo;
import com.mdvns.mdvn.task.papi.domain.entity.TaskDeliver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@JsonIgnoreProperties(value={"uuid", "deliverId", "isDeleted"})
public class TaskDetail {

    private Integer uuid;
    private String storyId;
    private String taskId;
    private String projId;
    private String creatorId;
    private String assigneeId;
    private String description;
    private Long startTime;
    private Long endTime;
    private Integer progress;
    private String status;
    private String comment;
    private String attachmentIds;
    //工作用时，创建task时自己估算的时间
    private Float usedTime;

    // 交附件
    private Integer deliverId;
    private Long createTime;
    private Long lastUpdateTime;
    private Integer isDeleted = 0;
    private String remarks;

    private TaskDeliver deliver;

    private Staff creator;

    private Staff assignee;

    private List<AttchInfo> attachUrlList;

    private List<StaffAuthInfo> staffAuthInfo;

    public TaskDetail() {
    }

    public Float getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(Float usedTime) {
        this.usedTime = usedTime;
    }

    public List<StaffAuthInfo> getStaffAuthInfo() {
        return staffAuthInfo;
    }

    public void setStaffAuthInfo(List<StaffAuthInfo> staffAuthInfo) {
        this.staffAuthInfo = staffAuthInfo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public TaskDeliver getDeliver() {
        return deliver;
    }

    public void setDeliver(TaskDeliver deliver) {
        this.deliver = deliver;
    }

    public Staff getCreator() {
        return creator;
    }

    public void setCreator(Staff creator) {
        this.creator = creator;
    }


    public Staff getAssignee() {
        return assignee;
    }

    public void setAssignee(Staff assignee) {
        this.assignee = assignee;
    }

    public List<AttchInfo> getAttachUrlList() {
        return attachUrlList;
    }

    public void setAttachUrlList(List<AttchInfo> attachUrlList) {
        this.attachUrlList = attachUrlList;
    }

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(String assigneeId) {
        this.assigneeId = assigneeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAttachmentIds() {
        return attachmentIds;
    }

    public void setAttachmentIds(String attachmentIds) {
        this.attachmentIds = attachmentIds;
    }

    public Integer getDeliverId() {
        return deliverId;
    }

    public void setDeliverId(Integer deliverId) {
        this.deliverId = deliverId;
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

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskDetail)) return false;

        TaskDetail that = (TaskDetail) o;

        if (getUuid() != null ? !getUuid().equals(that.getUuid()) : that.getUuid() != null) return false;
        if (getStoryId() != null ? !getStoryId().equals(that.getStoryId()) : that.getStoryId() != null) return false;
        if (getTaskId() != null ? !getTaskId().equals(that.getTaskId()) : that.getTaskId() != null) return false;
        if (getProjId() != null ? !getProjId().equals(that.getProjId()) : that.getProjId() != null) return false;
        if (getCreatorId() != null ? !getCreatorId().equals(that.getCreatorId()) : that.getCreatorId() != null)
            return false;
        if (getAssigneeId() != null ? !getAssigneeId().equals(that.getAssigneeId()) : that.getAssigneeId() != null)
            return false;
        if (getDescription() != null ? !getDescription().equals(that.getDescription()) : that.getDescription() != null)
            return false;
        if (getStartTime() != null ? !getStartTime().equals(that.getStartTime()) : that.getStartTime() != null)
            return false;
        if (getEndTime() != null ? !getEndTime().equals(that.getEndTime()) : that.getEndTime() != null) return false;
        if (getProgress() != null ? !getProgress().equals(that.getProgress()) : that.getProgress() != null)
            return false;
        if (getStatus() != null ? !getStatus().equals(that.getStatus()) : that.getStatus() != null) return false;
        if (getComment() != null ? !getComment().equals(that.getComment()) : that.getComment() != null) return false;
        if (getAttachmentIds() != null ? !getAttachmentIds().equals(that.getAttachmentIds()) : that.getAttachmentIds() != null)
            return false;
        if (getDeliverId() != null ? !getDeliverId().equals(that.getDeliverId()) : that.getDeliverId() != null)
            return false;
        if (getCreateTime() != null ? !getCreateTime().equals(that.getCreateTime()) : that.getCreateTime() != null)
            return false;
        if (getLastUpdateTime() != null ? !getLastUpdateTime().equals(that.getLastUpdateTime()) : that.getLastUpdateTime() != null)
            return false;
        if (getIsDeleted() != null ? !getIsDeleted().equals(that.getIsDeleted()) : that.getIsDeleted() != null)
            return false;
        if (getRemarks() != null ? !getRemarks().equals(that.getRemarks()) : that.getRemarks() != null) return false;
        if (getDeliver() != null ? !getDeliver().equals(that.getDeliver()) : that.getDeliver() != null) return false;
        if (getCreator() != null ? !getCreator().equals(that.getCreator()) : that.getCreator() != null) return false;
        if (getAssignee() != null ? !getAssignee().equals(that.getAssignee()) : that.getAssignee() != null)
            return false;
        if (getAttachUrlList() != null ? !getAttachUrlList().equals(that.getAttachUrlList()) : that.getAttachUrlList() != null)
            return false;
        return getStaffAuthInfo() != null ? getStaffAuthInfo().equals(that.getStaffAuthInfo()) : that.getStaffAuthInfo() == null;
    }

    @Override
    public int hashCode() {
        int result = getUuid() != null ? getUuid().hashCode() : 0;
        result = 31 * result + (getStoryId() != null ? getStoryId().hashCode() : 0);
        result = 31 * result + (getTaskId() != null ? getTaskId().hashCode() : 0);
        result = 31 * result + (getProjId() != null ? getProjId().hashCode() : 0);
        result = 31 * result + (getCreatorId() != null ? getCreatorId().hashCode() : 0);
        result = 31 * result + (getAssigneeId() != null ? getAssigneeId().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getStartTime() != null ? getStartTime().hashCode() : 0);
        result = 31 * result + (getEndTime() != null ? getEndTime().hashCode() : 0);
        result = 31 * result + (getProgress() != null ? getProgress().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        result = 31 * result + (getComment() != null ? getComment().hashCode() : 0);
        result = 31 * result + (getAttachmentIds() != null ? getAttachmentIds().hashCode() : 0);
        result = 31 * result + (getDeliverId() != null ? getDeliverId().hashCode() : 0);
        result = 31 * result + (getCreateTime() != null ? getCreateTime().hashCode() : 0);
        result = 31 * result + (getLastUpdateTime() != null ? getLastUpdateTime().hashCode() : 0);
        result = 31 * result + (getIsDeleted() != null ? getIsDeleted().hashCode() : 0);
        result = 31 * result + (getRemarks() != null ? getRemarks().hashCode() : 0);
        result = 31 * result + (getDeliver() != null ? getDeliver().hashCode() : 0);
        result = 31 * result + (getCreator() != null ? getCreator().hashCode() : 0);
        result = 31 * result + (getAssignee() != null ? getAssignee().hashCode() : 0);
        result = 31 * result + (getAttachUrlList() != null ? getAttachUrlList().hashCode() : 0);
        result = 31 * result + (getStaffAuthInfo() != null ? getStaffAuthInfo().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TaskDetail{" +
                "uuid=" + uuid +
                ", storyId='" + storyId + '\'' +
                ", taskId='" + taskId + '\'' +
                ", projId='" + projId + '\'' +
                ", creatorId='" + creatorId + '\'' +
                ", assigneeId='" + assigneeId + '\'' +
                ", description='" + description + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", progress=" + progress +
                ", status='" + status + '\'' +
                ", comment='" + comment + '\'' +
                ", attachmentIds='" + attachmentIds + '\'' +
                ", deliverId=" + deliverId +
                ", createTime=" + createTime +
                ", lastUpdateTime=" + lastUpdateTime +
                ", isDeleted=" + isDeleted +
                ", remarks='" + remarks + '\'' +
                ", deliver=" + deliver +
                ", creator=" + creator +
                ", assignee=" + assignee +
                ", attachUrlList=" + attachUrlList +
                ", staffAuthInfo=" + staffAuthInfo +
                '}';
    }
}
