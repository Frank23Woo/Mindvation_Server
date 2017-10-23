package com.mdvns.mdvn.model.papi.domain;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreateModelResponse {

    //模块
    private Model model;
    //过程方法模块
    private List<FunctionLabel> functionLabels;
    //模块下对应的角色
    private List<ModelRole> roles;
//    private List<IterationModel> iterationModels;
    private List<CreateItModelResponse> iterationModels;
    private List<TaskDelivery> taskDeliveries;
    private List<String> remarks;

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public List<FunctionLabel> getFunctionLabels() {
        return functionLabels;
    }

    public void setFunctionLabels(List<FunctionLabel> functionLabels) {
        this.functionLabels = functionLabels;
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

    public List<CreateItModelResponse> getIterationModels() {
        return iterationModels;
    }

    public void setIterationModels(List<CreateItModelResponse> iterationModels) {
        this.iterationModels = iterationModels;
    }

    public List<TaskDelivery> getTaskDeliveries() {
        return taskDeliveries;
    }

    public void setTaskDeliveries(List<TaskDelivery> taskDeliveries) {
        this.taskDeliveries = taskDeliveries;
    }

    public void setRemarks(List<String> remarks) {
        this.remarks = remarks;
    }
}
