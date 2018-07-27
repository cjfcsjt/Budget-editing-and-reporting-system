package com.cjf.ysxt.service.impl;

import java.util.ArrayList;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjf.ysxt.dao.AcquisitionTableDao;
import com.cjf.ysxt.dao.BudgetDao;
import com.cjf.ysxt.dao.DepartmentControlnumDao;
import com.cjf.ysxt.dao.DepartmentDao;
import com.cjf.ysxt.dao.ProjectDao;
import com.cjf.ysxt.dto.AcquisitionExecution;
import com.cjf.ysxt.dto.BudgetExecution;
import com.cjf.ysxt.entity.AcquisitionTable;
import com.cjf.ysxt.entity.Budget;
import com.cjf.ysxt.entity.Department;
import com.cjf.ysxt.entity.DepartmentControlnum;
import com.cjf.ysxt.entity.Project;
import com.cjf.ysxt.enums.InsertStateEnum;
import com.cjf.ysxt.enums.UpdateStateEnum;
import com.cjf.ysxt.exception.InsertException;
import com.cjf.ysxt.exception.NoNumberException;
import com.cjf.ysxt.exception.RepeatException;
import com.cjf.ysxt.service.AcquisitionService;

@Service
public class AcquisitionServiceImpl implements AcquisitionService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private ProjectDao projectDao;
	@Autowired
	private AcquisitionTableDao acquisitionTableDao;
	@Autowired
	private DepartmentControlnumDao departmentControlnumDao;
	@Autowired
	private BudgetDao budgetDao;
	
	@Transactional
	public AcquisitionExecution insert(String collegeName,String departmentName,String projectName, String auditTime,
			float budgetProposal, String notes) {
		try {
			//学院名称+部门名称一定存在
			Department department = departmentDao.queryByName(departmentName,collegeName);
			int departmentId = department.getIddepartment();
			//项目id一定存在
			Project project = projectDao.queryByProjectName(projectName);
			int projectId = project.getIdproject();
			
			//判断是否插入重复
			AcquisitionTable acquisitionTable = acquisitionTableDao.queryByPDC(projectId, departmentId, collegeName);
			if(acquisitionTable != null)
				throw new RepeatException("重复插入");
			//插入
			AcquisitionTable record = new AcquisitionTable(collegeName, 
					departmentId, projectId, budgetProposal, notes, auditTime, false);
			acquisitionTableDao.insertAcquisitionTable(record);
			return new AcquisitionExecution(projectName, budgetProposal, notes, InsertStateEnum.SUCCESS, record);
		} catch (RepeatException e1) {
			throw e1;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
            throw new InsertException("insert inner error:" + e.getMessage());
		}
		
	}

	public List<AcquisitionExecution> getByName(String collegeName, String departmentName) {
		List<AcquisitionExecution> acquisitionExecutions = new ArrayList<AcquisitionExecution>(); 
		//学院名称+部门名称一定存在
		Department department = departmentDao.queryByName(departmentName,collegeName);
		int departmentId = department.getIddepartment();
		
		List<AcquisitionTable> acquisitionTables = acquisitionTableDao.queryByDC(departmentId, collegeName);
		for(AcquisitionTable acquisitionTable : acquisitionTables) {
			//获取departmentName
			//获取projectName
			int projectid = acquisitionTable.getProjectId();
			Project project = projectDao.queryByProjectId(projectid);
			String projectName = project.getName();
			AcquisitionExecution acquisitionExecution = new AcquisitionExecution(projectName,departmentName, acquisitionTable);
			acquisitionExecutions.add(acquisitionExecution);
		}
		return acquisitionExecutions;
	}
	
	public List<BudgetExecution> getBudgetByName(String collegeName, String departmentName) {
		List<BudgetExecution> budgetExecutions = new ArrayList<BudgetExecution>(); 
		//学院名称+部门名称一定存在
		Department department = departmentDao.queryByName(departmentName,collegeName);
		int departmentId = department.getIddepartment();
		List<Budget> budgets = budgetDao.queryByDC(departmentId, collegeName);
		for(Budget budget : budgets) {
			//获取departmentName
			//获取projectName
			int projectid = budget.getProjectId();
			Project project = projectDao.queryByProjectId(projectid);
			String projectName = project.getName();
			BudgetExecution budgetExecution = new BudgetExecution(projectName,departmentName, budget);
			budgetExecutions.add(budgetExecution);
		}
		return budgetExecutions;
	}
	
	@Transactional
	public AcquisitionExecution update(String collegeName,String departmentName,
			String projectName,float budgetProposal) {
		try {
			//学院名称+部门名称一定存在
			Department department = departmentDao.queryByName(departmentName,collegeName);
			int departmentId = department.getIddepartment();
			//项目id一定存在
			Project project = projectDao.queryByProjectName(projectName);
			int projectId = project.getIdproject();
			//判断
			AcquisitionTable acquisitionTable = acquisitionTableDao.queryByPDC(projectId, departmentId, collegeName);
			if(acquisitionTable == null)
				throw new RepeatException("不存在该条目");
			acquisitionTableDao.updateAcquisitionTable(projectId,departmentId,collegeName,budgetProposal);
			return new AcquisitionExecution(budgetProposal, UpdateStateEnum.SUCCESS, acquisitionTable);
		} catch (NoNumberException e1) {
			throw e1;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
            throw new InsertException("insert inner error:" + e.getMessage());
		}
	}

	public DepartmentControlnum queryDCN(String collegeName, String departmentName) {
		Department department = departmentDao.queryByName(departmentName, collegeName);
		int departmentId = department.getIddepartment();
		DepartmentControlnum departmentControlnum = departmentControlnumDao.queryByDepartmentId(departmentId);
		return departmentControlnum;
	}

	@Transactional
	public BudgetExecution insertBudget(String collegeName, String departmentName, String projectName,
			float budgetAmount, String budgetNotes, String auditTime, boolean auditResult) {
		try {
			Department department = departmentDao.queryByName(departmentName, collegeName);
			int departmentId = department.getIddepartment();
			Project project = projectDao.queryByProjectName(projectName);
			int projectId = project.getIdproject();
			Budget repeat = budgetDao.queryByPDC(projectId, departmentId, collegeName);
			if(repeat != null)
				throw new RepeatException("重复插入");
			Budget record = new Budget(collegeName, departmentId, projectId, budgetAmount, budgetNotes, auditTime, auditResult);
			budgetDao.insertBudget(record);
			return new BudgetExecution(InsertStateEnum.SUCCESS, record);
			
		} catch (RepeatException e1) {
			throw e1;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
            throw new InsertException("insert inner error:" + e.getMessage());
		}
		
	}

	

	

}
