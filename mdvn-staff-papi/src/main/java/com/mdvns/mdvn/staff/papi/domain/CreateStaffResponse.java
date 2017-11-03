package com.mdvns.mdvn.staff.papi.domain;

import com.mdvns.mdvn.common.beans.DepartmentDetail;
import com.mdvns.mdvn.common.beans.Position;
import com.mdvns.mdvn.common.beans.Tag;
import org.omg.IOP.TAG_CODE_SETS;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreateStaffResponse {

    private String staffId;
    /* staff real name*/
    private String name;

    private String gender;

    private String deptId;
    private Integer positionId;

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getEmailAddr() {
        return emailAddr;
    }

    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }

    private String emailAddr;

    private String phoneNum;

    private Integer tagsCnt;

    private String status;

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }



    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Integer getTagsCnt() {
        return tagsCnt;
    }

    public void setTagsCnt(Integer tagsCnt) {
        this.tagsCnt = tagsCnt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
