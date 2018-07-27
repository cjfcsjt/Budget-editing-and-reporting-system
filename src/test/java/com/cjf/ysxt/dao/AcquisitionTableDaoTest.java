package com.cjf.ysxt.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cjf.ysxt.BaseTest;
import com.cjf.ysxt.entity.AcquisitionTable;
public class AcquisitionTableDaoTest extends BaseTest{

	@Autowired
	private AcquisitionTableDao acquisitionTableDao;

	@Test
	public void testQueryById() throws Exception {
		System.out.println("测试testQueryById");
		int id = 1;
		AcquisitionTable acquisitionTable = acquisitionTableDao.queryByAcquisitionTableId(id);
		System.out.println(acquisitionTable);
	}
	
	@Test
	public void testQueryAll() throws Exception {
		System.out.println("测试testQueryAll");
		List<AcquisitionTable> acquisitionTables = acquisitionTableDao.queryAll(0, 4);
		for (AcquisitionTable acquisitionTable : acquisitionTables) {
			System.out.println(acquisitionTable);
		}
	}
	
	@Test
	public void testquerySummaryBudget() throws Exception {
		System.out.println("测试querySummaryBudget");
		String collegeName="软件学院";
		List<AcquisitionTable> acquisitionTables = acquisitionTableDao.querySummaryBudget(collegeName);
		for (AcquisitionTable acquisitionTable : acquisitionTables) {
			System.out.println(acquisitionTable);
		}
	}

	
	@Test
	public void testQueryByCollegeName() throws Exception {
		System.out.println("测试testQueryByCollegeName");
		String name = "软件学院";
		List<AcquisitionTable> acquisitionTables = acquisitionTableDao.queryByCollegeName(name);
		for (AcquisitionTable acquisitionTable : acquisitionTables) {
			System.out.println(acquisitionTable);
		}
	}
	
	@Test
	public void testQueryBadStatus() throws Exception {
		System.out.println("测试testQueryBadStatus");
		String name = "软件学院";
		boolean auditResult = false;
		List<AcquisitionTable> acquisitionTables = acquisitionTableDao.queryBadStatus(name, auditResult);
		for (AcquisitionTable acquisitionTable : acquisitionTables) {
			System.out.println(acquisitionTable);
		}
	}
	
	@Test
	public void testQueryByDepartmentId() throws Exception {
		System.out.println("测试testQueryByDepartmentId");
		int id = 3;
		List<AcquisitionTable> acquisitionTables = acquisitionTableDao.queryByDepartmentId(id);
		for (AcquisitionTable acquisitionTable : acquisitionTables) {
			System.out.println(acquisitionTable);
		}
	}
	
	@Test
	public void testQueryByProjectId() throws Exception {
		System.out.println("测试testQueryByProjectId");
		int id = 1;
		List<AcquisitionTable> acquisitionTables = acquisitionTableDao.queryByProjectId(id);
		for (AcquisitionTable acquisitionTable : acquisitionTables) {
			System.out.println(acquisitionTable);
		}
	}
	
	@Test
	public void testQueryByPDC() throws Exception {
		System.out.println("测试testQueryByPDC");
		int projectId = 2;
		int departmentId = 17;
		String collegeName="计算机学�?";
		AcquisitionTable acquisitionTable = acquisitionTableDao.queryByPDC(projectId, departmentId, collegeName);
		System.out.println(acquisitionTable);
		
	}
	
	@Test
	public void testQueryByDC() throws Exception {
		System.out.println("测试testQueryByPDC");
		int departmentId = 17;
		String collegeName="计算机学�?";
		List<AcquisitionTable> acquisitionTables = acquisitionTableDao.queryByDC(departmentId, collegeName);
		for (AcquisitionTable acquisitionTable : acquisitionTables) {
			System.out.println(acquisitionTable);
		}
		
	}
	
	@Test
    public void testInsertAcquisitionTable() throws Exception {
		
		AcquisitionTable acquisitionTable = new AcquisitionTable("软件学院",1,11,153222.151f,"测试","20180718",false);
        int insert = acquisitionTableDao.insertAcquisitionTable(acquisitionTable);
        System.out.println("insert=" + insert);
    }
	
	@Test
    public void testUpdateAcquisitionTable() throws Exception {
		
		float budget_proposal = 444.11f;
		int update = acquisitionTableDao.updateAcquisitionTable(11, 1, "软件学院", budget_proposal);
        System.out.println("update=" + update);
    }
	
	@Test
    public void testUpdate() throws Exception {
	boolean auditResult = true;
		int update = acquisitionTableDao.updateAuditResult(11, 1, "软件学院", auditResult);
        System.out.println("update=" + update);
    }
}
