package com.mdvns.mdvn.project.papi.domain;

import org.springframework.stereotype.Component;

@Component
public class ProjAttchUrls {

    //uuId
    private Integer uuId;
    //附件Id
    private Integer attachmentId;
    //项目Id
    private String projId;
    //是否被删除
    private Integer isDeleted;
    //更新时间
    private Long updateTime;

    public Integer getUuId() {
        return uuId;
    }

    public void setUuId(Integer uuId) {
        this.uuId = uuId;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

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

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

}
