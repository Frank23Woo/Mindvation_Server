package com.mdvns.mdvn.model.papi.domain;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreateItModelRequest {

    private String name;
    //包含的 过程/方法模块 id
//    private String labelIds;
//    private List<String> labelIds;
    //包含的 过程/方法模块 name
    private List<String> labels;
    // 顺序标志
    private Integer itIndex;
    //所在模型模板Id
    private String modelId;
    private Long createTime;
    private Integer isDeleted;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public Integer getItIndex() {
        return itIndex;
    }

    public void setItIndex(Integer itIndex) {
        this.itIndex = itIndex;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}
