package com.cjf.ysxt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cjf.ysxt.entity.FirstSummaryTable;
import com.cjf.ysxt.entity.SecondSummaryTable;


public interface SecondSummaryTableDao {

	/**
	 * 查询�?有FirstSummaryTable
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<SecondSummaryTable> queryAll(@Param("offset") int offset, @Param("limit") int limit);
	
	/**
	 * query sst group by collegeName
	 * @return
	 */
	List<SecondSummaryTable> queryCollegeBudget();
	
	/**
	 * 根据部门id查询SecondSummaryTable
	 * @param id
	 * @return
	 */
	SecondSummaryTable queryByDepartmentId(int id);
	
	/**
	 * 根据学院名称查询SecondSummaryTable
	 * @param name
	 * @return
	 */
	List<SecondSummaryTable> queryByCollegeName(String name);
	
	/**
	 * 插入SecondSummaryTable
	 * @param record
	 * @return
	 */
	int insertSecondSummaryTable(SecondSummaryTable record);
	
	/**
	 * uodate sst status
	 * @param departmentId
	 * @param auditResult
	 * @return
	 */
	int updateAuditResult(@Param("departmentId") int departmentId,@Param("auditResult") boolean auditResult);
	
	int updateAuditResult2(@Param("collegeName") String collegeName,@Param("auditResult") boolean auditResult);
}
