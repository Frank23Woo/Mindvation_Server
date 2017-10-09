package com.mdvns.mdvn.model.papi.domain;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * 創建模型
 */
@Component
public class CreateModelRequest {

    /*新建模型名稱*/
    @NotBlank(message = "标签名称不能为空")
    @Size(max=100, message = "标签名称过长")
    private String name;

    /*新建模型人的编号*/
    @NotBlank(message = "创建人编号不能为空")
    @Size(max=50, message = "创建人编号过长")
    private String creatorId;

    /*標簽色值*/
    @NotBlank(message = "标签色值不能为空")
    @Size(max = 10, message = "色值编码不正确")
    private String color;
    //模型类型
    private String modelType;

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

    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    public List<String> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<String> remarks) {
        this.remarks = remarks;
    }

}
