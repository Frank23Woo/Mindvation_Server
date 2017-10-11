package com.mdvns.mdvn.reqmnt.sapi.domain;

import com.mdvns.mdvn.common.beans.Staff;
import com.mdvns.mdvn.reqmnt.sapi.domain.entity.ReqmntCheckList;
import org.springframework.stereotype.Component;

@Component
public class ReqmntCheckListDetail {
    private ReqmntCheckList ReqmntCheckList;
    private Staff creatorInfo;
    private Staff executorInfo;
    private Staff assignerInfo;

    public ReqmntCheckList getReqmntCheckList() {
        return ReqmntCheckList;
    }

    public void setReqmntCheckList(ReqmntCheckList reqmntCheckList) {
        this.ReqmntCheckList = reqmntCheckList;
    }

    public Staff getCreatorInfo() {
        return creatorInfo;
    }

    public void setCreatorInfo(Staff creatorInfo) {
        this.creatorInfo = creatorInfo;
    }

    public Staff getExecutorInfo() {
        return executorInfo;
    }

    public void setExecutorInfo(Staff executorInfo) {
        this.executorInfo = executorInfo;
    }

    public Staff getAssignerInfo() {
        return assignerInfo;
    }

    public void setAssignerInfo(Staff assignerInfo) {
        this.assignerInfo = assignerInfo;
    }
}
