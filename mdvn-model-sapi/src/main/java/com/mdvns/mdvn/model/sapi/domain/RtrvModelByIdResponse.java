package com.mdvns.mdvn.model.sapi.domain;

import com.mdvns.mdvn.model.sapi.domain.entity.FunctionModel;
import com.mdvns.mdvn.model.sapi.domain.entity.ModelRole;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RtrvModelByIdResponse {
    private List<FunctionModel> functionModels;
    private List<ModelRole> modelRoles;

    public List<FunctionModel> getFunctionModels() {
        return functionModels;
    }

    public void setFunctionModels(List<FunctionModel> functionModels) {
        this.functionModels = functionModels;
    }

    public List<ModelRole> getModelRoles() {
        return modelRoles;
    }

    public void setModelRoles(List<ModelRole> modelRoles) {
        this.modelRoles = modelRoles;
    }
}
