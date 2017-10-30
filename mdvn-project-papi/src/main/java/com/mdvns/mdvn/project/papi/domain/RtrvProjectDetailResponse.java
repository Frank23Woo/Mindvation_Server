package com.mdvns.mdvn.project.papi.domain;

import com.mdvns.mdvn.common.beans.StaffAuthInfo;
import org.springframework.stereotype.Component;

@Component
public class RtrvProjectDetailResponse {
    private ProjectDetail projectDetail;

    private StaffAuthInfo staffAuthInfo;

    public StaffAuthInfo getStaffAuthInfo() {
        return staffAuthInfo;
    }

    public void setStaffAuthInfo(StaffAuthInfo staffAuthInfo) {
        this.staffAuthInfo = staffAuthInfo;
    }

    public ProjectDetail getProjectDetail() {
        return projectDetail;
    }

    public void setProjectDetail(ProjectDetail projectDetail) {
        this.projectDetail = projectDetail;
    }
}
