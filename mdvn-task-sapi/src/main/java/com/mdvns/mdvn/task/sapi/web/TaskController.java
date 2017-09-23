package com.mdvns.mdvn.task.sapi.web;

import com.mdvns.mdvn.task.sapi.entity.Task;
import com.mdvns.mdvn.task.sapi.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/rtrvTaskList")
    private List<Task> rtrvTaskList(@RequestParam("storyId") String storyId,
                                    @RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
                                    @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        return taskService.rtrvTaskList(storyId, page, pageSize);
    }


    @PostMapping("/createTask")
    private Task createTask(@RequestBody Task task) throws SQLException {
        return taskService.createTask(task);
    }

}
