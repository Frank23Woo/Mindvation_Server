package com.mdvns.mdvn.project.papi.domain;

import org.springframework.stereotype.Component;

@Component
public class RtrvProjectDetailResponse {
    private ProjectDetail projectDetail;

    public ProjectDetail getProjectDetail() {
        return projectDetail;
    }

    public void setProjectDetail(ProjectDetail projectDetail) {
        this.projectDetail = projectDetail;
    }
}
