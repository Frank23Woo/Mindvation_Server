package com.mdvns.mdvn.story.sapi.domain;

import com.mdvns.mdvn.story.sapi.domain.entity.StoryRoleMember;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdateSMembersRequest {

    private String storyId;
    private List<StoryRoleMember> sRoleMembers;
    
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
