package com.mdvns.mdvn.project.sapi.domain;

import com.mdvns.mdvn.project.sapi.domain.entity.ProjLeaders;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class UpdatePLeadersRequest {

    /*更新Project负责人信息的员工的staffId*/
    private String staffId;
    private String projId;
    private List<ProjLeaders> leaders;

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public List<ProjLeaders> getLeaders() {
        return leaders;
    }

    public void setLeaders(List<ProjLeaders> leaders) {
        this.leaders = leaders;
    }
}
