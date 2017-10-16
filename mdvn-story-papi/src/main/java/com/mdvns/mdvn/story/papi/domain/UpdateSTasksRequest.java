package com.mdvns.mdvn.story.papi.domain;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdateSTasksRequest {
    //storyID
    private String storyId;
    //staffId
    private String staffId;
    //用户故事Task（集合）
    private List<StoryTask> sTasks;

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public List<StoryTask> getsTasks() {
        return sTasks;
    }

    public void setsTasks(List<StoryTask> sTasks) {
        this.sTasks = sTasks;
    }
}
