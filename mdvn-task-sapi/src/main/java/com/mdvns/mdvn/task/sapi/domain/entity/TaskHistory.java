package com.mdvns.mdvn.task.sapi.domain.entity;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Component
@Entity
public class TaskHistory {
    @Id
    @GeneratedValue
    private Integer uuId;
    @Column(nullable = false)
    private String taskId;
    private String action;
    private String updateId;
    @Column(nullable = false)
    private Timestamp updateTime;
    private Integer beforeProgress;
    private Integer nowProgress;
    @Column(columnDefinition = "text")
    private String beforeRemarks;
    @Column(columnDefinition = "text")
    private String nowRemarks;
    private Integer deleteAttachId;
    private Integer addAttachId;
    @Column(columnDefinition = "integer default 0")
    private Integer isDeleted = 0;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUuId() {
        return uuId;
    }

    public void setUuId(Integer uuId) {
        this.uuId = uuId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Integer getBeforeProgress() {
        return beforeProgress;
    }

    public void setBeforeProgress(Integer beforeProgress) {
        this.beforeProgress = beforeProgress;
    }

    public Integer getNowProgress() {
        return nowProgress;
    }

    public void setNowProgress(Integer nowProgress) {
        this.nowProgress = nowProgress;
    }

    public String getBeforeRemarks() {
        return beforeRemarks;
    }

    public void setBeforeRemarks(String beforeRemarks) {
        this.beforeRemarks = beforeRemarks;
    }

    public String getNowRemarks() {
        return nowRemarks;
    }

    public void setNowRemarks(String nowRemarks) {
        this.nowRemarks = nowRemarks;
    }

    public Integer getDeleteAttachId() {
        return deleteAttachId;
    }

    public void setDeleteAttachId(Integer deleteAttachId) {
        this.deleteAttachId = deleteAttachId;
    }

    public Integer getAddAttachId() {
        return addAttachId;
    }

    public void setAddAttachId(Integer addAttachId) {
        this.addAttachId = addAttachId;
    }
}
