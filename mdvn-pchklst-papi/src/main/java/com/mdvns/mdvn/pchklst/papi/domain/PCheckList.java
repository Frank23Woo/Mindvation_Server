package com.mdvns.mdvn.pchklst.papi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Component;

@Component
public class PCheckList {

    /* uninque id*/
    private	Integer	uuId ;

    /* check list id*/
    private	String pCheckListId ;

    /* the project this item belongs to*/
    private	String	projId ;

    /* item description*/
    private	String description ;

    /* asigness of this item*/
    private	String asigneeId ;

    /* creator id of this item */
    private	String asignerId ;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Long startDate ;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Long endDate ;

    /* status of this time*/
    private	Integer status ;

    /* create time of this item*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private	Long createTime ;

    /* last update time*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private	Long updateTime ;

    /* close time of this item*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private	Long closeTime ;




    public Integer getUuId() {
        return uuId;
    }

    public void setUuId(Integer uuId) {
        this.uuId = uuId;
    }

    public String getpCheckListId() {
        return pCheckListId;
    }

    public void setpCheckListId(String pCheckListId) {
        this.pCheckListId = pCheckListId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAsigneeId() {
        return asigneeId;
    }

    public void setAsigneeId(String asigneeId) {
        this.asigneeId = asigneeId;
    }

    public String getAsignerId() {
        return asignerId;
    }

    public void setAsignerId(String asignerId) {
        this.asignerId = asignerId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Long getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Long closeTime) {
        this.closeTime = closeTime;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PCheckList that = (PCheckList) o;

        if (uuId != null ? !uuId.equals(that.uuId) : that.uuId != null) return false;
        if (pCheckListId != null ? !pCheckListId.equals(that.pCheckListId) : that.pCheckListId != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (asigneeId != null ? !asigneeId.equals(that.asigneeId) : that.asigneeId != null) return false;
        if (asignerId != null ? !asignerId.equals(that.asignerId) : that.asignerId != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (closeTime != null ? !closeTime.equals(that.closeTime) : that.closeTime != null) return false;
        if (projId != null ? !projId.equals(that.projId) : that.projId != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        return endDate != null ? endDate.equals(that.endDate) : that.endDate == null;
    }

    @Override
    public int hashCode() {
        int result = uuId != null ? uuId.hashCode() : 0;
        result = 31 * result + (pCheckListId != null ? pCheckListId.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (asigneeId != null ? asigneeId.hashCode() : 0);
        result = 31 * result + (asignerId != null ? asignerId.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (closeTime != null ? closeTime.hashCode() : 0);
        result = 31 * result + (projId != null ? projId.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PCheckList{" +
                "uuId=" + uuId +
                ", pCheckListId='" + pCheckListId + '\'' +
                ", description='" + description + '\'' +
                ", asigneeId='" + asigneeId + '\'' +
                ", asignerId='" + asignerId + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", closeTime=" + closeTime +
                ", projId='" + projId + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
