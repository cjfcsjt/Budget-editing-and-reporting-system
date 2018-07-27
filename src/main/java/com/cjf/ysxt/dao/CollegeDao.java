package com.cjf.ysxt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;


import com.cjf.ysxt.entity.College;


public interface CollegeDao {


	/**
	 * 根据学院名称查询College
	 * @param name
	 * @return
	 */
	College queryByCollegeName(String name);
	
	/**
	 * 查询�?有College
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<College> queryAll(@Param("offset") int offset, @Param("limit") int limit);
	
	/**
	 * 根据学院id查询College
	 * @param collegeName
	 * @return
	 */
	College queryByCollegeId(int id);
	
	
	/**
	 * 插入College
	 * @param record
	 * @return
	 */
	int insertCollege(College record);
	
	/**
	 * delete college by collegeName
	 * @param collegeName
	 * @return
	 */
	int deleteCollege(String collegeName);
}
