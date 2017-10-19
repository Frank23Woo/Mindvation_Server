package com.mdvns.mdvn.task.sapi.domain;

import com.mdvns.mdvn.common.beans.AttchUrl;
import com.mdvns.mdvn.common.beans.Staff;
import com.mdvns.mdvn.task.sapi.domain.entity.Task;
import com.mdvns.mdvn.task.sapi.domain.entity.TaskDeliver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskDetail extends Task {

    private TaskDeliver deliver;

    private Staff creator;

    private Staff assignee;

    private List<AttchUrl> attchUrlList;

    public TaskDetail() {
    }

    public TaskDetail(Task task) {
        this.setUuid(task.getUuid());
        this.setAssigneeId(task.getAssigneeId());
        this.setCreatorId(task.getCreatorId());
        this.setDescription(task.getDescription());
        this.setStartTime(task.getStartTime());
        this.setEndTime(task.getEndTime());
        this.setStoryId(task.getStoryId());
        this.setTaskId(task.getTaskId());
        this.setProgress(task.getProgress());
        this.setComment(task.getComment());
        this.setAttachmentIds(task.getAttachmentIds());
        this.setIsDeleted(task.getIsDeleted());
        this.setLastUpdateTime(task.getLastUpdateTime());
        this.setCreateTime(task.getCreateTime());
        this.setDeliverId(task.getDeliverId());
        this.setRemarks(task.getRemarks());
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

    public List<AttchUrl> getAttchUrlList() {
        return attchUrlList;
    }

    public void setAttchUrlList(List<AttchUrl> attchUrlList) {
        this.attchUrlList = attchUrlList;
    }
}
