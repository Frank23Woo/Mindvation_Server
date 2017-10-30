package com.mdvns.mdvn.task.papi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mdvns.mdvn.common.beans.AttchInfo;
import com.mdvns.mdvn.common.beans.Staff;
import com.mdvns.mdvn.common.beans.StaffAuthInfo;
import com.mdvns.mdvn.task.papi.domain.entity.TaskDeliver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@JsonIgnoreProperties(value={"uuid", "deliverId", "isDeleted"})
public class TaskDetail {

    private Integer uuid;
    private String storyId;
    private String taskId;
    private String projId;
    private String creatorId;
    private String assigneeId;
    private String description;
    private Long startTime;
    private Long endTime;
    private Integer progress;
    private String status;
    private String comment;
    private String attachmentIds;

    // 交附件
    private Integer deliverId;
    private Long createTime;
    private Long lastUpdateTime;
    private Integer isDeleted = 0;
    private String remarks;

    private TaskDeliver deliver;

    private Staff creator;

    private Staff assignee;

    private List<AttchInfo> attachUrlList;

    private StaffAuthInfo staffAuthInfo;

    public TaskDetail() {
    }

    public StaffAuthInfo getStaffAuthInfo() {
        return staffAuthInfo;
    }

    public void setStaffAuthInfo(StaffAuthInfo staffAuthInfo) {
        this.staffAuthInfo = staffAuthInfo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public TaskDeliver getDeliver() {
        return deliver;
    }

    public void setDeliver(TaskDeliver deliver) {
        this.deliver = deliver;
    }

    public Staff getCreator() {
        return creator;
    }

    public void setCreator(Staff creator) {
        this.creator = creator;
    }


    public Staff getAssignee() {
        return assignee;
    }

    public void setAssignee(Staff assignee) {
        this.assignee = assignee;
    }

    public List<AttchInfo> getAttachUrlList() {
        return attachUrlList;
    }

    public void setAttachUrlList(List<AttchInfo> attachUrlList) {
        this.attachUrlList = attachUrlList;
    }

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(String assigneeId) {
        this.assigneeId = assigneeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getAttachmentIds() {
        return attachmentIds;
    }

    public void setAttachmentIds(String attachmentIds) {
        this.attachmentIds = attachmentIds;
    }

    public Integer getDeliverId() {
        return deliverId;
    }

    public void setDeliverId(Integer deliverId) {
        this.deliverId = deliverId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
