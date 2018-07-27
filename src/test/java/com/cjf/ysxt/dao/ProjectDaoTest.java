package com.cjf.ysxt.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cjf.ysxt.BaseTest;
import com.cjf.ysxt.entity.Project;
import com.cjf.ysxt.dao.ProjectDao;
public class ProjectDaoTest extends BaseTest{

	@Autowired
	private ProjectDao projectDao;

	@Test
	public void testQueryById() throws Exception {
		System.out.println("测试testQueryById");
		int id = 1;
		Project project = projectDao.queryByProjectId(id);
		System.out.println(project);
	}
	
	@Test
	public void testQueryAll() throws Exception {
		System.out.println("测试testQueryAll");
		List<Project> projects = projectDao.queryAll(0, 4);
		for (Project project : projects) {
			System.out.println(project);
		}
	}
	
	
	
	@Test
	public void testQueryByProjectName() throws Exception {
		System.out.println("测试testQueryByCollegeName");
		String name = "保险费";
		Project project = projectDao.queryByProjectName(name);
		int bbb = project.getIdproject();
		System.out.println("bbb"+bbb);
		
	}
	
	
	@Test
    public void testInsertProject() throws Exception {
		//测试时需要换�?个测试用�?
		Project project = new Project("test");
        int insert = projectDao.insertProject(project);
        System.out.println("insert=" + insert);
    }
}
