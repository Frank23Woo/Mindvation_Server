package com.mdvns.mdvn.story.sapi.domain.entity;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;


@Component
@Entity
public class StoryNote {

    @Id
    @GeneratedValue
    private Integer uuId;
    //用户故事ID
    private String storyId;
    //便签描述
    @Column(columnDefinition = "text",nullable = false)
    private String noteDesc;
    //创建者ID(更改note的staffId)
    private String creatorId;
    //最后一次更改时间
    @Column(name = "last_update_time", columnDefinition = "timestamp NOT NULL default current_timestamp ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp lastUpdateTime;
    //是否被删除
    @Column(name = "is_deleted", columnDefinition = "INT default 0")
    private Integer isDeleted;

    public Integer getUuId() {
        return uuId;
    }

    public void setUuId(Integer uuId) {
        this.uuId = uuId;
    }

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }

    public String getNoteDesc() {
        return noteDesc;
    }

    public void setNoteDesc(String noteDesc) {
        this.noteDesc = noteDesc;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public Timestamp getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Timestamp lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}
