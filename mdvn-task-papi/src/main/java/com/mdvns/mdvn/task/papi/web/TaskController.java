package com.mdvns.mdvn.task.papi.web;

import com.mdvns.mdvn.task.papi.domain.*;
import com.mdvns.mdvn.task.papi.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/createTask")
    private CreateTaskResponse createTask(@RequestBody CreateTaskRequest task) throws Exception {
        return taskService.createTask(task);
    }

    @PostMapping("/retrieveTaskList")
    private RetrieveTaskListResponse getTaskList(@RequestBody RetrieveTaskListRequest request) throws Exception {
        return taskService.retrieveTaskList(request);
    }

    @PostMapping("/deleteTask")
    private BaseResponse deleteTask(@RequestBody DeleteTaskRequest request) throws Exception {
        return taskService.deleteTask(request);
    }

    @PostMapping("/updateTask")
    private CreateTaskResponse updateTask(@RequestBody Task request) throws Exception{
        return taskService.updateTask(request);
    }

}
