package com.cjf.ysxt.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cjf.ysxt.BaseTest;
import com.cjf.ysxt.entity.CollegeControlnum;
import com.cjf.ysxt.dao.CollegeControlnumDao;
public class CollegeControlnumDaoTest extends BaseTest{

	@Autowired
	private CollegeControlnumDao CollegeControlnumDao;


	
	@Test
	public void testQueryAll() throws Exception {
		System.out.println("测试testQueryAll");
		List<CollegeControlnum> CollegeControlnums = CollegeControlnumDao.queryAll(0, 2);
		for (CollegeControlnum CollegeControlnum : CollegeControlnums) {
			System.out.println(CollegeControlnum);
		}
	}
	
	
	
	@Test
	public void testQueryByCollegeName() throws Exception {
		System.out.println("测试testQueryByCollegeName");
		String name = "软件学院";
		CollegeControlnum CollegeControlnum = CollegeControlnumDao.queryByCollegeName(name);
		System.out.println(CollegeControlnum);
		
	}
	
	
	
	@Test
    public void testInsertCollegeControlnum() throws Exception {
		//测试时需要换�?个测试用�?
		CollegeControlnum CollegeControlnum = new CollegeControlnum("数学学院",2135.54f,"测试");
        int insert = CollegeControlnumDao.insertCollegeControlnum(CollegeControlnum);
        System.out.println("insert=" + insert);
    }
}
