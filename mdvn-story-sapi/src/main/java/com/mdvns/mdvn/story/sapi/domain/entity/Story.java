package com.mdvns.mdvn.story.sapi.domain.entity;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Component
@Entity
public class Story {
    //项目ID
    private String projId;
    //用户故事点数
    private Float storyPoint;
    //用户故事编号
    private String storyId;
    //表自增长生成的Id
    @Id
    @GeneratedValue
    private Integer uuId;
    //需求Id
    private String reqmntId;
    //用户故事概要
    @Column(nullable = false)
    private String summary;
    //过程方法子模块
    private String labelId;
    //用户故事创建者Id
    private String creatorId;
    //用户故事描述
    @Column(columnDefinition = "text",nullable = false)
    private String description;
    //用户故事优先级
    @Column(nullable = false)
    private Integer priority;
    //用户故事开始日期
//    @Column(name = "start_date", columnDefinition = "timestamp", nullable = false)
    private Timestamp startDate;
    //用户故事结束时期
//    @Column(name = "end_date", columnDefinition = "timestamp", nullable = false)
    private Timestamp endDate;
    //用户故事最后一次更新时间
//    @Column(name = "last_update_time", columnDefinition = "timestamp", nullable = false)
    private Timestamp lastUpdateTime;
    //创建时间
    @Column(name = "create_time", nullable = false)
    private Timestamp createTime;
    //用户故事状态
    private String status;
    //rag状态（R/A/G）
    private String ragStatus;
    //效率值
    @Column(name = "efficiency", columnDefinition = "Double default 0")
    private Double efficiency;
    //用户故事进度
    @Column(name = "progress", columnDefinition = "Double default 0")
    private Float progress;
    //用户故事预期进度
    @Column(name = "expect_progress", columnDefinition = "Double default 0")
    private Float expectProgress;
    //storyPointCnt+crStoryPointCnt
    @Column(columnDefinition = "Float default 0")
    private Float totalStoryPoint;
    //持续时间
    @Column(columnDefinition = "Float default 0")
    private Float duration;
    //已完成storypoint
    @Column(columnDefinition = "Float default 0")
    private Float finishedSP;
    //缺陷总数
    private Integer defectQty;

    public Float getExpectProgress() {
        return expectProgress;
    }

    public void setExpectProgress(Float expectProgress) {
        this.expectProgress = expectProgress;
    }

    public String getLabelId() {
        return labelId;
    }

    public void setLabelId(String labelId) {
        this.labelId = labelId;
    }

    //缺陷严重
    @Column(columnDefinition = "int default 0")
    private Integer criticalDefectQtys;
    //缺陷高
    @Column(columnDefinition = "int default 0")
    private Integer highDefectsQty;
    //缺陷中
    @Column(columnDefinition = "int default 0")
    private Integer midiumDefectsQty;
    //缺陷低
    @Column(columnDefinition = "int default 0")
    private Integer lowDefectsQty;
    //缺陷修复率
    @Column(columnDefinition = "Double default 0")
    private Double defectFixRate;
    //品控指数
    @Column(columnDefinition = "Double default 0")
    private Double qualityIndex;
    //是否被删除
    @Column(name = "is_deleted", columnDefinition = "INT default 0")
    private Integer isDeleted;
    //备注
    private String remarks;

    public Integer getLowDefectsQty() {
        return lowDefectsQty;
    }

    public void setLowDefectsQty(Integer lowDefectsQty) {
        this.lowDefectsQty = lowDefectsQty;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public Float getStoryPoint() {
        return storyPoint;
    }

    public void setStoryPoint(Float storyPoint) {
        this.storyPoint = storyPoint;
    }

    public Timestamp getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Timestamp lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
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

    public Double getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(Double efficiency) {
        this.efficiency = efficiency;
    }

    public Float getProgress() {
        return progress;
    }

    public void setProgress(Float progress) {
        this.progress = progress;
    }

    public Float getTotalStoryPoint() {
        return totalStoryPoint;
    }

    public void setTotalStoryPoint(Float totalStoryPoint) {
        this.totalStoryPoint = totalStoryPoint;
    }

    public Float getDuration() {
        return duration;
    }

    public void setDuration(Float duration) {
        this.duration = duration;
    }

    public Float getFinishedSP() {
        return finishedSP;
    }

    public void setFinishedSP(Float finishedSP) {
        this.finishedSP = finishedSP;
    }

    public Integer getDefectQty() {
        return defectQty;
    }

    public void setDefectQty(Integer defectQty) {
        this.defectQty = defectQty;
    }

    public Integer getCriticalDefectQtys() {
        return criticalDefectQtys;
    }

    public void setCriticalDefectQtys(Integer criticalDefectQtys) {
        this.criticalDefectQtys = criticalDefectQtys;
    }

    public Integer getHighDefectsQty() {
        return highDefectsQty;
    }

    public void setHighDefectsQty(Integer highDefectsQty) {
        this.highDefectsQty = highDefectsQty;
    }

    public Integer getMidiumDefectsQty() {
        return midiumDefectsQty;
    }

    public void setMidiumDefectsQty(Integer midiumDefectsQty) {
        this.midiumDefectsQty = midiumDefectsQty;
    }

    public Double getDefectFixRate() {
        return defectFixRate;
    }

    public void setDefectFixRate(Double defectFixRate) {
        this.defectFixRate = defectFixRate;
    }

    public Double getQualityIndex() {
        return qualityIndex;
    }

    public void setQualityIndex(Double qualityIndex) {
        this.qualityIndex = qualityIndex;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getReqmntId() {
        return reqmntId;
    }

    public void setReqmntId(String reqmntId) {
        this.reqmntId = reqmntId;
    }
}
