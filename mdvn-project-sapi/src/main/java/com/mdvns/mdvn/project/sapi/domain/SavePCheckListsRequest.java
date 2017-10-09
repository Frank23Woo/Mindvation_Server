package com.mdvns.mdvn.project.sapi.domain;

import com.mdvns.mdvn.project.sapi.domain.entity.ProjChecklists;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SavePCheckListsRequest {

    //登录者staffID
    private String staffId;
    //项目checkList（集合）
    private List<ProjChecklists> checkLists;

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public List<ProjChecklists> getCheckLists() {
        return checkLists;
    }

    public void setCheckLists(List<ProjChecklists> checkLists) {
        this.checkLists = checkLists;
    }
}
