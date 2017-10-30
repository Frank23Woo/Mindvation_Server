package com.mdvns.mdvn.dashboard.papi.domain;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;
//
//*
// * 任务列表类，映射表SprintInfo
// * name不能重复

@Component
public class SprintInfo {

    private Integer uuId;
    //sprint编号
    private String sprintId;
    //迭代计划模板的name
    private String name;
    //所属项目Id
    private String subjectId;
    //所属modelId
    private String modelId;
    //顺序标记(0/1)//名称(CurrentSprint/NextSprint) 可由index 标示
    private Integer sprintIndex;
    //所包含的StoryIds/taskIds
    private String itemIds;
    //所包含的StoryIds/taskIds数量
    private Integer spQty;
// 創建sprint人的編號,即員工編號(staffId)
    private String creatorId;
// sprint創建時間
    private Long createTime;
// sprint更改時間
    private Long updateTime;
//是否已删除
    private Integer isDeleted;

    private String labelIds;

    private String status;
    //迭代周期
    private Integer iterationCycle;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getIterationCycle() {
        return iterationCycle;
    }

    public void setIterationCycle(Integer iterationCycle) {
        this.iterationCycle = iterationCycle;
    }

    public String getLabelIds() {
        return labelIds;
    }

    public void setLabelIds(String labelIds) {
        this.labelIds = labelIds;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSprintId() {
        return sprintId;
    }

    public void setSprintId(String sprintId) {
        this.sprintId = sprintId;
    }

    public Integer getUuId() {
        return uuId;
    }

    public void setUuId(Integer uuId) {
        this.uuId = uuId;
    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getSprintIndex() {
        return sprintIndex;
    }

    public void setSprintIndex(Integer sprintIndex) {
        this.sprintIndex = sprintIndex;
    }

    public String getItemIds() {
        return itemIds;
    }

    public void setItemIds(String itemIds) {
        this.itemIds = itemIds;
    }

    public Integer getSpQty() {
        return spQty;
    }

    public void setSpQty(Integer spQty) {
        this.spQty = spQty;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}
