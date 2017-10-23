package com.mdvns.mdvn.model.sapi.domain.entity;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Component
public class IterationModel {

    @Id
    @GeneratedValue
    private Integer uuId;
    @Column(nullable = false)
    private String name;
    //包含的 过程/方法模块 id
    private String labelIds;
    // 顺序标志
    private Integer itIndex;
    //所在模型模板Id
    private String modelId;
    @Column(columnDefinition = "timestamp", nullable = false)
    private Timestamp createTime;
    private Integer isDeleted;

    public Integer getUuId() {
        return uuId;
    }

    public void setUuId(Integer uuId) {
        this.uuId = uuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabelIds() {
        return labelIds;
    }

    public void setLabelIds(String labelIds) {
        this.labelIds = labelIds;
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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}
