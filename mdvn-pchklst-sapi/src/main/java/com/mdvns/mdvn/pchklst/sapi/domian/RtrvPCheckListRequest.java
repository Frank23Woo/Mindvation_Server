package com.mdvns.mdvn.pchklst.sapi.domian;


import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RtrvPCheckListRequest {

    /* The project ID which this Check List belongs to*/
    private String projectId;
    private Integer page ;
    private Integer pageSize ;
    private List<String> remarks ;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<String> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<String> remarks) {
        this.remarks = remarks;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RtrvPCheckListRequest that = (RtrvPCheckListRequest) o;

        if (projectId != null ? !projectId.equals(that.projectId) : that.projectId != null) return false;
        if (page != null ? !page.equals(that.page) : that.page != null) return false;
        if (pageSize != null ? !pageSize.equals(that.pageSize) : that.pageSize != null) return false;
        return remarks != null ? remarks.equals(that.remarks) : that.remarks == null;
    }

    @Override
    public int hashCode() {
        int result = projectId != null ? projectId.hashCode() : 0;
        result = 31 * result + (page != null ? page.hashCode() : 0);
        result = 31 * result + (pageSize != null ? pageSize.hashCode() : 0);
        result = 31 * result + (remarks != null ? remarks.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RtrvPCheckListRequest{" +
                "projectId='" + projectId + '\'' +
                ", page=" + page +
                ", pageSize=" + pageSize +
                ", remarks=" + remarks +
                '}';
    }
}