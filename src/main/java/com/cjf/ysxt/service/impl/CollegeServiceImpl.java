package com.cjf.ysxt.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;


import com.cjf.ysxt.dao.AcquisitionTableDao;
import com.cjf.ysxt.dao.BudgetDao;
import com.cjf.ysxt.dao.CollegeControlnumDao;
import com.cjf.ysxt.dao.DepartmentControlnumDao;
import com.cjf.ysxt.dao.DepartmentDao;
import com.cjf.ysxt.dao.FirstSummaryTableDao;
import com.cjf.ysxt.dao.ProjectDao;
import com.cjf.ysxt.dao.SecondSummaryTableDao;
import com.cjf.ysxt.dto.AcquisitionExecution;
import com.cjf.ysxt.dto.BudgetExecution;
import com.cjf.ysxt.dto.DCNExecution;
import com.cjf.ysxt.dto.FSTExecution;
import com.cjf.ysxt.dto.FSTExecutionD;
import com.cjf.ysxt.dto.SSTExecution;
import com.cjf.ysxt.dto.SSTExecutionD;
import com.cjf.ysxt.entity.AcquisitionTable;
import com.cjf.ysxt.entity.Budget;
import com.cjf.ysxt.entity.CollegeControlnum;
import com.cjf.ysxt.entity.Department;
import com.cjf.ysxt.entity.DepartmentControlnum;
import com.cjf.ysxt.entity.FirstSummaryTable;
import com.cjf.ysxt.entity.Project;
import com.cjf.ysxt.entity.SecondSummaryTable;
import com.cjf.ysxt.enums.InsertStateEnum;
import com.cjf.ysxt.enums.UpdateStateEnum;
import com.cjf.ysxt.exception.InsertException;
import com.cjf.ysxt.exception.NoNumberException;
import com.cjf.ysxt.exception.RepeatException;
import com.cjf.ysxt.exception.UpdateException;
import com.cjf.ysxt.service.CollegeService;

@Service
public class CollegeServiceImpl implements CollegeService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private ProjectDao projectDao;
	@Autowired
	private AcquisitionTableDao acquisitionTableDao;
	@Autowired
	private FirstSummaryTableDao firstSummaryTableDao;
	@Autowired
	private SecondSummaryTableDao secondSummaryTableDao;
	@Autowired
	private CollegeControlnumDao collegeControlnumDao;
	@Autowired
	private DepartmentControlnumDao departmentControlnumDao;
	@Autowired
	private BudgetDao budgetDao;
	
	
	public List<AcquisitionExecution> getByStatus(String collegeName,boolean auditResult) {
		List<AcquisitionExecution> acquisitionExecutions = new ArrayList<AcquisitionExecution>(); 
		List<AcquisitionTable> acquisitionTables = acquisitionTableDao.queryBadStatus(collegeName, auditResult);
		for(AcquisitionTable acquisitionTable : acquisitionTables) {
			//获取departmentName
			int departmentid = acquisitionTable.getDepartmentId();
			Department department = departmentDao.queryByDepartmentId(departmentid);
			String departmentName = department.getName();
			//获取projectName
			int projectid = acquisitionTable.getProjectId();
			Project project = projectDao.queryByProjectId(projectid);
			String projectName = project.getName();
			AcquisitionExecution acquisitionExecution = new AcquisitionExecution(projectName,departmentName, acquisitionTable);
			acquisitionExecutions.add(acquisitionExecution);
		}
		return acquisitionExecutions;
	}
	
	public List<BudgetExecution> getBudgetByStatus(String collegeName,boolean auditResult) {
		List<BudgetExecution> budgetExecutions = new ArrayList<BudgetExecution>(); 
		List<Budget> budgets = budgetDao.queryBadStatus(collegeName, auditResult);
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
	
	@Transactional
	public AcquisitionExecution update(String collegeName, String departmentName, String projectName,
			boolean auditResult) {
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
				throw new NoNumberException("不存在该条目");
			acquisitionTableDao.updateAuditResult(projectId, departmentId, collegeName, auditResult);
			return new AcquisitionExecution(auditResult, UpdateStateEnum.SUCCESS, acquisitionTable);
		} catch (NoNumberException e1) {
			throw e1;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
            throw new UpdateException("insert inner error:" + e.getMessage());
		}
	}

	@Transactional
	public FSTExecution insertFST(String collegeName, String notes, String auditTime) {
		try {
			boolean flag;
			int departmentId;
			float summaryBudget = 0;
			List<FirstSummaryTable> records = new ArrayList<FirstSummaryTable>();
			List<AcquisitionTable> acquisitionTables2 = null;
			//获取groupby
			List<AcquisitionTable> acquisitionTables = acquisitionTableDao.querySummaryBudget(collegeName);
			//读取某一个学院的所有部门
			List<Department> departments = departmentDao.queryByCollegeName(collegeName);
			//对每一个部门，查询他在acq表中的状态是否都为true，如果是，则FLAG = true
			for(Department department: departments) {
				acquisitionTables2 = null;
				departmentId = department.getIddepartment();
				FirstSummaryTable firstSummaryTable = firstSummaryTableDao.queryByDepartmentId(departmentId);
				if(firstSummaryTable==null) {
				//读取该部门对应acq表的所有条目
					acquisitionTables2 = acquisitionTableDao.queryByDepartmentId(departmentId);
					if(!acquisitionTables2.isEmpty()) {
						flag = true;
						System.out.println("flag1 = " + flag);
						for (AcquisitionTable acquisitionTable : acquisitionTables2) {
							System.out.println("条目=" + acquisitionTable.getDepartmentId()+acquisitionTable.getNotes());
							System.out.println("flag2 = " + flag);
							if(!acquisitionTable.isAuditResult()) {
								System.out.println("status = " + acquisitionTable.isAuditResult());
								flag = false;
							}
						}
						System.out.println("flag = "+ flag);
						if(flag == true) {
							for(AcquisitionTable acquisitionTable : acquisitionTables) {
								if(acquisitionTable.getDepartmentId() == departmentId)
									summaryBudget = acquisitionTable.getBudgetProposal();
							}
							//插入
							System.out.println("aaaaa");
							FirstSummaryTable record = new FirstSummaryTable(departmentId, collegeName, summaryBudget, notes, auditTime,
									false);
							firstSummaryTableDao.insertFirstSummaryTable(record);
							records.add(record);
						}
					}	
				}
			}
			return new FSTExecution(InsertStateEnum.SUCCESS, records);
		} catch (RepeatException e1) {
			throw e1;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
            throw new InsertException("insert inner error:" + e.getMessage());
		}
	}

	public List<FSTExecutionD> queryALL(String collegeName) {
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
	
	public List<SSTExecutionD> queryALL2(String collegeName) {
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

	public CollegeControlnum queryByCollegeName(String collegeName) {
		CollegeControlnum collegeControlnum = collegeControlnumDao.queryByCollegeName(collegeName);
		return collegeControlnum;
	}
	@Transactional
	public DCNExecution insertDCN(String collegeName, String departmentName,float budgetControlNum, String notes) {
		try {
			Department department = departmentDao.queryByName(departmentName, collegeName);
			int departmentId = department.getIddepartment();
			DepartmentControlnum repeat = departmentControlnumDao.queryByDepartmentId(departmentId);
			if(repeat != null)
				throw new RepeatException("已经存在该项目");
			DepartmentControlnum record = new DepartmentControlnum(departmentId, collegeName, budgetControlNum, notes);
			departmentControlnumDao.insertDepartmentControlnum(record);
			return new DCNExecution(InsertStateEnum.SUCCESS, record);
		} catch (RepeatException e1) {
			throw e1;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
            throw new InsertException("insert inner error:" + e.getMessage());
		}
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
	
	public List<AcquisitionExecution> queryAcByDC(String collegeName, String departmentName) {
		List<AcquisitionExecution> acquisitionExecutions = new ArrayList<AcquisitionExecution>();
		Department department = departmentDao.queryByName(departmentName, collegeName);
		int departmentId = department.getIddepartment();
		List<AcquisitionTable> acquisitionTables = acquisitionTableDao.queryByDC(departmentId, collegeName);
		for(AcquisitionTable acquisitionTable : acquisitionTables) {
			int projectid = acquisitionTable.getProjectId();
			Project project = projectDao.queryByProjectId(projectid);
			String projectName = project.getName();
			
			AcquisitionExecution acquisitionExecution = new AcquisitionExecution(projectName, departmentName, acquisitionTable);
			acquisitionExecutions.add(acquisitionExecution);
		}
		return acquisitionExecutions;
	}
	
	
	
	@Transactional
	public BudgetExecution updateBudgetStatus(String collegeName, String departmentName, String projectName,
			boolean auditResult) {
		try {
			//学院名称+部门名称一定存在
			Department department = departmentDao.queryByName(departmentName,collegeName);
			int departmentId = department.getIddepartment();
			//项目id一定存在
			Project project = projectDao.queryByProjectName(projectName);
			int projectId = project.getIdproject();
			//判断
			Budget budget = budgetDao.queryByPDC(projectId, departmentId, collegeName);
			if(budget == null)
				throw new NoNumberException("不存在该条目");
			budgetDao.updateAuditResult(projectId, departmentId, collegeName, auditResult);
			return new BudgetExecution(auditResult, UpdateStateEnum.SUCCESS, budget);
		} catch (NoNumberException e1) {
			throw e1;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
            throw new UpdateException("update inner error:" + e.getMessage());
		}
	}
	@Transactional
	public SSTExecution insertSST(String collegeName, String notes, String auditTime) {
		try {
			boolean flag;
			int departmentId;
			float summaryBudget = 0;
			List<SecondSummaryTable> records = new ArrayList<SecondSummaryTable>();
			List<Budget> budgets2 = null;
			//获取groupby
			List<Budget> budgets = budgetDao.querySummaryBudget(collegeName);
			//读取某一个学院的所有部门
			List<Department> departments = departmentDao.queryByCollegeName(collegeName);
			//对每一个部门，查询他在acq表中的状态是否都为true，如果是，则FLAG = true
			for(Department department: departments) {
				budgets2 = null;
				departmentId = department.getIddepartment();
				//判断是否插入重复
				SecondSummaryTable SecondSummaryTable = secondSummaryTableDao.queryByDepartmentId(departmentId);
				if(SecondSummaryTable==null) {
				//读取该部门对应budget表的所有条目
					budgets2 = budgetDao.queryByDepartmentId(departmentId);
					if(!budgets2.isEmpty()) {
						flag = true;
						for (Budget budget : budgets2) {
							if(!budget.isAuditResult())
								flag = false;
						}
						if(flag == true) {
							for(Budget budget : budgets) {
								if(budget.getDepartmentId() == departmentId)
									summaryBudget = budget.getBudgetAmount();
							}
							//插入
							SecondSummaryTable record = new SecondSummaryTable(collegeName,departmentId, 
									summaryBudget, notes, auditTime, false);
							secondSummaryTableDao.insertSecondSummaryTable(record);
							records.add(record);
						}
					}	
				}	
			}
			return new SSTExecution(InsertStateEnum.SUCCESS, records);
		} catch (RepeatException e1) {
			throw e1;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
            throw new InsertException("insert inner error:" + e.getMessage());
		}
	}

	

	

	
	}



