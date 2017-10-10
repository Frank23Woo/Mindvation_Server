package com.mdvns.mdvn.tag.papi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "url")
@Component
public class WebConfig {

    /*保存或修改标签Url*/
    private String saveTagUrl;

    //保存完tag通过触发器获取其tagId
    private String getTagIdByUuIdUrl;

    /*获取标签列表Url*/
    private String rtrvTagListUrl;

    /*更新标签引用次数Url*/
    private String updateQuoteCntUrl;

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

    public String getGetTagIdByUuIdUrl() {
        return getTagIdByUuIdUrl;
    }

    public void setGetTagIdByUuIdUrl(String getTagIdByUuIdUrl) {
        this.getTagIdByUuIdUrl = getTagIdByUuIdUrl;
    }
}
