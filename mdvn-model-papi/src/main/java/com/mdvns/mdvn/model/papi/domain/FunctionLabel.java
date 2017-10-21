package com.mdvns.mdvn.model.papi.domain;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;


@Component
//第二层过程方法模块的详情
public class FunctionLabel {

    private String name;
    private String modelId;
    //父模型Id
    private String parentId;

    // 创建人ID
    private String creatorId;

    private Long createTime;

    /*是否已删除*/
    private Integer isDeleted;

    private List<SubFunctionLabel> subFunctionLabels;

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

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
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

    public List<SubFunctionLabel> getSubFunctionLabels() {
        return subFunctionLabels;
    }

    public void setSubFunctionLabels(List<SubFunctionLabel> subFunctionLabels) {
        this.subFunctionLabels = subFunctionLabels;
    }

    public List<String> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<String> remarks) {
        this.remarks = remarks;
    }
}
