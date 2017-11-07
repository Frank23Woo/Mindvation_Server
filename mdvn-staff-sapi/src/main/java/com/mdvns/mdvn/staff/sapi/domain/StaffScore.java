package com.mdvns.mdvn.staff.sapi.domain;

import org.springframework.stereotype.Component;

@Component
public class StaffScore {

    private String staffId;

    private Double tagScore;

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public Double getTagScore() {
        return tagScore;
    }

    public void setTagScore(Double tagScore) {
        this.tagScore = tagScore;
    }
}
