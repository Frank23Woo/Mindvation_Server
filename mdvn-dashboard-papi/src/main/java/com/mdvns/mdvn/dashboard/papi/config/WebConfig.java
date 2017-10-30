package com.mdvns.mdvn.dashboard.papi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "url")
@Component
public class WebConfig {
    private String rtrvReqmntInfoBymodelIdUrl;

    private String findModelByIdUrl;

    private String rtrvStoryInfoListUrl;

    private String findDashboardInfoByIdUrl;

    private String rtrvStoryInfoListByStoryIdsUrl;

    private String createSprintInfoUrl;

    private String findDashboardInfoByIdsUrl;

    private String updateDashboardUrl;

    private String findIterationModelByIdUrl;

    private String updateSprintInfoUrl;
    private String updateMyDashboardUrl;
    private String myDashboardInfosUrl;

    public String getMyDashboardInfosUrl() {
        return myDashboardInfosUrl;
    }

    public void setMyDashboardInfosUrl(String myDashboardInfosUrl) {
        this.myDashboardInfosUrl = myDashboardInfosUrl;
    }

    public String getUpdateMyDashboardUrl() {
        return updateMyDashboardUrl;
    }

    public void setUpdateMyDashboardUrl(String updateMyDashboardUrl) {
        this.updateMyDashboardUrl = updateMyDashboardUrl;
    }

    public String getUpdateSprintInfoUrl() {
        return updateSprintInfoUrl;
    }

    public void setUpdateSprintInfoUrl(String updateSprintInfoUrl) {
        this.updateSprintInfoUrl = updateSprintInfoUrl;
    }

    public String getFindIterationModelByIdUrl() {
        return findIterationModelByIdUrl;
    }

    public void setFindIterationModelByIdUrl(String findIterationModelByIdUrl) {
        this.findIterationModelByIdUrl = findIterationModelByIdUrl;
    }

    public String getUpdateDashboardUrl() {
        return updateDashboardUrl;
    }

    public void setUpdateDashboardUrl(String updateDashboardUrl) {
        this.updateDashboardUrl = updateDashboardUrl;
    }

    public String getFindDashboardInfoByIdsUrl() {
        return findDashboardInfoByIdsUrl;
    }

    public void setFindDashboardInfoByIdsUrl(String findDashboardInfoByIdsUrl) {
        this.findDashboardInfoByIdsUrl = findDashboardInfoByIdsUrl;
    }

    public String getRtrvStoryInfoListByStoryIdsUrl() {
        return rtrvStoryInfoListByStoryIdsUrl;
    }

    public void setRtrvStoryInfoListByStoryIdsUrl(String rtrvStoryInfoListByStoryIdsUrl) {
        this.rtrvStoryInfoListByStoryIdsUrl = rtrvStoryInfoListByStoryIdsUrl;
    }

    public String getFindDashboardInfoByIdUrl() {
        return findDashboardInfoByIdUrl;
    }

    public void setFindDashboardInfoByIdUrl(String findDashboardInfoByIdUrl) {
        this.findDashboardInfoByIdUrl = findDashboardInfoByIdUrl;
    }

    public String getRtrvStoryInfoListUrl() {
        return rtrvStoryInfoListUrl;
    }

    public void setRtrvStoryInfoListUrl(String rtrvStoryInfoListUrl) {
        this.rtrvStoryInfoListUrl = rtrvStoryInfoListUrl;
    }

    public String getFindModelByIdUrl() {
        return findModelByIdUrl;
    }

    public void setFindModelByIdUrl(String findModelByIdUrl) {
        this.findModelByIdUrl = findModelByIdUrl;
    }

    public String getRtrvReqmntInfoBymodelIdUrl() {
        return rtrvReqmntInfoBymodelIdUrl;
    }

    public void setRtrvReqmntInfoBymodelIdUrl(String rtrvReqmntInfoBymodelIdUrl) {
        this.rtrvReqmntInfoBymodelIdUrl = rtrvReqmntInfoBymodelIdUrl;
    }

    public String getCreateSprintInfoUrl() {
        return createSprintInfoUrl;
    }

    public void setCreateSprintInfoUrl(String createSprintInfoUrl) {
        this.createSprintInfoUrl = createSprintInfoUrl;
    }
}
