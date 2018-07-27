package com.cjf.ysxt.dto;

import java.util.List;

import com.cjf.ysxt.entity.FirstSummaryTable;
import com.cjf.ysxt.enums.InsertStateEnum;
import com.cjf.ysxt.enums.UpdateStateEnum;
public class FSTExecution {

	private String departmentName;
	private float budgetProposal;
	private boolean auditResult;
	private String notes;
	private int state;
	private String stateinfo;
	private List<FirstSummaryTable> firstSummaryTables;
	private FirstSummaryTable firstSummaryTable;
	
	public void setFirstSummaryTable(FirstSummaryTable firstSummaryTable) {
		this.firstSummaryTable = firstSummaryTable;
	}
	public List<FirstSummaryTable> getFirstSummaryTables() {
		return firstSummaryTables;
	}
	public void setFirstSummaryTables(List<FirstSummaryTable> firstSummaryTables) {
		this.firstSummaryTables = firstSummaryTables;
	}
	
	//acq插入失败的构造器
	public FSTExecution(InsertStateEnum stateEnum) {
		super();
		this.state = stateEnum.getState();
		this.stateinfo = stateEnum.getStateInfo();
	}
	//acq插入成功的构造器
	public FSTExecution(InsertStateEnum stateEnum , List<FirstSummaryTable> firstSummaryTables) {
		super();
		this.state = stateEnum.getState();
		this.stateinfo = stateEnum.getStateInfo();
		this.firstSummaryTables = firstSummaryTables;
	}
	//acq更新失败的构造器
	public FSTExecution(float budgetProposal, UpdateStateEnum stateEnum) {
		super();
		this.budgetProposal = budgetProposal;
		this.state = stateEnum.getState();
		this.stateinfo = stateEnum.getStateInfo();
	}
	//acq更新成功的构造器
	public FSTExecution(float budgetProposal, UpdateStateEnum stateEnum, List<FirstSummaryTable> firstSummaryTables) {
		super();
		this.budgetProposal = budgetProposal;
		this.state = stateEnum.getState();
		this.stateinfo = stateEnum.getStateInfo();
		this.firstSummaryTables = firstSummaryTables;
	}
	
	//acq更新失败的构造器
	public FSTExecution(boolean auditResult, UpdateStateEnum stateEnum) {
			super();
			this.auditResult = auditResult;
			this.state = stateEnum.getState();
			this.stateinfo = stateEnum.getStateInfo();
		}
		//acq更新成功的构造器
	public FSTExecution(boolean auditResult, UpdateStateEnum stateEnum, List<FirstSummaryTable> firstSummaryTables ) {
			super();
			this.auditResult = auditResult;
			this.state = stateEnum.getState();
			this.stateinfo = stateEnum.getStateInfo();
			this.firstSummaryTables = firstSummaryTables;
		}
	
	public FSTExecution(boolean auditResult, UpdateStateEnum stateEnum, FirstSummaryTable firstSummaryTable) {
		super();
		this.auditResult = auditResult;
		this.state = stateEnum.getState();
		this.stateinfo = stateEnum.getStateInfo();
		this.firstSummaryTable = firstSummaryTable;
	}
	
	public FSTExecution(String departmentName, FirstSummaryTable firstSummaryTable) {
		super();
		this.departmentName = departmentName;
		this.firstSummaryTable = firstSummaryTable;
	}
	public FSTExecution(String departmentName,List<FirstSummaryTable> firstSummaryTables) {
		super();
		this.departmentName = departmentName;
		this.firstSummaryTables = firstSummaryTables;
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
	public FirstSummaryTable getFirstSummaryTable() {
		return firstSummaryTable;
	}
	@Override
	public String toString() {
		return "FSTExecution [departmentName=" + departmentName + ", budgetProposal=" + budgetProposal
				+ ", auditResult=" + auditResult + ", notes=" + notes + ", state=" + state + ", stateinfo=" + stateinfo
				+ ", firstSummaryTables=" + firstSummaryTables + ", firstSummaryTable=" + firstSummaryTable + "]";
	}
	
	
	
}
