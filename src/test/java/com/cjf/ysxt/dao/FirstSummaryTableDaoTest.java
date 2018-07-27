package com.cjf.ysxt.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cjf.ysxt.BaseTest;
import com.cjf.ysxt.entity.AcquisitionTable;
import com.cjf.ysxt.entity.FirstSummaryTable;
import com.cjf.ysxt.dao.FirstSummaryTableDao;
public class FirstSummaryTableDaoTest extends BaseTest{

	@Autowired
	private FirstSummaryTableDao firstSummaryTableDao;

	
	//@Test
	public void testQueryAll() throws Exception {
		System.out.println("测试testQueryAll");
		List<FirstSummaryTable> firstSummaryTables = firstSummaryTableDao.queryAll(0, 3);
		for (FirstSummaryTable firstSummaryTable : firstSummaryTables) {
			System.out.println(firstSummaryTable);
		}
	}
	
	//@Test
	public void testQueyCollegeBudget() throws Exception {
		System.out.println("测试testQueyCollegeBudget");
		List<FirstSummaryTable> firstSummaryTables = firstSummaryTableDao.queryCollegeBudget();
		for (FirstSummaryTable firstSummaryTable : firstSummaryTables) {
			System.out.println(firstSummaryTable);
		}
	}
	
	
	
	//@Test
	public void testQueryByDC() throws Exception {
		System.out.println("测试testQueryByPDC");
		int departmentId = 2;
		String collegeName="软件学院";
		FirstSummaryTable firstSummaryTable= firstSummaryTableDao.queryByDC(departmentId, collegeName);
		System.out.println(firstSummaryTable);
		
		
	}
	
	//@Test
	public void testQueryByCollegeName() throws Exception {
		System.out.println("测试testQueryByCollegeName");
		String name = "软件学院";
		List<FirstSummaryTable> firstSummaryTables = firstSummaryTableDao.queryByCollegeName(name);
		for (FirstSummaryTable firstSummaryTable : firstSummaryTables) {
			System.out.println(firstSummaryTable);
		}
	}
	
	//@Test
	public void testQueryByDepartmentId() throws Exception {
		System.out.println("测试testQueryByDepartmentId");
		int id = 3;
		FirstSummaryTable firstSummaryTable = firstSummaryTableDao.queryByDepartmentId(id);
		System.out.println(firstSummaryTable);
		
	}
	
	//@Test
    public void testInsertFirstSummaryTable() throws Exception {
		
		FirstSummaryTable firstSummaryTable = new FirstSummaryTable(8,"软件学院",122111.22f,"测试","20180713",false);
        int insert = firstSummaryTableDao.insertFirstSummaryTable(firstSummaryTable);
        System.out.println("insert=" + insert);
    }
	
	@Test
	public void testupdateStatus() throws Exception {
		System.out.println("testupdateStatus");
		int departmentId = 1;
		int auditResult = 0;
		int update = firstSummaryTableDao.updateAuditResult(departmentId, auditResult);
		System.out.println("update" + update);
	}
	
	//@Test
	public void testupdateStatus2() throws Exception {
		System.out.println("testupdateStatus2");
		String collegeName = "软件学院";
		boolean auditResult = false;
		int update = firstSummaryTableDao.updateAuditResult2(collegeName, auditResult);
		System.out.println("update" + update);
	}
}
