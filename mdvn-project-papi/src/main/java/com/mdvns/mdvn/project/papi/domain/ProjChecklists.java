package com.mdvns.mdvn.project.papi.domain;


import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class ProjChecklists {

    private Integer uu_id;
    //任务ID
    private String checkListId;
    //projectID
    private String projId;
    //任务描述
    private String checkListDesc;
    //执行者ID
    private String executorId;
    //设计者ID
    private String assignerId;
    //任务创建时间
    private Long createTime;
    //最后一次更改时间
    private Long lastUpdateTime;
    //开始日期
    private Long startDate;
    //结束日期
    private Long endDate;
    //任务状态
    private String checkListStatus;
    //是否被删除
    private Integer isDeleted;
    //更新时间
    private Long updateTime;

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUu_id() {
        return uu_id;
    }

    public void setUu_id(Integer uu_id) {
        this.uu_id = uu_id;
    }


    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }


    public String getExecutorId() {
        return executorId;
    }

    public void setExecutorId(String executorId) {
        this.executorId = executorId;
    }

    public String getAssignerId() {
        return assignerId;
    }

    public void setAssignerId(String assignerId) {
        this.assignerId = assignerId;
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

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getCheckListId() {
        return checkListId;
    }

    public void setCheckListId(String checkListId) {
        this.checkListId = checkListId;
    }

    public String getCheckListDesc() {
        return checkListDesc;
    }

    public void setCheckListDesc(String checkListDesc) {
        this.checkListDesc = checkListDesc;
    }

    public String getCheckListStatus() {
        return checkListStatus;
    }

    public void setCheckListStatus(String checkListStatus) {
        this.checkListStatus = checkListStatus;
    }
}
