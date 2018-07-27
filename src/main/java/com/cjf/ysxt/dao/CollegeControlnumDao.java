package com.cjf.ysxt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cjf.ysxt.entity.CollegeControlnum;


public interface CollegeControlnumDao {

	
	
	/**
	 * 查询�?有CollegeControlnum
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<CollegeControlnum> queryAll(@Param("offset") int offset, @Param("limit") int limit);
	
	/**
	 * 根据学院名称CollegeControlnum
	 * @param collegeName
	 * @return
	 */
	CollegeControlnum queryByCollegeName(String collegeName);
	
	
	
	/**
	 * 插入�?个CollegeControlnum
	 * @param record
	 * @return
	 */
	int insertCollegeControlnum(CollegeControlnum record);
}
