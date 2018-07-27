package com.cjf.ysxt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cjf.ysxt.entity.Worker;

public interface WorkerDao {

	/**
	 * ͨ
	 * 根据id查询worker
	 * @param id
	 * @return
	 */
	Worker queryByWorkerId(String id);
	
	/**
	 * 查询�?有worker
	 * @param offset 
	 * @param limit 
	 * @return
	 */
	List<Worker> queryAll(@Param("offset") int offset, @Param("limit") int limit);
	
	/**
	 * 根据名字查询worker
	 * @param name
	 * @return
	 */
	List<Worker> queryByWorkerName(String name);
	
	/**
	 * 根据学院名称查询worker
	 * @param name
	 * @return
	 */
	List<Worker> queryByCollegeName(String name);
	
	/**
	 * 根据部门id查询worker
	 * @param name
	 * @return
	 */
	List<Worker> queryByDepartmentId(int id);
	
	/**
	 * insert worker
	 * @param record
	 * @return
	 */
	int insertWorker(Worker record);
	
	/**
	 * delete worker
	 * @param workerid
	 * @return
	 */
	int deleteWorker(String workerid);
}
