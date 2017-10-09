package com.mdvns.mdvn.project.papi.domain;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdatePLeadersRequest {

    private String projId;
    private List<ProjLeaders> leaders;

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
