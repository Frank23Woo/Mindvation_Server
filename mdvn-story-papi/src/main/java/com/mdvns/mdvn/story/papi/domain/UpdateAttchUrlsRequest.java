package com.mdvns.mdvn.story.papi.domain;


import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdateAttchUrlsRequest {

    //所属模块的Id
    private String beLongTo;
//    private List<AttchUrl> attchUrls;

    public String getBeLongTo() {
        return beLongTo;
    }

    public void setBeLongTo(String beLongTo) {
        this.beLongTo = beLongTo;
    }

//    public List<AttchUrl> getAttchUrls() {
//        return attchUrls;
//    }
//
//    public void setAttchUrls(List<AttchUrl> attchUrls) {
//        this.attchUrls = attchUrls;
//    }
}
