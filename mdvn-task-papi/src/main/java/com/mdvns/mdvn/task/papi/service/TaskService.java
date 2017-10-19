package com.mdvns.mdvn.task.papi.service;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.task.papi.domain.*;

public interface TaskService {
    RestResponse createTask(CreateOrUpdateTaskRequest request) throws Exception;
    RestResponse retrieveTaskList(RtrvTaskListRequest request) throws Exception;
    RestResponse updateTask(CreateOrUpdateTaskRequest request) throws Exception;
}
