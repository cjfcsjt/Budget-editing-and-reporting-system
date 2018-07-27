package com.cjf.ysxt.dto;

import com.cjf.ysxt.entity.College;
import com.cjf.ysxt.entity.Department;
import com.cjf.ysxt.enums.DeleteStateEnum;
import com.cjf.ysxt.enums.InsertStateEnum;

public class CollegeExecution {

	private int state;
	private String stateinfo;
	private College college;
	public CollegeExecution(InsertStateEnum insertStateEnum) {
		super();
		this.state = insertStateEnum.getState();
		this.stateinfo = insertStateEnum.getStateInfo();
	}
	public CollegeExecution(InsertStateEnum insertStateEnum, College college) {
		super();
		this.state = insertStateEnum.getState();
		this.stateinfo = insertStateEnum.getStateInfo();
		this.college = college;
	}
	//删除成功构造器
		public CollegeExecution(DeleteStateEnum deleteStateEnum, College record) {
			super();
			this.state = deleteStateEnum.getState();
			this.stateinfo = deleteStateEnum.getStateInfo();
			this.college = record;
		}
		//删除失败构造器
		public CollegeExecution(DeleteStateEnum deleteStateEnum) {
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
		public College getCollege() {
			return college;
		}
		public void setCollege(College college) {
			this.college = college;
		}
		@Override
		public String toString() {
			return "CollegeExecution [state=" + state + ", stateinfo=" + stateinfo + ", college=" + college + "]";
		}
	
	
	
	
	
	
}
