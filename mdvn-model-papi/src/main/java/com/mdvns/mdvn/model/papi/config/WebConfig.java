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

    /*获取标签列表Url(不分页)*/
    private String rtrvModelsUrl;

    /*更新标签引用次数Url*/
    private String updateQuoteCntUrl;

    /*根据指定名称获取标签Url*/
    private String findByNameUrl;

    public String getRtrvModelsUrl() {
        return rtrvModelsUrl;
    }

    public void setRtrvModelsUrl(String rtrvModelsUrl) {
        this.rtrvModelsUrl = rtrvModelsUrl;
    }

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
}
