package com.mdvns.mdvn.project.papi.domain;


import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class ProjChecklists {

    private Integer uu_id;
    //任务ID
    private String checklistId;
    //projectID
    private String projId;
    //任务描述
    private String checklistDesc;
    //执行者ID
    private String executorId;
    //设计者ID
    private String assignerId;
    //任务创建时间
    private Timestamp createTime;
    //最后一次更改时间
    private Timestamp lastUpdateTime;
    //开始日期
    private Timestamp startDate;
    //结束日期
    private Timestamp endDate;
    //任务状态
    private String checklistStatus;

    public Integer getUu_id() {
        return uu_id;
    }

    public void setUu_id(Integer uu_id) {
        this.uu_id = uu_id;
    }

    public String getChecklistId() {
        return checklistId;
    }

    public void setChecklistId(String checklistId) {
        this.checklistId = checklistId;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public String getChecklistDesc() {
        return checklistDesc;
    }

    public void setChecklistDesc(String checklistDesc) {
        this.checklistDesc = checklistDesc;
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

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public String getChecklistStatus() {
        return checklistStatus;
    }

    public void setChecklistStatus(String checklistStatus) {
        this.checklistStatus = checklistStatus;
    }
}
