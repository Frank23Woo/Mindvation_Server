package com.mdvns.mdvn.tag.papi.domain;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * 創建標簽
 */
@Component
public class CreateTagRequest {

    /*新建標簽名稱*/
    @NotBlank(message = "标签名称不能为空")
    @Size(max=100, message = "标签名称过长")
    private String name;

    /*新建标签人的编号*/
    @NotBlank(message = "创建人编号不能为空")
    @Size(max=50, message = "创建人编号过长")
    private String creatorId;

    /*標簽色值*/
    @NotBlank(message = "标签色值不能为空")
    @Size(max = 10, message = "色值编码不正确")
    private String color;

    /*后加的字段，1~7随机给一个数字*/
    private Integer tagStyle;


    private List<String> remarks;

    public Integer getTagStyle() {
        return tagStyle;
    }

    public void setTagStyle(Integer tagStyle) {
        this.tagStyle = tagStyle;
    }

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
