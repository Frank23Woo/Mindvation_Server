package com.mdvns.mdvn.dashboard.papi.domain;


import com.mdvns.mdvn.common.beans.Story;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RtrvMyDashboardInfoForStoryResponse {

    private List<Story> toDo;

    private List<Story> InProgress;

    private List<Story> done;

    public List<Story> getToDo() {
        return toDo;
    }

    public void setToDo(List<Story> toDo) {
        this.toDo = toDo;
    }

    public List<Story> getInProgress() {
        return InProgress;
    }

    public void setInProgress(List<Story> inProgress) {
        InProgress = inProgress;
    }

    public List<Story> getDone() {
        return done;
    }

    public void setDone(List<Story> done) {
        this.done = done;
    }
}
