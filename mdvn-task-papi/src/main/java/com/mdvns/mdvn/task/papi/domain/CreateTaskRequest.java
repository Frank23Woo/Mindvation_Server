package com.mdvns.mdvn.task.papi.domain;

import org.springframework.stereotype.Component;

@Component
public class CreateTaskRequest {
    // 任务描述
    private String description;
    // 执行人id
    private Integer asigneeId;
    // 创建者id
    private Integer asignerId;
    private String storyId;
    // 状态
    private Integer status;
    // 优先级
    private String priority;
    // 备注
    private String remarks;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAsigneeId() {
        return asigneeId;
    }

    public void setAsigneeId(Integer asigneeId) {
        this.asigneeId = asigneeId;
    }

    public Integer getAsignerId() {
        return asignerId;
    }

    public void setAsignerId(Integer asignerId) {
        this.asignerId = asignerId;
    }

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
