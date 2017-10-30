package com.mdvns.mdvn.department.sapi.domain;

import org.springframework.stereotype.Component;

@Component
public class QueryDepartmentRequest {

    private String departmentId;

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
}
