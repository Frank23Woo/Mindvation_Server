package com.mdvns.mdvn.task.papi.domain;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RtrvTaskHistoryListResponse {

    private List<TaskHistory> taskHistories;
    //总历史记录数
    private Long totalElements;

    public List<TaskHistory> getTaskHistories() {
        return taskHistories;
    }

    public void setTaskHistories(List<TaskHistory> taskHistories) {
        this.taskHistories = taskHistories;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }
}
