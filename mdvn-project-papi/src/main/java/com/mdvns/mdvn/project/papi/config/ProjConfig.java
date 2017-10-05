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
    //通过uuId获取项目的projId(触发器引发的问题)
    private String getProjIdByUuIdUrl;
    //创建project时保存leaders基础信息
    private String savePLeadersUrl;
    //创建project时保存tags信息
    private String savePTagsUrl;
    //创建project时保存models信息
    private String savePModelsUrl;
    //创建project时保存checklists信息
    private String saveCheckListsUrl;
    //通过checklist的uuid查询它的checklistId
    private String getChecklistsListByUuIdUrl;
    //创建project时保存AttchUrls信息
    private String savePAttchUrlsUrl;

    //---------------------------------更改项目--------------------------

    //更改项目基本信息
    private String updateProjBaseInfoUrl;
    //更改项目负责人信息
    private String updateProjLeadersUrl;
    //更该项目附件信息
    private String updateProjAttchUrlsUrl;


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

    public String getGetProjIdByUuIdUrl() {
        return getProjIdByUuIdUrl;
    }

    public void setGetProjIdByUuIdUrl(String getProjIdByUuIdUrl) {
        this.getProjIdByUuIdUrl = getProjIdByUuIdUrl;
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

    public String getGetChecklistsListByUuIdUrl() {
        return getChecklistsListByUuIdUrl;
    }

    public void setGetChecklistsListByUuIdUrl(String getChecklistsListByUuIdUrl) {
        this.getChecklistsListByUuIdUrl = getChecklistsListByUuIdUrl;
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
}
