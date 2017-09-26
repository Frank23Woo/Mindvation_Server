package com.mdvns.mdvn.project.papi.domain;


import org.springframework.stereotype.Component;

@Component
public class ProjLeaders {

    private Integer uuId;
    //员工Id
    private String staffId;
    //项目Id
    private String projId;

    public Integer getUuId() {
        return uuId;
    }

    public void setUuId(Integer uuId) {
        this.uuId = uuId;
    }

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
}
