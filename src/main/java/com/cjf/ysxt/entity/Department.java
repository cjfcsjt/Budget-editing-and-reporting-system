package com.cjf.ysxt.entity;

public class Department {
	
	private int iddepartment;
	private String name;
	private String head;
	private String tel;
	private String collegeName;
	private boolean departState;
	
	public Department(int iddepartment, String name, String head, String tel, String collegeName, boolean departState) {
		super();
		this.iddepartment = iddepartment;
		this.name = name;
		this.head = head;
		this.tel = tel;
		this.collegeName = collegeName;
		this.departState = departState;
	}

	
	public Department(String name, String head, String tel, String collegeName, boolean departState) {
		super();
		this.name = name;
		this.head = head;
		this.tel = tel;
		this.collegeName = collegeName;
		this.departState = departState;
	}


	public Department() {
	}

	public int getIddepartment() {
		return iddepartment;
	}

	public void setIddepartment(int iddepartment) {
		this.iddepartment = iddepartment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public boolean isDepartState() {
		return departState;
	}

	public void setDepartState(boolean departState) {
		this.departState = departState;
	}

	@Override
	public String toString() {
		return "Department [iddepartment=" + iddepartment + ", name=" + name + ", head=" + head + ", tel=" + tel
				+ ", collegeName=" + collegeName + ", departState=" + departState + "]";
	}
	
	
	
}
