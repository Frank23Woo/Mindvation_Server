package com.mdvns.mdvn.project.sapi.domain.entity;


import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Timestamp;

@Component
@Entity
@Table(name = "checklist_proj")
public class ProjChecklists {

    @Id
    @GeneratedValue
    private Integer uu_id;
    //任务ID
    private String checklistId;
    //projectID
    private String projId;
    //任务描述
    @Column(columnDefinition = "text",nullable = false)
    private String checklistDesc;
    //执行者ID
    private String executorId;
    //设计者ID
    private String assignerId;
    //任务创建时间
    @Column(name = "create_time", columnDefinition = "timestamp default current_timestamp", nullable = false)
    private Timestamp createTime;
    //最后一次更改时间
    @Column(name = "last_update_time", columnDefinition = "timestamp")
    private Timestamp lastUpdateTime;
    //开始日期
    @Column(name = "start_date", columnDefinition = "timestamp")
    private Timestamp startDate;
    //结束日期
    @Column(name = "end_date", columnDefinition = "timestamp")
    private Timestamp endDate;
    //任务状态
    private String checklistStatus;
    //有效标志
    @Column(columnDefinition = "varchar(5) default 'Y'")
    private String yxbz;


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

    public String getYxbz() {
        return yxbz;
    }

    public void setYxbz(String yxbz) {
        this.yxbz = yxbz;
    }
}
