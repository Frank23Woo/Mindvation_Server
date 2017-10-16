package com.mdvns.mdnv.file.domain.entity;


import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Component
public class AttchUrl {

    @Id
    @GeneratedValue
    private Integer attchId;

    private String belongTo;

    private String url;
    //是否被删除
    @Column(name = "is_deleted", columnDefinition = "INT default 0")
    private Integer isDeleted;
    //更改时间
    @Column(columnDefinition = "timestamp default current_timestamp ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp updateTime;

    public AttchUrl(String belongTo, String url) {
        this.belongTo = belongTo;
        this.url = url;
    }

    public AttchUrl(String belongTo, String url, Integer isDeleted, Timestamp updateTime) {
        this.belongTo = belongTo;
        this.url = url;
        this.isDeleted = isDeleted;
        this.updateTime = updateTime;
    }

    public Integer getAttchId() {
        return attchId;
    }

    public void setAttchId(Integer attchId) {
        this.attchId = attchId;
    }

    public String getBelongTo() {
        return belongTo;
    }

    public void setBelongTo(String belongTo) {
        this.belongTo = belongTo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
