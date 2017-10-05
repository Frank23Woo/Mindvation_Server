package com.mdvns.mdvn.project.papi.domain;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdatePModelsRequest {
    //项目ID
    private String projId;
    //项目模型（集合）
    private List<ProjModels> models;

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public List<ProjModels> getModels() {
        return models;
    }

    public void setModels(List<ProjModels> models) {
        this.models = models;
    }
}
