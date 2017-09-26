package com.mdvns.mdvn.project.sapi.domain.entity;


import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "model_proj_map", uniqueConstraints = {@UniqueConstraint(columnNames="uuId")})
public class ProjModels {
    @Id
    @GeneratedValue
    private Integer uuId;
    //模块Id
    private String modelId;
    //项目Id
    private String projId;

    public Integer getUuId() {
        return uuId;
    }

    public void setUuId(Integer uuId) {
        this.uuId = uuId;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }
}
