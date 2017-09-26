package com.mdvns.mdvn.project.papi.domain;

import org.springframework.stereotype.Component;

@Component
public class ProjAttchUrls {

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
