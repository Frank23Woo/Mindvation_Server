package com.mdvns.mdvn.story.sapi.domain.entity;


import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Timestamp;


@Component
@Entity
@Table(name = "model_story_map", uniqueConstraints = {@UniqueConstraint(columnNames="uuId")})
public class StoryModel {
    @Id
    @GeneratedValue
    private Integer uuId;
    //模块Id
    private String modelId;
    //用户故事Id
    private String storyId;
    //是否被删除
    @Column(name = "is_deleted", columnDefinition = "INT default 0")
    private Integer isDeleted;
    //更改时间
    @Column(columnDefinition = "timestamp NOT NULL default current_timestamp ON UPDATE CURRENT_TIMESTAMP", nullable = false)
    private Timestamp updateTime;

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

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
