package com.mdvns.mdvn.staff.papi.domain;


import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RtrvStaffListByStaffIbListRequest {
    private List<String> staffIdList;

    public List<String> getStaffIdList() {
        return staffIdList;
    }

    public void setStaffIdList(List<String> staffIdList) {
        this.staffIdList = staffIdList;
    }
}
