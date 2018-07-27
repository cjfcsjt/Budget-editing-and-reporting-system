package com.cjf.ysxt.entity;

public class SecondSummaryTable {
	
	private String collegeName;
	private int departmentId;
	private float summaryBudget;
	private String budgetNotes;
	private String auditTime;
	private boolean auditResult;
	public SecondSummaryTable(String collegeName, int departmentId,float summaryBudget, String budgetNotes,
			String auditTime, boolean auditResult) {
		super();
		this.departmentId = departmentId;
		this.collegeName = collegeName;
		this.summaryBudget = summaryBudget;
		this.budgetNotes = budgetNotes;
		this.auditTime = auditTime;
		this.auditResult = auditResult;
	}
	public SecondSummaryTable() {
		
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	public float getSummaryBudget() {
		return summaryBudget;
	}
	public void setSummaryBudget(float summaryBudget) {
		this.summaryBudget = summaryBudget;
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
		return "SecondSummaryTable [collegeName=" + collegeName + ", departmentId=" + departmentId + ", summaryBudget="
				+ summaryBudget + ", budgetNotes=" + budgetNotes + ", auditTime=" + auditTime + ", auditResult="
				+ auditResult + "]";
	}
	
	
}
