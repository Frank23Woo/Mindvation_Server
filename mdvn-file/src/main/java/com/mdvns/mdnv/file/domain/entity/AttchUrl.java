package com.mdvns.mdnv.file.domain.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AttchUrl {

    @Id
    @GeneratedValue
    private Integer attchId;

    private String belongTo;

    private String url;

    public AttchUrl(String belongTo, String url) {
        this.belongTo = belongTo;
        this.url = url;
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
}
