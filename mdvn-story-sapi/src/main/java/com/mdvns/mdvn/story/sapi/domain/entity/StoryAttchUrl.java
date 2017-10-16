package com.mdvns.mdvn.story.sapi.domain.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Timestamp;

@Component
@Entity
@Table(name = "attachment_story", uniqueConstraints = {@UniqueConstraint(columnNames="attachmentId")})
public class StoryAttchUrl {
    @Id
    @GeneratedValue
    //附件Id
    private Integer attachmentId;
    //用户故事Id
    private String storyId;
    //附件名
    private String attachmentName;
    //是否被删除
    @Column(name = "is_deleted", columnDefinition = "INT default 0")
    private Integer isDeleted;
    //更改时间
    @Column(columnDefinition = "timestamp default current_timestamp ON UPDATE CURRENT_TIMESTAMP", nullable = false)
    private Timestamp updateTime;

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

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
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