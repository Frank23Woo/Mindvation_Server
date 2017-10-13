package com.mdvns.mdvn.staff.papi.domain;

import org.springframework.stereotype.Component;


@Component
public class Staff {

    private Integer uuId;
    private String staffId;
    private String name;
    //员工头像
    private String avatar;
    private String deptId;
    private String positionId;
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

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
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
}
