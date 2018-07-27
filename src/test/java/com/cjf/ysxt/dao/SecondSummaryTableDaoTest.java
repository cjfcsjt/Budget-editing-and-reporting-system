package com.cjf.ysxt.dao;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cjf.ysxt.BaseTest;
import com.cjf.ysxt.entity.SecondSummaryTable;

public class SecondSummaryTableDaoTest extends BaseTest{

	@Autowired
	private SecondSummaryTableDao secondSummaryTableDao;

	@Test
	public void testQueryById() throws Exception {
		System.out.println("测试testQueryById");
		int id = 1;
		SecondSummaryTable secondSummaryTable = secondSummaryTableDao.queryByDepartmentId(id);
		System.out.println(secondSummaryTable);
	}
	
	@Test
	public void testQueryAll() throws Exception {
		System.out.println("测试testQueryAll");
		List<SecondSummaryTable> secondSummaryTables = secondSummaryTableDao.queryAll(0, 5);
		for (SecondSummaryTable secondSummaryTable : secondSummaryTables) {
			System.out.println(secondSummaryTable);
		}
	}
	
	
	
	@Test
	public void testQueryByCollegeName() throws Exception {
		System.out.println("测试testQueryByCollegeName");
		String name = "软件学院";
		List<SecondSummaryTable> secondSummaryTables = secondSummaryTableDao.queryByCollegeName(name);
		for (SecondSummaryTable secondSummaryTable : secondSummaryTables) {
			System.out.println(secondSummaryTable);
		}
	}
	
	
	@Test
    public void testInsert() throws Exception {
		//测试时需要换�?个测试用�?
		System.out.println("测试testInsert");
		SecondSummaryTable secondSummaryTable = 
				new SecondSummaryTable("软件学院",9,2022222,"12222","20180713",false);
        int insert = secondSummaryTableDao.insertSecondSummaryTable(secondSummaryTable);
        System.out.println("insert=" + insert);
    }
	
	@Test
	public void testQueryBudget() throws Exception {
		System.out.println("testQueryBudget");
	List<SecondSummaryTable> secondSummaryTables = secondSummaryTableDao.queryCollegeBudget();
	for(SecondSummaryTable secondSummaryTable :secondSummaryTables) {
		System.out.println(secondSummaryTable);
		}
	}
	
	@Test
	public void testupdateStatus() throws Exception {
		System.out.println("testupdateStatus");
		int departmentId = 1;
		boolean auditResult = false;
		int update = secondSummaryTableDao.updateAuditResult(departmentId, auditResult);
		System.out.println("update" + update);
	}
	
	@Test
	public void testupdateStatus2() throws Exception {
		System.out.println("testupdateStatus2");
		String collegeName = "软件学院";
		boolean auditResult = false;
		int update = secondSummaryTableDao.updateAuditResult2(collegeName, auditResult);
		System.out.println("update" + update);
	}
}
