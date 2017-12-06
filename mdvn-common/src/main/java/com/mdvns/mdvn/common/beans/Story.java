package com.mdvns.mdvn.common.beans;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Story {
    //用户故事编号
    private String storyId;
    //表自增长生成的Id
    private Integer uuId;
    //用户故事概要
    private String summary;
    //项目ID
    private String projId;
    //需求Id
    private String reqmntId;
    //用户故事创建者Id
    private String creatorId;
    //过程方法子模块
    private String labelId;
    //用户故事描述
    private String description;
    //用户故事优先级
    private Integer priority;
    //用户故事开始日期
    private Long startDate;
    //用户故事结束时期
    private Long endDate;
    //用户故事最后一次更新时间
    private Long lastUpdateTime;
    //创建时间
    private Long createTime;
    //用户故事状态
    private String status;
    //rag状态（R/A/G）
    private String ragStatus;
    //效率值
    private Double efficiency;
    //用户故事进度
    private Float progress;
    //用户故事预期进度
    private Float expectProgress;
    //storyPointCnt+crStoryPointCnt
    private Float totalStoryPoint;
    //持续时间
    private Float duration;
    //已完成storypoint
    private Float finishedSP;
    //缺陷总数
    private Integer defectQty;
    //缺陷严重
    private Integer criticalDefectQtys;
    //缺陷高
    private Integer highDefectsQty;
    //缺陷低
    private Integer midiumDefectsQty;
    //缺陷修复率
    private Double defectFixRate;
    //品控指数
    private Double qutityIndex;
    //是否被删除
    private Integer isDeleted;
    //用户故事点数
    private Float storyPoint;
    //备注
    private String remarks;

    private Staff creatorInfo;

    private Integer memberCunt;

    public Float getExpectProgress() {
        return expectProgress;
    }

    public void setExpectProgress(Float expectProgress) {
        this.expectProgress = expectProgress;
    }

    public Staff getCreatorInfo() {
        return creatorInfo;
    }

    public void setCreatorInfo(Staff creatorInfo) {
        this.creatorInfo = creatorInfo;
    }

    public Integer getMemberCunt() {
        return memberCunt;
    }

    public void setMemberCunt(Integer memberCunt) {
        this.memberCunt = memberCunt;
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

    public Double getQutityIndex() {
        return qutityIndex;
    }

    public void setQutityIndex(Double qutityIndex) {
        this.qutityIndex = qutityIndex;
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

    public String getLabelId() {
        return labelId;
    }

    public void setLabelId(String labelId) {
        this.labelId = labelId;
    }

    public String getReqmntId() {
        return reqmntId;
    }

    public void setReqmntId(String reqmntId) {
        this.reqmntId = reqmntId;
    }

    public Long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
