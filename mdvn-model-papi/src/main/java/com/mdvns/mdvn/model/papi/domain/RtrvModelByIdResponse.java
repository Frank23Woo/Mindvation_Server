package com.mdvns.mdvn.model.papi.domain;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RtrvModelByIdResponse {
    private List<SubFunctionLabel> FunctionLabels;
    private List<ModelRole> modelRoles;

    public List<SubFunctionLabel> getFunctionLabels() {
        return FunctionLabels;
    }

    public void setFunctionLabels(List<SubFunctionLabel> functionLabels) {
        FunctionLabels = functionLabels;
    }

    public List<ModelRole> getModelRoles() {
        return modelRoles;
    }

    public void setModelRoles(List<ModelRole> modelRoles) {
        this.modelRoles = modelRoles;
    }
}
