package com.mdvns.mdvn.project.sapi.domain;

import com.mdvns.mdvn.project.sapi.domain.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RtrvProjectListResponse {
    //总记录数
    private Long totalElements;

    private Page<Project> projects;

    private List<String> remarks;

    public Page<Project> getProjects() {
        return projects;
    }

    public void setProjects(Page<Project> projects) {
        this.projects = projects;
    }

    public List<String> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<String> remarks) {
        this.remarks = remarks;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }
}
