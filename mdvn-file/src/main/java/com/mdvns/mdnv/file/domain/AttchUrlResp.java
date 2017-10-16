package com.mdvns.mdnv.file.domain;

import com.mdvns.mdnv.file.domain.entity.AttchUrl;

import java.util.List;

public class AttchUrlResp {

    private List<AttchUrl> attchUrls;

    private List<String> remarks;


    public List<AttchUrl> getAttchUrls() {
        return attchUrls;
    }

    public void setAttchUrls(List<AttchUrl> attchUrls) {
        this.attchUrls = attchUrls;
    }

    public List<String> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<String> remarks) {
        this.remarks = remarks;
    }
}
