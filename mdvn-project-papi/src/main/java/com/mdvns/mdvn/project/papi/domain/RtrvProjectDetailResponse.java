package com.mdvns.mdvn.project.papi.domain;

import com.mdvns.mdvn.common.beans.StaffAuthInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RtrvProjectDetailResponse {
    private ProjectDetail projectDetail;

    private List<StaffAuthInfo> staffAuthInfo;


    public ProjectDetail getProjectDetail() {
        return projectDetail;
    }

    public void setProjectDetail(ProjectDetail projectDetail) {
        this.projectDetail = projectDetail;
    }

    public List<StaffAuthInfo> getStaffAuthInfo() {
        return staffAuthInfo;
    }

    public void setStaffAuthInfo(List<StaffAuthInfo> staffAuthInfo) {
        this.staffAuthInfo = staffAuthInfo;
    }
}
