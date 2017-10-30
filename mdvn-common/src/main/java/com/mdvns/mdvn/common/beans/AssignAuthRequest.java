package com.mdvns.mdvn.common.beans;

public class AssignAuthRequest {

	/*项目Id*/
    private String projId;

    /*员工Id*/
    private String assigneeId;

    /*权限编号*/
    private Integer authCode;

    private  String assignerId;
    
    private String hierarchyId;

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public String getAssigneeId() {
		return assigneeId;
	}

	public void setAssigneeId(String assigneeId) {
		this.assigneeId = assigneeId;
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
		result = prime * result + ((assigneeId == null) ? 0 : assigneeId.hashCode());
		result = prime * result + ((assignerId == null) ? 0 : assignerId.hashCode());
		result = prime * result + ((authCode == null) ? 0 : authCode.hashCode());
		result = prime * result + ((hierarchyId == null) ? 0 : hierarchyId.hashCode());
		result = prime * result + ((projId == null) ? 0 : projId.hashCode());
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
		AssignAuthRequest other = (AssignAuthRequest) obj;
		if (assigneeId == null) {
			if (other.assigneeId != null)
				return false;
		} else if (!assigneeId.equals(other.assigneeId))
			return false;
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
		if (projId == null) {
			if (other.projId != null)
				return false;
		} else if (!projId.equals(other.projId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AssignAuthRequest [projId=" + projId + ", assigneeId=" + assigneeId + ", authCode=" + authCode
				+ ", assignerId=" + assignerId + ", hierarchyId=" + hierarchyId + "]";
	}

}
