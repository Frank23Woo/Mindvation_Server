package com.mdvns.mdvn.project.papi.domain;


import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import javax.validation.constraints.Null;
import java.util.List;

@Component
public class RtrvProjectListRequest {
    @NotBlank(message = "staffId不能为空")
    private String staffId;
    @Min(value=1,message = "page不能小于1")
    private Integer page;
    @Min(value=1,message = "pageSize不能小于1")
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
