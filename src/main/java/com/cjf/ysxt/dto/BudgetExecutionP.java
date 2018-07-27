package com.cjf.ysxt.dto;

import com.cjf.ysxt.entity.Budget;

public class BudgetExecutionP {

	private String projectName;
	private Budget budget;
	public BudgetExecutionP(String projectName, Budget budget) {
		super();
		this.projectName = projectName;
		this.budget = budget;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Budget getBudget() {
		return budget;
	}
	public void setBudget(Budget budget) {
		this.budget = budget;
	}
	@Override
	public String toString() {
		return "BudgetExecutionP [projectName=" + projectName + ", budget=" + budget + "]";
	}
	
	
}
