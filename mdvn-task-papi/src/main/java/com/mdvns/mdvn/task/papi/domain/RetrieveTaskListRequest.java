package com.mdvns.mdvn.task.papi.domain;

import org.springframework.stereotype.Component;

@Component
public class RetrieveTaskListRequest {
    // story编号
    private String storyId;
    // 页码
    private Integer page;
    // 每页条数
    private Integer pageSize;

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return new StringBuffer().append("storyId:").append(storyId)
                .append(" | page:").append(page)
                .append(" | pageSize:").append(pageSize)
                .toString();
    }
}
