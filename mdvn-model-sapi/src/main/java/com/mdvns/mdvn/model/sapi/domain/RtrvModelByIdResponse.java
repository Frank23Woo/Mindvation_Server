package com.mdvns.mdvn.model.sapi.domain;

import com.mdvns.mdvn.model.sapi.domain.entity.SubFunctionLabel;
import com.mdvns.mdvn.model.sapi.domain.entity.ModelRole;
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
