package com.cjf.ysxt.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjf.ysxt.dto.BudgetExecution;
import com.cjf.ysxt.dto.BudgetExecutionP;
import com.cjf.ysxt.entity.Department;
import com.cjf.ysxt.service.AdminService;
import com.cjf.ysxt.service.CollegeService;
import com.cjf.ysxt.service.SchoolService;

@Service
public class ExcelService {
	
	@Autowired
	private SchoolService schoolService;
	@Autowired
	private CollegeService collegeService;
	@Autowired
	private AdminService adminService;
	
	public Map<String, Map<String,Float>> selectAllInfo() {
		List<BudgetExecutionP> budgetExecutions = schoolService.queryProjectBudget();
		Map<String, Map<String,Float>> map = new HashMap<String, Map<String,Float>>();
		for (BudgetExecutionP budgetExecution : budgetExecutions) {
			String collegeName = budgetExecution.getBudget().getCollegeName();
			System.out.println("collegeName" + collegeName);
			map.put(collegeName, new HashMap<String, Float>());
		}
		
		for (BudgetExecutionP budgetExecution : budgetExecutions) {
			System.out.println(budgetExecution);
			String collegeName = budgetExecution.getBudget().getCollegeName();
			String projectName = budgetExecution.getProjectName();
			float summary_budget = budgetExecution.getBudget().getBudgetAmount();
			map.get(collegeName).put(projectName, summary_budget);
		}
        
        return map;
    }
	
	public Map<String, Map<String,Float>> selectAllInfo2(String collegeName) {
		List<Department> departments = adminService.queryDepartmentByCollegeName(collegeName);
		Map<String, Map<String,Float>> map = new HashMap<String, Map<String,Float>>();
		for (Department department : departments) {
			String departmentName = department.getName();
			map.put(departmentName, new HashMap<String, Float>());
		}
		List<BudgetExecution> budgetExecutions = collegeService.getBudgetByStatus(collegeName, true);
		System.out.println("bvvvvvvvvv" + budgetExecutions);
		for (BudgetExecution budgetExecution : budgetExecutions) {
			String departmentName = budgetExecution.getDepartmentName();
			String projectName = budgetExecution.getProjectName();
			float summary_budget = budgetExecution.getBudget().getBudgetAmount();
			map.get(departmentName).put(projectName, summary_budget);
		}
        
        return map;
    }
}
