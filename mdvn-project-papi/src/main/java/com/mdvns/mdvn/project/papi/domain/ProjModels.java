package com.mdvns.mdvn.project.papi.domain;


import org.springframework.stereotype.Component;

@Component
public class ProjModels {

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
