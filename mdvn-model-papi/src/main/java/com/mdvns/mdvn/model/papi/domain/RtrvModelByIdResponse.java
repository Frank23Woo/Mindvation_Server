package com.mdvns.mdvn.model.papi.domain;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RtrvModelByIdResponse {
    private List<SubFunctionLabel> subFunctionLabels;
    private List<ModelRole> modelRoles;

    public List<SubFunctionLabel> getSubFunctionLabels() {
        return subFunctionLabels;
    }

    public void setSubFunctionLabels(List<SubFunctionLabel> subFunctionLabels) {
        this.subFunctionLabels = subFunctionLabels;
    }

    public List<ModelRole> getModelRoles() {
        return modelRoles;
    }

    public void setModelRoles(List<ModelRole> modelRoles) {
        this.modelRoles = modelRoles;
    }
}
