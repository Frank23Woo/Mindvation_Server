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

    public String getSaveAttchInfoUrl() {
        return saveAttchInfoUrl;
    }

    public void setSaveAttchInfoUrl(String saveAttchInfoUrl) {
        this.saveAttchInfoUrl = saveAttchInfoUrl;
    }
}
