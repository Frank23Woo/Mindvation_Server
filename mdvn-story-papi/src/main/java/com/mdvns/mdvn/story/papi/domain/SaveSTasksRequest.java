package com.mdvns.mdvn.story.papi.domain;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SaveSTasksRequest {

    //登录者staffID
    private String staffId;
    //用户故事Tasks（集合）
    private List<StoryTask> sTasks;

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
