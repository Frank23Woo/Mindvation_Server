package com.mdvns.mdvn.dashboard.sapi.domain;

import org.springframework.stereotype.Component;

import java.util.List;

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
