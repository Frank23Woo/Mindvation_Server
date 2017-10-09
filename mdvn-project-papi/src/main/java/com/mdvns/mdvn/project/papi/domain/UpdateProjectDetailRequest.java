package com.mdvns.mdvn.project.papi.domain;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdateProjectDetailRequest {

    //项目ID
    private String projId;
    //staffID
    private String staffId;
    //项目名称
    private String name;
    //项目描述
    private String description;
    //项目优先级
    private Integer priority;
    //项目开始日期
    private Long startDate;
    //项目结束日期
    private Long endDate;
    //项目可调整系数
    private Double contingency;
    //状态
    private String status;
    private String ragStatus;
    //项目负责人（集合）
    private List<ProjLeaders> leaders;
    //项目标签（集合）
    private List<ProjTags> tags;
    //项目checkList（集合）
    private List<ProjChecklists> checkLists;
    //项目模型（集合）
    private List<ProjModels> models;
    //项目附件（集合）
    private List<ProjAttchUrls> attchUrls;

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    public Double getContingency() {
        return contingency;
    }

    public void setContingency(Double contingency) {
        this.contingency = contingency;
    }

    public List<ProjLeaders> getLeaders() {
        return leaders;
    }

    public void setLeaders(List<ProjLeaders> leaders) {
        this.leaders = leaders;
    }

    public List<ProjTags> getTags() {
        return tags;
    }

    public void setTags(List<ProjTags> tags) {
        this.tags = tags;
    }

    public List<ProjChecklists> getCheckLists() {
        return checkLists;
    }

    public void setCheckLists(List<ProjChecklists> checkLists) {
        this.checkLists = checkLists;
    }

    public List<ProjModels> getModels() {
        return models;
    }

    public void setModels(List<ProjModels> models) {
        this.models = models;
    }

    public List<ProjAttchUrls> getAttchUrls() {
        return attchUrls;
    }

    public void setAttchUrls(List<ProjAttchUrls> attchUrls) {
        this.attchUrls = attchUrls;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRagStatus() {
        return ragStatus;
    }

    public void setRagStatus(String ragStatus) {
        this.ragStatus = ragStatus;
    }
}
