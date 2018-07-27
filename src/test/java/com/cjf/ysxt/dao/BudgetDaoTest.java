package com.cjf.ysxt.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cjf.ysxt.BaseTest;
import com.cjf.ysxt.entity.AcquisitionTable;
import com.cjf.ysxt.entity.Budget;
import com.cjf.ysxt.dao.BudgetDao;
public class BudgetDaoTest extends BaseTest{

	@Autowired
	private BudgetDao budgetDao;

	@Test
	public void testQueryById() throws Exception {
		System.out.println("测试testQueryById");
		int id = 5;
		Budget budget = budgetDao.queryByBudgetId(id);
		System.out.println(budget);
	}
	
	@Test
	public void testQueryAll() throws Exception {
		System.out.println("测试testQueryAll");
		List<Budget> budgets = budgetDao.queryAll(0, 4);
		for (Budget budget : budgets) {
			System.out.println(budget);
		}
	}
	
	@Test
	public void testQueryBadStatus() throws Exception {
		System.out.println("测试testQueryBadStatus");
		String name = "软件学院";
		boolean auditResult = false;
		List<Budget> budgets = budgetDao.queryBadStatus(name, auditResult);
		for (Budget budget : budgets) {
			System.out.println(budget);
		}
	}
	
	@Test
	public void testQueryByCollegeName() throws Exception {
		System.out.println("测试testQueryByCollegeName");
		String name = "软件学院";
		List<Budget> budgets = budgetDao.queryByCollegeName(name);
		for (Budget budget : budgets) {
			System.out.println(budget);
		}
	}
	
	@Test
	public void testQueryByDepartmentId() throws Exception {
		System.out.println("测试testQueryByDepartmentId");
		int id = 3;
		List<Budget> budgets = budgetDao.queryByDepartmentId(id);
		for (Budget budget : budgets) {
			System.out.println(budget);
		}
	}
	
	@Test
	public void testQueryByProjectId() throws Exception {
		System.out.println("测试testQueryByProjectId");
		int id = 4;
		List<Budget> budgets = budgetDao.queryByProjectId(id);
		for (Budget budget : budgets) {
			System.out.println(budget);
		}
	}
	
	@Test
	public void testQueryByPDC() throws Exception {
		System.out.println("测试testQueryByPDC");
		int projectId = 1;
		int departmentId = 17;
		String collegeName="计算机学�?";
		Budget budget = budgetDao.queryByPDC(projectId, departmentId, collegeName);
		System.out.println(budget);
		
	}
	
	@Test
	public void testQueryByDC() throws Exception {
		System.out.println("测试testQueryByDC");
		int departmentId = 1;
		String collegeName="软件学院";
		List<Budget> budgets = budgetDao.queryByDC(departmentId, collegeName);
		for (Budget budget: budgets) {
			System.out.println(budget);
		}
		
	}
	
	@Test
    public void testInsertBudget() throws Exception {
		
		Budget budget = new Budget("软件学院",8,3,153222.151f,"测试","20180713",false);
        int insert = budgetDao.insertBudget(budget);
        System.out.println("insert=" + insert);
    }
	
	@Test
    public void testUpdate() throws Exception {
	boolean auditResult = false;
		int update = budgetDao.updateAuditResult(2, 2, "软件学院", auditResult);
        System.out.println("update=" + update);
    }
	
	@Test
	public void testquerySummaryBudget() throws Exception {
		System.out.println("testquerySummaryBudget");
		String collegeName = "软件学院";
		List<Budget> budgets = budgetDao.querySummaryBudget(collegeName);
		for(Budget budget: budgets) {
			System.out.println(budget);
		}
	}
	
	@Test
	public void queryProjectBudget() throws Exception {
		System.out.println("queryProjectBudget");
		List<Budget> budgets = budgetDao.queryProjectBudget();
		for(Budget budget: budgets) {
			System.out.println(budget);
		}
	}
}
