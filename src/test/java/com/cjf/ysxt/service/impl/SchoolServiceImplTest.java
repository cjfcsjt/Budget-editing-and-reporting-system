package com.cjf.ysxt.service.impl;

import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cjf.ysxt.BaseTest;
import com.cjf.ysxt.service.SchoolService;

import ch.qos.logback.core.net.SyslogOutputStream;

import com.cjf.ysxt.dto.AcquisitionExecution;
import com.cjf.ysxt.dto.BudgetExecution;
import com.cjf.ysxt.dto.CCNExecution;
import com.cjf.ysxt.dto.DCNExecution;
import com.cjf.ysxt.dto.FSTExecution;
import com.cjf.ysxt.dto.FSTExecutionD;
import com.cjf.ysxt.dto.SSTExecution;
import com.cjf.ysxt.entity.AcquisitionTable;
import com.cjf.ysxt.entity.Budget;
import com.cjf.ysxt.entity.CollegeControlnum;
import com.cjf.ysxt.entity.FirstSummaryTable;
import com.cjf.ysxt.entity.SecondSummaryTable;

public class SchoolServiceImplTest extends BaseTest {

	@Autowired
	private SchoolService schoolService;
	@Autowired
	private ExcelService excelService;
	
	//@Test
    public void testInsert() throws Exception {
        System.out.println("testInsert");
        String collegeName="数学学院";
		String notes= "测试";
		float budgetControlNum = 111111.5f;
		CCNExecution execution = schoolService.insert(collegeName, budgetControlNum, notes);
        System.out.println(execution);
    }
	
	//@Test
	public void queryCollegeBudget() throws Exception{
		System.out.println("queryCollegeBudget");
		 List<FirstSummaryTable> firstSummaryTables = schoolService.queryCollegeBudget();
		 for(FirstSummaryTable firstSummaryTable:firstSummaryTables) {
			 System.out.println(firstSummaryTable);
		 }
	}
	
	//@Test
    public void testqueryDepartmentBudget() throws Exception {
        System.out.println("testqueryDepartmentBudget");
        String collegeName="软件学院";
		List<FSTExecutionD> fstExecutions  = schoolService.queryDepartmentBudget(collegeName);
		System.out.println(fstExecutions);
    }
	
	//@Test
	public void testqueryCollegeBudget2() throws Exception {
		System.out.println("testqueryCollegeBudget2");
		List<SecondSummaryTable> secondSummaryTables = schoolService.queryCollegeBudget2();
		for(SecondSummaryTable secondSummaryTable:secondSummaryTables) {
			 System.out.println(secondSummaryTable);
		 }
		
	}
	
//	@Test
//	public void testqueryDepartmentBudget2() throws Exception {
//        System.out.println("testqueryDepartmentBudget2");
//        String collegeName="软件学院";
//		List<SSTExecution> secondSummaryTables  = schoolService.queryDepartmentBudget2(collegeName);
//		for(SSTExecution secondSummaryTable:secondSummaryTables) {
//			 System.out.println(secondSummaryTable);
//		 }
//    }
	
	//@Test
	public void testUpdate() throws Exception{
		System.out.println("testUpdate");
		String departmentName = "行政部门";
		String collegeName = "软件学院";
		boolean Status = true;
		SSTExecution sstExecution= schoolService.update(collegeName, departmentName, Status);
		System.out.println(sstExecution);
	}
	
	@Test
	public void testUpdatefst() throws Exception{
		System.out.println("testUpdatefst");
		String departmentName = "行政部门";
		String collegeName = "软件学院";
		boolean auditResult = false;
		FSTExecution fstExecution= schoolService.updatefst(collegeName, departmentName, auditResult);
		System.out.println(fstExecution);
	}
	
	//@Test
	public void testUpdateByCollege() throws Exception{
		System.out.println("testUpdateByCollege");
		String collegeName = "软件学院";
		boolean Status = true;
		SSTExecution sstExecution= schoolService.updateByCollege(collegeName, Status);
		System.out.println(sstExecution);
	}
	
	//@Test
	public void testUpdateByCollegefst() throws Exception{
		System.out.println("testUpdateByCollege");
		String collegeName = "软件学院";
		boolean Status = true;
		FSTExecution fstExecution= schoolService.updateByCollegefst(collegeName, Status);
		System.out.println(fstExecution);
	}
	
//	@Test
//	public void testqueryProjectBudget() throws Exception{
//		System.out.println("testqueryProjectBudget");
//		List<BudgetExecution> budgets = schoolService.queryProjectBudget();
//		for(BudgetExecution budget: budgets)
//			System.out.println(budget);
//	}
	
	@Test
	public void testqueryBudgetByCollegeName() throws Exception {
		System.out.println("testqueryBudgetByCollegeName");
		String collegeName = "软件学院";
		List<BudgetExecution> budgets =schoolService.queryBudgetByCollegeName(collegeName);
		for (BudgetExecution budget : budgets)
			System.out.println(budget.getDepartmentName());
	}
	
	@Test
	public void testqueryBudgetByDC() throws Exception {
		System.out.println("testqueryBudgetByDC");
		String collegeName = "软件学院";
		String departmentName = "图书资料室";
		List<BudgetExecution> budgets = schoolService.queryBudgetByDC(collegeName, departmentName);
		for (BudgetExecution budget : budgets)
			System.out.println(budget);
	}
	@Test
	public void testqueryAcByDC() throws Exception {
		System.out.println("testqueryBudgetByDC");
		String collegeName = "软件学院";
		String departmentName = "图书资料室";
		List<AcquisitionExecution> acquisitionTables = schoolService.queryAcByDC(collegeName, departmentName);
		for (AcquisitionExecution acquisitionTable : acquisitionTables)
			System.out.println(acquisitionTable);
	}
	
	@Test
	public void testselectAllInfo2() throws Exception {
		System.out.println("testselectAllInfo2");
		String collegeName = "软件学院";
		Map<String, Map<String,Float>> list = excelService.selectAllInfo2(collegeName);
		System.out.println(list);
	}
}
