package com.mdvns.mdvn.comment.sapi.domain;

import org.springframework.stereotype.Component;

@Component
public class DislikeCommentRequest {

    private Integer uuId;
    private String creatorId;

    public Integer getUuId() {
        return uuId;
    }

    public void setUuId(Integer uuId) {
        this.uuId = uuId;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }
}
