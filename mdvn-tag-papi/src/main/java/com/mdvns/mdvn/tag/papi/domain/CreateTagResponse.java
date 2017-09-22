package com.mdvns.mdvn.tag.papi.domain;


import org.springframework.stereotype.Component;

/* 新建标签response
 */
@Component
public class CreateTagResponse {

    /*标签对象*/
    private Tag tag;

    private String remarks;

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}