package com.cjf.ysxt.dto;

import com.cjf.ysxt.entity.FirstSummaryTable;

public class FSTExecutionD {

	private String departmentName;
	private FirstSummaryTable firstSummaryTable;
	public FSTExecutionD(String departmentName, FirstSummaryTable firstSummaryTable) {
		super();
		this.departmentName = departmentName;
		this.firstSummaryTable = firstSummaryTable;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public FirstSummaryTable getFirstSummaryTable() {
		return firstSummaryTable;
	}
	public void setFirstSummaryTable(FirstSummaryTable firstSummaryTable) {
		this.firstSummaryTable = firstSummaryTable;
	}
	@Override
	public String toString() {
		return "FSTExecution2 [departmentName=" + departmentName + ", firstSummaryTable=" + firstSummaryTable + "]";
	}
	
	
}
