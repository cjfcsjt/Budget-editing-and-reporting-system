package com.cjf.ysxt.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjf.ysxt.dao.WorkerDao;
import com.cjf.ysxt.dto.LoginExecution;
import com.cjf.ysxt.entity.Worker;
import com.cjf.ysxt.enums.LoginStateEnum;
import com.cjf.ysxt.exception.LoginException;
import com.cjf.ysxt.exception.NoNumberException;
import com.cjf.ysxt.service.WorkerService;



@Service
public class WorkerServiceImpl implements WorkerService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WorkerDao workerDao;
	
	public Worker getById(String idworker) {
		return workerDao.queryByWorkerId(idworker);
	}

	
	public List<Worker> getList() {
		return workerDao.queryAll(0, 10000);
	}
	
	@Transactional
	public LoginExecution login(String idworker, String password, String title) {
		try {
			
			int flag = 0; 
            // 查看密码和身�?
            Worker worker = this.getById(idworker);
            //不存在该用户
            if(worker == null)
            	throw new NoNumberException("不存在该用户�?");
            if (worker.getPassword().equals(password) && worker.getTitle().equals(title))
            	// 登录成功
            	return  new LoginExecution(idworker ,password, title, LoginStateEnum.SUCCESS, worker);
            else 
            	throw new NoNumberException("密码或身份错�?"); 
            
        // 要先于catch Exception异常前先catch住再抛出，不然自定义的异常也会被转换为AppointException，导致控制层无法具体识别是哪个异�?
        } catch (NoNumberException e1) {
            throw e1;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            // �?有编译期异常转换为运行期异常
            throw new LoginException("login inner error:" + e.getMessage());
        }
		
	}


}
