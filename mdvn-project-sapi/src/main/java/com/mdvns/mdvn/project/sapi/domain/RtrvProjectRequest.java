package com.mdvns.mdvn.project.sapi.domain;


import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RtrvProjectRequest {
    private String staffId;
    private Integer page;
    private Integer pageSize;
    /*排序条件：字段名*/
    private String sortBy;

    private List<String> remarks;

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
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

}
