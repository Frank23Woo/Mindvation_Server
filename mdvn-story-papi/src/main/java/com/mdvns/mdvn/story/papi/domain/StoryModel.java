package com.mdvns.mdvn.story.papi.domain;


import org.springframework.stereotype.Component;

import java.sql.Timestamp;


@Component
public class StoryModel {
    private Integer uuId;
    //模块Id
    private String modelId;
    //用户故事Id
    private String storyId;
    //是否被删除
    private Integer isDeleted;
    //更改时间
    private Long updateTime;

    public Integer getUuId() {
        return uuId;
    }

    public void setUuId(Integer uuId) {
        this.uuId = uuId;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
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

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}
