package com.cjf.ysxt.dto;

import com.cjf.ysxt.entity.AcquisitionTable;
import com.cjf.ysxt.enums.InsertStateEnum;
import com.cjf.ysxt.enums.UpdateStateEnum;
public class AcquisitionExecution {

	private String projectName;
	private float budgetProposal;
	private boolean auditResult;
	private String departmentName;
	private String notes;
	private int state;
	private String stateinfo;
	private AcquisitionTable acqtable;
	//acq插入失败的构造器
	public AcquisitionExecution(String projectName, float budgetProposal, String notes, InsertStateEnum stateEnum) {
		super();
		this.projectName = projectName;
		this.budgetProposal = budgetProposal;
		this.notes = notes;
		this.state = stateEnum.getState();
		this.stateinfo = stateEnum.getStateInfo();
	}
	//acq插入成功的构造器
	public AcquisitionExecution(String projectName, float budgetProposal, String notes,InsertStateEnum stateEnum , AcquisitionTable acqtable) {
		super();
		this.projectName = projectName;
		this.budgetProposal = budgetProposal;
		this.notes = notes;
		this.state = stateEnum.getState();
		this.stateinfo = stateEnum.getStateInfo();
		this.acqtable = acqtable;
	}
	//acq更新失败的构造器
	public AcquisitionExecution(float budgetProposal, UpdateStateEnum stateEnum) {
		super();
		this.budgetProposal = budgetProposal;
		this.state = stateEnum.getState();
		this.stateinfo = stateEnum.getStateInfo();
	}
	//acq更新成功的构造器
	public AcquisitionExecution(float budgetProposal, UpdateStateEnum stateEnum, AcquisitionTable acqtable) {
		super();
		this.budgetProposal = budgetProposal;
		this.state = stateEnum.getState();
		this.stateinfo = stateEnum.getStateInfo();
		this.acqtable = acqtable;
	}

	public AcquisitionExecution(boolean auditResult,UpdateStateEnum stateEnum) {
		super();
		this.auditResult = auditResult;
		this.state = stateEnum.getState();
		this.stateinfo = stateEnum.getStateInfo();
	}
	
	public AcquisitionExecution(boolean auditResult,UpdateStateEnum stateEnum,AcquisitionTable acqtable) {
		super();
		this.auditResult = auditResult;
		this.state = stateEnum.getState();
		this.stateinfo = stateEnum.getStateInfo();
		this.acqtable = acqtable;
	}
	
	public AcquisitionExecution(String projectName,String departmentName,AcquisitionTable acqtable) {
		this.projectName = projectName;
		this.departmentName = departmentName;
		this.acqtable = acqtable;
	}
	public boolean isAuditResult() {
		return auditResult;
	}
	public void setAuditResult(boolean auditResult) {
		this.auditResult = auditResult;
	}
	public String getProjectName() {
		return projectName;
	}
	
	
	public void setProjectName(String projectName) {
		this.projectName = projectName;
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
	public AcquisitionTable getAcqtable() {
		return acqtable;
	}
	public void setAcqtable(AcquisitionTable acqtable) {
		this.acqtable = acqtable;
	}
	
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	@Override
	public String toString() {
		return "AcquisitionExecution [projectName=" + projectName + ", budgetProposal=" + budgetProposal
				+ ", auditResult=" + auditResult + ", departmentName=" + departmentName + ", notes=" + notes
				+ ", state=" + state + ", stateinfo=" + stateinfo + ", acqtable=" + acqtable + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
}
