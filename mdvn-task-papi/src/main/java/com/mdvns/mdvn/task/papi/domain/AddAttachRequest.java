package com.mdvns.mdvn.task.papi.domain;

import org.springframework.stereotype.Component;

@Component
public class AddAttachRequest {

    private String taskId;
    private String attachmentId;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }
}
