package com.mdvns.mdvn.model.sapi.domain;


import com.mdvns.mdvn.model.sapi.domain.entity.ModelRole;
import com.mdvns.mdvn.model.sapi.domain.entity.TaskDelivery;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreateModelRequest {

    private String name;
    private String creatorId;
    //行业(model_type)
    private String modelType;
    //1~7的随机数
    private Integer style;
    //过程方法模块
    private List<FunctionLabel> functionLabels;
    //模块下对应的角色
    private List<ModelRole> roles;
    private List<CreateItModelRequest> iterationModels;
    private List<TaskDelivery> taskDeliveries;
    private List<String> remarks;

    public Integer getStyle() {
        return style;
    }

    public void setStyle(Integer style) {
        this.style = style;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
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

    public List<CreateItModelRequest> getIterationModels() {
        return iterationModels;
    }

    public void setIterationModels(List<CreateItModelRequest> iterationModels) {
        this.iterationModels = iterationModels;
    }

    public List<TaskDelivery> getTaskDeliveries() {
        return taskDeliveries;
    }

    public void setTaskDeliveries(List<TaskDelivery> taskDeliveries) {
        this.taskDeliveries = taskDeliveries;
    }

    public List<String> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<String> remarks) {
        this.remarks = remarks;
    }
}
