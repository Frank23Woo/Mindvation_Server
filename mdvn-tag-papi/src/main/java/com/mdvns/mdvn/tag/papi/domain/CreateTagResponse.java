package com.mdvns.mdvn.tag.papi.domain;


import org.springframework.stereotype.Component;

import java.util.List;

/* 新建标签response
 */
@Component
public class CreateTagResponse {

    /*标签对象*/
    private Tag tag;

    private List<String> remarks;

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public List<String> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<String> remarks) {
        this.remarks = remarks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateTagResponse)) return false;

        CreateTagResponse that = (CreateTagResponse) o;

        if (getTag() != null ? !getTag().equals(that.getTag()) : that.getTag() != null) return false;
        return remarks != null ? remarks.equals(that.remarks) : that.remarks == null;
    }

    @Override
    public int hashCode() {
        int result = getTag() != null ? getTag().hashCode() : 0;
        result = 31 * result + (remarks != null ? remarks.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CreateTagResponse{" +
                "tag=" + tag +
                ", remarks=" + remarks +
                '}';
    }
}