package com.cjf.ysxt.web;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import com.cjf.ysxt.dto.LoginExecution;

/**
 * 
 * @author Kemin
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 配置事务的回�?,对数据库的增删改都会回滚,便于测试用例的循环利�?
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class WorkerControllerTest extends AbstractContextControllerTests {

	private MockMvc mockMvc;
	private MockHttpSession session;
	private String checkloginUrl = "/worker/login.check";
	private String detailUrl = "/book/{bookId}/detail";
	private String appointUrl = "/book/{bookId}/appoint";
	private long bookId = 1000;

	@Before
	public void setup() {
		this.mockMvc = webAppContextSetup(this.wac).alwaysExpect(status().isOk()).alwaysDo(print()).build();
		this.session = new MockHttpSession();
	}
	
	
	

	@Test
	public void appointTest() throws Exception {
		System.out.println("testlogin");
		LoginExecution execution = workerService.login(2018071501, "123", "财政部门管理员");
		this.mockMvc.perform(post(checkloginUrl).param("username", "2018071501").
				param("password", "123").param("title", "教授").sessionAttr("user", execution.getWorker()));
	}
}
