package com.mdvns.mdvn.task.sapi.domain.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Component
public class Task {

    @Id
    @GeneratedValue
    private Integer uuid;
    private String storyId;
    private String taskId;
    @Column(nullable = false)
    private String creatorId;
    @Column(nullable = false)
    private String assigneeId;
    private String description;
    @Column(nullable = false)
    private Timestamp startTime;
    @Column(nullable = false)
    private Timestamp endTime;
    private Integer progress;
    private String comment;
    private String attachmentIds;

    // 交附件
    private Integer deliverId;
    @Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP", nullable = false)
    private Timestamp createTime;
    private Timestamp lastUpdateTime;

    @Column(columnDefinition = "integer default 0")
    private Integer isDeleted = 0;

    private String remarks;

    public Task() {
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
