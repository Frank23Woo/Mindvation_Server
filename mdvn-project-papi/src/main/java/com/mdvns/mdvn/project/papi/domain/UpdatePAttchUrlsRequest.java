package com.mdvns.mdvn.project.papi.domain;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdatePAttchUrlsRequest {

    private String projId;
    private List<ProjAttchUrls> attchUrls;

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public List<ProjAttchUrls> getAttchUrls() {
        return attchUrls;
    }

    public void setAttchUrls(List<ProjAttchUrls> attchUrls) {
        this.attchUrls = attchUrls;
    }
}
