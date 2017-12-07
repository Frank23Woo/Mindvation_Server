package com.mdvns.mdvn.task.papi.domain;

import com.mdvns.mdvn.common.beans.AttchInfo;
import com.mdvns.mdvn.common.beans.Staff;
import org.springframework.stereotype.Component;

@Component
public class TaskHistory {

    private Integer uuId;
    private String taskId;
    private String updateId;
    private Long updateTime;
    private Integer beforeProgress;
    private Integer nowProgress;
    private String beforeComment;
    private String nowComment;
    private String deleteAttachId;
    private String addAttachId;

    private Staff updateInfo;
    private AttchInfo addAttchInfo;
    private AttchInfo deleteAttchInfo;

    public AttchInfo getAddAttchInfo() {
        return addAttchInfo;
    }

    public void setAddAttchInfo(AttchInfo addAttchInfo) {
        this.addAttchInfo = addAttchInfo;
    }

    public AttchInfo getDeleteAttchInfo() {
        return deleteAttchInfo;
    }

    public void setDeleteAttchInfo(AttchInfo deleteAttchInfo) {
        this.deleteAttchInfo = deleteAttchInfo;
    }

    public Staff getUpdateInfo() {
        return updateInfo;
    }

    public void setUpdateInfo(Staff updateInfo) {
        this.updateInfo = updateInfo;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
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
