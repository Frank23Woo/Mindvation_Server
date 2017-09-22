package com.mdvns.mdvn.project.sapi.domain.entity;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Component
@Entity
public class Project {
    //项目编号
    @Id
    private String projId;
    //表自增长生成的Id
    @GeneratedValue
    private Integer uuId;
    //项目名称
    private String name;
    //项目描述
    private String description;
    //项目优先级
    private String priority;
    //项目开始日期
    private Timestamp startDate;
    //项目结束时期
    private Timestamp endDate;
    //项目状态
    private Integer status;
    //效率值
    private Double efficiency;
    //项目进度
    private Double progress;
    //项目可调整系数
    private Double contingency;
    //项目成本
    private Double cost;
    //项目需求变更成本
    private Double crCost;
    //story总数
    private Integer storyQty;
    //storypoint总数
    private Integer storyPointQty;
    //crstory总数
    private Integer crStoryQty;
    //crstorypoint总数
    private Integer crStoryPointQty;
    //任务总数
    private Integer taskQty;
    //需求变更占比
    private Double crRate;
    //备注
    private String remarks;

    public Integer getUuId() {
        return uuId;
    }

    public void setUuId(Integer uuId) {
        this.uuId = uuId;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(Double efficiency) {
        this.efficiency = efficiency;
    }

    public Double getProgress() {
        return progress;
    }

    public void setProgress(Double progress) {
        this.progress = progress;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getCrCost() {
        return crCost;
    }

    public void setCrCost(Double crCost) {
        this.crCost = crCost;
    }

    public Integer getStoryQty() {
        return storyQty;
    }

    public void setStoryQty(Integer storyQty) {
        this.storyQty = storyQty;
    }

    public Integer getStoryPointQty() {
        return storyPointQty;
    }

    public void setStoryPointQty(Integer storyPointQty) {
        this.storyPointQty = storyPointQty;
    }

    public Integer getCrStoryQty() {
        return crStoryQty;
    }

    public void setCrStoryQty(Integer crStoryQty) {
        this.crStoryQty = crStoryQty;
    }

    public Integer getCrStoryPointQty() {
        return crStoryPointQty;
    }

    public void setCrStoryPointQty(Integer crStoryPointQty) {
        this.crStoryPointQty = crStoryPointQty;
    }

    public Integer getTaskQty() {
        return taskQty;
    }

    public void setTaskQty(Integer taskQty) {
        this.taskQty = taskQty;
    }

    public Double getCrRate() {
        return crRate;
    }

    public void setCrRate(Double crRate) {
        this.crRate = crRate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Double getContingency() {
        return contingency;
    }

    public void setContingency(Double contingency) {
        this.contingency = contingency;
    }
}
