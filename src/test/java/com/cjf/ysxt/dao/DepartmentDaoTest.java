package com.cjf.ysxt.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cjf.ysxt.BaseTest;
import com.cjf.ysxt.entity.Department;
import com.cjf.ysxt.dao.DepartmentDao;
public class DepartmentDaoTest extends BaseTest{

	@Autowired
	private DepartmentDao departmentDao;

	@Test
	public void testQueryById() throws Exception {
		System.out.println("测试testQueryById");
		int id = 1;
		Department department = departmentDao.queryByDepartmentId(id);
		System.out.println(department);
	}
	
	@Test
	public void testQueryAll() throws Exception {
		System.out.println("测试testQueryAll");
		List<Department> departments = departmentDao.queryAll(0, 2);
		for (Department department : departments) {
			System.out.println(department);
		}
	}
	
	@Test
	public void testQueryByDepartmentName() throws Exception {
		System.out.println("测试testQueryByDepartmentName");
		String deptname = "图书资料室";
		String collegeName= "软件学院";
		Department department = departmentDao.queryByName(deptname,collegeName);
		int aaa = department.getIddepartment();
		System.out.println("aaa" + aaa);
		
	}
	
	@Test
	public void testQueryByCollegeName() throws Exception {
		System.out.println("测试testQueryByCollegeName");
		String name = "数学学院";
		List<Department> departments = departmentDao.queryByCollegeName(name);
		for (Department department : departments) {
			System.out.println(department);
		}
	}
	
	
	@Test
    public void testInsertDepartment() throws Exception {
		
		Department department = new Department(25,"测试","test","test","软件学院",false);
        int insert = departmentDao.insertDepartment(department);
        System.out.println("insert=" + insert);
    }
	
	@Test
    public void testDeleteDepartment() throws Exception {
		System.out.println("testdeleteDepartment");
        String departmentName = "测试";
		String collegeName = "软件学院";
		int delete = departmentDao.deleteDepartment(departmentName, collegeName);
        System.out.println("delete=" + delete);
    }
}
