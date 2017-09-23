package com.mdvns.mdvn.task.papi.service.impl;

import com.mdvns.mdvn.task.papi.domain.*;
import com.mdvns.mdvn.task.papi.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class TaskServiceImpl implements TaskService {

    /* 日志常量 */
    private static final Logger LOG = LoggerFactory.getLogger(TaskServiceImpl.class);

    private final String CLASS = this.getClass().getName();

    private static final int PAGE = 0;
    private static final int PAGE_SIZE = 10;
    private static final String SPI_BASE_URL = "http://localhost:10004/";


    @Autowired
    private RestTemplate restTemplate;

    @Override
    public CreateTaskResponse createTask(CreateTaskRequest request) throws Exception {
        if (request == null) {
            throw new NullPointerException("task is null");
        }

        if (request.getAsigneeId() == null || request.getAsignerId() == null || request.getStoryId() == null
                || request.getDescription() == null || request.getPriority() == null || request.getStatus() == null) {
            throw new IllegalArgumentException("缺少参数");
        }

        Task task = new Task();
        task.setAsigneeId(request.getAsigneeId());
        task.setAsignerId(request.getAsignerId());
        task.setDescription(request.getDescription());
        task.setPriority(request.getPriority());
        task.setRemarks(request.getRemarks());
        task.setStoryId(request.getStoryId());
        task.setStatus(request.getStatus());

        CreateTaskResponse response = new CreateTaskResponse();
        try {
            task = restTemplate.postForObject(SPI_BASE_URL + "createTask", task, Task.class);
            response.setErrorCode(0);
            response.setErrorMsg("");
            response.setResponseBody(task);
        } catch (RestClientException e) {
//            e.printStackTrace();
            throw new RuntimeException("task sapi error");
        }

        return response;
    }

    @Override
    public RetrieveTaskListResponse retrieveTaskList(RetrieveTaskListRequest request) throws Exception {
        if (request == null || request.getStoryId() == null){
            throw new NullPointerException("request or storyId is null");
        }

        int page = request.getPage() == null ? PAGE : request.getPage();
        int pageSize = request.getPageSize() == null ? PAGE_SIZE :  request.getPageSize();

        RetrieveTaskListResponse response = new RetrieveTaskListResponse();
        MultiValueMap params = new LinkedMultiValueMap();
        params.add("storyId", request.getStoryId());
        params.add("page", page);
        params.add("pageSise", pageSize);

        try {
            List<Task> taskList = restTemplate.postForObject(SPI_BASE_URL + "rtrvTaskList", params, List.class);
            response.setErrorCode(0);
            response.setErrorMsg("");
            response.setResponseBody(taskList);
        } catch (RestClientException e) {
            throw new RuntimeException("sapi调用失败");
        }

        return response;
    }
}
