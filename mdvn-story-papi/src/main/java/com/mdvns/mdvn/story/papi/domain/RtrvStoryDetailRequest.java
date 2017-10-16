package com.mdvns.mdvn.story.papi.domain;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RtrvStoryDetailRequest {
    @NotBlank(message = "请求参数错误，storyId不能为空")
    private String storyId;

    private List<String> remarks;

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }

    public List<String> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<String> remarks) {
        this.remarks = remarks;
    }
}
