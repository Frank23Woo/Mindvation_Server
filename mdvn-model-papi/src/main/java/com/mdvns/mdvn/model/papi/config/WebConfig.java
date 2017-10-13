package com.mdvns.mdvn.model.papi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "url")
@Component
public class WebConfig {

    /*保存或修改标签Url*/
    private String saveModelUrl;

    /*获取标签列表Url*/
    private String rtrvModelListUrl;


    /*更新标签引用次数Url*/
    private String updateQuoteCntUrl;

    /*根据指定名称获取标签Url*/
    private String findByNameUrl;

    private String findByIdUrl;

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

    public String getSaveModelUrl() {
        return saveModelUrl;
    }

    public void setSaveModelUrl(String saveModelUrl) {
        this.saveModelUrl = saveModelUrl;
    }

    public String getRtrvModelListUrl() {
        return rtrvModelListUrl;
    }

    public void setRtrvModelListUrl(String rtrvModelListUrl) {
        this.rtrvModelListUrl = rtrvModelListUrl;
    }

    public String getFindByIdUrl() {
        return findByIdUrl;
    }

    public void setFindByIdUrl(String findByIdUrl) {
        this.findByIdUrl = findByIdUrl;
    }
}
