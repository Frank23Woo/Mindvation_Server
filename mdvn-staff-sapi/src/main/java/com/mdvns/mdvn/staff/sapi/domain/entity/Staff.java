package com.mdvns.mdvn.staff.sapi.domain.entity;

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
    private String Name;
    //员工头像
    @Column(columnDefinition = "text")
    private String Avatar;
    private String DeptId;
    private String PositionId;
    //员工入职时间
    private Timestamp Hiredate;
    //员工效率值
    private Double Effective;
    //员工贡献值
    private Double Contribution;
    //员工推荐度
    private Double Recommendation;
    //工作饱和度
    private Double Worksaturation;

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

    public Timestamp getHiredate() {
        return Hiredate;
    }

    public void setHiredate(Timestamp hiredate) {
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
}
