package com.cjf.ysxt.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cjf.ysxt.BaseTest;
import com.cjf.ysxt.entity.DepartmentControlnum;
import com.cjf.ysxt.dao.DepartmentControlnumDao;
public class DepartmentControlnumDaoTest extends BaseTest{

	@Autowired
	private DepartmentControlnumDao departmentControlnumDao;

	
	@Test
	public void testQueryAll() throws Exception {
		System.out.println("测试testQueryAll");
		List<DepartmentControlnum> departmentControlnums = departmentControlnumDao.queryAll(0, 4);
		for (DepartmentControlnum departmentControlnum : departmentControlnums) {
			System.out.println(departmentControlnum);
		}
	}

	
	@Test
	public void testQueryByCollegeName() throws Exception {
		System.out.println("测试testQueryByCollegeName");
		String name = "软件学院";
		List<DepartmentControlnum> departmentControlnums = departmentControlnumDao.queryByCollegeName(name);
		for (DepartmentControlnum departmentControlnum : departmentControlnums) {
			System.out.println(departmentControlnum);
		}
	}
	
	@Test
	public void testQueryByDepartmentId() throws Exception {
		System.out.println("测试testQueryByDepartmentId");
		int id = 3;
		DepartmentControlnum departmentControlnum = departmentControlnumDao.queryByDepartmentId(id);
		System.out.println(departmentControlnum);
		
	}
	
	@Test
    public void testInsertDepartmentControlnum() throws Exception {
		//测试时需要换�?个测试用�?
		DepartmentControlnum departmentControlnum = new DepartmentControlnum("微电子学院",1666.72f,"测试");
        int insert = departmentControlnumDao.insertDepartmentControlnum(departmentControlnum);
        System.out.println("insert=" + insert);
    }
}
