package com.mdvns.mdvn.project.papi.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix="config")
@Component
public class ProjConfig {

    //-------------------------------------创建项目----------------------
    //获取project列表信息url
    private String rtrvProjInfoListUrl;
    //创建projecturl
    private String createProjectUrl;
    //创建project时保存project基础信息
    private String saveProjectBaseUrl;
    //创建project时保存leaders基础信息
    private String savePLeadersUrl;
    //创建project时保存tags信息
    private String savePTagsUrl;
    //创建project时保存models信息
    private String savePModelsUrl;
    //创建project时保存checklists信息
    private String saveCheckListsUrl;
    //通过checklist的uuid查询它的checklistId(详细Staff)
    private String checklistsListByUuIdUrl;
    //创建project时保存AttchUrls信息
    private String savePAttchUrlsUrl;



    //---------------------------------更改项目--------------------------

    //更改项目基本信息
    private String updateProjBaseInfoUrl;
    //更改项目负责人信息
    private String updateProjLeadersUrl;
    //更该项目附件信息
    private String updateProjAttchUrlsUrl;
    //更改项目标签信息
    private String updateProjTagsUrl;
    //更改项目checkList信息
    private String updateProjChecklistsUrl;
    //更改项目模型信息
    private String updateProjModelsUrl;
    //---------------------------------获取项目详细信息--------------------------
    private String rtrvProjBaseInfoUrl;
    private String rtrvProjLedersUrl;
    private String rtrvProjTagsUrl;
    private String rtrvProjModelsUrl;
    private String rtrvProjCheckListsUrl;
    private String rtrvProjAttUrlsUrl;
    //获取requirment列表信息 分页信息不能小于1
    //获取reqmnt列表page默认值
    private String reqmntListPage;
    //获取reqmnt列表pageSize默认值
    private String reqmntListPageSize;

    private String rtrvReqmntListUrl;

    private String rtrvAttchListUrl;

    private String rtrvStaffsByIdsUrl;

    private String rtrvReqmntMembersUrl;

    public String getRtrvReqmntMembersUrl() {
        return rtrvReqmntMembersUrl;
    }

    public void setRtrvReqmntMembersUrl(String rtrvReqmntMembersUrl) {
        this.rtrvReqmntMembersUrl = rtrvReqmntMembersUrl;
    }

    public String getRtrvStaffsByIdsUrl() {
        return rtrvStaffsByIdsUrl;
    }

    public void setRtrvStaffsByIdsUrl(String rtrvStaffsByIdsUrl) {
        this.rtrvStaffsByIdsUrl = rtrvStaffsByIdsUrl;
    }

    public String getRtrvAttchListUrl() {
        return rtrvAttchListUrl;
    }

    public void setRtrvAttchListUrl(String rtrvAttchListUrl) {
        this.rtrvAttchListUrl = rtrvAttchListUrl;
    }

    public String getReqmntListPage() {
        return reqmntListPage;
    }

    public void setReqmntListPage(String reqmntListPage) {
        this.reqmntListPage = reqmntListPage;
    }

    public String getReqmntListPageSize() {
        return reqmntListPageSize;
    }

    public void setReqmntListPageSize(String reqmntListPageSize) {
        this.reqmntListPageSize = reqmntListPageSize;
    }

    public String getRtrvProjInfoListUrl() {
        return rtrvProjInfoListUrl;
    }

    public void setRtrvProjInfoListUrl(String rtrvProjInfoListUrl) {
        this.rtrvProjInfoListUrl = rtrvProjInfoListUrl;
    }

    public String getCreateProjectUrl() {
        return createProjectUrl;
    }

    public void setCreateProjectUrl(String createProjectUrl) {
        this.createProjectUrl = createProjectUrl;
    }

    public String getSaveProjectBaseUrl() {
        return saveProjectBaseUrl;
    }

    public void setSaveProjectBaseUrl(String saveProjectBaseUrl) {
        this.saveProjectBaseUrl = saveProjectBaseUrl;
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

    public String getUpdateProjBaseInfoUrl() {
        return updateProjBaseInfoUrl;
    }

    public void setUpdateProjBaseInfoUrl(String updateProjBaseInfoUrl) {
        this.updateProjBaseInfoUrl = updateProjBaseInfoUrl;
    }

    public String getUpdateProjLeadersUrl() {
        return updateProjLeadersUrl;
    }

    public void setUpdateProjLeadersUrl(String updateProjLeadersUrl) {
        this.updateProjLeadersUrl = updateProjLeadersUrl;
    }

    public String getUpdateProjAttchUrlsUrl() {
        return updateProjAttchUrlsUrl;
    }

    public void setUpdateProjAttchUrlsUrl(String updateProjAttchUrlsUrl) {
        this.updateProjAttchUrlsUrl = updateProjAttchUrlsUrl;
    }

    public String getUpdateProjTagsUrl() {
        return updateProjTagsUrl;
    }

    public void setUpdateProjTagsUrl(String updateProjTagsUrl) {
        this.updateProjTagsUrl = updateProjTagsUrl;
    }

    public String getUpdateProjChecklistsUrl() {
        return updateProjChecklistsUrl;
    }

    public void setUpdateProjChecklistsUrl(String updateProjChecklistsUrl) {
        this.updateProjChecklistsUrl = updateProjChecklistsUrl;
    }

    public String getUpdateProjModelsUrl() {
        return updateProjModelsUrl;
    }

    public void setUpdateProjModelsUrl(String updateProjModelsUrl) {
        this.updateProjModelsUrl = updateProjModelsUrl;
    }

    public String getRtrvProjBaseInfoUrl() {
        return rtrvProjBaseInfoUrl;
    }

    public void setRtrvProjBaseInfoUrl(String rtrvProjBaseInfoUrl) {
        this.rtrvProjBaseInfoUrl = rtrvProjBaseInfoUrl;
    }

    public String getRtrvProjLedersUrl() {
        return rtrvProjLedersUrl;
    }

    public void setRtrvProjLedersUrl(String rtrvProjLedersUrl) {
        this.rtrvProjLedersUrl = rtrvProjLedersUrl;
    }

    public String getRtrvProjTagsUrl() {
        return rtrvProjTagsUrl;
    }

    public void setRtrvProjTagsUrl(String rtrvProjTagsUrl) {
        this.rtrvProjTagsUrl = rtrvProjTagsUrl;
    }

    public String getRtrvProjModelsUrl() {
        return rtrvProjModelsUrl;
    }

    public void setRtrvProjModelsUrl(String rtrvProjModelsUrl) {
        this.rtrvProjModelsUrl = rtrvProjModelsUrl;
    }

    public String getRtrvProjCheckListsUrl() {
        return rtrvProjCheckListsUrl;
    }

    public void setRtrvProjCheckListsUrl(String rtrvProjCheckListsUrl) {
        this.rtrvProjCheckListsUrl = rtrvProjCheckListsUrl;
    }

    public String getRtrvProjAttUrlsUrl() {
        return rtrvProjAttUrlsUrl;
    }

    public void setRtrvProjAttUrlsUrl(String rtrvProjAttUrlsUrl) {
        this.rtrvProjAttUrlsUrl = rtrvProjAttUrlsUrl;
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
}
