package com.mdvns.mdvn.tag.papi.domain;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 創建標簽
 */
@Component
public class CreateTagRequest {

    /* 新建標簽名稱*/
    private String name;

    /* 新建标签人的编号*/
    private String creatorId;

    /* 標簽色值*/
    private String color;

    /* 備注*/
    private List<String> remarks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<String> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<String> remarks) {
        this.remarks = remarks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateTagRequest)) return false;

        CreateTagRequest that = (CreateTagRequest) o;

        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (getCreatorId() != null ? !getCreatorId().equals(that.getCreatorId()) : that.getCreatorId() != null)
            return false;
        if (getColor() != null ? !getColor().equals(that.getColor()) : that.getColor() != null) return false;
        return getRemarks() != null ? getRemarks().equals(that.getRemarks()) : that.getRemarks() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getCreatorId() != null ? getCreatorId().hashCode() : 0);
        result = 31 * result + (getColor() != null ? getColor().hashCode() : 0);
        result = 31 * result + (getRemarks() != null ? getRemarks().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CreateTagRequest{" +
                "name='" + name + '\'' +
                ", creatorId='" + creatorId + '\'' +
                ", color='" + color + '\'' +
                ", remarks=" + remarks +
                '}';
    }
}
