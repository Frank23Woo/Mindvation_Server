package com.mdvns.mdvn.task.sapi.service.impl;


import com.mdvns.mdvn.common.beans.Story;
import com.mdvns.mdvn.common.utils.MdvnStringUtil;
import com.mdvns.mdvn.task.sapi.domain.*;
import com.mdvns.mdvn.task.sapi.domain.entity.Task;
import com.mdvns.mdvn.task.sapi.domain.entity.TaskDeliver;
import com.mdvns.mdvn.task.sapi.repository.TaskDeliverRepository;
import com.mdvns.mdvn.task.sapi.repository.TaskRepository;
import com.mdvns.mdvn.task.sapi.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    /* 日志常量 */
    private static final Logger LOG = LoggerFactory.getLogger(TaskServiceImpl.class);

    private final String CLASS = this.getClass().getName();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskDeliverRepository deliverRepository;

    @Override
    public List<TaskDetail> rtrvTaskList(RtrvTaskListRequest request) throws Exception {
        if (request == null) {
            return new ArrayList<>();
        }

        Integer page = request.getPage() == null ? 0 : request.getPage();
        Integer pageSize = request.getPageSize() == null ? 10 : request.getPageSize();

        List<TaskDetail> taskDetails = new ArrayList<>();

        Pageable pageable = new PageRequest(page, pageSize, new Sort(Sort.DEFAULT_DIRECTION, "uuid"));
        String storyId = request.getStoryId();
        List<Task> tasks = taskRepository.findAllByStoryIdAndIsDeleted(storyId, 0, pageable).getContent();
        //获取task列表时重新计算story的进度
        RtrvAverageStoryProgress tRequest = new RtrvAverageStoryProgress();
        tRequest.setStoryId(storyId);
        Float storyProgress = this.averageStoryProgress(tRequest);
        String sql = "UPDATE story SET progress = " + storyProgress + " WHERE story_id=" + "\"" + storyId + "\"";
        this.jdbcTemplate.update(sql);
        //所有的task进度都为100时，story的状态变为done
        Integer progresses = 0;
        String status = this.taskRepository.rtrvStatus(storyId);
        for (int i = 0; i < tasks.size(); i++) {
            progresses += tasks.get(i).getProgress();
        }
        if (progresses / tasks.size() == 100) {
            status = "done";
        }
        String statusSql = "UPDATE story SET status = " + "\""+ status+ "\"" + " WHERE story_id=" + "\"" + storyId + "\"";
        this.jdbcTemplate.update(statusSql);
        //获取task列表时重新计算story的完成用户故事点
        Float storyPoint = taskRepository.rtrvStoryPoint(storyId);
        Float finishedSP = storyPoint * storyProgress / 100;
        DecimalFormat df = new DecimalFormat("#.00");
        finishedSP = Float.valueOf(df.format(finishedSP));
        String finishedSPSql = "UPDATE story SET finishedsp = " + finishedSP + " WHERE story_id=" + "\"" + storyId + "\"";
        this.jdbcTemplate.update(finishedSPSql);

        for (Task task : tasks) {
            TaskDetail detail = new TaskDetail(task);
            detail.setDeliver(deliverRepository.getOne(task.getDeliverId()));
            taskDetails.add(detail);
        }
        return taskDetails;
    }

    /**
     * 获取单独task详细信息
     *
     * @param taskId
     * @return
     */
    @Override
    public TaskDetail rtrvTaskInfo(String taskId) {
        Task task = taskRepository.findByTaskId(taskId);
        TaskDetail detail = new TaskDetail(task);
        detail.setDeliver(deliverRepository.getOne(task.getDeliverId()));
        return detail;
    }

    @Override
    public TaskDetail createTask(CreateTaskRequest request) throws Exception {
        LOG.info("开始执行{} createTask()方法." + request.toString(), CLASS);

        TaskDeliver deliver = new TaskDeliver();
        deliver.setName(request.getDeliver().getName());
        deliver.setModelId(request.getDeliver().getModelId());
        deliverRepository.save(deliver);

        Task task = new Task();
        task.setProjId(request.getProjId());
        task.setStoryId(request.getStoryId());
        task.setCreatorId(request.getCreatorId());
        task.setAssigneeId(request.getAssigneeId());
        task.setDescription(request.getDescription());
        if (request.getUsedTime() != null) {
            task.setUsedTime(request.getUsedTime());
        }
        task.setStartTime(new Timestamp(request.getStartTime()));
        task.setEndTime(new Timestamp(request.getEndTime()));
        Timestamp now = new Timestamp(System.currentTimeMillis());
        task.setCreateTime(now);
        task.setLastUpdateTime(now);
        task.setProgress(0);
        task.setStatus("new");
        task.setComment("");
        task.setAttachmentIds("");
        task.setDeliverId(deliver.getId());
        task = taskRepository.save(task);
        task.setTaskId("T" + task.getUuid());
        task = taskRepository.save(task);

        //创建task重新计算story的进度
        RtrvAverageStoryProgress tRequest = new RtrvAverageStoryProgress();
        String storyId = request.getStoryId();
        tRequest.setStoryId(storyId);
        Float storyProgress = this.averageStoryProgress(tRequest);
        String sql = "UPDATE story SET progress = " + storyProgress + " WHERE story_id=" + "\"" + storyId + "\"";
        this.jdbcTemplate.update(sql);

        // 构造detail对象并返回
        TaskDetail detail = new TaskDetail(task);
        detail.setDeliver(deliver);

        LOG.info("执行结束{} createTask()方法." + task.toString(), CLASS);
        return detail;
    }

    @Override
    public Boolean deleteTask(Task task) throws Exception {
        LOG.info("deleteTask " + task.toString());
        if (task == null || task.getUuid() == null) {
            throw new NullPointerException("uuid is null");
        }

        taskRepository.delete(task);

        return true;
    }

    @Override
    public TaskDetail updateTask(CreateTaskRequest request) throws Exception {
        Task taskOld = taskRepository.findFirstByTaskIdAndIsDeleted(request.getTaskId(), 0);
        TaskDetail detail = null;

        if (taskOld != null) {
            boolean changed = false;
            if (request.getCreatorId() != null && !request.getCreatorId().equals(taskOld.getCreatorId())) {
                taskOld.setCreatorId(request.getCreatorId());
                changed = true;
            }

            if (request.getAssigneeId() != null && !request.getAssigneeId().equals(taskOld.getAssigneeId())) {
                taskOld.setAssigneeId(request.getAssigneeId());
                changed = true;
            }

            if (request.getDescription() != null && !request.getDescription().equals(taskOld.getDescription())) {
                taskOld.setDescription(request.getDescription());
                changed = true;
            }

            if (request.getStartTime() != null && request.getStartTime() != taskOld.getStartTime().getTime()) {
                taskOld.setStartTime(new Timestamp(request.getStartTime()));
                changed = true;
            }

            if (request.getUsedTime() != null && request.getUsedTime() != taskOld.getUsedTime()) {
                taskOld.setUsedTime(request.getUsedTime());
                changed = true;
            }

            if (request.getEndTime() != null && request.getEndTime() != taskOld.getEndTime().getTime()) {
                taskOld.setEndTime(new Timestamp(request.getEndTime()));
                changed = true;
            }

            if (request.getProgress() != null && request.getProgress() != taskOld.getProgress()) {
                if (request.getProgress() == 100) {
                    taskOld.setStatus("done");
                }
                if (request.getProgress().intValue() < 100 && request.getProgress().intValue() > 0) {
                    taskOld.setStatus("inProgress");
                }
                taskOld.setProgress(request.getProgress());
                changed = true;
            }

            if (request.getComment() != null && !request.getComment().equals(taskOld.getComment())) {
                taskOld.setComment(request.getComment());
                changed = true;
            }

            if (request.getAttachmentIds() != null) {
                List<String> attachmentIdList = request.getAttachmentIds();
                StringBuffer ids = new StringBuffer("");
                for (int i = 0; i < attachmentIdList.size(); i++) {
                    ids.append(attachmentIdList.get(i));
                    if (i != attachmentIdList.size() - 1) {
                        ids.append(",");
                    }
                }
                if (!ids.toString().equals(request.getAttachmentIds())) {
                    taskOld.setAttachmentIds(ids.toString());
                    changed = true;
                }
            }

            TaskDeliver deliver = request.getDeliver();
            if (deliver != null) {
                TaskDeliver deliverOld = deliverRepository.findOne(taskOld.getDeliverId());
                if (deliverOld == null) {
                    deliver = deliverRepository.save(deliver);
                    taskOld.setDeliverId(deliver.getId());
                    changed = true;
                } else {
                    deliverOld.setModelId(deliver.getModelId());
                    deliverOld.setName(deliver.getName());
                    deliver = deliverRepository.save(deliverOld);
                    changed = true;
                }
            }

            if (changed) {
                taskOld.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
                taskOld = taskRepository.save(taskOld);
            }

            detail = new TaskDetail(taskOld);
            detail.setDeliver(deliverRepository.findOne(taskOld.getDeliverId()));
        }

        return detail;
    }

    @Override
    public TaskDetail addAttachForTask(AddAttachRequest request) {
        if (request == null || request.getTaskId() == null || request.getAttachmentId() == null) {
            return null;
        }

        Task task = taskRepository.findFirstByTaskIdAndIsDeleted(request.getTaskId(), 0);
        if (task == null) {
            return null;
        }

        String ids = task.getAttachmentIds();
        if (StringUtils.isEmpty(ids)) {
            ids = "";
        } else {
            ids = ids + ",";
        }
        ids += request.getAttachmentId();
        task.setAttachmentIds(ids);
        task = taskRepository.save(task);
        TaskDetail detail = new TaskDetail(task);
        detail.setDeliver(deliverRepository.findOne(task.getDeliverId()));

        return detail;
    }

    @Override
    public TaskDetail deleteAttachForTask(AddAttachRequest request) {
        if (request == null || request.getTaskId() == null || request.getAttachmentId() == null) {
            return null;
        }

        Task task = taskRepository.findFirstByTaskIdAndIsDeleted(request.getTaskId(), 0);
        if (task == null) {
            return null;
        }

        String ids = task.getAttachmentIds();
        String[] cIds = ids.split(",");
        List<String> cIdList = new ArrayList<>();
        cIdList = Arrays.asList(cIds);
        List cList = new ArrayList(cIdList);
        cList.remove(request.getAttachmentId());
        String sIds = MdvnStringUtil.join(cList, ",");
//        if (StringUtils.isEmpty(ids)){
//            ids = "";
//        } else {
//            ids = ids + ",";
//        }
        task.setAttachmentIds(sIds);
        task = taskRepository.save(task);
        TaskDetail detail = new TaskDetail(task);
        detail.setDeliver(deliverRepository.findOne(task.getDeliverId()));

        return detail;
    }

    /**
     * 根据projId和staffId查询个人看板信息
     *
     * @param request
     * @return
     */
    @Override
    public RtrvMyDashboardInfoResponse findMyDashboardInfo(RtrvMyDashboardInfoRequest request) {
        RtrvMyDashboardInfoResponse rtrvMyDashboardInfoResponse = new RtrvMyDashboardInfoResponse();
        String projId = request.getProjId();
        String creatorId = request.getCreatorId();
        List<Task> toDoTasks = taskRepository.findAllByProjIdAndCreatorIdAndStatusAndIsDeleted(projId, creatorId, "new", 0);
        List<Task> InProgressTasks = taskRepository.findAllByProjIdAndCreatorIdAndStatusAndIsDeleted(projId, creatorId, "inProgress", 0);
        List<Task> doneTasks = taskRepository.findAllByProjIdAndCreatorIdAndStatusAndIsDeleted(projId, creatorId, "done", 0);
        rtrvMyDashboardInfoResponse.setToDo(toDoTasks);
        rtrvMyDashboardInfoResponse.setInProgress(InProgressTasks);
        rtrvMyDashboardInfoResponse.setDone(doneTasks);
        return rtrvMyDashboardInfoResponse;
    }

    /**
     * 更改个人看板
     *
     * @param request
     * @return
     */
    @Override
    public Task updateMyDashboard(UpdateMyDashboardRequest request) {
        String taskId = request.getTaskId();
        String status = request.getStatus();
        Task task = taskRepository.findByTaskId(taskId);
        if (status.equals("inProgress")) {
            task.setStatus("inProgress");
        } else {
            task.setStatus("done");
            task.setProgress(100);
        }
        task = taskRepository.saveAndFlush(task);
        return task;
    }

    /**
     * Story的进度为所有Task的进度平均值
     *
     * @param request
     * @return
     */
    @Override
    public Float averageStoryProgress(RtrvAverageStoryProgress request) {
        Float storyAverageProgress = Float.valueOf(0);
        //获取一个story下task用时的总和
        Float usedTimes = this.taskRepository.rtrvTaskUsedTimeQty(request.getStoryId());
        if (usedTimes == 0) {
            return storyAverageProgress;
        }
        //获取这个story下的task列表
        List<Task> taskList = this.taskRepository.findAllByStoryIdAndIsDeleted(request.getStoryId(), 0);
        List<Float> storyProgress = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            Integer progress = task.getProgress();
            Float usedTime = task.getUsedTime();
            //某个task所占的比重
            Float timeProportion = usedTime / usedTimes;
            Float progressProportion = progress * timeProportion;
            //计算某个story的整体平均进度
            storyProgress.add(progressProportion);
        }

        for (int i = 0; i < storyProgress.size(); i++) {
            storyAverageProgress += storyProgress.get(i);
        }
        DecimalFormat df = new DecimalFormat("#.00");
        storyAverageProgress = Float.valueOf(df.format(storyAverageProgress));
        return storyAverageProgress;
    }

}
