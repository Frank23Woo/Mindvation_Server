package com.mdvns.mdvn.story.papi.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix="config")
@Component
public class StoryConfig {

    //-------------------------------------创建用户故事----------------------
    //获取story列表信息url
    private String rtrvStoryInfoListUrl;
    //创建storyurl
    private String createStoryUrl;
    //创建story时保存story基础信息
    private String saveStoryBaseUrl;
    //创建story时保存leaders基础信息
    private String savePLeadersUrl;
    //创建story时保存tags信息
    private String savePTagsUrl;
    //创建story时保存models信息
    private String savePModelsUrl;
    //创建story时保存checklists信息
    private String saveCheckListsUrl;
    //通过checklist的uuid查询它的checklistId(详细Staff)
    private String checklistsListByUuIdUrl;
    //创建story时保存AttchUrls信息
    private String savePAttchUrlsUrl;
    //查询用户故事新建时过程方法子模块是否存在，如果不存在就添加
    private String judgeSubLabelIdUrl;

    //---------------------------------更改用户故事--------------------------

    //更改用户故事基本信息
    private String updateStoryBaseInfoUrl;
    //更改用户故事负责人信息
    private String updateStoryLeadersUrl;
    //更该用户故事附件信息
    private String updateStoryAttchUrlsUrl;
    //更改用户故事标签信息
    private String updateStoryTagsUrl;
    //更改用户故事checkList信息
    private String updateStoryChecklistsUrl;
    //更改用户故事模型信息
    private String updateStoryModelsUrl;
    //---------------------------------获取用户故事详细信息--------------------------
    private String rtrvStoryBaseInfoUrl;
    private String rtrvStoryLedersUrl;
    private String rtrvStoryTagsUrl;
    private String rtrvStoryModelsUrl;
    private String rtrvStoryCheckListsUrl;
    private String rtrvStoryAttUrlsUrl;
    //获取requirment列表信息
    private String rtrvReqmntListUrl;


    public String getRtrvStoryInfoListUrl() {
        return rtrvStoryInfoListUrl;
    }

    public void setRtrvStoryInfoListUrl(String rtrvStoryInfoListUrl) {
        this.rtrvStoryInfoListUrl = rtrvStoryInfoListUrl;
    }

    public String getCreateStoryUrl() {
        return createStoryUrl;
    }

    public void setCreateStoryUrl(String createStoryUrl) {
        this.createStoryUrl = createStoryUrl;
    }

    public String getSaveStoryBaseUrl() {
        return saveStoryBaseUrl;
    }

    public void setSaveStoryBaseUrl(String saveStoryBaseUrl) {
        this.saveStoryBaseUrl = saveStoryBaseUrl;
    }

    public String getSavePTagsUrl() {
        return savePTagsUrl;
    }

    public void setSavePTagsUrl(String savePTagsUrl) {
        this.savePTagsUrl = savePTagsUrl;
    }

    public String getSavePModelsUrl() {
        return savePModelsUrl;
    }

    public void setSavePModelsUrl(String savePModelsUrl) {
        this.savePModelsUrl = savePModelsUrl;
    }

    public String getSaveCheckListsUrl() {
        return saveCheckListsUrl;
    }

    public void setSaveCheckListsUrl(String saveCheckListsUrl) {
        this.saveCheckListsUrl = saveCheckListsUrl;
    }

    public String getSavePAttchUrlsUrl() {
        return savePAttchUrlsUrl;
    }

    public void setSavePAttchUrlsUrl(String savePAttchUrlsUrl) {
        this.savePAttchUrlsUrl = savePAttchUrlsUrl;
    }

    public String getSavePLeadersUrl() {
        return savePLeadersUrl;
    }

    public void setSavePLeadersUrl(String savePLeadersUrl) {
        this.savePLeadersUrl = savePLeadersUrl;
    }

    public String getUpdateStoryBaseInfoUrl() {
        return updateStoryBaseInfoUrl;
    }

    public void setUpdateStoryBaseInfoUrl(String updateStoryBaseInfoUrl) {
        this.updateStoryBaseInfoUrl = updateStoryBaseInfoUrl;
    }

    public String getUpdateStoryLeadersUrl() {
        return updateStoryLeadersUrl;
    }

    public void setUpdateStoryLeadersUrl(String updateStoryLeadersUrl) {
        this.updateStoryLeadersUrl = updateStoryLeadersUrl;
    }

    public String getUpdateStoryAttchUrlsUrl() {
        return updateStoryAttchUrlsUrl;
    }

    public void setUpdateStoryAttchUrlsUrl(String updateStoryAttchUrlsUrl) {
        this.updateStoryAttchUrlsUrl = updateStoryAttchUrlsUrl;
    }

    public String getUpdateStoryTagsUrl() {
        return updateStoryTagsUrl;
    }

    public void setUpdateStoryTagsUrl(String updateStoryTagsUrl) {
        this.updateStoryTagsUrl = updateStoryTagsUrl;
    }

    public String getUpdateStoryChecklistsUrl() {
        return updateStoryChecklistsUrl;
    }

    public void setUpdateStoryChecklistsUrl(String updateStoryChecklistsUrl) {
        this.updateStoryChecklistsUrl = updateStoryChecklistsUrl;
    }

    public String getUpdateStoryModelsUrl() {
        return updateStoryModelsUrl;
    }

    public void setUpdateStoryModelsUrl(String updateStoryModelsUrl) {
        this.updateStoryModelsUrl = updateStoryModelsUrl;
    }

    public String getRtrvStoryBaseInfoUrl() {
        return rtrvStoryBaseInfoUrl;
    }

    public void setRtrvStoryBaseInfoUrl(String rtrvStoryBaseInfoUrl) {
        this.rtrvStoryBaseInfoUrl = rtrvStoryBaseInfoUrl;
    }

    public String getRtrvStoryLedersUrl() {
        return rtrvStoryLedersUrl;
    }

    public void setRtrvStoryLedersUrl(String rtrvStoryLedersUrl) {
        this.rtrvStoryLedersUrl = rtrvStoryLedersUrl;
    }

    public String getRtrvStoryTagsUrl() {
        return rtrvStoryTagsUrl;
    }

    public void setRtrvStoryTagsUrl(String rtrvStoryTagsUrl) {
        this.rtrvStoryTagsUrl = rtrvStoryTagsUrl;
    }

    public String getRtrvStoryModelsUrl() {
        return rtrvStoryModelsUrl;
    }

    public void setRtrvStoryModelsUrl(String rtrvStoryModelsUrl) {
        this.rtrvStoryModelsUrl = rtrvStoryModelsUrl;
    }

    public String getRtrvStoryCheckListsUrl() {
        return rtrvStoryCheckListsUrl;
    }

    public void setRtrvStoryCheckListsUrl(String rtrvStoryCheckListsUrl) {
        this.rtrvStoryCheckListsUrl = rtrvStoryCheckListsUrl;
    }

    public String getRtrvStoryAttUrlsUrl() {
        return rtrvStoryAttUrlsUrl;
    }

    public void setRtrvStoryAttUrlsUrl(String rtrvStoryAttUrlsUrl) {
        this.rtrvStoryAttUrlsUrl = rtrvStoryAttUrlsUrl;
    }

    public String getChecklistsListByUuIdUrl() {
        return checklistsListByUuIdUrl;
    }

    public void setChecklistsListByUuIdUrl(String checklistsListByUuIdUrl) {
        this.checklistsListByUuIdUrl = checklistsListByUuIdUrl;
    }

    public String getRtrvReqmntListUrl() {
        return rtrvReqmntListUrl;
    }

    public void setRtrvReqmntListUrl(String rtrvReqmntListUrl) {
        this.rtrvReqmntListUrl = rtrvReqmntListUrl;
    }

    public String getJudgeSubLabelIdUrl() {
        return judgeSubLabelIdUrl;
    }

    public void setJudgeSubLabelIdUrl(String judgeSubLabelIdUrl) {
        this.judgeSubLabelIdUrl = judgeSubLabelIdUrl;
    }
}
