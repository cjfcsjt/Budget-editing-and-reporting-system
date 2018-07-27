package com.cjf.ysxt.service;

import java.util.List;

import com.cjf.ysxt.dto.CollegeExecution;
import com.cjf.ysxt.dto.DepartmentExecution;
import com.cjf.ysxt.dto.WorkerExecution;
import com.cjf.ysxt.entity.College;
import com.cjf.ysxt.entity.Department;
import com.cjf.ysxt.entity.Worker;

public interface AdminService {
	
	List<College> queryAllCollege();
	
	CollegeExecution insertCollege(String collegeName, String address,String head,boolean status);
	
	CollegeExecution deleteCollege(String collegeName);
	
	List<Department> queryDepartmentByCollegeName(String collegeName);
	
	List<WorkerExecution> queryAllworker();
	
	WorkerExecution insertWorker(String workerid, String name,String sex, String tel,String title,
			String address,String birth,String password,String collegeName,String departmentName);
	
	WorkerExecution deleteWorker(String workerid);
	
	List<Department> queryAllDepartment();
	
	Department queryById(int departmentId);
	
	DepartmentExecution insertDepartment(String departmentName, String head,String tel,
			String collegeName, boolean departmentStatus);
	
	DepartmentExecution deleteDepartment(String departmentName, String collegeName);
}
