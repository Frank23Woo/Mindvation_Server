package com.mdvns.mdvn.task.sapi.web;

import com.mdvns.mdvn.task.sapi.entity.Task;
import com.mdvns.mdvn.task.sapi.service.ITaskService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {

    private ITaskService taskService;

    @PostMapping(value = "/rtrvTaskList")
    private List<Task> rtrvTaskList(@RequestParam("storyId") Integer storyId, @RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        return taskService.rtrvTaskList(storyId, page, pageSize);
    }


    @PostMapping(value = "/createTask")
    private Task createTask(){
        Task task = new Task();
        return taskService.createTask(task);
    }

}
