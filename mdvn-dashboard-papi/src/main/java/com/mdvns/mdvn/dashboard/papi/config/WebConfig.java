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

    private String createDashboardUrl;

    private String findDashboardInfoByIdsUrl;

    private String updateDashboardUrl;

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

    public String getCreateDashboardUrl() {
        return createDashboardUrl;
    }

    public void setCreateDashboardUrl(String createDashboardUrl) {
        this.createDashboardUrl = createDashboardUrl;
    }
}
