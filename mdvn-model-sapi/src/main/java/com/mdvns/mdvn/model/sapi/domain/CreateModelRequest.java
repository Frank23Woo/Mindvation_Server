package com.mdvns.mdvn.model.sapi.domain;

import com.mdvns.mdvn.model.sapi.domain.entity.FunctionModel;
import com.mdvns.mdvn.model.sapi.domain.entity.ModelRole;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreateModelRequest {

    private String name;
    private String creatorId;
    //行业
    private String industry;
    //过程方法模块
    private List<FunctionModel> functionLabel;
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

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
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
