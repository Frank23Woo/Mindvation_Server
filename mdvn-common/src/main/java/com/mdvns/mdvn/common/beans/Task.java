package com.mdvns.mdvn.common.beans;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class Task {

    private Integer uuid;
    private String storyId;
    private String taskId;
    private String projId;
    private String creatorId;
    private String assigneeId;
    private String description;
    private Timestamp startTime;
    private Timestamp endTime;
    private Integer progress;
    private String status;
    private String comment;
    private String attachmentIds;
    //工作用时，创建task时自己估算的时间
    private Float usedTime;
    // 交附件
    private Integer deliverId;
    private Timestamp createTime;
    private Timestamp lastUpdateTime;

    private Integer isDeleted = 0;

    private String remarks;

    public Task() {
    }

    public Float getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(Float usedTime) {
        this.usedTime = usedTime;
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

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
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

    public Integer getDeliverId() {
        return deliverId;
    }

    public void setDeliverId(Integer deliverId) {
        this.deliverId = deliverId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Timestamp lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getAttachmentIds() {
        return attachmentIds;
    }

    public void setAttachmentIds(String attachmentIds) {
        this.attachmentIds = attachmentIds;
    }


    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "Task{" +
                "uuid=" + uuid +
                ", storyId='" + storyId + '\'' +
                ", taskId='" + taskId + '\'' +
                ", creatorId='" + creatorId + '\'' +
                ", assigneeId='" + assigneeId + '\'' +
                ", description='" + description + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", progress=" + progress +
                ", comment='" + comment + '\'' +
                ", attachmentIds='" + attachmentIds + '\'' +
                ", deliverId=" + deliverId +
                ", createTime=" + createTime +
                ", lastUpdateTime=" + lastUpdateTime +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
