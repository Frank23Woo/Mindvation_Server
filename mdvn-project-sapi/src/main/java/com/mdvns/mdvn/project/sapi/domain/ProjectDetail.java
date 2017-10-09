package com.mdvns.mdvn.project.sapi.domain;

import com.mdvns.mdvn.project.sapi.domain.entity.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjectDetail {
    //项目基本信息
    private Project project;
    //项目负责人（集合）
    private List<Staff> leaders;
    //项目标签（集合）
    private List<Tag> tags;
    //项目checkList（集合）
    private List<ProjChecklists> checkLists;
    //项目模型（集合）
    private List<Model> models;
    //项目附件（集合）
    private List<ProjAttchUrls> attchUrls;



    public List<Staff> getLeaders() {
        return leaders;
    }

    public void setLeaders(List<Staff> leaders) {
        this.leaders = leaders;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }

    public List<ProjChecklists> getCheckLists() {
        return checkLists;
    }

    public void setCheckLists(List<ProjChecklists> checkLists) {
        this.checkLists = checkLists;
    }

    public List<ProjAttchUrls> getAttchUrls() {
        return attchUrls;
    }

    public void setAttchUrls(List<ProjAttchUrls> attchUrls) {
        this.attchUrls = attchUrls;
    }


    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
