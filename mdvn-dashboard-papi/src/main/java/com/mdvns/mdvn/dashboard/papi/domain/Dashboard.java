package com.mdvns.mdvn.dashboard.papi.domain;

import org.springframework.stereotype.Component;


/**
 * 项目看板类，映射表Dashboard
 * name不能重复
 */

@Component
public class Dashboard {

    private Integer uuId;
    private String dashboardId;
    private String projId;
    private String modelId;
    //初始化时的任务列表（最开始包含的是所有storyList）
    private String productBacklogs;
    //当前任务列表（包含的是storyList）
    private String currentSprint;
    private String nextSprint;
    /* 創建人的編號,即員工編號(staffId) */
    private String creatorId;
    /* sprint創建時間*/
    private Long createTime;
    /*更改時間*/
    private Long updateTime;
    /*是否已删除*/
    private Integer isDeleted;

    public String getProductBacklogs() {
        return productBacklogs;
    }

    public void setProductBacklogs(String productBacklogs) {
        this.productBacklogs = productBacklogs;
    }

    public Integer getUuId() {
        return uuId;
    }

    public void setUuId(Integer uuId) {
        this.uuId = uuId;
    }

    public String getDashboardId() {
        return dashboardId;
    }

    public void setDashboardId(String dashboardId) {
        this.dashboardId = dashboardId;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getCurrentSprint() {
        return currentSprint;
    }

    public void setCurrentSprint(String currentSprint) {
        this.currentSprint = currentSprint;
    }

    public String getNextSprint() {
        return nextSprint;
    }

    public void setNextSprint(String nextSprint) {
        this.nextSprint = nextSprint;
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

    public Dashboard() {
        super();
    }
}
