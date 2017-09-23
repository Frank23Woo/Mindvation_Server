package com.mdvns.mdvn.task.papi.domain;

import org.springframework.stereotype.Component;

@Component
public class CreateTaskResponse {

    private Integer errorCode;
    private String errorMsg;
    private Task responseBody;

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

    public Task getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(Task responseBody) {
        this.responseBody = responseBody;
    }
}
