package com.cjf.ysxt.dto;



import com.cjf.ysxt.entity.DepartmentControlnum;
import com.cjf.ysxt.enums.InsertStateEnum;

public class DCNExecution {

	private int state;
	private String stateinfo;
	private DepartmentControlnum departmentControlnum;
	
	
	//acq插入失败的构造器
	public DCNExecution(InsertStateEnum stateEnum) {
		super();
		this.state = stateEnum.getState();
		this.stateinfo = stateEnum.getStateInfo();
	}
	//acq插入成功的构造器
	public DCNExecution(InsertStateEnum stateEnum , DepartmentControlnum departmentControlnum) {
		super();
		this.state = stateEnum.getState();
		this.stateinfo = stateEnum.getStateInfo();
		this.departmentControlnum = departmentControlnum;
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
	public DepartmentControlnum getDepartmentControlnum() {
		return departmentControlnum;
	}
	public void setDepartmentControlnum(DepartmentControlnum departmentControlnum) {
		this.departmentControlnum = departmentControlnum;
	}
	@Override
	public String toString() {
		return "DCNExecution [state=" + state + ", stateinfo=" + stateinfo + ", departmentControlnum="
				+ departmentControlnum + "]";
	}
	
	
	
	
	
	
	
}
