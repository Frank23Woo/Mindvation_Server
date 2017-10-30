package com.mdvns.mdvn.dashboard.papi.domain;

import org.springframework.stereotype.Component;

@Component
public class RtrvItSprintsRequest {

    private Integer uuId;

    public Integer getUuId() {
        return uuId;
    }

    public void setUuId(Integer uuId) {
        this.uuId = uuId;
    }
}
