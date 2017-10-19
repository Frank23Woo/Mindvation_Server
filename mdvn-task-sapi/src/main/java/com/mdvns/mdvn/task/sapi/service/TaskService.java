package com.mdvns.mdvn.task.sapi.service;

import com.mdvns.mdvn.task.sapi.domain.CreateTaskRequest;
import com.mdvns.mdvn.task.sapi.domain.entity.Task;
import com.mdvns.mdvn.task.sapi.domain.TaskDetail;

import java.util.List;

public interface TaskService {

    List<TaskDetail> rtrvTaskList(String storyId, Integer page, Integer pageSize) throws Exception;

    TaskDetail createTask(CreateTaskRequest request) throws Exception;

    Boolean deleteTask(Task task) throws Exception;

    TaskDetail updateTask(CreateTaskRequest request) throws Exception;

}
