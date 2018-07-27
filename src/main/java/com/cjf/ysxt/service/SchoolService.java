package com.cjf.ysxt.service;

import java.util.List;

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
import com.cjf.ysxt.entity.FirstSummaryTable;
import com.cjf.ysxt.entity.SecondSummaryTable;

public interface SchoolService {

	/**
	 * 查询所有学院的预算汇总
	 * @return
	 */
	List<FirstSummaryTable> queryCollegeBudget();
	
	/**
	 * 查询所有学院的预算汇总2
	 * @return
	 */
	List<SecondSummaryTable> queryCollegeBudget2();
	
	/**
	 * 查询某学院每一个部门对应的预算
	 * @param collegeName
	 * @return
	 */
	List<FSTExecutionD> queryDepartmentBudget(String collegeName);
	
	/**
	 * 查询某学院每一个部门对应的预算2
	 * @param collegeName
	 * @return
	 */
	List<SSTExecutionD> queryDepartmentBudget2(String collegeName);
	
	/**
	 * 插入collegeControlnum
	 * @param collegeName
	 * @param budgetControlNum
	 * @param notes
	 * @return
	 */
	CCNExecution insert(String collegeName, float budgetControlNum, String notes);
	
	/**
	 * 修改second_summary中的audit_result
	 * @param collegeName
	 * @param departmentId
	 * @param Status
	 * @return
	 */
	SSTExecution update(String collegeName,String departmentName,boolean Status);
	
	/**
	 * 修改first_summary中的audit_result
	 * @param collegeName
	 * @param departmentName
	 * @param Status
	 * @return
	 */
	FSTExecutionA updatefst(String collegeName,String departmentName,boolean auditResult);
	
	/**
	 * 修改second_summary中的某一个学院的所有auditResult
	 * @param collegeName
	 * @param Status
	 * @return
	 */
	SSTExecution updateByCollege(String collegeName,boolean Status);
	
	/**
	 * 修改first_summary中的某一个学院的所有auditResult
	 * @param collegeName
	 * @param Status
	 * @return
	 */
	FSTExecution updateByCollegefst(String collegeName,boolean Status);
	
	/**
	 * query budget from budget group by college and project
	 * @return
	 */
	List<BudgetExecutionP> queryProjectBudget();
	
	/**
	 * query all budget by collegeName
	 * @param collegeName
	 * @return
	 */
	List<BudgetExecution> queryBudgetByCollegeName(String collegeName);
	/**
	 * query list of Budget By DC
	 * @param collegeName
	 * @param departmentName
	 * @return
	 */
	List<BudgetExecution> queryBudgetByDC(String collegeName,String departmentName);
	
	/**
	 * query list of Ac By DC
	 * @param collegeName
	 * @param departmentName
	 * @return
	 */
	List<AcquisitionExecution> queryAcByDC(String collegeName,String departmentName);
}
