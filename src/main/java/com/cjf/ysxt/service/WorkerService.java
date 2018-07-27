package com.cjf.ysxt.service;

import java.util.List;

import com.cjf.ysxt.dto.LoginExecution;
import com.cjf.ysxt.entity.Worker;

public interface WorkerService {

	/**
	 * 查询�?个用�?
	 * 
	 * @param idworker
	 * @return
	 */
	Worker getById(String idworker);

	/**
	 * 查询�?有用�?
	 * 
	 * @return
	 */
	List<Worker> getList();
	
	/**
	 * 登录
	 * @param idworker
	 * @param password
	 * @param title
	 * @return
	 */
	LoginExecution login(String idworker, String password, String title);
}
