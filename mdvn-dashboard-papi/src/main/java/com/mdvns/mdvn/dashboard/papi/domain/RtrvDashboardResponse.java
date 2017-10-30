package com.mdvns.mdvn.dashboard.papi.domain;


import com.mdvns.mdvn.common.beans.IterationModel;
import com.mdvns.mdvn.common.beans.Model;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RtrvDashboardResponse {

    private Model model;
    private List<SprintStoryList> sprintStoryLists;

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public List<SprintStoryList> getSprintStoryLists() {
        return sprintStoryLists;
    }

    public void setSprintStoryLists(List<SprintStoryList> sprintStoryLists) {
        this.sprintStoryLists = sprintStoryLists;
    }
}
