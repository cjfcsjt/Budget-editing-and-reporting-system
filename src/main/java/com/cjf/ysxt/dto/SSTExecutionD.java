package com.cjf.ysxt.dto;


import com.cjf.ysxt.entity.SecondSummaryTable;

public class SSTExecutionD {

	private String departmentName;
	private SecondSummaryTable secondSummaryTable;
	public SSTExecutionD(String departmentName, SecondSummaryTable secondSummaryTable) {
		super();
		this.departmentName = departmentName;
		this.secondSummaryTable = secondSummaryTable;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public SecondSummaryTable getSecondSummaryTable() {
		return secondSummaryTable;
	}
	public void setSecondSummaryTable(SecondSummaryTable secondSummaryTable) {
		this.secondSummaryTable = secondSummaryTable;
	}
	@Override
	public String toString() {
		return "SSTExecutionD [departmentName=" + departmentName + ", secondSummaryTable=" + secondSummaryTable + "]";
	}
	
	
	
}
