package com.mdvns.mdvn.reqmnt.sapi.domain.entity;

import org.springframework.stereotype.Component;
import javax.persistence.*;
import java.sql.Timestamp;

@Component
@Entity
@Table(name = "tag_reqmnt_map", uniqueConstraints = {@UniqueConstraint(columnNames="uuId")})
public class ReqmntTag {

    @Id
    @GeneratedValue
    private Integer uuId;
    //标签Id
    private String tagId;
    //项目Id
    private String reqmntId;
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

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getReqmntId() {
        return reqmntId;
    }

    public void setReqmntId(String projId) {
        this.reqmntId = projId;
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
