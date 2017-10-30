package com.mdvns.mdvn.task.sapi.domain;

import com.mdvns.mdvn.task.sapi.domain.entity.Task;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RtrvMyDashboardInfoResponse {

    private List<Task> toDo;

    private List<Task> InProgress;

    private List<Task> done;

    public List<Task> getToDo() {
        return toDo;
    }

    public void setToDo(List<Task> toDo) {
        this.toDo = toDo;
    }

    public List<Task> getInProgress() {
        return InProgress;
    }

    public void setInProgress(List<Task> inProgress) {
        InProgress = inProgress;
    }

    public List<Task> getDone() {
        return done;
    }

    public void setDone(List<Task> done) {
        this.done = done;
    }
}
