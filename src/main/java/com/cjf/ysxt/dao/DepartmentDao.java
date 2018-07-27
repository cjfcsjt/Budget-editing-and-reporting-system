package com.cjf.ysxt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cjf.ysxt.entity.Department;



public interface DepartmentDao {

	/**
	 * 查询�?有Department
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<Department> queryAll(@Param("offset") int offset, @Param("limit") int limit);
	
	/**
	 * 根据学院名称+部门名称查询Department
	 * @param name
	 * @return
	 */
	Department queryByName(@Param("departmentName") String departmentName, 
			@Param("collegeName") String collegeName);
	
	/**
	 * 根据部门id查询Department
	 * @param id
	 * @return
	 */
	Department queryByDepartmentId(int id);
	
	/**
	 * 根据学院名称查询department
	 * @param name
	 * @return
	 */
	List<Department> queryByCollegeName(String name);
	
	/**
	 * 插入Department
	 * @param record
	 * @return
	 */
	int insertDepartment(Department record);
	
	int deleteDepartment(@Param("departmentName") String departmentName, 
			@Param("collegeName") String collegeName);
}
