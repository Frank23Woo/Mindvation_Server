package com.mdvns.mdvn.task.papi.domain;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RetrieveTaskListResponse {

    private Integer errorCode;
    private String errorMsg;


    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

}
