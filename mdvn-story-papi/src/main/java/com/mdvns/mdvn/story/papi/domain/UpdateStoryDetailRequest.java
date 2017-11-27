package com.mdvns.mdvn.story.papi.domain;


import com.mdvns.mdvn.common.beans.SubFunctionLabel;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdateStoryDetailRequest {

    //staffID
    @NotBlank(message = "请求参数错误，staffId不能为空")
    private String creatorId;
    //storyInfo
    private Story storyInfo;
    //成员（集合）
    private List<RoleMember> members;
//    private List<StoryRoleMember> sMembers;
    //用户故事标签（集合）
    private List<StoryTag> sTags;
    //用户故事Tasks（集合）
    private List<StoryTask> sTasks;
    //最后一层过程方法模块对象
    private SubFunctionLabel subFunctionLabel;
//    //用户故事附件（集合）
    private List<StoryAttchUrl> attchUrls;
    //便签信息（会议记录）
    private String noteDesc;

    public String getNoteDesc() {
        return noteDesc;
    }

    public void setNoteDesc(String noteDesc) {
        this.noteDesc = noteDesc;
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

    public List<RoleMember> getMembers() {
        return members;
    }

    public void setMembers(List<RoleMember> members) {
        this.members = members;
    }

    //    public List<StoryRoleMember> getsMembers() {
//        return sMembers;
//    }
//
//    public void setsMembers(List<StoryRoleMember> sMembers) {
//        this.sMembers = sMembers;
//    }

    public List<StoryTag> getsTags() {
        return sTags;
    }

    public void setsTags(List<StoryTag> sTags) {
        this.sTags = sTags;
    }

    public List<StoryTask> getsTasks() {
        return sTasks;
    }

    public void setsTasks(List<StoryTask> sTasks) {
        this.sTasks = sTasks;
    }

    public SubFunctionLabel getSubFunctionLabel() {
        return subFunctionLabel;
    }

    public void setSubFunctionLabel(SubFunctionLabel subFunctionLabel) {
        this.subFunctionLabel = subFunctionLabel;
    }

    public List<StoryAttchUrl> getAttchUrls() {
        return attchUrls;
    }

    public void setAttchUrls(List<StoryAttchUrl> attchUrls) {
        this.attchUrls = attchUrls;
    }
}
