package com.mdvns.mdvn.task.sapi.service.impl;


import com.mdvns.mdvn.task.sapi.entity.Task;
import com.mdvns.mdvn.task.sapi.repository.TaskRepository;
import com.mdvns.mdvn.task.sapi.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public class TaskService implements ITaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> rtrvTaskList(Integer storyId, Integer page, Integer pageSize) {
        Pageable pageable = new PageRequest(page, pageSize, new Sort(Sort.DEFAULT_DIRECTION, "uuid"));
        return taskRepository.findAll(pageable).getContent();
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }
}
