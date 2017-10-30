package com.mdvns.mdvn.dashboard.papi.domain;


import org.springframework.stereotype.Component;

import java.util.List;

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
