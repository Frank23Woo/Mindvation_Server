package com.mdvns.mdvn.task.papi.domain;

import com.mdvns.mdvn.common.beans.AttchInfo;
import com.mdvns.mdvn.common.beans.Staff;
import org.springframework.stereotype.Component;

@Component
public class TaskHistory {

    private String action;
    private Integer uuId;
    private String taskId;
    private String updateId;
    private Long updateTime;
    private Integer beforeProgress;
    private Integer nowProgress;
    private String beforeRemarks;
    private String nowRemarks;
    private Integer deleteAttachId;
    private Integer addAttachId;

    private Staff updateInfo;
    private AttchInfo addAttchInfo;
    private AttchInfo deleteAttchInfo;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

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
