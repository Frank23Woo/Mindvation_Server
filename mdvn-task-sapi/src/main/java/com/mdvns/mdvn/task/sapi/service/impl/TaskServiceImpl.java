package com.mdvns.mdvn.task.sapi.service.impl;


import com.mdvns.mdvn.task.sapi.entity.Task;
import com.mdvns.mdvn.task.sapi.repository.TaskRepository;
import com.mdvns.mdvn.task.sapi.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    /* 日志常量 */
    private static final Logger LOG = LoggerFactory.getLogger(TaskServiceImpl.class);

    private final String CLASS = this.getClass().getName();

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> rtrvTaskList(String storyId, Integer page, Integer pageSize) {
        Pageable pageable = new PageRequest(page, pageSize, new Sort(Sort.DEFAULT_DIRECTION, "uuid"));
        return taskRepository.findAllByStoryId(storyId, pageable).getContent();
    }

    @Override
    public Task createTask(Task task) throws SQLException{
        LOG.info("开始执行{} createTask()方法.", CLASS);
        Task result =  taskRepository.save(task);
        LOG.info("执行结束{} createTask()方法.", CLASS);
        return result;
    }
}
