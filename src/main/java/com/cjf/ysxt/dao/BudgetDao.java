package com.cjf.ysxt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cjf.ysxt.entity.AcquisitionTable;
import com.cjf.ysxt.entity.Budget;

public interface BudgetDao {
	
	/**
	 * query budget by collegeName where audit_result = ?
	 * @param collegeName
	 * @param auditResult
	 * @return
	 */
	List<Budget> queryBadStatus( @Param("collegeName") String collegeName,
			@Param("auditResult") boolean auditResult);

	/**
	 * 根据id查询Budget
	 * @param id
	 * @return
	 */
	Budget queryByBudgetId(int id);
	
	/**
	 * 查询�?有Budget
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<Budget> queryAll(@Param("offset") int offset, @Param("limit") int limit);
	
	/**
	 *根据学院名称查询Budget
	 * @param collegeName
	 * @return
	 */
	List<Budget> queryByCollegeName(String name);
	
	/**
	 * 根据部门id查询Budget
	 * @param departmentId
	 * @return
	 */
	List<Budget> queryByDepartmentId(int id);
	
	/**
	 * 根据项目id查询Budget
	 * @param projectId
	 * @return
	 */
	List<Budget> queryByProjectId(int id);
	
	/**
	 * 根据pdc查询Budget
	 * @param projectId
	 * @param departmentId
	 * @param collegeName
	 * @return
	 */
	Budget queryByPDC(@Param("projectId") int projectId, @Param("departmentId") int departmentId, 
			@Param("collegeName") String collegeName);
	/**
	 * 根据dc查询Budget
	 * @param departmentId
	 * @param collegeName
	 * @return
	 */
	List<Budget> queryByDC( @Param("departmentId") int departmentId, @Param("collegeName") String collegeName);
	
	/**
	 * 插入Budget
	 * @param record
	 * @return
	 */
	int insertBudget(Budget record);
	
	/**
	 * 更新Budget Status
	 * @param projectId
	 * @param departmentId
	 * @param collegeName
	 * @param auditResult
	 * @return
	 */
	int updateAuditResult(@Param("projectId") int projectId, 
			@Param("departmentId") int departmentId,
			@Param("collegeName") String collegeName,@Param("auditResult") boolean auditResult);
	
	/**
	 * budget---> group deptId
	 * @param collegeName
	 * @return (collegeName,departmentId,budgetAmount)
	 */
	List<Budget> querySummaryBudget(@Param("collegeName") String collegeName);
	
	/**
	 * budget ----> group by collegeName and projectId
	 * @return (collegeName,projectId,budgetAmount)
	 */
	List<Budget> queryProjectBudget ();
}
