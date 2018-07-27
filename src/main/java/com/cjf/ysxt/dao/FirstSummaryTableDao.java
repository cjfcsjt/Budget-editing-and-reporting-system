package com.cjf.ysxt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cjf.ysxt.entity.AcquisitionTable;
import com.cjf.ysxt.entity.FirstSummaryTable;


public interface FirstSummaryTableDao {

	/**
	 * 查询�?有FirstSummaryTable
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<FirstSummaryTable> queryAll(@Param("offset") int offset, @Param("limit") int limit);
	
	
	List<FirstSummaryTable> queryCollegeBudget();
	
	/**
	 * 根据部门id查询FirstSummaryTable
	 * @param id
	 * @return
	 */
	FirstSummaryTable queryByDepartmentId(int id);
	
	/**
	 * 根据学院名称查询FirstSummaryTable
	 * @param name
	 * @return
	 */
	List<FirstSummaryTable> queryByCollegeName(String name);
	
	/**
	 * DCquery
	 * @param departmentId
	 * @param collegeName
	 * @return
	 */
	FirstSummaryTable queryByDC( @Param("departmentId") int departmentId, @Param("collegeName") String collegeName);
	
	
	/**
	 * 插入FirstSummaryTable
	 * @param record
	 * @return
	 */
	int insertFirstSummaryTable(FirstSummaryTable record);
	
	int updateAuditResult(@Param("departmentId") int departmentId,@Param("auditResult") boolean auditResult);
	
	int updateAuditResult2(@Param("collegeName") String collegeName,@Param("auditResult") boolean auditResult);
}
