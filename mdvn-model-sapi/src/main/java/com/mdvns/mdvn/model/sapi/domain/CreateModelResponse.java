package com.mdvns.mdvn.model.sapi.domain;

import com.mdvns.mdvn.model.sapi.domain.entity.FunctionModel;
import com.mdvns.mdvn.model.sapi.domain.entity.Model;
import com.mdvns.mdvn.model.sapi.domain.entity.ModelRole;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreateModelResponse {

    //模块
    private Model model;
    //过程方法模块
    private List<FunctionModel> functionLabel;
    //模块下对应的角色
    private List<ModelRole> roles;
    private List<String> remarks;

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public List<FunctionModel> getFunctionLabel() {
        return functionLabel;
    }

    public void setFunctionLabel(List<FunctionModel> functionLabel) {
        this.functionLabel = functionLabel;
    }

    public List<ModelRole> getRoles() {
        return roles;
    }

    public void setRoles(List<ModelRole> roles) {
        this.roles = roles;
    }

    public List<String> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<String> remarks) {
        this.remarks = remarks;
    }
}
