package com.mdvns.mdvn.department.sapi.domain;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RtrvDepartmentListResponse {

    private Integer totalPage;
    private List<DepartmentDetail> departmentList;

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<DepartmentDetail> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<DepartmentDetail> departmentList) {
        this.departmentList = departmentList;
    }
}
