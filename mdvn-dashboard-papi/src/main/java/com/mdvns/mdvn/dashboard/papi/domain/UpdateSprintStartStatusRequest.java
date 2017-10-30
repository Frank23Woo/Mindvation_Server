package com.mdvns.mdvn.dashboard.papi.domain;

import org.springframework.stereotype.Component;

@Component
public class UpdateSprintStartStatusRequest {
    private Integer uuId;
    private Integer iterationCycle;

    public Integer getIterationCycle() {
        return iterationCycle;
    }
    public void setIterationCycle(Integer iterationCycle) {
        this.iterationCycle = iterationCycle;
    }
    public Integer getUuId() {
        return uuId;
    }

    public void setUuId(Integer uuId) {
        this.uuId = uuId;
    }

}
