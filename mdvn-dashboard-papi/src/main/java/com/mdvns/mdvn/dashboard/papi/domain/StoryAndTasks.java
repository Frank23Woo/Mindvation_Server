package com.mdvns.mdvn.dashboard.papi.domain;

import com.mdvns.mdvn.common.beans.Story;
import com.mdvns.mdvn.common.beans.Task;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StoryAndTasks {
    private Story story;
    private List<Task> tasks;

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
