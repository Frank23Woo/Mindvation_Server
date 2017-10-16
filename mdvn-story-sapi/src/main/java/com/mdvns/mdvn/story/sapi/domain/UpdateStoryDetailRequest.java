package com.mdvns.mdvn.story.sapi.domain;

import com.mdvns.mdvn.common.beans.AttchUrl;
import com.mdvns.mdvn.common.beans.FunctionLabel;
import com.mdvns.mdvn.story.sapi.domain.entity.Story;
import com.mdvns.mdvn.story.sapi.domain.entity.StoryRoleMember;
import com.mdvns.mdvn.story.sapi.domain.entity.StoryTag;
import com.mdvns.mdvn.story.sapi.domain.entity.StoryTask;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdateStoryDetailRequest {

    //staffID
    @NotBlank(message = "请求参数错误，staffId不能为空")
    private String staffId;
    //storyInfo
    private Story storyInfo;
    //成员（集合）
    private List<StoryRoleMember> sRoleMembers;
    //用户故事标签（集合）
    private List<StoryTag> sTags;
    //用户故事Tasks（集合）
    private List<StoryTask> sTasks;
    //最后一层过程方法模块对象
    private FunctionLabel subFunctionLabel;
    //用户故事附件（集合）
    private List<AttchUrl> attchUrls;

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public Story getStoryInfo() {
        return storyInfo;
    }

    public void setStoryInfo(Story storyInfo) {
        this.storyInfo = storyInfo;
    }

    public List<StoryRoleMember> getsRoleMembers() {
        return sRoleMembers;
    }

    public void setsRoleMembers(List<StoryRoleMember> sRoleMembers) {
        this.sRoleMembers = sRoleMembers;
    }

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

    public FunctionLabel getSubFunctionLabel() {
        return subFunctionLabel;
    }

    public void setSubFunctionLabel(FunctionLabel subFunctionLabel) {
        this.subFunctionLabel = subFunctionLabel;
    }

    public List<AttchUrl> getAttchUrls() {
        return attchUrls;
    }

    public void setAttchUrls(List<AttchUrl> attchUrls) {
        this.attchUrls = attchUrls;
    }
}
