package com.mdvns.mdvn.staff.papi.domain;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RetrieveStaffListAndTagCntResponse {

//    private List<Staff> staffs;
    private List<StaffAndTagCount> staffs;

    private Integer totalNumber;

    private List<String> remarks;

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    public List<StaffAndTagCount> getStaffs() {
        return staffs;
    }

    public void setStaffs(List<StaffAndTagCount> staffs) {
        this.staffs = staffs;
    }

    public List<String> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<String> remarks) {
        this.remarks = remarks;
    }
}
