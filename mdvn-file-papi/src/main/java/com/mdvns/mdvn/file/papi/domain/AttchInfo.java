package com.mdvns.mdvn.file.papi.domain;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AttchInfo {

    private Integer id;
    /*文件原始名称*/
    private String originName;

    /*文件上传后的url*/
    private String url;

    /*附件的主体：是project或requriement的附件*/
    private String subjectId;

    /*上传文件人的Id*/
    private String creatorId;

    /*文件上传时间*/
    private Long createTime;

    private List<String> remarks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public List<String> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<String> remarks) {
        this.remarks = remarks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AttchInfo)) return false;

        AttchInfo attchInfo = (AttchInfo) o;

        if (getId() != null ? !getId().equals(attchInfo.getId()) : attchInfo.getId() != null) return false;
        if (getOriginName() != null ? !getOriginName().equals(attchInfo.getOriginName()) : attchInfo.getOriginName() != null)
            return false;
        if (getUrl() != null ? !getUrl().equals(attchInfo.getUrl()) : attchInfo.getUrl() != null) return false;
        if (getCreatorId() != null ? !getCreatorId().equals(attchInfo.getCreatorId()) : attchInfo.getCreatorId() != null)
            return false;
        if (getCreateTime() != null ? !getCreateTime().equals(attchInfo.getCreateTime()) : attchInfo.getCreateTime() != null)
            return false;
        return getRemarks() != null ? getRemarks().equals(attchInfo.getRemarks()) : attchInfo.getRemarks() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getOriginName() != null ? getOriginName().hashCode() : 0);
        result = 31 * result + (getUrl() != null ? getUrl().hashCode() : 0);
        result = 31 * result + (getCreatorId() != null ? getCreatorId().hashCode() : 0);
        result = 31 * result + (getCreateTime() != null ? getCreateTime().hashCode() : 0);
        result = 31 * result + (getRemarks() != null ? getRemarks().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AttchInfo{" +
                "id=" + id +
                ", originName='" + originName + '\'' +
                ", url='" + url + '\'' +
                ", creatorId='" + creatorId + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
