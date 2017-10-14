package com.mdvns.mdvn.model.papi.domain;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Size;
import java.util.List;

@Component
public class CreateModelRequest {

    /*新建模型名稱*/
    @NotBlank(message = "模块名称不能为空")
    @Size(max=100, message = "模块名称过长")
    private String name;
    @NotBlank(message = "创建人编号不能为空")
    @Size(max=50, message = "创建人编号过长")
    private String creatorId;
    //行业
    @NotBlank(message = "模块行业不能为空")
    private String modelType;
    //过程方法模块
    private List<FunctionLabel> functionLabels;
    //模块下对应的角色
    private List<ModelRole> roles;
    private List<String> remarks;

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

    public List<String> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<String> remarks) {
        this.remarks = remarks;
    }
}
