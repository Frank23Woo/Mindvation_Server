package com.mdvns.mdvn.task.sapi.service;

import com.mdvns.mdvn.task.sapi.domain.AddAttachRequest;
import com.mdvns.mdvn.task.sapi.domain.CreateTaskRequest;
import com.mdvns.mdvn.task.sapi.domain.RtrvTaskListRequest;
import com.mdvns.mdvn.task.sapi.domain.entity.Task;
import com.mdvns.mdvn.task.sapi.domain.TaskDetail;

import java.util.List;

public interface TaskService {

    List<TaskDetail> rtrvTaskList(RtrvTaskListRequest request) throws Exception;

    TaskDetail createTask(CreateTaskRequest request) throws Exception;

    Boolean deleteTask(Task task) throws Exception;

    TaskDetail updateTask(CreateTaskRequest request) throws Exception;

    TaskDetail addAttachForTask(AddAttachRequest request);
    TaskDetail deleteAttachForTask(AddAttachRequest request);
}
