package com.mdvns.mdvn.reqmnt.sapi.domain.entity;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Component
@Entity
public class RequirementInfo {
    /* project ID */
    private String projId;

    /* requirement ID */
    private String reqmntId;

    /* model ID */
    private String modelId;

    /* unique id in db */
    @Id
    @GeneratedValue
    private Integer uuId;
    /* requirement summary */
    private String summary;
    /* staff id of creator */
    private String creatorId;
    /* requirement description*/
    private String description;
    /* requirement priority*/
    private Integer priority;
    private String functionLabelId;
    /* start date of this requirement*/
    private Long startDate;
    /* end date of this requirement*/
    private Long endDate;
    /* create time of this requirement*/
    private Long createTime;
    /* requirement status, eg. New, Open, In progess, Closed .etc*/
    private String status;
    /* requirement RAG status, ie. Red, Amber, Green*/
    private String ragStatus;
    /* requirement progress*/
    private Double progress;
    /* total amount of story point and CR story point*/
    private Integer totalStoryPoint;
    //备注
    private String remarks;
    //是否被删除
    private Integer isDeleted;

    private Long lastUpdateTime;

    public String getReqmntId() {
        return reqmntId;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public void setReqmntId(String reqmntId) {
        this.reqmntId = reqmntId;
    }

    public Integer getUuId() {
        return uuId;
    }

    public void setUuId(Integer uuId) {
        this.uuId = uuId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
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

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRagStatus() {
        return ragStatus;
    }

    public void setRagStatus(String ragStatus) {
        this.ragStatus = ragStatus;
    }

    public Double getProgress() {
        return progress;
    }

    public void setProgress(Double progress) {
        this.progress = progress;
    }

    public Integer getTotalStoryPoint() {
        return totalStoryPoint;
    }

    public void setTotalStoryPoint(Integer totalStoryPoint) {
        this.totalStoryPoint = totalStoryPoint;
    }


    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }


    public String getFunctionLabelId() {
        return functionLabelId;
    }

    public void setFunctionLabelId(String functionLabelId) {
        this.functionLabelId = functionLabelId;
    }


    public Long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }



}
