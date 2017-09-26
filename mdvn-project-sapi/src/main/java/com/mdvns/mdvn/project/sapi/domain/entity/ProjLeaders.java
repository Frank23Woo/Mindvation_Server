package com.mdvns.mdvn.project.sapi.domain.entity;


import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "staff_proj_map", uniqueConstraints = {@UniqueConstraint(columnNames="uuId")})
public class ProjLeaders {

    @Id
    @GeneratedValue
    private Integer uuId;
    //负责人Id
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
