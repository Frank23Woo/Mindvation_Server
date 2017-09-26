package com.mdvns.mdvn.task.sapi.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Entity
@Component
public class Task {

    @Id
    @GeneratedValue
    private Integer uuid;

    private String taskId;

    private String description;

    @Column(nullable = false)
    private Integer asigneeId;
    @Column(nullable = false)
    private Integer asignerId;
    @Column(nullable = false)
    private String storyId;
    @Column(nullable = false)
    private Integer status;
    @Column(nullable = false)
    private String priority;

    @Column(columnDefinition="timestamp default CURRENT_TIMESTAMP", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    private Date updateTime;

    private Date closeTime;

    private String remarks;

    public Task() {
    }

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAsigneeId() {
        return asigneeId;
    }

    public void setAsigneeId(Integer asigneeId) {
        this.asigneeId = asigneeId;
    }

    public Integer getAsignerId() {
        return asignerId;
    }

    public void setAsignerId(Integer asignerId) {
        this.asignerId = asignerId;
    }

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
