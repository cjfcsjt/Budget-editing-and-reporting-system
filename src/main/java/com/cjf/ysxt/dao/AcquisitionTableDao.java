package com.cjf.ysxt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.junit.runners.Parameterized.Parameters;

import com.cjf.ysxt.entity.AcquisitionTable;

public interface AcquisitionTableDao {
	/**
	 * 根据id查询AcquisitionTable
	 * @param id
	 * @return
	 */
	AcquisitionTable queryByAcquisitionTableId(int id);
	
	/**
	 * 查询�?有AcquisitionTable
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<AcquisitionTable> queryAll(@Param("offset") int offset, @Param("limit") int limit);
	
	/**
	 * 根据学院名称查询AcquisitionTable
	 * @param collegeName
	 * @return
	 */
	List<AcquisitionTable> queryByCollegeName(String name);
	
	
	
	/**
	 * 根据部门名称查询AcquisitionTable
	 * @param departmentId
	 * @return
	 */
	List<AcquisitionTable> queryByDepartmentId(int id);
	
	/**
	 * 根据项目id查询Acquisition Table
	 * @param projectId
	 * @return
	 */
	List<AcquisitionTable> queryByProjectId(int id);
	
	/**
	 * 根据projectId,departmentId,collegeName查询AcquisitionTable
	 * @param projectId
	 * @param departmentId
	 * @param collegeName
	 * @return
	 */
	AcquisitionTable queryByPDC(@Param("projectId") int projectId, @Param("departmentId") int departmentId, @Param("collegeName") String collegeName);
	
	/**
	 * 根据departmentId,collegeName查询AcquisitionTable
	 * @param departmentId
	 * @param collegeName
	 * @return
	 */
	List<AcquisitionTable> queryByDC( @Param("departmentId") int departmentId, @Param("collegeName") String collegeName);
	
	/**
	 * group
	 * @param departmentId
	 * @param collegeName
	 * @return
	 */
	List<AcquisitionTable> querySummaryBudget( @Param("collegeName") String collegeName);

	/**
	 * query ac by collegeName where audit_result = false
	 * @param collegeName
	 * @param auditResult
	 * @return
	 */
	List<AcquisitionTable> queryBadStatus( @Param("collegeName") String collegeName,@Param("auditResult") boolean auditResult);
	/**
	 * 插入Acqusition Table
	 * @param record
	 * @return
	 */
	int insertAcquisitionTable(AcquisitionTable record);
	
	/**
	 * 更新budgetProposal
	 * @param budgetProposal
	 * @return
	 */
	int updateAcquisitionTable(@Param("projectId") int projectId, 
			@Param("departmentId") int departmentId,
			@Param("collegeName") String collegeName,@Param("budgetProposal") float budgetProposal);
	
	/**
	 * 更新acq Status
	 * @param projectId
	 * @param departmentId
	 * @param collegeName
	 * @param auditResult
	 * @return
	 */
	int updateAuditResult(@Param("projectId") int projectId, 
			@Param("departmentId") int departmentId,
			@Param("collegeName") String collegeName,@Param("auditResult") boolean auditResult);
}
