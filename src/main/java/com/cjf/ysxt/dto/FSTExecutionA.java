package com.cjf.ysxt.dto;

import com.cjf.ysxt.entity.FirstSummaryTable;
import com.cjf.ysxt.enums.UpdateStateEnum;

public class FSTExecutionA {

	private boolean auditResult;
	private int state;
	private String stateinfo;
	private FirstSummaryTable firstSummaryTable;
	public FSTExecutionA(boolean auditResult, UpdateStateEnum updateStateEnum,FirstSummaryTable firstSummaryTable) {
		super();
		this.auditResult = auditResult;
		this.state = updateStateEnum.getState();
		this.stateinfo = updateStateEnum.getStateInfo();
		this.firstSummaryTable = firstSummaryTable;
	}
	public FSTExecutionA(boolean auditResult, UpdateStateEnum updateStateEnum) {
		super();
		this.auditResult = auditResult;
		this.state = updateStateEnum.getState();
		this.stateinfo = updateStateEnum.getStateInfo();
	}
	
	public boolean isAuditResult() {
		return auditResult;
	}
	public void setAuditResult(boolean auditResult) {
		this.auditResult = auditResult;
	}
	public FirstSummaryTable getFirstSummaryTable() {
		return firstSummaryTable;
	}
	public void setFirstSummaryTable(FirstSummaryTable firstSummaryTable) {
		this.firstSummaryTable = firstSummaryTable;
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
	@Override
	public String toString() {
		return "FSTExecutionA [auditResult=" + auditResult + ", state=" + state + ", stateinfo=" + stateinfo
				+ ", firstSummaryTable=" + firstSummaryTable + "]";
	}
	
	
	
	
}
