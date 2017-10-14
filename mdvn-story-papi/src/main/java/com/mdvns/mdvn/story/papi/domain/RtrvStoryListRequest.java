package com.mdvns.mdvn.story.papi.domain;


import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import java.util.List;

@Component
public class RtrvStoryListRequest {
    @NotBlank(message = "请求参数错误，reqmntId不能为空")
    private String reqmntId;
    @Min(value=1,message = "请求参数错误，page不能小于1")
    private Integer page;
    @Min(value=1,message = "请求参数错误，pageSize不能小于1")
    private Integer pageSize;
    /*排序条件：字段名*/
    private String sortBy;

    private List<String> remarks;

    public String getReqmntId() {
        return reqmntId;
    }

    public void setReqmntId(String reqmntId) {
        this.reqmntId = reqmntId;
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
