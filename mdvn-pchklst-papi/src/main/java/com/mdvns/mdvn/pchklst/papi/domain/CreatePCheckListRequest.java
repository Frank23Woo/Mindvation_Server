package com.mdvns.mdvn.pchklst.papi.domain;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreatePCheckListRequest {

    /* 任务描述*/
    private	String	description;

    /* 执行任务的人的编号 */
    private	String	asigneeId ;

    /* 创建任务的人的编号 */
    private	String	asignerId ;

    /* 项目编号 */
    private	String	projId ;

    private Long startDate ;

    private Long endDate ;

    /* 备注 */
    private List<String> remarks ;

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

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public List<String> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<String> remarks) {
        this.remarks = remarks;
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

        CreatePCheckListRequest that = (CreatePCheckListRequest) o;

        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (asigneeId != null ? !asigneeId.equals(that.asigneeId) : that.asigneeId != null) return false;
        if (asignerId != null ? !asignerId.equals(that.asignerId) : that.asignerId != null) return false;
        if (projId != null ? !projId.equals(that.projId) : that.projId != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        return remarks != null ? remarks.equals(that.remarks) : that.remarks == null;
    }

    @Override
    public int hashCode() {
        int result = description != null ? description.hashCode() : 0;
        result = 31 * result + (asigneeId != null ? asigneeId.hashCode() : 0);
        result = 31 * result + (asignerId != null ? asignerId.hashCode() : 0);
        result = 31 * result + (projId != null ? projId.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (remarks != null ? remarks.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CreatePCheckListRequest{" +
                "description='" + description + '\'' +
                ", asigneeId='" + asigneeId + '\'' +
                ", asignerId='" + asignerId + '\'' +
                ", projId='" + projId + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", remarks=" + remarks +
                '}';
    }
}
