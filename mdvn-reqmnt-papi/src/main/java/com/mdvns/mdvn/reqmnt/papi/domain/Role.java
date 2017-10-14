package com.mdvns.mdvn.reqmnt.papi.domain;

import com.mdvns.mdvn.common.beans.Staff;

import java.util.List;
import java.util.Map;

public class Role {

    // role name
    private String roleName;

    private List<Staff> members;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Staff> getMembers() {
        return members;
    }

    public void setMembers(List<Staff> members) {
        this.members = members;
    }
}
