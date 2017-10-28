package com.mdvns.mdvn.task.papi.web;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.task.papi.domain.*;
import com.mdvns.mdvn.task.papi.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping(value= {"/task", "/v1.0/task"})
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/createTask")
    private RestResponse createTask(@RequestBody CreateOrUpdateTaskRequest task) throws Exception {
        return taskService.createTask(task);
    }

    @PostMapping("/retrieveTaskList")
    private RestResponse getTaskList(@RequestBody RtrvTaskListRequest request) throws Exception {
        return taskService.retrieveTaskList(request);
    }

//    @PostMapping("/deleteTask")
//    private RestResponse deleteTask(@RequestBody DeleteTaskRequest request) throws Exception {
//        return taskService.deleteTask(request);
//    }

    @PostMapping("/updateTask")
    private RestResponse updateTask(@RequestBody CreateOrUpdateTaskRequest request) throws Exception{
        return taskService.updateTask(request);
    }

    @PostMapping("/addAttachForTask")
    private RestResponse addAttachForTask(@RequestBody AddAttachRequest request) throws Exception{
        return taskService.addAttachForTask(request);
    }


}
