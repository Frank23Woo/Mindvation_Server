package com.mdvns.mdvn.reqmnt.sapi.domain;

import com.mdvns.mdvn.reqmnt.sapi.domain.entity.ReqmntCheckList;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdatePCheckListsRequest {
    //项目ID
    private String projId;
    //staffId
    private String staffId;
    //项目checkList（集合）
    private List<ReqmntCheckList> checkLists;

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public List<ReqmntCheckList> getCheckLists() {
        return checkLists;
    }

    public void setCheckLists(List<ReqmntCheckList> checkLists) {
        this.checkLists = checkLists;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
}
