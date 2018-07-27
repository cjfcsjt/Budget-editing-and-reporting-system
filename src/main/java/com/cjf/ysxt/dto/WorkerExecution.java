package com.cjf.ysxt.dto;


import com.cjf.ysxt.entity.Worker;
import com.cjf.ysxt.enums.DeleteStateEnum;
import com.cjf.ysxt.enums.InsertStateEnum;

public class WorkerExecution {

	private String departmentName;
	private int state;
	private String stateinfo;
	private Worker Worker;
	public WorkerExecution(InsertStateEnum insertStateEnum) {
		super();
		this.state = insertStateEnum.getState();
		this.stateinfo = insertStateEnum.getStateInfo();
	}
	public WorkerExecution(InsertStateEnum insertStateEnum, Worker Worker) {
		super();
		this.state = insertStateEnum.getState();
		this.stateinfo = insertStateEnum.getStateInfo();
		this.Worker = Worker;
	}
	public WorkerExecution(int state, String stateinfo, Worker Worker) {
		super();
		this.state = state;
		this.stateinfo = stateinfo;
		this.Worker = Worker;
	}
	//删除成功构造器
		public WorkerExecution(DeleteStateEnum deleteStateEnum, Worker record) {
			super();
			this.state = deleteStateEnum.getState();
			this.stateinfo = deleteStateEnum.getStateInfo();
			this.Worker = record;
		}
		//删除失败构造器
		public WorkerExecution(DeleteStateEnum deleteStateEnum) {
			super();
			this.state = deleteStateEnum.getState();
			this.stateinfo = deleteStateEnum.getStateInfo();
		}
		
		public WorkerExecution(String departmentName,Worker worker) {
			super();
			this.departmentName = departmentName;
			this.Worker = worker;
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
		public Worker getWorker() {
			return Worker;
		}
		public void setWorker(Worker worker) {
			Worker = worker;
		}
		
		public String getDepartmentName() {
			return departmentName;
		}
		public void setDepartmentName(String departmentName) {
			this.departmentName = departmentName;
		}
		@Override
		public String toString() {
			return "WorkerExecution [departmentName=" + departmentName + ", state=" + state + ", stateinfo=" + stateinfo
					+ ", Worker=" + Worker + "]";
		}
	
		
	
	
	
	
	
}
