package com.mdvns.mdvn.project.sapi.domain;


import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RtrvProjectRequest {
    private Integer userId;
    private Integer page;
    private Integer pageSize;
    /*排序条件：字段名*/
    private String sortBy;

    private List<String> remarks;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

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

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public List<String> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<String> remarks) {
        this.remarks = remarks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RtrvProjectRequest that = (RtrvProjectRequest) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (page != null ? !page.equals(that.page) : that.page != null) return false;
        if (pageSize != null ? !pageSize.equals(that.pageSize) : that.pageSize != null) return false;
        if (sortBy != null ? !sortBy.equals(that.sortBy) : that.sortBy != null) return false;
        return remarks != null ? remarks.equals(that.remarks) : that.remarks == null;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (page != null ? page.hashCode() : 0);
        result = 31 * result + (pageSize != null ? pageSize.hashCode() : 0);
        result = 31 * result + (sortBy != null ? sortBy.hashCode() : 0);
        result = 31 * result + (remarks != null ? remarks.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RtrvProjectRequest{" +
                "userId=" + userId +
                ", page=" + page +
                ", pageSize=" + pageSize +
                ", sortBy='" + sortBy + '\'' +
                ", remarks=" + remarks +
                '}';
    }
}
