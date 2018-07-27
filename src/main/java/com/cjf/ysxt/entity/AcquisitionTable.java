package com.cjf.ysxt.entity;

public class AcquisitionTable {
	
	private int idacquisitionTable;
	private String collegeName;
	private int departmentId;
	private int projectId;
	private float budgetProposal;
	private String notes;
	private String auditTime;
	private boolean auditResult;
	
	
	public AcquisitionTable() {
		super();
	}



	public AcquisitionTable(int idacquisitionTable, String collegeName, int departmentId, int projectId,
			float budgetProposal, String notes, String auditTime, boolean auditResult) {
		super();
		this.idacquisitionTable = idacquisitionTable;
		this.collegeName = collegeName;
		this.departmentId = departmentId;
		this.projectId = projectId;
		this.budgetProposal = budgetProposal;
		this.notes = notes;
		this.auditTime = auditTime;
		this.auditResult = auditResult;
	}



	public AcquisitionTable(String collegeName, int departmentId, int projectId, float budgetProposal, String notes,
			String auditTime, boolean auditResult) {
		super();
		this.collegeName = collegeName;
		this.departmentId = departmentId;
		this.projectId = projectId;
		this.budgetProposal = budgetProposal;
		this.notes = notes;
		this.auditTime = auditTime;
		this.auditResult = auditResult;
	}



	public int getIdacquisition() {
		return idacquisitionTable;
	}

	public void setIdacquisition(int idacquisitionTable) {
		this.idacquisitionTable = idacquisitionTable;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public float getBudgetProposal() {
		return budgetProposal;
	}

	public void setBudgetProposal(float budgetProposal) {
		this.budgetProposal = budgetProposal;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}

	public boolean isAuditResult() {
		return auditResult;
	}

	public void setAuditResult(boolean auditResult) {
		this.auditResult = auditResult;
	}

	@Override
	public String toString() {
		return "AcquisitionTable [idacquisitionTable=" + idacquisitionTable + ", collegeName=" + collegeName + ", departmentId="
				+ departmentId + ", projectId=" + projectId + ", budgetProposal=" + budgetProposal + ", notes=" + notes
				+ ", auditTime=" + auditTime + ", auditResult=" + auditResult + "]";
	}
	
	
	
	
}
