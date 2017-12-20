package com.mdvns.mdvn.staff.papi.domain;

import org.springframework.stereotype.Component;

@Component
public class logOutRequest {
    private String staffId;

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
}
