package com.mdvns.mdvn.reqmnt.sapi.domain.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Timestamp;

@Component
@Entity
@Table(name = "attachment_reqmnt", uniqueConstraints = {@UniqueConstraint(columnNames="attachmentId")})
public class ReqmntAttchUrl {
    @Id
    @GeneratedValue
    //附件Id
    private Integer attachmentId;
    //项目Id
    private String reqmntId;
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

    public String getReqmntId() {
        return reqmntId;
    }

    public void setReqmntId(String reqmntId) {
        this.reqmntId = reqmntId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReqmntAttchUrl that = (ReqmntAttchUrl) o;

        if (attachmentId != null ? !attachmentId.equals(that.attachmentId) : that.attachmentId != null) return false;
        if (reqmntId != null ? !reqmntId.equals(that.reqmntId) : that.reqmntId != null) return false;
        return attachmentName != null ? attachmentName.equals(that.attachmentName) : that.attachmentName == null;
    }

    @Override
    public int hashCode() {
        int result = attachmentId != null ? attachmentId.hashCode() : 0;
        result = 31 * result + (reqmntId != null ? reqmntId.hashCode() : 0);
        result = 31 * result + (attachmentName != null ? attachmentName.hashCode() : 0);
        return result;
    }
}
