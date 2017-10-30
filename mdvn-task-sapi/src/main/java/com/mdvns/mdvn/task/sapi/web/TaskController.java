package com.mdvns.mdvn.task.sapi.web;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.task.sapi.domain.*;
import com.mdvns.mdvn.task.sapi.domain.entity.Task;
import com.mdvns.mdvn.task.sapi.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/addAttachForTask")
    private TaskDetail addAttachForTask(@RequestBody AddAttachRequest request) throws Exception{
        return taskService.addAttachForTask(request);
    }
    @PostMapping("/deleteAttachForTask")
    private TaskDetail deleteAttachForTask(@RequestBody AddAttachRequest request) throws Exception{
        return taskService.deleteAttachForTask(request);
    }

    /**
     * 根据projId和staffId查询个人看板信息
     * @param request
     * @return
     */
    @PostMapping(value = "/myDashboardInfos")
    public RtrvMyDashboardInfoResponse findMyDashboardInfo(@RequestBody RtrvMyDashboardInfoRequest request) {
        return this.taskService.findMyDashboardInfo(request);
    }
    /**
     * 更改个人看板信息
     * @param request
     * @return
     */
    @PostMapping(value = "/updateMyDashboard")
    public Task updateMyDashboard(@RequestBody UpdateMyDashboardRequest request) {
        return this.taskService.updateMyDashboard(request);
    }



}
