package com.cjf.ysxt.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjf.ysxt.dao.CollegeDao;
import com.cjf.ysxt.dao.DepartmentDao;
import com.cjf.ysxt.dao.WorkerDao;
import com.cjf.ysxt.dto.CollegeExecution;
import com.cjf.ysxt.dto.DCNExecution;
import com.cjf.ysxt.dto.DepartmentExecution;
import com.cjf.ysxt.dto.WorkerExecution;
import com.cjf.ysxt.entity.College;
import com.cjf.ysxt.entity.Department;
import com.cjf.ysxt.entity.DepartmentControlnum;
import com.cjf.ysxt.entity.Worker;
import com.cjf.ysxt.enums.DeleteStateEnum;
import com.cjf.ysxt.enums.InsertStateEnum;
import com.cjf.ysxt.exception.InsertException;
import com.cjf.ysxt.exception.NoNumberException;
import com.cjf.ysxt.exception.RepeatException;
import com.cjf.ysxt.exception.deleteException;
import com.cjf.ysxt.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CollegeDao collegeDao;
	@Autowired
	private WorkerDao workerDao;
	@Autowired
	private DepartmentDao departmentDao;
	
	public List<College> queryAllCollege() {
		List<College> colleges = collegeDao.queryAll(0, 100);
		return colleges;
	}
	@Transactional
	public CollegeExecution insertCollege(String collegeName, String address, String head, boolean status) {
		try {
			College record = new College(collegeName, address, head, status);
			collegeDao.insertCollege(record);
			return new CollegeExecution(InsertStateEnum.SUCCESS, record);
		} catch (RepeatException e1) {
			throw e1;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
            throw new InsertException("insert inner error:" + e.getMessage());
		}
	}
	@Transactional
	public CollegeExecution deleteCollege(String collegeName) {
		try {
			College record = collegeDao.queryByCollegeName(collegeName);
			if (record==null)
				throw new NoNumberException("不存在");
			collegeDao.deleteCollege(collegeName);
			return new CollegeExecution(DeleteStateEnum.SUCCESS, record);
		} catch (NoNumberException e1) {
			throw e1;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
            throw new deleteException("delete inner error:" + e.getMessage());
		}
	}

	public List<WorkerExecution> queryAllworker() {
		List<Worker> workers = workerDao.queryAll(0, 100);
		List<WorkerExecution> workerExecutions = new ArrayList<WorkerExecution>();
		for (Worker worker: workers) {
			int departmentid = worker.getDepartmentId();
			Department department = departmentDao.queryByDepartmentId(departmentid);
			String departmentName = department.getName();
			WorkerExecution workerExecution = new WorkerExecution(departmentName,worker);
			workerExecutions.add(workerExecution);
		}
		return workerExecutions;
	}

	@Transactional
	public WorkerExecution insertWorker(String workerid, String name, String sex, String tel, String title, String address,
			String birth, String password, String collegeName, String departmentName) {
		try {
			Department department = departmentDao.queryByName(departmentName, collegeName);
			int departmentId = department.getIddepartment();
			Worker record = new Worker(workerid, departmentName, sex, tel, title, address, 
					birth, password, collegeName, departmentId);
			
			workerDao.insertWorker(record);
			return new WorkerExecution(InsertStateEnum.SUCCESS, record);
		} catch (RepeatException e1) {
			throw e1;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
            throw new InsertException("insert inner error:" + e.getMessage());
		}
	}

	@Transactional
	public WorkerExecution deleteWorker(String workerid) {
		try {
			Worker record = workerDao.queryByWorkerId(workerid);
			if (record==null)
				throw new NoNumberException("不存在");
			workerDao.deleteWorker(workerid);
			return new WorkerExecution(DeleteStateEnum.SUCCESS, record);
		} catch (NoNumberException e1) {
			throw e1;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
            throw new deleteException("delete inner error:" + e.getMessage());
		}
	}

	public List<Department> queryAllDepartment() {
		List<Department> departments = departmentDao.queryAll(0, 100);
		return departments;
	}
	
	public Department queryById(int departmentId) {
		Department department = departmentDao.queryByDepartmentId(departmentId);
		return department;
	}

	@Transactional
	public DepartmentExecution insertDepartment(String departmentName, String head, String tel, String collegeName,
			boolean departmentStatus) {
		try {
			Department record = new Department(departmentName, head, tel, collegeName, departmentStatus);
			departmentDao.insertDepartment(record);
			return new DepartmentExecution(InsertStateEnum.SUCCESS, record);
		} catch (RepeatException e1) {
			throw e1;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
            throw new InsertException("insert inner error:" + e.getMessage());
		}
	}

	@Transactional
	public DepartmentExecution deleteDepartment(String departmentName, String collegeName) {
		try {
			Department record = departmentDao.queryByName(departmentName, collegeName);
			if (record==null)
				throw new NoNumberException("不存在");
			departmentDao.deleteDepartment(departmentName, collegeName);
			return new DepartmentExecution(DeleteStateEnum.SUCCESS, record);
		} catch (NoNumberException e1) {
			throw e1;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
            throw new deleteException("delete inner error:" + e.getMessage());
		}
	}
	
	public List<Department> queryDepartmentByCollegeName(String collegeName) {
		List<Department> departments = departmentDao.queryByCollegeName(collegeName);
		return departments;
	}
	
	

	

}
