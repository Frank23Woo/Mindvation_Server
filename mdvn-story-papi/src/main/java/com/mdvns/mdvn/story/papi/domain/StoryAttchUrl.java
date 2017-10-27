package com.mdvns.mdvn.story.papi.domain;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class StoryAttchUrl {
    //uuId
    private Integer uuId;
    //附件Id
    private Integer attachmentId;
    //项目Id
    private String storyId;
    //是否被删除
    private Integer isDeleted;
    //更改时间
    private Timestamp updateTime;

    public Integer getUuId() {
        return uuId;
    }

    public void setUuId(Integer uuId) {
        this.uuId = uuId;
    }

    public Integer getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(Integer attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
