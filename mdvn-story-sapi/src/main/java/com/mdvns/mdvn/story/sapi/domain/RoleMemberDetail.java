package com.mdvns.mdvn.story.sapi.domain;

import com.mdvns.mdvn.common.beans.ModelRole;
import com.mdvns.mdvn.common.beans.Staff;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class RoleMemberDetail {

    private ModelRole modelRole;
    private List<Staff> roleMembers;
    private List<String> remarks;

    public ModelRole getModelRole() {
        return modelRole;
    }

    public void setModelRole(ModelRole modelRole) {
        this.modelRole = modelRole;
    }

    public List<Staff> getRoleMembers() {
        return roleMembers;
    }

    public void setRoleMembers(List<Staff> roleMembers) {
        this.roleMembers = roleMembers;
    }

    public List<String> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<String> remarks) {
        this.remarks = remarks;
    }
}
