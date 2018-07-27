package com.cjf.ysxt.entity;

public class Budget {

	private int budgetid;
	private String collegeName;
	private int departmentId;
	private int projectId;
	private float budgetAmount;
	private String budgetNotes;
	private String auditTime;
	private boolean auditResult;
	
	public Budget(int budgetid, String collegeName, int departmentId, int projectId, float budgetAmount,
			String budgetNotes, String auditTime, boolean auditResult) {
		super();
		this.budgetid = budgetid;
		this.collegeName = collegeName;
		this.departmentId = departmentId;
		this.projectId = projectId;
		this.budgetAmount = budgetAmount;
		this.budgetNotes = budgetNotes;
		this.auditTime = auditTime;
		this.auditResult = auditResult;
	}
	
	public Budget(String collegeName, int departmentId, int projectId, float budgetAmount, String budgetNotes,
			String auditTime, boolean auditResult) {
		super();
		this.collegeName = collegeName;
		this.departmentId = departmentId;
		this.projectId = projectId;
		this.budgetAmount = budgetAmount;
		this.budgetNotes = budgetNotes;
		this.auditTime = auditTime;
		this.auditResult = auditResult;
	}

	public Budget() {
		
	}

	public int getBudgetid() {
		return budgetid;
	}

	public void setBudgetid(int budgetid) {
		this.budgetid = budgetid;
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

	public float getBudgetAmount() {
		return budgetAmount;
	}

	public void setBudgetAmount(float budgetAmount) {
		this.budgetAmount = budgetAmount;
	}

	public String getBudgetNotes() {
		return budgetNotes;
	}

	public void setBudgetNotes(String budgetNotes) {
		this.budgetNotes = budgetNotes;
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
		return "Budget [budgetid=" + budgetid + ", collegeName=" + collegeName + ", departmentId=" + departmentId
				+ ", projectId=" + projectId + ", budgetAmount=" + budgetAmount + ", budgetNotes=" + budgetNotes
				+ ", auditTime=" + auditTime + ", auditResult=" + auditResult + "]";
	}
	
	
	
	
}
