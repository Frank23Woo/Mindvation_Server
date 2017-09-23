package com.mdvns.mdvn.task.papi.domain;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RetrieveTaskListResponse {

    private Integer errorCode;
    private String errorMsg;
    private List<Task> responseBody;

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

    public List<Task> getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(List<Task> responseBody) {
        this.responseBody = responseBody;
    }

    @Override
    public String toString() {
        return new StringBuffer().append("errorCode:").append(errorCode)
                .append(" | errorMsg:").append(errorMsg)
                .append(" | body:").append(responseBody)
                .toString();
    }
}
