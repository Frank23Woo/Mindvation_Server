package com.mdvns.mdvn.project.sapi.domain.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;

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
}
