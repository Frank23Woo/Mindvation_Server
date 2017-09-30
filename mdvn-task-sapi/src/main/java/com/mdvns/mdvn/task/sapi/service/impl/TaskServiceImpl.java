package com.mdvns.mdvn.task.sapi.service.impl;


import com.mdvns.mdvn.task.sapi.entity.Task;
import com.mdvns.mdvn.task.sapi.repository.TaskRepository;
import com.mdvns.mdvn.task.sapi.service.TaskService;
import com.sun.org.apache.regexp.internal.RE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    /* 日志常量 */
    private static final Logger LOG = LoggerFactory.getLogger(TaskServiceImpl.class);

    private final String CLASS = this.getClass().getName();

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> rtrvTaskList(String storyId, Integer page, Integer pageSize) throws Exception{
        Pageable pageable = new PageRequest(page, pageSize, new Sort(Sort.DEFAULT_DIRECTION, "uuid"));
        return taskRepository.findAllByStoryId(storyId, pageable).getContent();
    }

    @Override
    public Task createTask(Task task) throws Exception{
        LOG.info("开始执行{} createTask()方法.", CLASS);
        Date now = new Date();
        task.setCreateTime(now);
        task.setUpdateTime(now);
        Task result =  taskRepository.save(task);
        LOG.info("执行结束{} createTask()方法.", CLASS);
        return result;
    }

    @Override
    public Boolean deleteTask(Task task) throws Exception {
        if (task == null || task.getUuid() == null) {
            throw new NullPointerException("uuid is null");
        }

        taskRepository.delete(task);

        return true;
    }

    @Override
    public Task updateTask(Task task) throws Exception {
        Task taskOld = null;
        if (task.getUuid() != null){
            taskOld = taskRepository.getOne(task.getUuid());
        } else if (task.getStoryId() != null) {
            taskOld = taskRepository.findOneByStoryId(task.getStoryId());
        }

        // task 不存在
        if (taskOld == null) {
            LOG.error("task does not exist");
            return null;
        }

        Date now = new Date();
        taskOld.setUpdateTime(now);
        taskOld.setDescription(task.getDescription());
        taskOld.setAsigneeId(task.getAsigneeId());
        taskOld.setStartTime(task.getStartTime());
        taskOld.setEndTime(task.getEndTime());
        taskOld.setUpdateTime(now);
        return taskRepository.saveAndFlush(taskOld);
    }
}
