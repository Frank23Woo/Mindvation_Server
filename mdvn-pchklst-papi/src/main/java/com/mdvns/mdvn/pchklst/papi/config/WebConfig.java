package com.mdvns.mdvn.pchklst.papi.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "url")
@Component
public class WebConfig {

    private String createPCheckListUrl;

    private String rtrvPCheckListUrl;

    public String getCreatePCheckListUrl() {
        return createPCheckListUrl;
    }

    public void setCreatePCheckListUrl(String createPCheckListUrl) {
        this.createPCheckListUrl = createPCheckListUrl;
    }

    public String getRtrvPCheckListUrl() {
        return rtrvPCheckListUrl;
    }

    public void setRtrvPCheckListUrl(String rtrvPCheckListUrl) {
        this.rtrvPCheckListUrl = rtrvPCheckListUrl;
    }
}
