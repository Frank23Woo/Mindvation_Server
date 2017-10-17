package com.mdvns.mdvn.reqmnt.papi.domain;

import com.mdvns.mdvn.common.beans.ModelRole;
import com.mdvns.mdvn.common.beans.Staff;

import java.util.List;
import java.util.Map;

public class RoleAndMember {



    private List<Staff> memberDetails;

    private ModelRole roleDetail;

    public List<Staff> getMemberDetails() {
        return memberDetails;
    }

    public void setMemberDetails(List<Staff> memberDetails) {
        this.memberDetails = memberDetails;
    }

    public ModelRole getRoleDetail() {
        return roleDetail;
    }

    public void setRoleDetail(ModelRole roleDetail) {
        this.roleDetail = roleDetail;
    }
}
