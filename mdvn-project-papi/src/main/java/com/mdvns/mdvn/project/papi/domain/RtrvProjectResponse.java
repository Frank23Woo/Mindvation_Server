package com.mdvns.mdvn.project.papi.domain;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RtrvProjectResponse {
    private List<Project> projects;

    private List<String> remarks;

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<String> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<String> remarks) {
        this.remarks = remarks;
    }
}
