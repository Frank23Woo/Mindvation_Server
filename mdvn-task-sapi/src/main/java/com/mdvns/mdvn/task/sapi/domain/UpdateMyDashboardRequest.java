package com.mdvns.mdvn.task.sapi.domain;


import org.springframework.stereotype.Component;

@Component
public class UpdateMyDashboardRequest {

    private String taskId;
    private String status;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
