package com.cjf.ysxt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cjf.ysxt.entity.Project;



public interface ProjectDao {

	/**
	 * 查询�?有Project
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<Project> queryAll(@Param("offset") int offset, @Param("limit") int limit);
	
	
	/**
	 * 根据项目id查询project
	 * @param id
	 * @return
	 */
	Project queryByProjectId(int id);
	
	/**
	 * 根据项目名称查询project
	 * @param name
	 * @return
	 */
	Project queryByProjectName(String name);
	
	/**
	 * 插入Project
	 * @param record
	 * @return
	 */
	int insertProject(Project record);
}
