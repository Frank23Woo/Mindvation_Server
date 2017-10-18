package com.mdvns.mdvn.story.papi.domain;

import com.mdvns.mdvn.common.beans.AttchUrl;
import com.mdvns.mdvn.common.beans.SubFunctionLabel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreateStoryRequest {


    private String creatorId;
    private Story storyInfo;
    //过程方法模块Id(用在判断是否子模块在数据库中)
    private SubFunctionLabel subFunctionLabel;
    //用户故事负责人（集合）
    private List<StoryRoleMember> sMembers;
    //用户故事标签（集合）
    private List<StoryTag> sTags;
//    //用户故事checkList（集合）
//    private List<StoryCheckList> checkLists;
//    //用户故事模型（集合）
//    private List<StoryModel> sModels;
    //用户故事附件（集合）
    private List<AttchUrl> sAttchUrls;

    public SubFunctionLabel getSubFunctionLabel() {
        return subFunctionLabel;
    }

    public void setSubFunctionLabel(SubFunctionLabel subFunctionLabel) {
        this.subFunctionLabel = subFunctionLabel;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public Story getStoryInfo() {
        return storyInfo;
    }

    public void setStoryInfo(Story storyInfo) {
        this.storyInfo = storyInfo;
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

//    public List<StoryModel> getsModels() {
//        return sModels;
//    }
//
//    public void setsModels(List<StoryModel> sModels) {
//        this.sModels = sModels;
//    }

    public List<AttchUrl> getsAttchUrls() {
        return sAttchUrls;
    }

    public void setsAttchUrls(List<AttchUrl> sAttchUrls) {
        this.sAttchUrls = sAttchUrls;
    }
}
