package com.mdvns.mdvn.dashboard.papi.domain;


import com.mdvns.mdvn.common.beans.IterationModel;
import com.mdvns.mdvn.common.beans.Model;
import com.mdvns.mdvn.common.beans.Staff;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RtrvDashboardResponse {

//    private Staff creatorInfo;
    private Model model;
//    private List<SprintStoryList> sprintStoryLists;

    private List<SprintStoryListAndLabelId> sprintStoryLists;

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public List<SprintStoryListAndLabelId> getSprintStoryLists() {
        return sprintStoryLists;
    }

    public void setSprintStoryLists(List<SprintStoryListAndLabelId> sprintStoryLists) {
        this.sprintStoryLists = sprintStoryLists;
    }
}
