package com.cjf.ysxt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cjf.ysxt.entity.DepartmentControlnum;

public interface DepartmentControlnumDao {

	/**
	 * 查询�?有DepartmentControlnum
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<DepartmentControlnum> queryAll(@Param("offset") int offset, @Param("limit") int limit);
	
	/**
	 * 根据学院名称查询DepartmentControlnum
	 * @param name
	 * @return
	 */
	List<DepartmentControlnum> queryByCollegeName(String name);
	
	/**
	 * 根据部门id查询DepartmentControlnum
	 * @param name
	 * @return
	 */
	DepartmentControlnum queryByDepartmentId(int id);
	
	/**
	 * 插入DepartmentControlnum
	 * @param record
	 * @return
	 */
	int insertDepartmentControlnum(DepartmentControlnum record);
}
