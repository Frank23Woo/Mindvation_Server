package com.mdvns.mdvn.task.sapi.service;

import com.mdvns.mdvn.task.sapi.entity.Task;

import java.sql.SQLException;
import java.util.List;

public interface TaskService {

    List<Task> rtrvTaskList(String storyId, Integer page, Integer pageSize);

    Task createTask(Task task) throws SQLException;

}
