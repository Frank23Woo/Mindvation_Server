package com.mdvns.mdvn.task.papi.domain;

import org.springframework.stereotype.Component;

@Component
public class RtrvTaskInfoRequest {

    private String taskId;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}
