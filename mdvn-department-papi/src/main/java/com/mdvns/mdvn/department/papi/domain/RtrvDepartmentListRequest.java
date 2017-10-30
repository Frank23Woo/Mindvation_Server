package com.mdvns.mdvn.department.papi.domain;

import org.springframework.stereotype.Component;

@Component
public class RtrvDepartmentListRequest {
    private Integer page;
    private Integer pageSize;

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
}
