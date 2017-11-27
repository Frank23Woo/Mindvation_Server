package com.mdvns.mdvn.staff.papi.domain;

import com.mdvns.mdvn.common.beans.DepartmentDetail;
import com.mdvns.mdvn.common.beans.Position;
import com.mdvns.mdvn.common.beans.Tag;

import java.util.List;

public class RtrvStaffDetailResponse {

    private Staff staffInfo;
    private List<Tag> tags;
    private DepartmentDetail departmentDetail;
    private Position position;

    public DepartmentDetail getDepartmentDetail() {
        return departmentDetail;
    }

    public void setDepartmentDetail(DepartmentDetail departmentDetail) {
        this.departmentDetail = departmentDetail;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

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
