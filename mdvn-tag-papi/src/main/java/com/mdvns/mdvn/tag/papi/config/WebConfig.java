package com.mdvns.mdvn.tag.papi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "url")
@Component
public class WebConfig {

    /*保存或修改标签Url*/
    private String saveTagUrl;

    /*获取标签列表Url*/
    private String rtrvTagListUrl;

    public String getSaveTagUrl() {
        return saveTagUrl;
    }

    public void setSaveTagUrl(String saveTagUrl) {
        this.saveTagUrl = saveTagUrl;
    }

    public String getRtrvTagListUrl() {
        return rtrvTagListUrl;
    }

    public void setRtrvTagListUrl(String rtrvTagListUrl) {
        this.rtrvTagListUrl = rtrvTagListUrl;
    }
}
