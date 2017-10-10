package com.mdvns.mdvn.reqmnt.papi.domain;

import org.springframework.stereotype.Component;

@Component
public class ReqmntTag {

    private Integer uuId;
    //标签Id
    private String tagId;
    //项目Id
    private String reqmntId;
    //有效标志
    private Integer isDeleted;
    //更新时间
    private Long updateTime;

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

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

    public void setReqmntId(String reqmntId) {
        this.reqmntId = reqmntId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReqmntTag reqmntTag = (ReqmntTag) o;

        if (uuId != null ? !uuId.equals(reqmntTag.uuId) : reqmntTag.uuId != null) return false;
        if (tagId != null ? !tagId.equals(reqmntTag.tagId) : reqmntTag.tagId != null) return false;
        if (reqmntId != null ? !reqmntId.equals(reqmntTag.reqmntId) : reqmntTag.reqmntId != null) return false;
        if (isDeleted != null ? !isDeleted.equals(reqmntTag.isDeleted) : reqmntTag.isDeleted != null) return false;
        return updateTime != null ? updateTime.equals(reqmntTag.updateTime) : reqmntTag.updateTime == null;
    }

    @Override
    public int hashCode() {
        int result = uuId != null ? uuId.hashCode() : 0;
        result = 31 * result + (tagId != null ? tagId.hashCode() : 0);
        result = 31 * result + (reqmntId != null ? reqmntId.hashCode() : 0);
        result = 31 * result + (isDeleted != null ? isDeleted.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ReqmntTag{" +
                "uuId=" + uuId +
                ", tagId='" + tagId + '\'' +
                ", reqmntId='" + reqmntId + '\'' +
                ", isDeleted=" + isDeleted +
                ", updateTime=" + updateTime +
                '}';
    }
}
