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
    private String updateId;
    @Column(nullable = false)
    private Timestamp updateTime;
    private Integer beforeProgress;
    private Integer nowProgress;
    private String beforeComment;
    private String nowComment;
    private String deleteAttachId;
    private String addAttachId;
    @Column(columnDefinition = "integer default 0")
    private Integer isDeleted = 0;

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

    public String getBeforeComment() {
        return beforeComment;
    }

    public void setBeforeComment(String beforeComment) {
        this.beforeComment = beforeComment;
    }

    public String getNowComment() {
        return nowComment;
    }

    public void setNowComment(String nowComment) {
        this.nowComment = nowComment;
    }

    public String getDeleteAttachId() {
        return deleteAttachId;
    }

    public void setDeleteAttachId(String deleteAttachId) {
        this.deleteAttachId = deleteAttachId;
    }

    public String getAddAttachId() {
        return addAttachId;
    }

    public void setAddAttachId(String addAttachId) {
        this.addAttachId = addAttachId;
    }
}
