package com.mdvns.mdvn.story.papi.domain;

import com.mdvns.mdvn.common.beans.ModelRole;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RtrvModelByIdResponse {
    private List<ModelRole> modelRoles;


    public List<ModelRole> getModelRoles() {
        return modelRoles;
    }

    public void setModelRoles(List<ModelRole> modelRoles) {
        this.modelRoles = modelRoles;
    }
}
