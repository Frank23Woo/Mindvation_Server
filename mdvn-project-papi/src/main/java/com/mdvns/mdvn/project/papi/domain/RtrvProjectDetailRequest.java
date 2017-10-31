package com.mdvns.mdvn.project.papi.domain;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

@Component
public class RtrvProjectDetailRequest {
    @NotBlank(message = "请求参数错误，projId不能为空")
    private String projId;

    /*staffId(获取用户在项目中的权限)*/
    @NotBlank(message = "请求参数错误，staffId不能为空")
    private String staffId;

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    @Override
    public String toString() {
        return "RtrvProjectDetailRequest{" +
                "projId='" + projId + '\'' +
                ", staffId='" + staffId + '\'' +
                '}';
    }
}
