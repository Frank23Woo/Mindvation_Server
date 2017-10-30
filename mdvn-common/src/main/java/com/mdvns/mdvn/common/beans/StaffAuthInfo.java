package com.mdvns.mdvn.common.beans;

import org.springframework.stereotype.Component;

@Component
public class StaffAuthInfo {

    private Integer id;

    /*项目Id*/
    private String projId;

    /*员工Id*/
    private String staffId;

    /*权限编号*/
    private Integer authCode;

    private  String assignerId;
    
    private String hierarchyId;

    

    public StaffAuthInfo() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public Integer getAuthCode() {
        return authCode;
    }

    public void setAuthCode(Integer authCode) {
        this.authCode = authCode;
    }

    
    public String getAssignerId() {
		return assignerId;
	}

	public void setAssignerId(String assignerId) {
		this.assignerId = assignerId;
	}

	public String getHierarchyId() {
		return hierarchyId;
	}

	public void setHierarchyId(String hierarchyId) {
		this.hierarchyId = hierarchyId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assignerId == null) ? 0 : assignerId.hashCode());
		result = prime * result + ((authCode == null) ? 0 : authCode.hashCode());
		result = prime * result + ((hierarchyId == null) ? 0 : hierarchyId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((projId == null) ? 0 : projId.hashCode());
		result = prime * result + ((staffId == null) ? 0 : staffId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StaffAuthInfo other = (StaffAuthInfo) obj;
		if (assignerId == null) {
			if (other.assignerId != null)
				return false;
		} else if (!assignerId.equals(other.assignerId))
			return false;
		if (authCode == null) {
			if (other.authCode != null)
				return false;
		} else if (!authCode.equals(other.authCode))
			return false;
		if (hierarchyId == null) {
			if (other.hierarchyId != null)
				return false;
		} else if (!hierarchyId.equals(other.hierarchyId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (projId == null) {
			if (other.projId != null)
				return false;
		} else if (!projId.equals(other.projId))
			return false;
		if (staffId == null) {
			if (other.staffId != null)
				return false;
		} else if (!staffId.equals(other.staffId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StaffAuthInfo [id=" + id + ", projId=" + projId + ", staffId=" + staffId + ", authCode=" + authCode
				+ ", assignerId=" + assignerId + ", hierarchyId=" + hierarchyId + "]";
	}

	
}
