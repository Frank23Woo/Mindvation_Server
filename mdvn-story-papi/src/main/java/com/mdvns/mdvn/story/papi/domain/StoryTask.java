package com.mdvns.mdvn.story.papi.domain;


import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class StoryTask {

    private Integer uuId;
    //任务ID
    private String taskId;
    //storyId
    private String storyId;
    //任务描述
    private String taskDesc;
    //创建者ID(更改checkList的staffId)
    private String creatorId;
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
    //关闭时间
    private Long closeTime;
    //任务状态
    private String status;
    //是否被删除
    private Integer isDeleted;


    public Integer getUuId() {
        return uuId;
    }

    public void setUuId(Integer uuId) {
        this.uuId = uuId;
    }

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
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

    public void setCloseTime(Long closeTime) {
        this.closeTime = closeTime;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public Long getCloseTime() {
        return closeTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
