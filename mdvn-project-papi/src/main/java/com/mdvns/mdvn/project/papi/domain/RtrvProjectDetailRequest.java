package com.mdvns.mdvn.project.papi.domain;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

@Component
public class RtrvProjectDetailRequest {
    @NotBlank(message = "请求参数错误，projId不能为空")
    private String projId;

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }
}
