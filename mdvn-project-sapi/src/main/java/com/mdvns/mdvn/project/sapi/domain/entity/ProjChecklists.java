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
    private String checkListId;
    //projectID
    private String projId;
    //任务描述
    @Column(columnDefinition = "text",nullable = false)
    private String checkListDesc;
    //创建者ID(更改checkList的staffId)
    private String creatorId;
    //执行者ID
    private String executorId;
    //设计者ID
    private String assignerId;
    //任务创建时间
    @Column(name = "create_time")
    private Timestamp createTime;
    //最后一次更改时间
    @Column(name = "last_update_time", columnDefinition = "timestamp NOT NULL default current_timestamp ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp lastUpdateTime;
    //开始日期
    @Column(name = "start_date")
    private Timestamp startDate;
    //结束日期
    @Column(name = "end_date")
    private Timestamp endDate;
    //关闭时间
    @Column(name = "close_time")
    private Timestamp closeTime;
    //任务状态
    private String status;
    //是否被删除
    @Column(name = "is_deleted", columnDefinition = "INT default 0")
    private Integer isDeleted;


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

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
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

    public Timestamp getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Timestamp closeTime) {
        this.closeTime = closeTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
