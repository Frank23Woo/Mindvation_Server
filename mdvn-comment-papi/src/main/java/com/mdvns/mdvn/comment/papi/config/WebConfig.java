package com.mdvns.mdvn.comment.papi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "url")
@Component
public class WebConfig {
    private String createCommentInfoUrl;

    private String rtrvStaffInfoUrl;

    private String likeOrDislikeUrl;

    public String getLikeOrDislikeUrl() {
        return likeOrDislikeUrl;
    }

    public void setLikeOrDislikeUrl(String likeOrDislikeUrl) {
        this.likeOrDislikeUrl = likeOrDislikeUrl;
    }

    public String getRtrvStaffInfoUrl() {
        return rtrvStaffInfoUrl;
    }

    public void setRtrvStaffInfoUrl(String rtrvStaffInfoUrl) {
        this.rtrvStaffInfoUrl = rtrvStaffInfoUrl;
    }

    public String getCreateCommentInfoUrl() {
        return createCommentInfoUrl;
    }

    public void setCreateCommentInfoUrl(String createCommentInfoUrl) {
        this.createCommentInfoUrl = createCommentInfoUrl;
    }
}