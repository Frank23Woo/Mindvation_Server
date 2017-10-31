package com.mdvns.mdvn.file.papi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Papi 调用SAPI的相关URL配置
 */

@ConfigurationProperties(prefix = "url")
@Component
public class WebConfig {

    /*SAPI保存附件信息url*/
    private String saveAttchInfoUrl;

    private String deleteAttchUrl;

    private String retrieveUrl;

    private String retrieveAttchsUrl;


    public String getSaveAttchInfoUrl() {
        return saveAttchInfoUrl;
    }

    public void setSaveAttchInfoUrl(String saveAttchInfoUrl) {
        this.saveAttchInfoUrl = saveAttchInfoUrl;
    }

    public String getDeleteAttchUrl() {
        return deleteAttchUrl;
    }

    public void setDeleteAttchUrl(String deleteAttchUrl) {
        this.deleteAttchUrl = deleteAttchUrl;
    }

    public String getRetrieveUrl() {
        return retrieveUrl;
    }

    public void setRetrieveUrl(String retrieveUrl) {
        this.retrieveUrl = retrieveUrl;
    }

    public String getRetrieveAttchsUrl() {
        return retrieveAttchsUrl;
    }

    public void setRetrieveAttchsUrl(String retrieveAttchsUrl) {
        this.retrieveAttchsUrl = retrieveAttchsUrl;
    }

}
