package com.mdvns.mdvn.staff.papi.domain;

import org.springframework.stereotype.Component;


@Component
public class Staff {

    private Integer uuId;
    private String staffId;

    private String account;

    private String name;
    //员工头像
    private String avatar;
    private String deptId;
    private Integer positionId;
    //员工入职时间
    private Long hiredate;
    //员工效率值
    private Double effective;
    //员工贡献值
    private Double contribution;
    //员工推荐度
    private Double recommendation;
    //工作饱和度
    private Double worksaturation;

    private String gender;

    /*who create this staff*/
    private String creatorId;

    private String positionLvl;

    private String emailAddr;

    private String phoneNum;

    private String status;

    private Integer tagsCnt;

    public Integer getTagsCnt() {
        return tagsCnt;
    }

    public void setTagsCnt(Integer tagsCnt) {
        this.tagsCnt = tagsCnt;
    }

    public Staff() {
        super();
    }


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

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

    public Long getHiredate() {
        return hiredate;
    }

    public void setHiredate(Long hiredate) {
        this.hiredate = hiredate;
    }

    public Double getEffective() {
        return effective;
    }

    public void setEffective(Double effective) {
        this.effective = effective;
    }

    public Double getContribution() {
        return contribution;
    }

    public void setContribution(Double contribution) {
        this.contribution = contribution;
    }

    public Double getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(Double recommendation) {
        this.recommendation = recommendation;
    }

    public Double getWorksaturation() {
        return worksaturation;
    }

    public void setWorksaturation(Double worksaturation) {
        this.worksaturation = worksaturation;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getPositionLvl() {
        return positionLvl;
    }

    public void setPositionLvl(String positionLvl) {
        this.positionLvl = positionLvl;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Staff)) return false;

        Staff staff = (Staff) o;

        if (getUuId() != null ? !getUuId().equals(staff.getUuId()) : staff.getUuId() != null) return false;
        if (getStaffId() != null ? !getStaffId().equals(staff.getStaffId()) : staff.getStaffId() != null) return false;
        if (getName() != null ? !getName().equals(staff.getName()) : staff.getName() != null) return false;
        if (getAvatar() != null ? !getAvatar().equals(staff.getAvatar()) : staff.getAvatar() != null) return false;
        if (getDeptId() != null ? !getDeptId().equals(staff.getDeptId()) : staff.getDeptId() != null) return false;
        if (getPositionId() != null ? !getPositionId().equals(staff.getPositionId()) : staff.getPositionId() != null)
            return false;
        if (getHiredate() != null ? !getHiredate().equals(staff.getHiredate()) : staff.getHiredate() != null)
            return false;
        if (getEffective() != null ? !getEffective().equals(staff.getEffective()) : staff.getEffective() != null)
            return false;
        if (getContribution() != null ? !getContribution().equals(staff.getContribution()) : staff.getContribution() != null)
            return false;
        if (getRecommendation() != null ? !getRecommendation().equals(staff.getRecommendation()) : staff.getRecommendation() != null)
            return false;
        return getWorksaturation() != null ? getWorksaturation().equals(staff.getWorksaturation()) : staff.getWorksaturation() == null;
    }

    @Override
    public int hashCode() {
        int result = getUuId() != null ? getUuId().hashCode() : 0;
        result = 31 * result + (getStaffId() != null ? getStaffId().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getAvatar() != null ? getAvatar().hashCode() : 0);
        result = 31 * result + (getDeptId() != null ? getDeptId().hashCode() : 0);
        result = 31 * result + (getPositionId() != null ? getPositionId().hashCode() : 0);
        result = 31 * result + (getHiredate() != null ? getHiredate().hashCode() : 0);
        result = 31 * result + (getEffective() != null ? getEffective().hashCode() : 0);
        result = 31 * result + (getContribution() != null ? getContribution().hashCode() : 0);
        result = 31 * result + (getRecommendation() != null ? getRecommendation().hashCode() : 0);
        result = 31 * result + (getWorksaturation() != null ? getWorksaturation().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "uuId=" + uuId +
                ", staffId='" + staffId + '\'' +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", deptId='" + deptId + '\'' +
                ", positionId='" + positionId + '\'' +
                ", hiredate=" + hiredate +
                ", effective=" + effective +
                ", contribution=" + contribution +
                ", recommendation=" + recommendation +
                ", worksaturation=" + worksaturation +
                '}';
    }
}
