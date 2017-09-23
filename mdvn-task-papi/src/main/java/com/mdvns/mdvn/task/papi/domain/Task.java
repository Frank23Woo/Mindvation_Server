package com.mdvns.mdvn.task.papi.domain;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
public class Task {
    private Integer uuid;

    private String taskId;

    private String description;

    private Integer asigneeId;

    private Integer asignerId;

    private String storyId;

    private Integer status;

    private String priority;

    private Timestamp createTime;

    private Date updateTime;

    private Date closeTime;

    private String remarks;

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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
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
