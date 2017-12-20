package com.mdvns.mdvn.websocket.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "url")
@Component
public class WebConfig {

    /*获取staff列表Url*/
    private String rtrvStaffListUrl;
}
