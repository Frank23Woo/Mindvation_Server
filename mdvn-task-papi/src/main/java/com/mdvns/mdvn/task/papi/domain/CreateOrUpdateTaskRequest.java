package com.mdvns.mdvn.task.papi.domain;

import com.mdvns.mdvn.task.papi.domain.entity.TaskDeliver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreateOrUpdateTaskRequest {


    private String projId;
    private String storyId;
    private String taskId;
    private String creatorId;
    // 任务描述
    private String description;
    // 执行人id
    private String assigneeId;
    // 开始时间
    private Long startTime;
    // 结束时间
    private Long endTime;
    // 附件
    private List<String> attachmentIds;
    private Integer progress;
    private String comment;
    //工作用时，创建task时自己估算的时间
    private Float usedTime;
    // 交附件
    private TaskDeliver deliver;

    // 备注
    private String remarks;

    public Float getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(Float usedTime) {
        this.usedTime = usedTime;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(String assigneeId) {
        this.assigneeId = assigneeId;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public List<String> getAttachmentIds() {
        return attachmentIds;
    }

    public void setAttachmentIds(List<String> attachmentIds) {
        this.attachmentIds = attachmentIds;
    }

    public TaskDeliver getDeliver() {
        return deliver;
    }

    public void setDeliver(TaskDeliver deliver) {
        this.deliver = deliver;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "CreateTaskRequest{" +
                "storyId='" + storyId + '\'' +
                ", creatorId='" + creatorId + '\'' +
                ", description='" + description + '\'' +
                ", assigneeId=" + assigneeId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", attachmentIds=" + attachmentIds +
                ", deliver=" + deliver +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
