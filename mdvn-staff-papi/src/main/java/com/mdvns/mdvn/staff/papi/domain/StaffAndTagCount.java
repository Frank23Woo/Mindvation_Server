package com.mdvns.mdvn.staff.papi.domain;

import org.springframework.stereotype.Component;

@Component
public class StaffAndTagCount {

    private Staff staff;
    private Integer tagCnt;

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Integer getTagCnt() {
        return tagCnt;
    }

    public void setTagCnt(Integer tagCnt) {
        this.tagCnt = tagCnt;
    }
}
