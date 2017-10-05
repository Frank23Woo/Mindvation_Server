package com.mdvns.mdvn.project.sapi.domain;

import com.mdvns.mdvn.project.sapi.domain.entity.ProjChecklists;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdatePCheckListsRequest {
    //项目ID
    private String projId;
    //项目checkList（集合）
    private List<ProjChecklists> checkLists;

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public List<ProjChecklists> getCheckLists() {
        return checkLists;
    }

    public void setCheckLists(List<ProjChecklists> checkLists) {
        this.checkLists = checkLists;
    }
}
