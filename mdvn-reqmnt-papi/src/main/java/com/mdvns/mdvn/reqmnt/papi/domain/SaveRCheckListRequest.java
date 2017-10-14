package com.mdvns.mdvn.reqmnt.papi.domain;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SaveRCheckListRequest {

    //登录者staffID
    private String staffId;
    //requirement checkList（集合）
    private List<ReqmntCheckList> checkLists;

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public List<ReqmntCheckList> getCheckLists() {
        return checkLists;
    }

    public void setCheckLists(List<ReqmntCheckList> checkLists) {
        this.checkLists = checkLists;
    }
}