package com.mdvns.mdvn.reqmnt.papi.domain;

import org.springframework.stereotype.Component;

@Component
public class ReqmntMember {

    private String uuId;

    private String staffId;

    private String roleName;

    private String reqmntId;

    private Integer isDeleted;

    private Long lastUpdateTime;

    public String getUuId() {
        return uuId;
    }

    public void setUuId(String uuId) {
        this.uuId = uuId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getReqmntId() {
        return reqmntId;
    }

    public void setReqmntId(String reqmntId) {
        this.reqmntId = reqmntId;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Override
    public String toString() {
        return "ReqmntMember{" +
                "uuId='" + uuId + '\'' +
                ", staffId='" + staffId + '\'' +
                ", roleName='" + roleName + '\'' +
                ", reqmntId='" + reqmntId + '\'' +
                ", isDeleted=" + isDeleted +
                ", lastUpdateTime=" + lastUpdateTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReqmntMember that = (ReqmntMember) o;

        if (uuId != null ? !uuId.equals(that.uuId) : that.uuId != null) return false;
        if (staffId != null ? !staffId.equals(that.staffId) : that.staffId != null) return false;
        if (roleName != null ? !roleName.equals(that.roleName) : that.roleName != null) return false;
        if (reqmntId != null ? !reqmntId.equals(that.reqmntId) : that.reqmntId != null) return false;
        if (isDeleted != null ? !isDeleted.equals(that.isDeleted) : that.isDeleted != null) return false;
        return lastUpdateTime != null ? lastUpdateTime.equals(that.lastUpdateTime) : that.lastUpdateTime == null;
    }

    @Override
    public int hashCode() {
        int result = uuId != null ? uuId.hashCode() : 0;
        result = 31 * result + (staffId != null ? staffId.hashCode() : 0);
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        result = 31 * result + (reqmntId != null ? reqmntId.hashCode() : 0);
        result = 31 * result + (isDeleted != null ? isDeleted.hashCode() : 0);
        result = 31 * result + (lastUpdateTime != null ? lastUpdateTime.hashCode() : 0);
        return result;
    }
}
