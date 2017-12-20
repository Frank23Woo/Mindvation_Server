package com.mdvns.mdvn.common.beans;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class Staff {
    private Integer uuId;
    private String staffId;
    private String Name;
    //员工头像
    private String Avatar;
    private String DeptId;
    private String PositionId;
    //员工入职时间
    private Long Hiredate;
    //员工效率值
    private Double Effective;
    //员工贡献值
    private Double Contribution;
    //员工推荐度
    private Double Recommendation;
    //工作饱和度
    private Double Worksaturation;

    /*who create this staff*/
    private String creatorId;

    private String positionLvl;

    private String emailAddr;

    private String phoneNum;

    private String status;
    private Integer isOnline;

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

    public Integer getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Integer isOnline) {
        this.isOnline = isOnline;
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
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    public String getDeptId() {
        return DeptId;
    }

    public void setDeptId(String deptId) {
        DeptId = deptId;
    }

    public String getPositionId() {
        return PositionId;
    }

    public void setPositionId(String positionId) {
        PositionId = positionId;
    }

    public Long getHiredate() {
        return Hiredate;
    }

    public void setHiredate(Long hiredate) {
        Hiredate = hiredate;
    }

    public Double getEffective() {
        return Effective;
    }

    public void setEffective(Double effective) {
        Effective = effective;
    }

    public Double getContribution() {
        return Contribution;
    }

    public void setContribution(Double contribution) {
        Contribution = contribution;
    }

    public Double getRecommendation() {
        return Recommendation;
    }

    public void setRecommendation(Double recommendation) {
        Recommendation = recommendation;
    }

    public Double getWorksaturation() {
        return Worksaturation;
    }

    public void setWorksaturation(Double worksaturation) {
        Worksaturation = worksaturation;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "uuId=" + uuId +
                ", staffId='" + staffId + '\'' +
                ", Name='" + Name + '\'' +
                ", Avatar='" + Avatar + '\'' +
                ", DeptId='" + DeptId + '\'' +
                ", PositionId='" + PositionId + '\'' +
                ", Hiredate=" + Hiredate +
                ", Effective=" + Effective +
                ", Contribution=" + Contribution +
                ", Recommendation=" + Recommendation +
                ", Worksaturation=" + Worksaturation +
                ", creatorId='" + creatorId + '\'' +
                ", positionLvl='" + positionLvl + '\'' +
                ", emailAddr='" + emailAddr + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", status='" + status + '\'' +
                ", isOnline=" + isOnline +
                '}';
    }
}
