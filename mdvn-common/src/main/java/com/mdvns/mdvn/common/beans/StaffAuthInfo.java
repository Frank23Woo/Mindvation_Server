package com.mdvns.mdvn.common.beans;

import org.springframework.stereotype.Component;

@Component
public class StaffAuthInfo {

    private Integer id;

    /*项目Id*/
    private String projId;

    /*员工Id*/
    private String staffId;

    /*权限编号*/
    private Integer authCode;

    private  String assignerId;
    
    private String moduleId;

    

    public StaffAuthInfo() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public Integer getAuthCode() {
        return authCode;
    }

    public void setAuthCode(Integer authCode) {
        this.authCode = authCode;
    }

    @Override
    public String toString() {
        return "ProjAuth{" +
                "id=" + id +
                ", projId='" + projId + '\'' +
                ", staffId='" + staffId + '\'' +
                ", authCode=" + authCode +
                '}';
    }
}
