package com.cjf.ysxt.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cjf.ysxt.BaseTest;
import com.cjf.ysxt.entity.College;
import com.cjf.ysxt.dao.CollegeDao;
public class CollegeDaoTest extends BaseTest{

	@Autowired
	private CollegeDao collegeDao;

	@Test
	public void testQueryById() throws Exception {
		System.out.println("测试testQueryById");
		int id = 2;
		College college = collegeDao.queryByCollegeId(id);
		System.out.println(college);
	}
	
	@Test
	public void testQueryAll() throws Exception {
		System.out.println("测试testQueryAll");
		List<College> colleges = collegeDao.queryAll(0, 2);
		for (College college : colleges) {
			System.out.println(college);
		}
	}
	
	
	
	@Test
	public void testQueryByCollegeName() throws Exception {
		System.out.println("测试testQueryByCollegeName");
		String name = "软件学院";
		College college = collegeDao.queryByCollegeName(name);
		System.out.println(college);
		
	}
	
	
	
	@Test
    public void testInsertCollege() throws Exception {
		College college = new College(20,"微电子学院","测试","测试",true);
        int insert = collegeDao.insertCollege(college);
        System.out.println("insert=" + insert);
    }
	
	@Test
    public void testDeleteCollege() throws Exception {
        String collegeName = "陈氏集团学院";
		int delete = collegeDao.deleteCollege(collegeName);
        System.out.println("delete=" + delete);
    }
}
