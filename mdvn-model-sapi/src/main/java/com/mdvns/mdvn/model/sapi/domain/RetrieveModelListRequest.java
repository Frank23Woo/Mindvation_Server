package com.mdvns.mdvn.model.sapi.domain;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RetrieveModelListRequest {

    /*第几页*/
    private Integer page;

    /*本次查询获取几条数据*/
    private Integer pageSize;

    /*排序条件：字段名*/
    private String sortBy;

    protected List<String> remarks;

    public RetrieveModelListRequest() {
    }

    public RetrieveModelListRequest(Integer page, Integer pageSize, String sortBy) {
        this.page = page;
        this.pageSize = pageSize;
        this.sortBy = sortBy;
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
        if (!(o instanceof RetrieveModelListRequest)) return false;

        RetrieveModelListRequest that = (RetrieveModelListRequest) o;

        if (getPage() != null ? !getPage().equals(that.getPage()) : that.getPage() != null) return false;
        if (getPageSize() != null ? !getPageSize().equals(that.getPageSize()) : that.getPageSize() != null)
            return false;
        if (getSortBy() != null ? !getSortBy().equals(that.getSortBy()) : that.getSortBy() != null)
            return false;
        return getRemarks() != null ? getRemarks().equals(that.getRemarks()) : that.getRemarks() == null;
    }

    @Override
    public int hashCode() {
        int result = getPage() != null ? getPage().hashCode() : 0;
        result = 31 * result + (getPageSize() != null ? getPageSize().hashCode() : 0);
        result = 31 * result + (getSortBy() != null ? getSortBy().hashCode() : 0);
        result = 31 * result + (getRemarks() != null ? getRemarks().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RetrieveTagListRequest{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                ", sortBy='" + sortBy + '\'' +
                ", remarks=" + remarks +
                '}';
    }
}
