package com.mdvns.mdvn.tag.papi.domain;

public class CreateTagRequest {

    private String name;
    private Integer quoteCnt;
    private Integer creatorId;
    private String color;
    private String remarks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuoteCnt() {
        return quoteCnt;
    }

    public void setQuoteCnt(Integer quoteCnt) {
        this.quoteCnt = quoteCnt;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "CreateTagRequest{" +
                "name='" + name + '\'' +
                ", quoteCnt=" + quoteCnt +
                ", creatorId=" + creatorId +
                ", color='" + color + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
