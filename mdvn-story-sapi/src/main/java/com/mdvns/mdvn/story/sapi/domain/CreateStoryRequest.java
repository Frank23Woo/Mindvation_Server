package com.mdvns.mdvn.story.sapi.domain;

import com.mdvns.mdvn.common.beans.AttchUrl;
import com.mdvns.mdvn.story.sapi.domain.entity.Story;
import com.mdvns.mdvn.story.sapi.domain.entity.StoryRoleMember;
import com.mdvns.mdvn.story.sapi.domain.entity.StoryTag;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreateStoryRequest {

    private String creatorId;

    private Story storyInfo;
    //用户故事负责人（集合）
    private List<StoryRoleMember> sMembers;
    //用户故事标签（集合）
    private List<StoryTag> sTags;
//    //用户故事checkList（集合）
//    private List<StoryCheckList> checkLists;
    //用户故事附件（集合）
    private List<AttchUrl> sAttchUrls;

    public Story getStoryInfo() {
        return storyInfo;
    }

    public void setStoryInfo(Story storyInfo) {
        this.storyInfo = storyInfo;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public List<StoryRoleMember> getsMembers() {
        return sMembers;
    }

    public void setsMembers(List<StoryRoleMember> sMembers) {
        this.sMembers = sMembers;
    }

    public List<StoryTag> getsTags() {
        return sTags;
    }

    public void setsTags(List<StoryTag> sTags) {
        this.sTags = sTags;
    }

    public List<AttchUrl> getsAttchUrls() {
        return sAttchUrls;
    }

    public void setsAttchUrls(List<AttchUrl> sAttchUrls) {
        this.sAttchUrls = sAttchUrls;
    }
}
