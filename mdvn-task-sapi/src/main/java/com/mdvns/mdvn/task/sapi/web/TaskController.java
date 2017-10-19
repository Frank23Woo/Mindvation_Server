package com.mdvns.mdvn.task.sapi.web;

import com.mdvns.mdvn.task.sapi.domain.CreateTaskRequest;
import com.mdvns.mdvn.task.sapi.domain.RtrvTaskListRequest;
import com.mdvns.mdvn.task.sapi.domain.TaskDetail;
import com.mdvns.mdvn.task.sapi.domain.entity.Task;
import com.mdvns.mdvn.task.sapi.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/rtrvTaskList")
    private List<TaskDetail> rtrvTaskList(@RequestBody RtrvTaskListRequest request) throws Exception {
        return taskService.rtrvTaskList(request);
    }

    @PostMapping("/saveTask")
    private TaskDetail createTask(@RequestBody CreateTaskRequest task) throws Exception {
        return taskService.createTask(task);
    }

    @PostMapping("/deleteTask")
    private Boolean deleteTask(@RequestBody Task task) throws Exception {
        return taskService.deleteTask(task);
    }

    @PostMapping("/updateTask")
    private TaskDetail updateTask(@RequestBody CreateTaskRequest request) throws Exception {
        return taskService.updateTask(request);
    }

}
