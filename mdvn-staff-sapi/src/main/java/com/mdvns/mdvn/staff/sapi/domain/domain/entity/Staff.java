package com.mdvns.mdvn.staff.sapi.domain.domain.entity;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Component
public class Staff {
    @Id
    @GeneratedValue
    private Integer uuId;

    private String staffId;
    private String account;
    private String password;
    private String name;
    //员工头像
    @Column(columnDefinition = "text")
    private String avatar;
    private String deptId;
    private Integer positionId;
    //员工入职时间
    private Timestamp hiredate;
    //员工效率值
    private Double effective;
    //员工贡献值
    private Double contribution;
    //员工推荐度
    private Double recommendation;
    //工作饱和度
    private Double worksaturation;


    /*who addes this staff*/
    private String creatorId;

    private String positionLvl;

    private String emailAddr;

    private String phoneNum;

    private String status; // active, inactive, unregistered

    private String gender;

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

    public Timestamp getHiredate() {
        return hiredate;
    }

    public void setHiredate(Timestamp hiredate) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Staff staff = (Staff) o;

        if (uuId != null ? !uuId.equals(staff.uuId) : staff.uuId != null) return false;
        if (staffId != null ? !staffId.equals(staff.staffId) : staff.staffId != null) return false;
        if (account != null ? !account.equals(staff.account) : staff.account != null) return false;
        if (password != null ? !password.equals(staff.password) : staff.password != null) return false;
        if (name != null ? !name.equals(staff.name) : staff.name != null) return false;
        if (avatar != null ? !avatar.equals(staff.avatar) : staff.avatar != null) return false;
        if (deptId != null ? !deptId.equals(staff.deptId) : staff.deptId != null) return false;
        if (positionId != null ? !positionId.equals(staff.positionId) : staff.positionId != null) return false;
        if (hiredate != null ? !hiredate.equals(staff.hiredate) : staff.hiredate != null) return false;
        if (effective != null ? !effective.equals(staff.effective) : staff.effective != null) return false;
        if (contribution != null ? !contribution.equals(staff.contribution) : staff.contribution != null) return false;
        if (recommendation != null ? !recommendation.equals(staff.recommendation) : staff.recommendation != null)
            return false;
        if (worksaturation != null ? !worksaturation.equals(staff.worksaturation) : staff.worksaturation != null)
            return false;
        if (creatorId != null ? !creatorId.equals(staff.creatorId) : staff.creatorId != null) return false;
        if (positionLvl != null ? !positionLvl.equals(staff.positionLvl) : staff.positionLvl != null) return false;
        if (emailAddr != null ? !emailAddr.equals(staff.emailAddr) : staff.emailAddr != null) return false;
        if (phoneNum != null ? !phoneNum.equals(staff.phoneNum) : staff.phoneNum != null) return false;
        return status != null ? status.equals(staff.status) : staff.status == null;
    }

    @Override
    public int hashCode() {
        int result = uuId != null ? uuId.hashCode() : 0;
        result = 31 * result + (staffId != null ? staffId.hashCode() : 0);
        result = 31 * result + (account != null ? account.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
        result = 31 * result + (deptId != null ? deptId.hashCode() : 0);
        result = 31 * result + (positionId != null ? positionId.hashCode() : 0);
        result = 31 * result + (hiredate != null ? hiredate.hashCode() : 0);
        result = 31 * result + (effective != null ? effective.hashCode() : 0);
        result = 31 * result + (contribution != null ? contribution.hashCode() : 0);
        result = 31 * result + (recommendation != null ? recommendation.hashCode() : 0);
        result = 31 * result + (worksaturation != null ? worksaturation.hashCode() : 0);
        result = 31 * result + (creatorId != null ? creatorId.hashCode() : 0);
        result = 31 * result + (positionLvl != null ? positionLvl.hashCode() : 0);
        result = 31 * result + (emailAddr != null ? emailAddr.hashCode() : 0);
        result = 31 * result + (phoneNum != null ? phoneNum.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "uuId=" + uuId +
                ", staffId='" + staffId + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", deptId='" + deptId + '\'' +
                ", positionId='" + positionId + '\'' +
                ", hiredate=" + hiredate +
                ", effective=" + effective +
                ", contribution=" + contribution +
                ", recommendation=" + recommendation +
                ", worksaturation=" + worksaturation +
                ", creatorId='" + creatorId + '\'' +
                ", positionLvl='" + positionLvl + '\'' +
                ", emailAddr='" + emailAddr + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
