package com.cjf.ysxt.entity;

public class Project {

	private int idproject;
	private String name;
	public Project(int idproject, String name) {
		super();
		this.idproject = idproject;
		this.name = name;
	}
	
	public Project(String name) {
		super();
		this.name = name;
	}

	public Project() {
	}
	public int getIdproject() {
		return idproject;
	}
	public void setIdproject(int idproject) {
		this.idproject = idproject;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Project [idproject=" + idproject + ", name=" + name + "]";
	}
	
	
}
