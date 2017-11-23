package com.mdvns.mdvn.dashboard.papi.domain;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyDashboardResponse {

    private List<StoryAndTasks> toDo;

    private List<StoryAndTasks> InProgress;

    private List<StoryAndTasks> done;

    public List<StoryAndTasks> getToDo() {
        return toDo;
    }

    public void setToDo(List<StoryAndTasks> toDo) {
        this.toDo = toDo;
    }

    public List<StoryAndTasks> getInProgress() {
        return InProgress;
    }

    public void setInProgress(List<StoryAndTasks> inProgress) {
        InProgress = inProgress;
    }

    public List<StoryAndTasks> getDone() {
        return done;
    }

    public void setDone(List<StoryAndTasks> done) {
        this.done = done;
    }
}
