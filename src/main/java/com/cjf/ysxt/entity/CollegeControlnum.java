package com.cjf.ysxt.entity;

public class CollegeControlnum {
	private String collegeName;
	private float budgetControlNum;
	private String notes;
	
	public CollegeControlnum(String collegeName, float budgetControlNum, String notes) {
		super();
		this.collegeName = collegeName;
		this.budgetControlNum = budgetControlNum;
		this.notes = notes;
	}

	public CollegeControlnum() {
		super();
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
		return "CollegeControlnum [collegeName=" + collegeName + ", budgetControlNum=" + budgetControlNum + ", notes="
				+ notes + "]";
	}
	
	
}
