package com.mdvns.mdvn.reqmnt.sapi.domain;

import com.mdvns.mdvn.reqmnt.sapi.domain.entity.ReqmntAttchUrl;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdatePAttchUrlsRequest {

    private String projId;
    private List<ReqmntAttchUrl> attchUrls;

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public List<ReqmntAttchUrl> getAttchUrls() {
        return attchUrls;
    }

    public void setAttchUrls(List<ReqmntAttchUrl> attchUrls) {
        this.attchUrls = attchUrls;
    }
}
