package com.mdvns.mdnv.file.domain;


import com.mdvns.mdnv.file.domain.entity.AttachUrl;

import java.util.List;

public class AttchUrlResp {

    private List<AttachUrl> attchUrls;

    private List<String> remarks;


    public List<AttachUrl> getAttchUrls() {
        return attchUrls;
    }

    public void setAttchUrls(List<AttachUrl> attchUrls) {
        this.attchUrls = attchUrls;
    }

    public List<String> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<String> remarks) {
        this.remarks = remarks;
    }
}
