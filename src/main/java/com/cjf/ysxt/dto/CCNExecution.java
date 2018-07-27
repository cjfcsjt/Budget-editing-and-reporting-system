package com.cjf.ysxt.dto;

import java.util.List;

import com.cjf.ysxt.entity.CollegeControlnum;
import com.cjf.ysxt.enums.InsertStateEnum;
public class CCNExecution {

	private int state;
	private String stateinfo;
	private CollegeControlnum collegeControlnums;
	//acq插入失败的构造器
	public CCNExecution(InsertStateEnum stateEnum) {
		super();
		this.state = stateEnum.getState();
		this.stateinfo = stateEnum.getStateInfo();
	}
	//acq插入成功的构造器
	public CCNExecution(InsertStateEnum stateEnum , CollegeControlnum collegeControlnum) {
		super();
		this.state = stateEnum.getState();
		this.stateinfo = stateEnum.getStateInfo();
		this.collegeControlnums = collegeControlnum;
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
	public CollegeControlnum getCollegeControlnums() {
		return collegeControlnums;
	}
	public void setCollegeControlnums(CollegeControlnum collegeControlnums) {
		this.collegeControlnums = collegeControlnums;
	}
	@Override
	public String toString() {
		return "CCNExecution [state=" + state + ", stateinfo=" + stateinfo + ", collegeControlnums="
				+ collegeControlnums + "]";
	}
	
	
	
	
	
	
	
	
}
