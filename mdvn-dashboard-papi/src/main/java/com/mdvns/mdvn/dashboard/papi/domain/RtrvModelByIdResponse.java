package com.mdvns.mdvn.dashboard.papi.domain;

import com.mdvns.mdvn.common.beans.Model;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RtrvModelByIdResponse {
    private List<Model> models;

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }
}
