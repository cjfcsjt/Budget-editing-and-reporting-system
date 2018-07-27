package com.cjf.ysxt.service.impl;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cjf.ysxt.BaseTest;
import com.cjf.ysxt.service.WorkerService;
import com.cjf.ysxt.dto.LoginExecution;

public class WorkerServiceImplTest extends BaseTest {

	@Autowired
    private WorkerService loginService;
	
	@Test
    public void testLogin() throws Exception {
        int idworker = 2018071501;
        String password = "123";
        String title = "财政部门管理员";
        LoginExecution execution = loginService.login(idworker,password,title);
        System.out.println(execution);
    }
	

	
}
