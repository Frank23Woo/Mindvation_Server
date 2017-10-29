package com.mdvns.mdvn.project.papi.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RtrvProjectDetailResponse {
    private ProjectDetail projectDetail;

    @Autowired
    private StaffAuthInfo staffAuthInfo;

    public ProjectDetail getProjectDetail() {
        return projectDetail;
    }

    public void setProjectDetail(ProjectDetail projectDetail) {
        this.projectDetail = projectDetail;
    }
}
