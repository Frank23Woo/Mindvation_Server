package com.mdvns.mdvn.mdvnwebsocketpapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "url")
@Component
public class WebConfig {
       /*获取staff*/
    private String rtrvStaffInfoUrl;

    private String rtrvServerPushInfoListUrl;

    private String deleteServerPushInfoUrl;

    public String getRtrvStaffInfoUrl() {
        return rtrvStaffInfoUrl;
    }

    public void setRtrvStaffInfoUrl(String rtrvStaffInfoUrl) {
        this.rtrvStaffInfoUrl = rtrvStaffInfoUrl;
    }

    public String getRtrvServerPushInfoListUrl() {
        return rtrvServerPushInfoListUrl;
    }

    public void setRtrvServerPushInfoListUrl(String rtrvServerPushInfoListUrl) {
        this.rtrvServerPushInfoListUrl = rtrvServerPushInfoListUrl;
    }

    public String getDeleteServerPushInfoUrl() {
        return deleteServerPushInfoUrl;
    }

    public void setDeleteServerPushInfoUrl(String deleteServerPushInfoUrl) {
        this.deleteServerPushInfoUrl = deleteServerPushInfoUrl;
    }
}
