package com.mdvns.mdvn.tag.papi.domain;

import java.util.Date;

public class Tag {

    private	Integer	tagId;
    private	String	tagName;
    private	Integer	tagQuoteCnt;
    private	Integer	tagCreatorId;
    private	String	tagColor;
    private Date tagCreateTime;
    private	String	remarks;

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Integer getTagQuoteCnt() {
        return tagQuoteCnt;
    }

    public void setTagQuoteCnt(Integer tagQuoteCnt) {
        this.tagQuoteCnt = tagQuoteCnt;
    }

    public Integer getTagCreatorId() {
        return tagCreatorId;
    }

    public void setTagCreatorId(Integer tagCreatorId) {
        this.tagCreatorId = tagCreatorId;
    }

    public String getTagColor() {
        return tagColor;
    }

    public void setTagColor(String tagColor) {
        this.tagColor = tagColor;
    }

    public Date getTagCreateTime() {
        return tagCreateTime;
    }

    public void setTagCreateTime(Date tagCreateTime) {
        this.tagCreateTime = tagCreateTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tagId=" + tagId +
                ", tagName='" + tagName + '\'' +
                ", tagQuoteCnt=" + tagQuoteCnt +
                ", tagCreatorId=" + tagCreatorId +
                ", tagColor='" + tagColor + '\'' +
                ", tagCreateTime=" + tagCreateTime +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
