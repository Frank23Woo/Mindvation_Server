package com.mdvns.mdvn.reqmnt.papi.domain;


import org.springframework.stereotype.Component;

@Component
public class ReqmntLeader {

    private Integer uuId;
    //员工Id
    private String staffId;
    //项目Id
    private String reqmntId;

    //更新时间
    private Long updateTime;

    private Integer idDeleted;

    public Integer getUuId() {
        return uuId;
    }

    public void setUuId(Integer uuId) {
        this.uuId = uuId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getReqmntId() {
        return reqmntId;
    }

    public void setReqmntId(String reqmntId) {
        this.reqmntId = reqmntId;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIdDeleted() {
        return idDeleted;
    }

    public void setIdDeleted(Integer idDeleted) {
        this.idDeleted = idDeleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReqmntLeader that = (ReqmntLeader) o;

        if (uuId != null ? !uuId.equals(that.uuId) : that.uuId != null) return false;
        if (staffId != null ? !staffId.equals(that.staffId) : that.staffId != null) return false;
        if (reqmntId != null ? !reqmntId.equals(that.reqmntId) : that.reqmntId != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        return idDeleted != null ? idDeleted.equals(that.idDeleted) : that.idDeleted == null;
    }

    @Override
    public int hashCode() {
        int result = uuId != null ? uuId.hashCode() : 0;
        result = 31 * result + (staffId != null ? staffId.hashCode() : 0);
        result = 31 * result + (reqmntId != null ? reqmntId.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (idDeleted != null ? idDeleted.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ReqmntLeader{" +
                "uuId=" + uuId +
                ", staffId='" + staffId + '\'' +
                ", reqmntId='" + reqmntId + '\'' +
                ", updateTime=" + updateTime +
                ", idDeleted=" + idDeleted +
                '}';
    }
}
