package com.mdvns.mdvn.project.sapi.domain.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Timestamp;

@Component
@Entity
@Table(name = "attachment_proj", uniqueConstraints = {@UniqueConstraint(columnNames="attachmentId")})
public class ProjAttchUrls {
    @Id
    @GeneratedValue
    //附件Id
    private Integer attachmentId;
    //项目Id
    private String projId;
    //附件名
    private String attachmentName;
    //有效标志
    @Column(columnDefinition = "varchar(5) default 'Y'")
    private String yxbz;
    //更改时间
    @Column(columnDefinition = "timestamp default current_timestamp", nullable = false)
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

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getYxbz() {
        return yxbz;
    }

    public void setYxbz(String yxbz) {
        this.yxbz = yxbz;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
