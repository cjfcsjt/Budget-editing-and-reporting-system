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

import javax.servlet.ServletContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestAttributes;

import com.cjf.ysxt.dto.LoginExecution;

/**
 * 
 * @author Kemin
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 配置事务的回�?,对数据库的增删改都会回滚,便于测试用例的循环利�?
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class CollegeControllerTest extends AbstractContextControllerTests {

	private MockMvc mockMvc;
	private String Url1 = "/college/getByStatus";
	private String Url2 = "/college/getBudgetByStatus";
	private String Url4 = "/college/update";
	private String Url5 = "/college/insertFST";
	private String Url6 = "/college/insertSST";
	private String Url7 = "/college/queryALL";
	private String Url8 = "/college/queryByCollegeName";
	private String Url9 = "/college/insertDCN";
	private String Url10 = "/college/queryBudgetByCollegeName";
	private String Url11 = "/college/queryBudgetByDC";
	private String Url12 = "/college/updateBudgetStatus";
	

	@Before
	public void setup() {
		this.mockMvc = webAppContextSetup(this.wac).alwaysExpect(status().isOk()).alwaysDo(print()).build();
	}

	

	@Test
	public void getByStatusTest() throws Exception {
		System.out.println("getByStatusTest");
		LoginExecution execution = workerService.login(2018071501, "123", "教授");
		this.mockMvc.perform(post(Url1).param("audiResult", "true").sessionAttr("user", execution.getWorker()));
	}
	
	@Test
	public void getBudgetByStatusTest() throws Exception {
		System.out.println("getBudgetByStatusTest");
		LoginExecution execution = workerService.login(2018071501, "123", "教授");
		this.mockMvc.perform(post(Url2).param("username", "2018071501").
				param("password", "123").param("title", "教授"));
	}
	@Test
	public void updateTest() throws Exception {
		System.out.println("updateTest");
		LoginExecution execution = workerService.login(2018071501, "123", "教授");
		this.mockMvc.perform(post(Url4).param("username", "2018071501").
				param("password", "123").param("title", "教授"));
	}
	@Test
	public void insertFSTTest() throws Exception {
		System.out.println("insertFSTTest");
		LoginExecution execution = workerService.login(2018071501, "123", "教授");
		this.mockMvc.perform(post(Url5).param("notes", "test").
				param("auditTime", "2018071501").sessionAttr("user", execution.getWorker()));
	}
	@Test
	public void insertSSTTest() throws Exception {
		System.out.println("insertSSTTest");
		LoginExecution execution = workerService.login(2018071501, "123", "教授");
		this.mockMvc.perform(post(Url6).param("username", "2018071501").
				param("password", "123").param("title", "教授"));
	}@Test
	public void queryALLTest() throws Exception {
		System.out.println("queryALLTest");
		LoginExecution execution = workerService.login(2018071501, "123", "教授");
		this.mockMvc.perform(post(Url7).param("username", "2018071501").
				param("password", "123").param("title", "教授"));
	}@Test
	public void queryByCollegeNameTest() throws Exception {
		System.out.println("queryByCollegeNameTest");
		LoginExecution execution = workerService.login(2018071501, "123", "教授");
		this.mockMvc.perform(post(Url8).param("username", "2018071501").
				param("password", "123").param("title", "教授"));
	}@Test
	public void insertDCNTest() throws Exception {
		System.out.println("insertDCNTest");
		LoginExecution execution = workerService.login(2018071501, "123", "教授");
		this.mockMvc.perform(post(Url9).param("username", "2018071501").
				param("password", "123").param("title", "教授"));
	}@Test
	public void queryBudgetByCollegeNameTest() throws Exception {
		System.out.println("queryBudgetByCollegeNameTest");
		LoginExecution execution = workerService.login(2018071501, "123", "教授");
		this.mockMvc.perform(post(Url10).param("username", "2018071501").
				param("password", "123").param("title", "教授"));
	}
	public void queryBudgetByDCTest() throws Exception {
		System.out.println("queryBudgetByDCTest");
		LoginExecution execution = workerService.login(2018071501, "123", "教授");
		this.mockMvc.perform(post(Url11).param("username", "2018071501").
				param("password", "123").param("title", "教授"));
	}
	public void updateBudgetStatusTest() throws Exception {
		System.out.println("updateBudgetStatusTest");
		LoginExecution execution = workerService.login(2018071501, "123", "教授");
		this.mockMvc.perform(post(Url12).param("username", "2018071501").
				param("password", "123").param("title", "教授"));
	}
	
	
	
	
}
