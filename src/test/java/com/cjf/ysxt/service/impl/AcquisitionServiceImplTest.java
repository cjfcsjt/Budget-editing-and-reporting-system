package com.cjf.ysxt.service.impl;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cjf.ysxt.BaseTest;
import com.cjf.ysxt.dao.ProjectDao;
import com.cjf.ysxt.service.AcquisitionService;
import com.cjf.ysxt.service.AdminService;
import com.cjf.ysxt.service.WorkerService;
import com.cjf.ysxt.dto.AcquisitionExecution;
import com.cjf.ysxt.dto.BudgetExecution;
import com.cjf.ysxt.entity.AcquisitionTable;
import com.cjf.ysxt.entity.DepartmentControlnum;
import com.cjf.ysxt.entity.Project;

public class AcquisitionServiceImplTest extends BaseTest {

	@Autowired
    private AcquisitionService acquisitionService;
	@Autowired
	private ExcelService excelService;
	@Autowired
	private ProjectDao projectDao;
	
	//@Test
    public void testInsert() throws Exception {
        
        String collegeName="软件学院";
		String departmentName = "图书资料室";
		String projectName ="差旅费";
		String auditTime ="20180718";
		float budgetProposal = 1116.25f;
		String notes= "测试";
		AcquisitionExecution execution = acquisitionService.insert(collegeName, departmentName, projectName, 
        		auditTime, budgetProposal, notes);
        System.out.println(execution);
    }
	
	//@Test
	public void testQuery() throws Exception{
		 String collegeName="软件学院";
		 String departmentName = "图书资料室";
		 List<AcquisitionExecution> acquisitionTables = acquisitionService.getByName(collegeName, departmentName);
		 for(AcquisitionExecution acquisitionTable:acquisitionTables) {
			 System.out.println(acquisitionTable);
		 }
	}
	//@Test
	public void testQuery2() throws Exception{
		 String collegeName="软件学院";
		 String departmentName = "图书资料室";
		 List<BudgetExecution> budgetExecutions = acquisitionService.getBudgetByName(collegeName, departmentName);
		 for(BudgetExecution budgetExecution:budgetExecutions) {
			 System.out.println(budgetExecution);
		 }
	}
	
	//@Test
    public void testUpdate() throws Exception {
        
        String collegeName="软件学院";
		String departmentName = "图书资料室";
		String projectName ="差旅费";
		float budgetProposal = 4444.25f;
		AcquisitionExecution execution = acquisitionService.update(collegeName, departmentName, projectName, budgetProposal);
        System.out.println(execution);
    }
	
	//@Test
	public void testqueryDCN() throws Exception {
		String collegeName = "软件学院";
		String departmentName = "图书资料室";
		DepartmentControlnum departmentControlnum= acquisitionService.queryDCN(collegeName, departmentName);
		System.out.println(departmentControlnum);
	}
	
	@Test
	public void testInsertBudget() throws Exception {
		String collegeName = "软件学院";
		String departmentName = "图书资料室";
		String projectName = "差旅费" ;
		float budgetAmount = 1699.22f;
		String budgetNotes = "test";
		String auditTime = "201810719";
		boolean auditResult = false;
		acquisitionService.insertBudget(collegeName, departmentName, projectName, 
				budgetAmount, budgetNotes, auditTime, auditResult);
	}
	
	//@Test
	public void testExcel() throws Exception{
		ArrayList<String> titles = new ArrayList<String>();
		titles.add("学院");
		//String[] titles={"学院"};
		List<Project> projects = projectDao.queryAll(0, 100);
		System.out.println(projects);
		for(Project project : projects) {
			titles.add(project.getName());
		}
		String [] title = (String[])titles.toArray();
		System.out.println(title);
	}
	
}
