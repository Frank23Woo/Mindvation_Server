package com.mdvns.mdvn.project.sapi.domain.entity;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Component
@Entity
public class Project {
    //项目编号
    private String projId;
    //表自增长生成的Id
    @Id
    @GeneratedValue
    private Integer uuId;
    //项目名称
    @Column(nullable = false)
    private String name;
    //项目创建者Id
    private String creatorId;
    //项目描述
    @Column(columnDefinition = "text",nullable = false)
    private String description;
    //项目优先级
    private Integer priority;
    //项目开始日期
    @Column(name = "start_date", columnDefinition = "timestamp", nullable = false)
    private Timestamp startDate;
    //项目结束时期
    @Column(name = "end_date", columnDefinition = "timestamp", nullable = false)
    private Timestamp endDate;
    //创建时间
    @Column(name = "create_time", nullable = false)
    private Timestamp createTime;
    //项目状态
    private String status;
    //rag状态（R/A/G）
    private String ragStatus;
    //效率值
    @Column(name = "efficiency", columnDefinition = "Double default 0")
    private Double efficiency;
    //项目进度
    @Column(name = "progress", columnDefinition = "Double default 0")
    private Double progress;
    //项目可调整系数
    private Double contingency;
    //项目成本
    @Column(columnDefinition = "Double default 0")
    private Double cost;
    //项目需求变更成本
    @Column(columnDefinition = "Double default 0")
    private Double crCost;
    //story总数
    @Column(columnDefinition = "int default 0")
    private Integer storyQty;
    //storypoint总数
    @Column(columnDefinition = "int default 0")
    private Float storyPointQty;
    //crstory总数
    @Column(columnDefinition = "int default 0")
    private Integer crStoryQty;
    //crstorypoint总数
    @Column(columnDefinition = "int default 0")
    private Integer crStoryPointQty;
    //任务总数
    private Integer checkListQty;
    //需求变更占比
    @Column(columnDefinition = "Double default 0")
    private Double crRate;
    //备注
    private String remarks;
    //是否被删除
    @Column(name = "is_deleted", columnDefinition = "INT default 0")
    private Integer isDeleted;

    public Integer getUuId() {
        return uuId;
    }

    public Integer setUuId(Integer uuId) {
        this.uuId = uuId;
        return uuId;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
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

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public Float getStoryPointQty() {
        return storyPointQty;
    }

    public void setStoryPointQty(Float storyPointQty) {
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

    public Integer getCheckListQty() {
        return checkListQty;
    }

    public void setCheckListQty(Integer checkListQty) {
        this.checkListQty = checkListQty;
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

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getRagStatus() {
        return ragStatus;
    }

    public void setRagStatus(String ragStatus) {
        this.ragStatus = ragStatus;
    }
}
