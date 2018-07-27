package com.cjf.ysxt.service.impl;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cjf.ysxt.BaseTest;
import com.cjf.ysxt.service.CollegeService;
import com.cjf.ysxt.dto.AcquisitionExecution;
import com.cjf.ysxt.dto.BudgetExecution;
import com.cjf.ysxt.dto.CCNExecution;
import com.cjf.ysxt.dto.DCNExecution;
import com.cjf.ysxt.dto.FSTExecution;
import com.cjf.ysxt.dto.SSTExecution;
import com.cjf.ysxt.entity.AcquisitionTable;
import com.cjf.ysxt.entity.Budget;
import com.cjf.ysxt.entity.CollegeControlnum;
import com.cjf.ysxt.entity.FirstSummaryTable;

public class CollegeServiceImplTest extends BaseTest {

	@Autowired
	private CollegeService collegeService;
	
	@Test
    public void testInsert() throws Exception {
        System.out.println("testInsertFST");
        String collegeName="软件学院";
		String auditTime ="20180718";
		String notes= "测试111";
		FSTExecution execution = collegeService.insertFST(collegeName, notes, auditTime);
        System.out.println(execution);
    }
	
	//@Test
	public void testgetByStatus() throws Exception{
		System.out.println("testgetByStatus");
		 String collegeName="软件学院";
		 boolean auditResult = false;
		 List<AcquisitionExecution> acquisitionExecutions = collegeService.getByStatus(collegeName, auditResult);
		 for(AcquisitionExecution acquisitionExecution:acquisitionExecutions) {
			 System.out.println(acquisitionExecution);
		 }
	}
	
	//@Test
	public void testgetBudgetByStatus() throws Exception{
		System.out.println("testgetBudgetByStatus");
		 String collegeName="软件学院";
		 boolean auditResult = false;
		 List<BudgetExecution> budgets = collegeService.getBudgetByStatus(collegeName, auditResult);
		 for(BudgetExecution budget:budgets) {
			 System.out.println(budget);
		 }
	}
	
	//@Test
    public void testUpdate() throws Exception {
        
        String collegeName="软件学院";
		String departmentName = "行政部门";
		String projectName ="差旅费";
		AcquisitionExecution execution = collegeService.update(collegeName, departmentName, projectName, false);
        System.out.println(execution);
    }
	
//	@Test
//	public void testqueryAll() throws Exception{
//		String collegeName = "软件学院";
//		List<FSTExecution> firstSummaryTable= collegeService.queryALL(collegeName);
//		System.out.println(firstSummaryTable);
//	}
	
//	@Test
//	public void testqueryAll2() throws Exception{
//		String collegeName = "软件学院";
//		List<SSTExecution> firstSummaryTable= collegeService.queryALL2(collegeName);
//		System.out.println(firstSummaryTable);
//	}
	
	//@Test
	public void queryByCollegeName() throws Exception{
		String collegeName = "软件学院";
		CollegeControlnum collegeControlnum= collegeService.queryByCollegeName(collegeName);
		System.out.println(collegeControlnum);
	}
	
	//@Test
	public void queryInsert2() throws Exception{
		String collegeName = "软件学院";
		float budgetControlNum = 1999.2f;
		String departmentName="教务办公室";
		String notes="测试";
		DCNExecution dcnExecution= collegeService.insertDCN(collegeName, departmentName, 
				budgetControlNum, notes);
		System.out.println(dcnExecution);
	}
	
	//@Test
	public void testInsertSST() throws Exception {
		System.out.println("testInsertSST");
		String collegeName = "软件学院";
		String notes = "测试";
		String auditTime = "20180719";
		SSTExecution sstExecution = collegeService.insertSST(collegeName, notes, auditTime);
		System.out.println(sstExecution);
	}
	
	//@Test
	public void testqueryBudgetByCollegeName() throws Exception {
		System.out.println("testqueryBudgetByCollegeName");
		String collegeName="软件学院";
		List<BudgetExecution> budgets = collegeService.queryBudgetByCollegeName(collegeName);
		for ( BudgetExecution budget : budgets)
			System.out.println(budget);
	}
	
	//@Test
	public void testqueryBudgetByDC() throws Exception {
		System.out.println("testqueryBudgetByDC");
		String collegeName="软件学院";
		String departmentName="行政部门";
		List<BudgetExecution> budgets = collegeService.queryBudgetByDC(collegeName, departmentName);
		for ( BudgetExecution budget : budgets)
			System.out.println(budget);
	}
	
	//@Test
	public void testupdateBudgetStatus() throws Exception {
		System.out.println("testupdateBudgetStatus");
		String collegeName="软件学院";
		String departmentName="行政部门";
		String projectName="差旅费";
		boolean auditResult = true;
		BudgetExecution budgetExecution = collegeService.updateBudgetStatus(collegeName, 
				departmentName, projectName, auditResult);
		System.out.println(budgetExecution);
	}
	
	
}
