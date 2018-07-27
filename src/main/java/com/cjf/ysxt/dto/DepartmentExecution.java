package com.cjf.ysxt.dto;

import com.cjf.ysxt.entity.Department;
import com.cjf.ysxt.enums.DeleteStateEnum;
import com.cjf.ysxt.enums.InsertStateEnum;

public class DepartmentExecution {

	private int state;
	private String stateinfo;
	private Department department;
	//插入失败构造器
	public DepartmentExecution(InsertStateEnum insertStateEnum) {
		super();
		this.state = insertStateEnum.getState();
		this.stateinfo = insertStateEnum.getStateInfo();
	}
	//插入成功构造器
	public DepartmentExecution(InsertStateEnum insertStateEnum, Department department) {
		super();
		this.state = insertStateEnum.getState();
		this.stateinfo = insertStateEnum.getStateInfo();
		this.department = department;
	}
	//删除成功构造器
	public DepartmentExecution(DeleteStateEnum deleteStateEnum, Department department) {
		super();
		this.state = deleteStateEnum.getState();
		this.stateinfo = deleteStateEnum.getStateInfo();
		this.department = department;
	}
	//删除失败构造器
	public DepartmentExecution(DeleteStateEnum deleteStateEnum) {
		super();
		this.state = deleteStateEnum.getState();
		this.stateinfo = deleteStateEnum.getStateInfo();
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
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	@Override
	public String toString() {
		return "DepartmentExecution [state=" + state + ", stateinfo=" + stateinfo + ", department=" + department + "]";
	}
	
	
	
	
	
	
}
