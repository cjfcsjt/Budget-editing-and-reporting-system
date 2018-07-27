package com.cjf.ysxt.entity;

public class College {
	
	private int idcollege;
	private String name;
	private String address;
	private String head;
	private boolean collegeState;
	
	public College(int idcollege, String name, String address, String head, boolean collegeState) {
		super();
		this.idcollege = idcollege;
		this.name = name;
		this.address = address;
		this.head = head;
		this.collegeState = collegeState;
	}

	
	public College(String name, String address, String head, boolean collegeState) {
		super();
		this.name = name;
		this.address = address;
		this.head = head;
		this.collegeState = collegeState;
	}


	public College() {
	}

	public int getIdcollege() {
		return idcollege;
	}

	public void setIdcollege(int idcollege) {
		this.idcollege = idcollege;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public boolean isCollegeState() {
		return collegeState;
	}

	public void setCollegeState(boolean collegeState) {
		this.collegeState = collegeState;
	}

	@Override
	public String toString() {
		return "College [idcollege=" + idcollege + ", name=" + name + ", address=" + address + ", head=" + head
				+ ", collegeState=" + collegeState + "]";
	}
	
	
	
	
}
