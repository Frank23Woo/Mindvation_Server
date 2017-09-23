package com.mdvns.mdvn.task.papi.service;

import com.mdvns.mdvn.task.papi.domain.*;

public interface TaskService {
    CreateTaskResponse createTask(CreateTaskRequest request) throws Exception;
    RetrieveTaskListResponse retrieveTaskList(RetrieveTaskListRequest request) throws Exception;
}
