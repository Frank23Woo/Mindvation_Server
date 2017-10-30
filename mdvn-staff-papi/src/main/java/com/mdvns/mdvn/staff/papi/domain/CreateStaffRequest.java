package com.mdvns.mdvn.staff.papi.domain;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreateStaffRequest {

    /* who create this staff*/
    private String creatorId;
    /* staff real name*/
    private String name;

    /* staff login name*/
    private String account;

    private String password;

    private String gender;

    private String deptId;

    private String positionId;

    private String positionLvl;

    private String emailAddr;

    private String phoneNum;

    private List<String> tagIds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getPositionLvl() {
        return positionLvl;
    }

    public void setPositionLvl(String positionLvl) {
        this.positionLvl = positionLvl;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getEmailAddr() {
        return emailAddr;
    }

    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public List<String> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<String> tagIds) {
        this.tagIds = tagIds;
    }
}
