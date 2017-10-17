package com.mdvns.mdvn.reqmnt.papi.domain;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RtrvReqmntInfoRequest {

    private String reqmntId;
    private List<String> remarks;

    public RtrvReqmntInfoRequest(){

    }

    public RtrvReqmntInfoRequest(String reqmntId) {
        this.reqmntId = reqmntId;
    }

    public String getReqmntId() {
        return reqmntId;
    }

    public void setReqmntId(String reqmntId) {
        this.reqmntId = reqmntId;
    }

    public List<String> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<String> remarks) {
        this.remarks = remarks;
    }
}
