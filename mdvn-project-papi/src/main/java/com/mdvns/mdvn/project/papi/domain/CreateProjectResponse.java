package com.mdvns.mdvn.project.papi.domain;

import org.springframework.stereotype.Component;


@Component
public class CreateProjectResponse {
    private Project project;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
