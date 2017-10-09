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

    /*更新标签引用次数Url*/
    private String updateQuoteCntUrl;

    /*根据指定名称获取标签Url*/
    private String findByNameUrl;

    public String getFindByNameUrl() {
        return findByNameUrl;
    }

    public void setFindByNameUrl(String findByNameUrl) {
        this.findByNameUrl = findByNameUrl;
    }

    public String getUpdateQuoteCntUrl() {
        return updateQuoteCntUrl;
    }

    public void setUpdateQuoteCntUrl(String updateQuoteCntUrl) {
        this.updateQuoteCntUrl = updateQuoteCntUrl;
    }

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
