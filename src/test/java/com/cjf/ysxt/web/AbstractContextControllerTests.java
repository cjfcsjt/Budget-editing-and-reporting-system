package com.cjf.ysxt.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import com.cjf.ysxt.service.WorkerService;

@WebAppConfiguration
@ContextConfiguration({ "classpath:spring/spring-web.xml", "classpath:spring/spring-service.xml",
		"classpath:spring/spring-dao.xml" })
public class AbstractContextControllerTests {

	@Autowired
	protected WebApplicationContext wac;
	@Autowired
	protected WorkerService workerService;
	
}