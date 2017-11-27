package com.mdvns.mdvn.staff.papi.domain;

import org.springframework.stereotype.Component;

@Component
public class UpdatePasswordRequest {
    private String staffId;
    private String beforePassword;
    private String afterPassword;

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getBeforePassword() {
        return beforePassword;
    }

    public void setBeforePassword(String beforePassword) {
        this.beforePassword = beforePassword;
    }

    public String getAfterPassword() {
        return afterPassword;
    }

    public void setAfterPassword(String afterPassword) {
        this.afterPassword = afterPassword;
    }
}
