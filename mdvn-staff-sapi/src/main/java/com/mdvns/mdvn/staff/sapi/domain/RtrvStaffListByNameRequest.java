package com.mdvns.mdvn.staff.sapi.domain;

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

    public List<String> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<String> remarks) {
        this.remarks = remarks;
    }
}
