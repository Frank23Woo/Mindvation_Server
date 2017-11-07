package com.mdvns.mdvn.staff.sapi.domain.domain;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RtrvStaffListByNameRequest {
    /*第几页*/
    private Integer page;

    /*本次查询获取几条数据*/
    private Integer pageSize;

    /*排序条件：字段名*/
    private String sortBy;

    private String name;

    private List<String> tags;

    protected List<String> remarks;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
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
        if (!(o instanceof RtrvStaffListByNameRequest)) return false;

        RtrvStaffListByNameRequest that = (RtrvStaffListByNameRequest) o;

        if (getPage() != null ? !getPage().equals(that.getPage()) : that.getPage() != null) return false;
        if (getPageSize() != null ? !getPageSize().equals(that.getPageSize()) : that.getPageSize() != null)
            return false;
        if (getSortBy() != null ? !getSortBy().equals(that.getSortBy()) : that.getSortBy() != null) return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (getTags() != null ? !getTags().equals(that.getTags()) : that.getTags() != null) return false;
        return getRemarks() != null ? getRemarks().equals(that.getRemarks()) : that.getRemarks() == null;
    }

    @Override
    public int hashCode() {
        int result = getPage() != null ? getPage().hashCode() : 0;
        result = 31 * result + (getPageSize() != null ? getPageSize().hashCode() : 0);
        result = 31 * result + (getSortBy() != null ? getSortBy().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getTags() != null ? getTags().hashCode() : 0);
        result = 31 * result + (getRemarks() != null ? getRemarks().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RtrvStaffListByNameRequest{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                ", sortBy='" + sortBy + '\'' +
                ", name='" + name + '\'' +
                ", tags=" + tags +
                ", remarks=" + remarks +
                '}';
    }
}
