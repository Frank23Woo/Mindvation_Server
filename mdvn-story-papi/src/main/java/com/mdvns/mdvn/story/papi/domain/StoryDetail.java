package com.mdvns.mdvn.story.papi.domain;

import com.mdvns.mdvn.common.beans.AttchUrl;
import com.mdvns.mdvn.common.beans.FunctionLabel;
import com.mdvns.mdvn.common.beans.Staff;
import com.mdvns.mdvn.common.beans.Tag;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class StoryDetail {

    //用户故事基本信息
    private Story storyInfo;
    //用户故事成员（集合）
    private List<Staff> members;
    //用户故事标签（集合）
    private List<Tag> tags;
    //最后一层过程方法模块对象
    private FunctionLabel subFunctionLabel;
    //用户故事附件（集合）
    private List<AttchUrl> sattchUrls;
    //用户故事task(可以创建完后添加)
    private List<StoryTask> sTasks;
    private List<String> remarks;

    public Story getStoryInfo() {
        return storyInfo;
    }

    public void setStoryInfo(Story storyInfo) {
        this.storyInfo = storyInfo;
    }

    public List<Staff> getMembers() {
        return members;
    }

    public void setMembers(List<Staff> members) {
        this.members = members;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public FunctionLabel getSubFunctionLabel() {
        return subFunctionLabel;
    }

    public void setSubFunctionLabel(FunctionLabel subFunctionLabel) {
        this.subFunctionLabel = subFunctionLabel;
    }

    public List<AttchUrl> getSattchUrls() {
        return sattchUrls;
    }

    public void setSattchUrls(List<AttchUrl> sattchUrls) {
        this.sattchUrls = sattchUrls;
    }

    public List<StoryTask> getsTasks() {
        return sTasks;
    }

    public void setsTasks(List<StoryTask> sTasks) {
        this.sTasks = sTasks;
    }

    public List<String> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<String> remarks) {
        this.remarks = remarks;
    }
}
