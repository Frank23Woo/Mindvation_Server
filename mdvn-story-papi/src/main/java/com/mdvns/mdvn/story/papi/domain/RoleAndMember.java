package com.mdvns.mdvn.story.papi.domain;

import com.mdvns.mdvn.common.beans.ModelRole;
import com.mdvns.mdvn.common.beans.Staff;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class RoleAndMember {

    private ModelRole roleDetail;

    private List<Staff> memberDetails;

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
