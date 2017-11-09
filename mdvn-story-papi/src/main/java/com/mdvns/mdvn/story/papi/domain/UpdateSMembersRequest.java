package com.mdvns.mdvn.story.papi.domain;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdateSMembersRequest {

    private String staffId;
    private String projId;
    private String storyId;
    private List<StoryRoleMember> sRoleMembers;

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

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }

    public List<StoryRoleMember> getsRoleMembers() {
        return sRoleMembers;
    }

    public void setsRoleMembers(List<StoryRoleMember> sRoleMembers) {
        this.sRoleMembers = sRoleMembers;
    }
}
