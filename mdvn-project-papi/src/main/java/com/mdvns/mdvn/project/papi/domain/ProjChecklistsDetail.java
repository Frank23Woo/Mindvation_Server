package com.mdvns.mdvn.project.papi.domain;


import com.mdvns.mdvn.common.beans.Staff;
import org.springframework.stereotype.Component;

@Component
public class ProjChecklistsDetail {
    private ProjChecklists projChecklists;
    private Staff creatorInfo;
    private Staff executorInfo;
    private Staff assignerInfo;

    public ProjChecklists getProjChecklists() {
        return projChecklists;
    }

    public void setProjChecklists(ProjChecklists projChecklists) {
        this.projChecklists = projChecklists;
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
