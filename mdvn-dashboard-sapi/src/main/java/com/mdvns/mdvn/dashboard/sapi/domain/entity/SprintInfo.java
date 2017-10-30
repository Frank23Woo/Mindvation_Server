package com.mdvns.mdvn.dashboard.sapi.domain.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;
//
//*
// * 任务列表类，映射表SprintInfo
// * name不能重复


@Entity
@Component
public class SprintInfo {

    @Id
    @GeneratedValue
    private Integer uuId;
    //sprint编号
//    private String sprintId;
    //迭代计划模板的name
    @NotBlank(message = "任务列表名称不能为空")
    @Column(nullable = false)
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
    @NotBlank(message = "创建者Id不能为空")
    @Column(name = "creator_id", columnDefinition = "Varchar (50)", nullable = false)
    private String creatorId;
    // sprint創建時間
    @Column(name = "create_time",nullable = false)
    private Timestamp createTime;
    // sprint更改時間
    @Column(name = "update_time", columnDefinition = "timestamp default current_timestamp ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp updateTime;
    //是否已删除
    private Integer isDeleted;

    private String labelIds;

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

    public Integer getUuId() {
        return uuId;
    }

    public void setUuId(Integer uuId) {
        this.uuId = uuId;
    }

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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
