package com.mdvns.mdvn.task.sapi.service;

import com.mdvns.mdvn.task.sapi.entity.Task;

import java.util.List;

public interface ITaskService {

    List<Task> rtrvTaskList(Integer storyId, Integer page, Integer pageSize);

    Task createTask(Task task);

}
