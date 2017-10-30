package com.mdvns.mdvn.common.beans;

import java.util.List;

public class AssignAuthRequest {

    private String projId;

    private String assignerId;

    private List<String> assignees;

    private String hierarchyId;

    private Integer authCode;

    public AssignAuthRequest() {
        super();
    }

    public AssignAuthRequest(String projId, String assignerId, List<String> assignees, String hierarchyId) {
        this.projId = projId;
        this.assignerId = assignerId;
        this.assignees = assignees;
        this.hierarchyId = hierarchyId;
    }

    
    public AssignAuthRequest(String projId, String assignerId, List<String> assignees, String hierarchyId,
			Integer authCode) {
		super();
		this.projId = projId;
		this.assignerId = assignerId;
		this.assignees = assignees;
		this.hierarchyId = hierarchyId;
		this.authCode = authCode;
	}

	public Integer getAuthCode() {
        return authCode;
    }

    public void setAuthCode(Integer authCode) {
        this.authCode = authCode;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public String getAssignerId() {
        return assignerId;
    }

    public void setAssignerId(String assignerId) {
        this.assignerId = assignerId;
    }

    public List<String> getAssignees() {
        return assignees;
    }

    public void setAssignees(List<String> assignees) {
        this.assignees = assignees;
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
        if (!(o instanceof AssignAuthRequest)) return false;

        AssignAuthRequest that = (AssignAuthRequest) o;

        if (getProjId() != null ? !getProjId().equals(that.getProjId()) : that.getProjId() != null) return false;
        if (getAssignerId() != null ? !getAssignerId().equals(that.getAssignerId()) : that.getAssignerId() != null)
            return false;
        if (getAssignees() != null ? !getAssignees().equals(that.getAssignees()) : that.getAssignees() != null)
            return false;
        if (getHierarchyId() != null ? !getHierarchyId().equals(that.getHierarchyId()) : that.getHierarchyId() != null)
            return false;
        return getAuthCode() != null ? getAuthCode().equals(that.getAuthCode()) : that.getAuthCode() == null;
    }

    @Override
    public int hashCode() {
        int result = getProjId() != null ? getProjId().hashCode() : 0;
        result = 31 * result + (getAssignerId() != null ? getAssignerId().hashCode() : 0);
        result = 31 * result + (getAssignees() != null ? getAssignees().hashCode() : 0);
        result = 31 * result + (getHierarchyId() != null ? getHierarchyId().hashCode() : 0);
        result = 31 * result + (getAuthCode() != null ? getAuthCode().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AssignAuthRequest{" +
                "projId='" + projId + '\'' +
                ", assignerId='" + assignerId + '\'' +
                ", assigneeId=" + assignees +
                ", hierarchyId='" + hierarchyId + '\'' +
                ", authCode=" + authCode +
                '}';
    }
}
