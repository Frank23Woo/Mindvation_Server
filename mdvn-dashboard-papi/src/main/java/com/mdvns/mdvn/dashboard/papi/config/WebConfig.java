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
    private String rtrvStaffInfoUrl;

    private String rtrvlabelIdBystoryIdUrl;

    private String updateSprintStartStatusUrl;

    private String updateSprintCloseStatusUrl;

    private String itSprintUrl;
    private String findAllDashboardByIdUrl;
    private String findAllDashboardInfoByIdsUrl;
    private String updateMyDashboardForTaskUrl;
    private String myDashboardInfosForTaskUrl;
    private String findUuIdByIdsUrl;
    private String updateDashboardForAndroidUrl;

    public String getUpdateDashboardForAndroidUrl() {
        return updateDashboardForAndroidUrl;
    }

    public void setUpdateDashboardForAndroidUrl(String updateDashboardForAndroidUrl) {
        this.updateDashboardForAndroidUrl = updateDashboardForAndroidUrl;
    }

    public String getFindUuIdByIdsUrl() {
        return findUuIdByIdsUrl;
    }

    public void setFindUuIdByIdsUrl(String findUuIdByIdsUrl) {
        this.findUuIdByIdsUrl = findUuIdByIdsUrl;
    }

    public String getUpdateMyDashboardForTaskUrl() {
        return updateMyDashboardForTaskUrl;
    }

    public void setUpdateMyDashboardForTaskUrl(String updateMyDashboardForTaskUrl) {
        this.updateMyDashboardForTaskUrl = updateMyDashboardForTaskUrl;
    }

    public String getMyDashboardInfosForTaskUrl() {
        return myDashboardInfosForTaskUrl;
    }

    public void setMyDashboardInfosForTaskUrl(String myDashboardInfosForTaskUrl) {
        this.myDashboardInfosForTaskUrl = myDashboardInfosForTaskUrl;
    }

    public String getFindAllDashboardInfoByIdsUrl() {
        return findAllDashboardInfoByIdsUrl;
    }

    public void setFindAllDashboardInfoByIdsUrl(String findAllDashboardInfoByIdsUrl) {
        this.findAllDashboardInfoByIdsUrl = findAllDashboardInfoByIdsUrl;
    }

    public String getFindAllDashboardByIdUrl() {
        return findAllDashboardByIdUrl;
    }

    public void setFindAllDashboardByIdUrl(String findAllDashboardByIdUrl) {
        this.findAllDashboardByIdUrl = findAllDashboardByIdUrl;
    }

    public String getItSprintUrl() {
        return itSprintUrl;
    }

    public void setItSprintUrl(String itSprintUrl) {
        this.itSprintUrl = itSprintUrl;
    }

    public String getUpdateSprintStartStatusUrl() {
        return updateSprintStartStatusUrl;
    }

    public void setUpdateSprintStartStatusUrl(String updateSprintStartStatusUrl) {
        this.updateSprintStartStatusUrl = updateSprintStartStatusUrl;
    }

    public String getUpdateSprintCloseStatusUrl() {
        return updateSprintCloseStatusUrl;
    }

    public void setUpdateSprintCloseStatusUrl(String updateSprintCloseStatusUrl) {
        this.updateSprintCloseStatusUrl = updateSprintCloseStatusUrl;
    }

    public String getRtrvlabelIdBystoryIdUrl() {
        return rtrvlabelIdBystoryIdUrl;
    }

    public void setRtrvlabelIdBystoryIdUrl(String rtrvlabelIdBystoryIdUrl) {
        this.rtrvlabelIdBystoryIdUrl = rtrvlabelIdBystoryIdUrl;
    }

    public String getRtrvStaffInfoUrl() {
        return rtrvStaffInfoUrl;
    }

    public void setRtrvStaffInfoUrl(String rtrvStaffInfoUrl) {
        this.rtrvStaffInfoUrl = rtrvStaffInfoUrl;
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
