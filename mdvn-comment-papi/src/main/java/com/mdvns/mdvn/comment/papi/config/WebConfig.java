package com.mdvns.mdvn.comment.papi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "url")
@Component
public class WebConfig {
    private String createCommentInfoUrl;

    private String rtrvStaffInfoUrl;

    private String likeOrDislikeUrl;

    private String rtrvCreatorIdUrl;

    private String sendMessageUrl;

    private String rtrvCommentDetailInfoUrl;

    public String getRtrvCommentDetailInfoUrl() {
        return rtrvCommentDetailInfoUrl;
    }

    public void setRtrvCommentDetailInfoUrl(String rtrvCommentDetailInfoUrl) {
        this.rtrvCommentDetailInfoUrl = rtrvCommentDetailInfoUrl;
    }

    public String getSendMessageUrl() {
        return sendMessageUrl;
    }

    public void setSendMessageUrl(String sendMessageUrl) {
        this.sendMessageUrl = sendMessageUrl;
    }

    public String getRtrvCreatorIdUrl() {
        return rtrvCreatorIdUrl;
    }

    public void setRtrvCreatorIdUrl(String rtrvCreatorIdUrl) {
        this.rtrvCreatorIdUrl = rtrvCreatorIdUrl;
    }

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
