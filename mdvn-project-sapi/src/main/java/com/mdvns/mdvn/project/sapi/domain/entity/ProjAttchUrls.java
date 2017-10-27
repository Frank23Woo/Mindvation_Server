package com.mdvns.mdvn.project.sapi.domain.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Timestamp;

@Component
@Entity
@Table(name = "attachment_proj")
public class ProjAttchUrls {
    @Id
    @GeneratedValue
    //uuId
    private Integer uuId;
    //附件Id
    private Integer attachmentId;
    //项目Id
    private String projId;
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

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public Integer getUuId() {
        return uuId;
    }

    public void setUuId(Integer uuId) {
        this.uuId = uuId;
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
