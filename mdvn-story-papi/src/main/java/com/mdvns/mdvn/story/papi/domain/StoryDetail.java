package com.mdvns.mdvn.story.papi.domain;

import com.mdvns.mdvn.common.beans.*;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class StoryDetail {

    //用户故事基本信息
    private Story storyInfo;
    private List<CommentDetail> commentDetails;
    private StoryNote storyNote;
    //用户故事成员（集合）
    private List<RoleAndMember> members;
    //需求成员（集合）
    private List<RoleAndMember> rMembers;
    //用户故事标签（集合）
    private List<Tag> tags;
    private SubFunctionLabel subFunctionLabel;
    private FunctionLabel labelDetail;
    private List<SubFunctionLabel> subFunctionLabels;
    private List<TaskDelivery> taskDeliveries;

//    private List<RoleAndMember> members;
    //最后一层过程方法模块对象

    //用户故事附件（集合）
    private List<AttchInfo> attchInfos;
    //用户故事task(可以创建完后添加)
    private List<TaskDetail> sTasks;
    private List<String> remarks;

    public List<CommentDetail> getCommentDetails() {
        return commentDetails;
    }

    public void setCommentDetails(List<CommentDetail> commentDetails) {
        this.commentDetails = commentDetails;
    }

    public StoryNote getStoryNote() {
        return storyNote;
    }

    public void setStoryNote(StoryNote storyNote) {
        this.storyNote = storyNote;
    }

    public List<SubFunctionLabel> getSubFunctionLabels() {
        return subFunctionLabels;
    }

    public void setSubFunctionLabels(List<SubFunctionLabel> subFunctionLabels) {
        this.subFunctionLabels = subFunctionLabels;
    }

    public List<TaskDelivery> getTaskDeliveries() {
        return taskDeliveries;
    }

    public void setTaskDeliveries(List<TaskDelivery> taskDeliveries) {
        this.taskDeliveries = taskDeliveries;
    }

    public List<RoleAndMember> getrMembers() {
        return rMembers;
    }

    public void setrMembers(List<RoleAndMember> rMembers) {
        this.rMembers = rMembers;
    }

    public FunctionLabel getLabelDetail() {
        return labelDetail;
    }

    public void setLabelDetail(FunctionLabel labelDetail) {
        this.labelDetail = labelDetail;
    }

    public Story getStoryInfo() {
        return storyInfo;
    }

    public void setStoryInfo(Story storyInfo) {
        this.storyInfo = storyInfo;
    }

    public List<RoleAndMember> getMembers() {
        return members;
    }

    public void setMembers(List<RoleAndMember> members) {
        this.members = members;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public SubFunctionLabel getSubFunctionLabel() {
        return subFunctionLabel;
    }

    public void setSubFunctionLabel(SubFunctionLabel subFunctionLabel) {
        this.subFunctionLabel = subFunctionLabel;
    }

//    public List<AttchUrl> getSattchUrls() {
//        return sattchUrls;
//    }
//
//    public void setSattchUrls(List<AttchUrl> sattchUrls) {
//        this.sattchUrls = sattchUrls;
//    }

    public List<TaskDetail> getsTasks() {
        return sTasks;
    }

    public void setsTasks(List<TaskDetail> sTasks) {
        this.sTasks = sTasks;
    }

    public List<String> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<String> remarks) {
        this.remarks = remarks;
    }

    public List<AttchInfo> getAttchInfos() {
        return attchInfos;
    }

    public void setAttchInfos(List<AttchInfo> attchInfos) {
        this.attchInfos = attchInfos;
    }
}
