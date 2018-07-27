package com.cjf.ysxt.entity;

public class DepartmentControlnum {

	private int departmentId;
	private String collegeName;
	private float budgetControlNum;
	private String notes;
	public DepartmentControlnum(int departmentId, String collegeName, float budgetControlNum, String notes) {
		super();
		this.departmentId = departmentId;
		this.collegeName = collegeName;
		this.budgetControlNum = budgetControlNum;
		this.notes = notes;
	}
	public DepartmentControlnum() {
		
	}
	
	public DepartmentControlnum(String collegeName, float budgetControlNum, String notes) {
		super();
		this.collegeName = collegeName;
		this.budgetControlNum = budgetControlNum;
		this.notes = notes;
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
	public float getBudgetControlNum() {
		return budgetControlNum;
	}
	public void setBudgetControlNum(float budgetControlNum) {
		this.budgetControlNum = budgetControlNum;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	@Override
	public String toString() {
		return "DepartmentControlnum [departmentId=" + departmentId + ", collegeName=" + collegeName
				+ ", budgetControlNum=" + budgetControlNum + ", notes=" + notes + "]";
	}
	
	
}
