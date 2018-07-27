package com.cjf.ysxt.entity;

public class FirstSummaryTable {
	
	private int departmentId;
	private String collegeName;
	private float summaryBudget;
	private String notes;
	private String auditTime;
	private boolean auditResult;
	public FirstSummaryTable(int departmentId, String collegeName, float summaryBudget, String notes, String auditTime,
			boolean auditResult) {
		super();
		this.departmentId = departmentId;
		this.collegeName = collegeName;
		this.summaryBudget = summaryBudget;
		this.notes = notes;
		this.auditTime = auditTime;
		this.auditResult = auditResult;
	}
	public FirstSummaryTable() {
		
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
		return "FirstSummaryTable [departmentId=" + departmentId + ", collegeName=" + collegeName + ", summaryBudget="
				+ summaryBudget + ", notes=" + notes + ", auditTime=" + auditTime + ", auditResult=" + auditResult
				+ "]";
	}
	
	
	
}
