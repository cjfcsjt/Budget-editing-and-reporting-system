package com.cjf.ysxt.dto;

import com.cjf.ysxt.entity.Worker;
import com.cjf.ysxt.enums.LoginStateEnum;
public class LoginExecution {

	private String id;
	private String password;
	private String title;
	private int state;
	private String stateinfo;
	private Worker worker;
	
	 
	public LoginExecution() {
		super();
	}
	//登录失败的构造器
	public LoginExecution(String id, String password, String title, LoginStateEnum stateEnum) {
		super();
		this.id = id;
		this.password = password;
		this.title = title;
		this.state = stateEnum.getState();
		this.stateinfo = stateEnum.getStateInfo();
	}
	//登录成功的构造器
	public LoginExecution(String id, String password, String title, LoginStateEnum stateEnum, Worker worker) {
		super();
		this.id = id;
		this.password = password;
		this.title = title;
		this.state = stateEnum.getState();
		this.stateinfo = stateEnum.getStateInfo();
		this.worker = worker;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
		return worker;
	}
	public void setWorker(Worker worker) {
		this.worker = worker;
	}
	@Override
	public String toString() {
		return "LoginExecution [id=" + id + ", password=" + password + ", title=" + title + ", state=" + state
				+ ", stateinfo=" + stateinfo + ", worker=" + worker + "]";
	}
	
	
	
	
	
}
