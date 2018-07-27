package com.cjf.ysxt.service;

import java.util.List;

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
import com.cjf.ysxt.entity.FirstSummaryTable;

public interface CollegeService {

	/**
	 * query ac by collegeName where audit_result = false
	 * @param collegeName
	 * @param departmentName
	 * @return
	 */
	List<AcquisitionExecution> getByStatus(String collegeName,boolean auditResult);
	
	/**
	 * query budget by collegeName where audit_result = false
	 * @param collegeName
	 * @param auditResult
	 * @return
	 */
	List<BudgetExecution> getBudgetByStatus(String collegeName,boolean auditResult);
	
	/**
	 * update audit_result
	 * @param collegeName
	 * @param departmentName
	 * @param projectName
	 * @param auditResult
	 * @return
	 */
	AcquisitionExecution update(String collegeName,String departmentName,String projectName,boolean auditResult);
	
	
	
	/**
	 * insert firstsummarytable
	 * @param collegeName
	 * @param departmentName
	 * @param notes
	 * @param auditTime
	 * @return
	 */
	FSTExecution insertFST(String collegeName,
			String notes,String auditTime);
	/**
	 * insert secondsummarytable when status of all project in budget is true in one dept
	 * @param collegeName
	 * @param notes
	 * @param auditTime
	 * @return
	 */
	SSTExecution insertSST(String collegeName,
			String notes,String auditTime);
	
	/**
	 * query all first summary table
	 * @param collegeName
	 * @return
	 */
	List<FSTExecutionD> queryALL(String collegeName);
	
	List<SSTExecutionD> queryALL2(String collegeName);
	
	/**
	 * query ccn by collegeName
	 * @param collegeName
	 * @return
	 */
	CollegeControlnum queryByCollegeName(String collegeName);
	
	/**
	 * insert deptcontrolnum
	 * @param collegeName
	 * @param departmentName
	 * @param budgetControlNum
	 * @param notes
	 * @return
	 */
	DCNExecution insertDCN(String collegeName,String departmentName,float budgetControlNum, String notes);
	
	/**
	 * query all of budget by collegeName (no group)
	 * @param collegeName
	 * @return
	 */
	List<BudgetExecution> queryBudgetByCollegeName(String collegeName);
	
	/**
	 * query budget by dc (no group)
	 * @param collegeName
	 * @param departmentName
	 * @return
	 */
	List<BudgetExecution> queryBudgetByDC(String collegeName,String departmentName);
	
	List<AcquisitionExecution> queryAcByDC(String collegeName,String departmentName);
	
	/**
	 * update budget AuditResult
	 * @param collegeName
	 * @param departmentName
	 * @param projectName
	 * @param auditResult
	 * @return
	 */
	BudgetExecution updateBudgetStatus(String collegeName,String departmentName,String projectName,boolean auditResult);
}
