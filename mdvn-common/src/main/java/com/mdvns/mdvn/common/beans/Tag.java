package com.mdvns.mdvn.common.beans;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * 标签类，映射表tag
 * 添加联合约束 uniqueConstraints: tagId,name
 * name不能重复
 */
@Component
public class Tag {

    /* 标签编号 */
    private Integer uuId;

    private String tagId;

    private String name;

    /* 标签被引用的次数*/
    private Integer quoteCnt;

    /* 創建標簽人的編號,即員工編號(staffId) */
    private String creatorId;

    /* 標簽色值*/
    private String color;

    /* 標簽創建時間*/
    private Long createTime;

    /*后加的字段，1~7随机给一个数字*/
    private Integer tagStyle;

    /*是否已删除*/
    private Integer isDeleted;

    public Integer getTagStyle() {
        return tagStyle;
    }

    public void setTagStyle(Integer tagStyle) {
        this.tagStyle = tagStyle;
    }

    public Integer getUuId() {
        return uuId;
    }

    public void setUuId(Integer uuId) {
        this.uuId = uuId;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

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

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;

        Tag tag = (Tag) o;

        if (getTagId() != null ? !getTagId().equals(tag.getTagId()) : tag.getTagId() != null) return false;
        if (getName() != null ? !getName().equals(tag.getName()) : tag.getName() != null) return false;
        if (getQuoteCnt() != null ? !getQuoteCnt().equals(tag.getQuoteCnt()) : tag.getQuoteCnt() != null) return false;
        if (getCreatorId() != null ? !getCreatorId().equals(tag.getCreatorId()) : tag.getCreatorId() != null)
            return false;
        if (getColor() != null ? !getColor().equals(tag.getColor()) : tag.getColor() != null) return false;
        return getCreateTime() != null ? getCreateTime().equals(tag.getCreateTime()) : tag.getCreateTime() == null;
    }

    @Override
    public int hashCode() {
        int result = getTagId() != null ? getTagId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getQuoteCnt() != null ? getQuoteCnt().hashCode() : 0);
        result = 31 * result + (getCreatorId() != null ? getCreatorId().hashCode() : 0);
        result = 31 * result + (getColor() != null ? getColor().hashCode() : 0);
        result = 31 * result + (getCreateTime() != null ? getCreateTime().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tagId=" + tagId +
                ", name='" + name + '\'' +
                ", quoteCnt=" + quoteCnt +
                ", creatorId=" + creatorId +
                ", color='" + color + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
