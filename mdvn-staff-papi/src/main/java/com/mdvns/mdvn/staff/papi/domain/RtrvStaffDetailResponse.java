package com.mdvns.mdvn.staff.papi.domain;

import com.mdvns.mdvn.common.beans.Tag;

import java.util.List;

public class RtrvStaffDetailResponse {

    private Staff staffInfo;
    private List<Tag> tags;

    public Staff getStaffInfo() {
        return staffInfo;
    }

    public void setStaffInfo(Staff staffInfo) {
        this.staffInfo = staffInfo;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
