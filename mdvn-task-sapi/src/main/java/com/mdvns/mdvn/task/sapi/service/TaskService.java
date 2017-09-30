package com.mdvns.mdvn.task.sapi.service;

import com.mdvns.mdvn.task.sapi.entity.Task;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.sql.SQLException;
import java.util.List;

public interface TaskService {

    List<Task> rtrvTaskList(String storyId, Integer page, Integer pageSize) throws Exception;

    Task createTask(Task task) throws Exception;

    Boolean deleteTask(Task task) throws Exception;

    Task updateTask(Task task) throws Exception;

}
