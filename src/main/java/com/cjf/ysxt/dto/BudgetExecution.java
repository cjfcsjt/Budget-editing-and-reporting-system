package com.cjf.ysxt.dto;

import com.cjf.ysxt.entity.Budget;
import com.cjf.ysxt.enums.InsertStateEnum;
import com.cjf.ysxt.enums.UpdateStateEnum;

public class BudgetExecution {

	private boolean auditResult;
	private int state;
	private String stateinfo;
	private String projectName;
	private String departmentName;
	private Budget budget;
	

	
	//插入成功的构造器
	public BudgetExecution(InsertStateEnum stateEnum, Budget budget) {
		super();
		this.state = stateEnum.getState();
		this.stateinfo = stateEnum.getStateInfo();
		this.budget = budget;
	}
	
	//插入失败的构造器
	public BudgetExecution(InsertStateEnum stateEnum) {
		super();
		this.state = stateEnum.getState();
		this.stateinfo = stateEnum.getStateInfo();
	}
	
	
	//更新失败的构造器
	public BudgetExecution(boolean auditResult, UpdateStateEnum stateEnum) {
		super();
		this.auditResult = auditResult;
		this.state = stateEnum.getState();
		this.stateinfo = stateEnum.getStateInfo();
		
	}
	
	
	//更新成功的构造器
	public BudgetExecution(boolean auditResult, UpdateStateEnum stateEnum, Budget budget) {
		super();
		this.auditResult = auditResult;
		this.state = stateEnum.getState();
		this.stateinfo = stateEnum.getStateInfo();
		this.budget = budget;
	}
	
	public BudgetExecution(String projectName, Budget budget) {
		this.projectName = projectName;
		this.budget = budget;
	}
	
	public BudgetExecution(String projectName,String department, Budget budget) {
		this.projectName = projectName;
		this.departmentName = department;
		this.budget = budget;
	}

	public boolean isAuditResult() {
		return auditResult;
	}

	public void setAuditResult(boolean auditResult) {
		this.auditResult = auditResult;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateinfo() {
		return stateinfo;
	}

	public void setStateinfo(String stateinfo) {
		this.stateinfo = stateinfo;
	}

	public Budget getBudget() {
		return budget;
	}

	public void setBudget(Budget budget) {
		this.budget = budget;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Override
	public String toString() {
		return "BudgetExecution [auditResult=" + auditResult + ", state=" + state + ", stateinfo=" + stateinfo
				+ ", budget=" + budget + ", projectName=" + projectName + ", departmentName=" + departmentName + "]";
	}

	
	
	
}
