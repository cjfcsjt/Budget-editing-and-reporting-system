package com.cjf.ysxt.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cjf.ysxt.dto.LoginExecution;
import com.cjf.ysxt.dto.Result;
import com.cjf.ysxt.enums.LoginStateEnum;
import com.cjf.ysxt.exception.NoNumberException;
import com.cjf.ysxt.service.WorkerService;


@Controller
@RequestMapping(value = "worker")
public class WorkerController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WorkerService workerService;
	
	/**
	 * 登录检查
	 * "/worker/login.check"
	 * @param username
	 * @param password
	 * @param title
	 * @return Result<LoginExecution>
	 */
    @RequestMapping(value = "login.check")
    @ResponseBody
    private Result<LoginExecution> login(@Param("username") String username, 
    		@Param("password") String password,
    		@Param("title") String title,    		
    		HttpServletRequest request, HttpServletResponse response) {
    	//String username = request.getParameter("username");
    
		//String password = request.getParameter("password");
    	//String title = request.getParameter("title");
        LoginExecution execution = null;
        try {
        	System.out.println(username + password + title);
            execution = workerService.login(username, password, title);

        } catch (NoNumberException e1) {
            execution = new LoginExecution(username,password,title, LoginStateEnum.NO_NUMBER);
        }  catch (Exception e) {
            execution = new LoginExecution(username,password,title, LoginStateEnum.INNER_ERROR);
        }
        request.getSession().setAttribute("user", execution.getWorker());
        return new Result<LoginExecution>(true, execution);
    }
	
}
