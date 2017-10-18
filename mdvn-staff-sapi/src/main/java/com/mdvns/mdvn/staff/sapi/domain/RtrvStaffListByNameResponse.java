package com.mdvns.mdvn.staff.sapi.domain;

import com.mdvns.mdvn.staff.sapi.domain.entity.Staff;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RtrvStaffListByNameResponse {

    private List<Staff> staffs;

    private Long totalNumber;

    private List<String> remarks;

    public List<Staff> getStaffs() {
        return staffs;
    }

    public void setStaffs(List<Staff> staffs) {
        this.staffs = staffs;
    }

    public Long getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Long totalNumber) {
        this.totalNumber = totalNumber;
    }

    public List<String> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<String> remarks) {
        this.remarks = remarks;
    }
}
