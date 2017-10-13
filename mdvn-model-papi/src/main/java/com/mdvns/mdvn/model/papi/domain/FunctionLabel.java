package com.mdvns.mdvn.model.papi.domain;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;


@Component
public class FunctionLabel {

    private String name;
    private String modelId;
    //父模型Id
    private String parentId;

    // 创建人ID
    private String creatorId;

    private Timestamp createTime;

    /*是否已删除*/
    private Integer isDeleted;

    private List<FunctionModel> subFunctionLabels;

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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public List<FunctionModel> getSubFunctionLabels() {
        return subFunctionLabels;
    }

    public void setSubFunctionLabels(List<FunctionModel> subFunctionLabels) {
        this.subFunctionLabels = subFunctionLabels;
    }

    public List<String> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<String> remarks) {
        this.remarks = remarks;
    }
}
