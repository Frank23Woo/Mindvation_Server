package com.mdvns.mdvn.staff.sapi.domain;

import com.mdvns.mdvn.common.beans.Tag;
import com.mdvns.mdvn.staff.sapi.domain.entity.Staff;

import java.util.List;

public class UpdateStaffDetailRequest {

    private Staff staffInfo;
    private List<String> tagIds;

    public Staff getStaffInfo() {
        return staffInfo;
    }

    public void setStaffInfo(Staff staffInfo) {
        this.staffInfo = staffInfo;
    }

    public List<String> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<String> tagIds) {
        this.tagIds = tagIds;
    }
}
