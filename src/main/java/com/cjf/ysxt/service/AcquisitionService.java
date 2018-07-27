package com.cjf.ysxt.service;

import java.util.List;

import com.cjf.ysxt.dto.AcquisitionExecution;
import com.cjf.ysxt.dto.BudgetExecution;
import com.cjf.ysxt.entity.AcquisitionTable;
import com.cjf.ysxt.entity.DepartmentControlnum;

public interface AcquisitionService {

	/**
	 * insert Acq
	 * @param projectName
	 * @param budgetProposal
	 * @param notes
	 * @return
	 */
	AcquisitionExecution insert(String collegeName,String departmentName,String projectName,
			String auditTime,float budgetProposal,String notes);
	
	/**
	 * query Acq by collname and deptname
	 * @param collegeName
	 * @param departmentName
	 * @return
	 */
	List<AcquisitionExecution> getByName(String collegeName, String departmentName);
	
	List<BudgetExecution> getBudgetByName(String collegeName, String departmentName);

	/**
	 * update budgetProposal
	 * @param budgetProposal
	 * @return
	 */
	AcquisitionExecution update(String collegeName,String departmentName,String projectName,float budgetProposal);
	
	/**
	 * query DepartmentControlnum by collegeName, departmentName
	 * @param collegeName
	 * @param departmentName
	 * @return
	 */
	DepartmentControlnum queryDCN(String collegeName,String departmentName);
	
	/**
	 * insert budget
	 * @param collegeName
	 * @param departmentName
	 * @param projectName
	 * @param budgetAmount
	 * @param budgetNotes
	 * @param auditTime
	 * @param auditResult
	 * @return
	 */
	BudgetExecution insertBudget(String collegeName,String departmentName,String projectName, 
			float budgetAmount,String budgetNotes,String auditTime,boolean auditResult);
	
}
