package com.cjf.ysxt.dto;

import java.util.List;


import com.cjf.ysxt.entity.SecondSummaryTable;
import com.cjf.ysxt.enums.InsertStateEnum;
import com.cjf.ysxt.enums.UpdateStateEnum;
public class SSTExecution {

	private String departmentName;
	private float budgetProposal;
	private boolean auditResult;
	private String notes;
	private int state;
	private String stateinfo;
	private List<SecondSummaryTable> secondSummaryTables;
	private SecondSummaryTable secondSummaryTable;
	
	public SSTExecution(String departmentName, SecondSummaryTable secondSummaryTable) {
		super();
		this.departmentName = departmentName;
		this.secondSummaryTable = secondSummaryTable;
	}

	//acq插入失败的构造器
	public SSTExecution(InsertStateEnum stateEnum) {
		super();
		this.state = stateEnum.getState();
		this.stateinfo = stateEnum.getStateInfo();
	}
	//acq插入成功的构造器
	public SSTExecution(InsertStateEnum stateEnum , List<SecondSummaryTable> secondSummaryTable) {
		super();
		this.state = stateEnum.getState();
		this.stateinfo = stateEnum.getStateInfo();
		this.secondSummaryTables = secondSummaryTable;
	}
	//acq更新失败的构造器
	public SSTExecution(float budgetProposal, UpdateStateEnum stateEnum) {
		super();
		this.budgetProposal = budgetProposal;
		this.state = stateEnum.getState();
		this.stateinfo = stateEnum.getStateInfo();
	}
	//acq更新成功的构造器
	public SSTExecution(float budgetProposal, UpdateStateEnum stateEnum, List<SecondSummaryTable> secondSummaryTables) {
		super();
		this.budgetProposal = budgetProposal;
		this.state = stateEnum.getState();
		this.stateinfo = stateEnum.getStateInfo();
		this.secondSummaryTables = secondSummaryTables;
	}
	//acq更新成功的构造器
		public SSTExecution(boolean auditResult, UpdateStateEnum stateEnum, List<SecondSummaryTable> secondSummaryTables) {
			super();
			this.auditResult = auditResult;
			this.state = stateEnum.getState();
			this.stateinfo = stateEnum.getStateInfo();
			this.secondSummaryTables = secondSummaryTables;
		}
	//acq更新失败的构造器
		public SSTExecution(boolean auditResult, UpdateStateEnum stateEnum) {
			super();
			this.auditResult = auditResult;
			this.state = stateEnum.getState();
			this.stateinfo = stateEnum.getStateInfo();
		}
		public SSTExecution(boolean auditResult, UpdateStateEnum stateEnum, SecondSummaryTable secondSummaryTable) {
			super();
			this.auditResult = auditResult;
			this.state = stateEnum.getState();
			this.stateinfo = stateEnum.getStateInfo();
			this.secondSummaryTable = secondSummaryTable;
		}
		
		public SSTExecution(String departmentName,List<SecondSummaryTable> secondSummaryTables) {
			this.departmentName = departmentName;
			this.secondSummaryTables = secondSummaryTables;
		}

		public String getDepartmentName() {
			return departmentName;
		}

		public void setDepartmentName(String departmentName) {
			this.departmentName = departmentName;
		}

		public float getBudgetProposal() {
			return budgetProposal;
		}

		public void setBudgetProposal(float budgetProposal) {
			this.budgetProposal = budgetProposal;
		}

		public boolean isAuditResult() {
			return auditResult;
		}

		public void setAuditResult(boolean auditResult) {
			this.auditResult = auditResult;
		}

		public String getNotes() {
			return notes;
		}

		public void setNotes(String notes) {
			this.notes = notes;
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

		public List<SecondSummaryTable> getSecondSummaryTables() {
			return secondSummaryTables;
		}

		public void setSecondSummaryTables(List<SecondSummaryTable> secondSummaryTables) {
			this.secondSummaryTables = secondSummaryTables;
		}

		public SecondSummaryTable getSecondSummaryTable() {
			return secondSummaryTable;
		}

		public void setSecondSummaryTable(SecondSummaryTable secondSummaryTable) {
			this.secondSummaryTable = secondSummaryTable;
		}

		@Override
		public String toString() {
			return "SSTExecution [departmentName=" + departmentName + ", budgetProposal=" + budgetProposal
					+ ", auditResult=" + auditResult + ", notes=" + notes + ", state=" + state + ", stateinfo="
					+ stateinfo + ", secondSummaryTables=" + secondSummaryTables + ", secondSummaryTable="
					+ secondSummaryTable + "]";
		}
	
		
	
	
	
	
	
	
	
	
}
