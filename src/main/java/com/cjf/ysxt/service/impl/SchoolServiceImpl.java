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
import com.cjf.ysxt.dao.CollegeControlnumDao;
import com.cjf.ysxt.dao.DepartmentDao;
import com.cjf.ysxt.dao.FirstSummaryTableDao;
import com.cjf.ysxt.dao.ProjectDao;
import com.cjf.ysxt.dao.SecondSummaryTableDao;
import com.cjf.ysxt.dto.AcquisitionExecution;
import com.cjf.ysxt.dto.BudgetExecution;
import com.cjf.ysxt.dto.BudgetExecutionP;
import com.cjf.ysxt.dto.CCNExecution;
import com.cjf.ysxt.dto.FSTExecution;
import com.cjf.ysxt.dto.FSTExecutionA;
import com.cjf.ysxt.dto.FSTExecutionD;
import com.cjf.ysxt.dto.SSTExecution;
import com.cjf.ysxt.dto.SSTExecutionD;
import com.cjf.ysxt.entity.AcquisitionTable;
import com.cjf.ysxt.entity.Budget;
import com.cjf.ysxt.entity.CollegeControlnum;
import com.cjf.ysxt.entity.Department;
import com.cjf.ysxt.entity.FirstSummaryTable;
import com.cjf.ysxt.entity.Project;
import com.cjf.ysxt.entity.SecondSummaryTable;
import com.cjf.ysxt.enums.InsertStateEnum;
import com.cjf.ysxt.enums.UpdateStateEnum;
import com.cjf.ysxt.exception.InsertException;
import com.cjf.ysxt.exception.NoNumberException;
import com.cjf.ysxt.exception.RepeatException;
import com.cjf.ysxt.exception.UpdateException;
import com.cjf.ysxt.service.SchoolService;

@Service
public class SchoolServiceImpl implements SchoolService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@Autowired
	private FirstSummaryTableDao firstSummaryTableDao;
	@Autowired
	private SecondSummaryTableDao secondSummaryTableDao;
	@Autowired
	private CollegeControlnumDao controlnumDao;
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private BudgetDao budgetDao;
	@Autowired
	private ProjectDao projectDao;
	@Autowired
	private AcquisitionTableDao acquisitionTableDao;
	
	public List<FirstSummaryTable> queryCollegeBudget() {
		List<FirstSummaryTable> firstSummaryTables = firstSummaryTableDao.queryCollegeBudget();
		return firstSummaryTables;
	}
	
	public List<SecondSummaryTable> queryCollegeBudget2() {
		List<SecondSummaryTable> secondSummaryTables = secondSummaryTableDao.queryCollegeBudget();
		return secondSummaryTables;
	}
	@Transactional
	public List<FSTExecutionD> queryDepartmentBudget(String collegeName) {
		List<FSTExecutionD> fstExecutions = new ArrayList<FSTExecutionD>(); 
		List<FirstSummaryTable> firstSummaryTables = firstSummaryTableDao.queryByCollegeName(collegeName);
		for(FirstSummaryTable firstSummaryTable : firstSummaryTables) {
			//获取departmentName
			int departmentid = firstSummaryTable.getDepartmentId();
			
			Department department = departmentDao.queryByDepartmentId(departmentid);
			String departmentName = department.getName();
			FSTExecutionD fstExecution = new FSTExecutionD(departmentName, firstSummaryTable);
			
			fstExecutions.add(fstExecution);
		}
		return fstExecutions;
	}

	@Transactional
	public List<SSTExecutionD> queryDepartmentBudget2(String collegeName) {
		List<SSTExecutionD> sstExecutions = new ArrayList<SSTExecutionD>(); 
		List<SecondSummaryTable> secondSummaryTables = secondSummaryTableDao.queryByCollegeName(collegeName);
		for(SecondSummaryTable secondSummaryTable : secondSummaryTables) {
			//获取departmentName
			int departmentid = secondSummaryTable.getDepartmentId();
			Department department = departmentDao.queryByDepartmentId(departmentid);
			String departmentName = department.getName();
			SSTExecutionD sstExecution = new SSTExecutionD(departmentName, secondSummaryTable);
			sstExecutions.add(sstExecution);
		}
		return sstExecutions;
	}
	@Transactional
	public CCNExecution insert(String collegeName, float budgetControlNum, String notes) {
		try {
			CollegeControlnum repeat = controlnumDao.queryByCollegeName(collegeName);
			if(repeat != null)
				throw new RepeatException("重复插入");
			CollegeControlnum record = new CollegeControlnum(collegeName, budgetControlNum, notes);
			controlnumDao.insertCollegeControlnum(record);
			return new CCNExecution(InsertStateEnum.SUCCESS, record);
		} catch (RepeatException e1) {
			throw e1;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
            throw new InsertException("insert inner error:" + e.getMessage());
		}
	}
	@Transactional
	public SSTExecution update(String collegeName, String departmentName, 
			boolean auditResult) {
		try {
			//学院名称+部门名称一定存在
			Department department = departmentDao.queryByName(departmentName,collegeName);
			int departmentId = department.getIddepartment();
			//判断
			SecondSummaryTable secondSummaryTable = secondSummaryTableDao.queryByDepartmentId(departmentId);
			if(secondSummaryTable == null)
				throw new NoNumberException("不存在该条目");
			secondSummaryTableDao.updateAuditResult(departmentId,auditResult);
			return new SSTExecution(auditResult, UpdateStateEnum.SUCCESS, secondSummaryTable);
		} catch (NoNumberException e1) {
			throw e1;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
            throw new UpdateException("update inner error:" + e.getMessage());
		}
	}

	public List<BudgetExecutionP> queryProjectBudget() {
		List<BudgetExecutionP> budgetExecutions = new ArrayList<BudgetExecutionP>();
		List<Budget> budgets = budgetDao.queryProjectBudget();
		for(Budget budget : budgets) {
			//获取projectName
			int projectid = budget.getProjectId();
			Project project = projectDao.queryByProjectId(projectid);
			String projectName = project.getName();
			BudgetExecutionP budgetExecution = new BudgetExecutionP(projectName, budget);
			budgetExecutions.add(budgetExecution);
		}
		return budgetExecutions;
	}

	public List<BudgetExecution> queryBudgetByCollegeName(String collegeName) {
		List<BudgetExecution> budgetExecutions = new ArrayList<BudgetExecution>();
		List<Budget> budgets = budgetDao.queryByCollegeName(collegeName);
		for(Budget budget : budgets) {
			//获取departmentName
			int departmentid = budget.getDepartmentId();
			Department department = departmentDao.queryByDepartmentId(departmentid);
			String departmentName = department.getName();
			//获取projectName
			int projectid = budget.getProjectId();
			Project project = projectDao.queryByProjectId(projectid);
			String projectName = project.getName();
			BudgetExecution budgetExecution = new BudgetExecution(projectName,departmentName, budget);
			budgetExecutions.add(budgetExecution);
		}
		return budgetExecutions;
	}
	
	public List<BudgetExecution> queryBudgetByDC(String collegeName,String departmentName){
		List<BudgetExecution> budgetExecutions = new ArrayList<BudgetExecution>();
		Department department = departmentDao.queryByName(departmentName, collegeName);
		int departmentId = department.getIddepartment();
		List<Budget> budgets = budgetDao.queryByDC(departmentId, collegeName);
		for(Budget budget : budgets) {
		
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
	public SSTExecution updateByCollege(String collegeName, boolean auditResult) {
		try {
			//判断
			List<SecondSummaryTable> secondSummaryTables = secondSummaryTableDao.queryByCollegeName(collegeName);
			if(secondSummaryTables.isEmpty())
				throw new NoNumberException("不存在条目");
			secondSummaryTableDao.updateAuditResult2(collegeName,auditResult);
			return new SSTExecution(auditResult, UpdateStateEnum.SUCCESS, secondSummaryTables);
		} catch (NoNumberException e1) {
			throw e1;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
            throw new UpdateException("update inner error:" + e.getMessage());
		}
	}

	public FSTExecutionA updatefst(String collegeName, String departmentName, boolean auResult) {
		try {
			//学院名称+部门名称一定存在
			Department department = departmentDao.queryByName(departmentName,collegeName);
			int departmentId = department.getIddepartment();
			//判断
			FirstSummaryTable firstSummaryTable = firstSummaryTableDao.queryByDepartmentId(departmentId);
			if(firstSummaryTable == null)
				throw new NoNumberException("不存在该条目");
			firstSummaryTableDao.updateAuditResult(departmentId,auResult);
			return new FSTExecutionA(auResult, UpdateStateEnum.SUCCESS, firstSummaryTable);
		} catch (NoNumberException e1) {
			throw e1;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
            throw new UpdateException("update inner error:" + e.getMessage());
		}
	}

	public FSTExecution updateByCollegefst(String collegeName, boolean auditResult) {
		try {
			//判断
			List<FirstSummaryTable> firstSummaryTables = firstSummaryTableDao.queryByCollegeName(collegeName);
			if(firstSummaryTables.isEmpty())
				throw new NoNumberException("不存在条目");
			firstSummaryTableDao.updateAuditResult2(collegeName, auditResult);
			return new FSTExecution(auditResult, UpdateStateEnum.SUCCESS, firstSummaryTables);
		} catch (NoNumberException e1) {
			throw e1;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
            throw new UpdateException("update inner error:" + e.getMessage());
		}
	}

	public List<AcquisitionExecution> queryAcByDC(String collegeName, String departmentName) {
		List<AcquisitionExecution> acquisitionExecutions = new ArrayList<AcquisitionExecution>();
		Department department = departmentDao.queryByName(departmentName, collegeName);
		int departmentId = department.getIddepartment();
		List<AcquisitionTable> acquisitionTables = acquisitionTableDao.queryByDC(departmentId, collegeName);
		for(AcquisitionTable acquisitionTable : acquisitionTables) {
			//获取projectName
			int projectid = acquisitionTable.getProjectId();
			Project project = projectDao.queryByProjectId(projectid);
			String projectName = project.getName();
			AcquisitionExecution acquisitionExecution = new AcquisitionExecution(projectName,departmentName, acquisitionTable);
			acquisitionExecutions.add(acquisitionExecution);
		}
		return acquisitionExecutions;
	}


}
