package com.cjf.ysxt.entity;

public class Worker {

	private String idworker;
	private String name;
	private String sex;
	private String tel;
	private String title;
	private String address;
	private String birth;
	private String password;
	private String collegeName;
	private int departmentId;
	
	public Worker(String idworker, String name, String sex, String tel, String title, String address, String birth,
			String password, String collegeName, int departmentId) {
		super();
		this.idworker = idworker;
		this.name = name;
		this.sex = sex;
		this.tel = tel;
		this.title = title;
		this.address = address;
		this.birth = birth;
		this.password = password;
		this.collegeName = collegeName;
		this.departmentId = departmentId;
	}

	public Worker() {
	}

	public String getIdworker() {
		return idworker;
	}

	public void setIdworker(String idworker) {
		this.idworker = idworker;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	@Override
	public String toString() {
		return "Worker [idworker=" + idworker + ", name=" + name + ", sex=" + sex + ", tel=" + tel + ", title=" + title
				+ ", address=" + address + ", birth=" + birth + ", password=" + password + ", collegeName="
				+ collegeName + ", departmentId=" + departmentId + "]";
	}
	
	
	
}
