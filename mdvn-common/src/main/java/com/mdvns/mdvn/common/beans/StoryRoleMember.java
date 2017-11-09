package com.mdvns.mdvn.common.beans;


import org.springframework.stereotype.Component;

@Component
public class StoryRoleMember {

    private Integer uuId;
    //成员Id
    private String staffId;
    //角色Id
    private String roleId;
    //用户故事Id
    private String storyId;
    //是否被删除
    private Integer isDeleted;
    //更改时间
    private Long updateTime;

    public Integer getUuId() {
        return uuId;
    }

    public void setUuId(Integer uuId) {
        this.uuId = uuId;
    }

    public String getStaffId() {
        return staffId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}
