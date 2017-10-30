package com.mdvns.mdvn.common.beans;

public class RemoveAuthRequest {

    /*操作人(删除权限的人)Id*/
    private String operatorId;

    private String staffId;

    /*项目Id*/
    private String projId;

    /*项目中层级的Id*/
    private String hierarchyId;

    public RemoveAuthRequest() {
    }

    public RemoveAuthRequest(String operatorId, String staffId, String projId, String hierarchyId) {
        this.operatorId = operatorId;
        this.staffId = staffId;
        this.projId = projId;
        this.hierarchyId = hierarchyId;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public String getHierarchyId() {
        return hierarchyId;
    }

    public void setHierarchyId(String hierarchyId) {
        this.hierarchyId = hierarchyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RemoveAuthRequest)) return false;

        RemoveAuthRequest that = (RemoveAuthRequest) o;

        if (getOperatorId() != null ? !getOperatorId().equals(that.getOperatorId()) : that.getOperatorId() != null)
            return false;
        if (getStaffId() != null ? !getStaffId().equals(that.getStaffId()) : that.getStaffId() != null) return false;
        if (getProjId() != null ? !getProjId().equals(that.getProjId()) : that.getProjId() != null) return false;
        return getHierarchyId() != null ? getHierarchyId().equals(that.getHierarchyId()) : that.getHierarchyId() == null;
    }

    @Override
    public int hashCode() {
        int result = getOperatorId() != null ? getOperatorId().hashCode() : 0;
        result = 31 * result + (getStaffId() != null ? getStaffId().hashCode() : 0);
        result = 31 * result + (getProjId() != null ? getProjId().hashCode() : 0);
        result = 31 * result + (getHierarchyId() != null ? getHierarchyId().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RemoveAuthRequest{" +
                "operatorId='" + operatorId + '\'' +
                ", staffId='" + staffId + '\'' +
                ", projId='" + projId + '\'' +
                ", hierarchyId='" + hierarchyId + '\'' +
                '}';
    }
}
