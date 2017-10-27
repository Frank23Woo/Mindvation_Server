package com.mdvns.mdvn.dashboard.papi.domain;


import com.mdvns.mdvn.common.beans.Model;
import org.springframework.stereotype.Component;

@Component
public class RtrvDashboardResponse {

    private Model model;
    private ProductStoryList productStoryList;
    private CurrentSprintStoryList currentSprintStoryList;
    private NextSprintSrotyList nextSprintSrotyList;

    public ProductStoryList getProductStoryList() {
        return productStoryList;
    }

    public void setProductStoryList(ProductStoryList productStoryList) {
        this.productStoryList = productStoryList;
    }

    public CurrentSprintStoryList getCurrentSprintStoryList() {
        return currentSprintStoryList;
    }

    public void setCurrentSprintStoryList(CurrentSprintStoryList currentSprintStoryList) {
        this.currentSprintStoryList = currentSprintStoryList;
    }

    public NextSprintSrotyList getNextSprintSrotyList() {
        return nextSprintSrotyList;
    }

    public void setNextSprintSrotyList(NextSprintSrotyList nextSprintSrotyList) {
        this.nextSprintSrotyList = nextSprintSrotyList;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}
