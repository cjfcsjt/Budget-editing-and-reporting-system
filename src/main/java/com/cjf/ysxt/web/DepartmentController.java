package com.cjf.ysxt.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cjf.ysxt.dto.AcquisitionExecution;
import com.cjf.ysxt.dto.BudgetExecution;
import com.cjf.ysxt.dto.Result;
import com.cjf.ysxt.entity.AcquisitionTable;
import com.cjf.ysxt.entity.Department;
import com.cjf.ysxt.entity.DepartmentControlnum;
import com.cjf.ysxt.entity.Worker;
import com.cjf.ysxt.enums.InsertStateEnum;
import com.cjf.ysxt.enums.UpdateStateEnum;
import com.cjf.ysxt.exception.NoNumberException;
import com.cjf.ysxt.exception.RepeatException;
import com.cjf.ysxt.service.AcquisitionService;
import com.cjf.ysxt.service.AdminService;


@Controller
@RequestMapping(value = "department")
public class DepartmentController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@Autowired
	private AcquisitionService acquisitionService;
	@Autowired
	private AdminService adminservice;
	
    
    /**
     * OK
     * 插入采集表
     * "/department/insert"
     * @param projectName
     * @param notes
     * @param budgetProposal
     * @param auditTime
     * @return Result<AcquisitionExecution>
     */
    @RequestMapping(value = "insert")
    @ResponseBody
    private Result<AcquisitionExecution> insert( @Param("projectName") String projectName,
    		@Param("notes") String notes,
    		@Param("budgetProposal") String budgetProposal,
    		@Param("auditTime") String auditTime,    		
    		HttpServletRequest request, HttpServletResponse response) {
    	//获取用户的collegeName
    	Worker worker = (Worker)request.getSession().getAttribute("user");
    	String collegeName = worker.getCollegeName();
    	//获取用户的departmentName
    	int departmentId = worker.getDepartmentId();
    	Department department = adminservice.queryById(departmentId);
    	String departmentName = department.getName();
    	//String ---> Float
    	float budgetproposal = Float.parseFloat(budgetProposal);
    	//判断输入
    	if (collegeName.equals("") || notes.equals("") || auditTime.equals("")) {
            return new Result<AcquisitionExecution>(false, "输入内容不能为空");
        }
    	
    	AcquisitionExecution execution = null;
        try {
            execution = acquisitionService.insert(collegeName, departmentName, projectName, 
            		auditTime, budgetproposal, notes);
        } catch (RepeatException e1) {
            execution = new AcquisitionExecution(departmentName, budgetproposal, notes, InsertStateEnum.REPEAT_INSERT);
        }  catch (Exception e) {
            execution = new AcquisitionExecution(departmentName, budgetproposal, notes,InsertStateEnum.INNER_ERROR);
        }
        return new Result<AcquisitionExecution>(true, execution);
    }
    
    /**
     * OK
     * 获取当前用户所在学院所在部门的采集表Ac明细
     * "/department/getByName"
     * @return List<AcquisitionTable>
     */
    @RequestMapping(value = "getByName")
    @ResponseBody
    private List<AcquisitionExecution> getByName(
    		HttpServletRequest request, HttpServletResponse response) {
    	//获取用户的collegeName
    	Worker worker = (Worker)request.getSession().getAttribute("user");
    	String collegeName = worker.getCollegeName();
    	//获取用户的departmentName
    	int departmentId = worker.getDepartmentId();
    	Department department = adminservice.queryById(departmentId);
    	String departmentName = department.getName();
    	List<AcquisitionExecution> acquisitionExecutions = acquisitionService.getByName(collegeName, departmentName);
    	return acquisitionExecutions;
    }
    
    /**
     * OK
     * 获取当前用户所在学院所在部门的预算表budget明细
     * "/department/getBudgetByName"
     * @return List<BudgetExecution>
     */
    @RequestMapping(value = "getBudgetByName")
    @ResponseBody
    private List<BudgetExecution> getBudgetByName(
    		HttpServletRequest request, HttpServletResponse response) {
    	//获取用户的collegeName
    	Worker worker = (Worker)request.getSession().getAttribute("user");
    	String collegeName = worker.getCollegeName();
    	//获取用户的departmentName
    	int departmentId = worker.getDepartmentId();
    	Department department = adminservice.queryById(departmentId);
    	String departmentName = department.getName();
    	List<BudgetExecution> budgetExecutions = acquisitionService.getBudgetByName(collegeName, departmentName);
    	return budgetExecutions;
    }
    
    /**
     * OK
     * 更改采集表的状态，当前用户所在学院所在部门某一项目
     * "/department/update"
     * @param projectName
     * @param budgetProposal
     * @return Result<AcquisitionExecution>
     */
    @RequestMapping(value = "update")
    @ResponseBody
    private Result<AcquisitionExecution> update( 
    		@Param("projectName") String projectName,
    		@Param("budgetProposal") String budgetProposal,    		
    		HttpServletRequest request, HttpServletResponse response) {
    	//获取用户的collegeName
    	Worker worker = (Worker)request.getSession().getAttribute("user");
    	String collegeName = worker.getCollegeName();
    	//获取用户的departmentName
    	int departmentId = worker.getDepartmentId();
    	Department department = adminservice.queryById(departmentId);
    	String departmentName = department.getName();
    	//String ---> Float
    	float budgetproposal = Float.parseFloat(budgetProposal);
    	if (collegeName.equals("") || departmentName.equals("")|| projectName.equals("")) {
            return new Result<AcquisitionExecution>(false, "输入内容不能为空");
        }
    	
    	AcquisitionExecution execution = null;
        try {
            execution = acquisitionService.update(collegeName, departmentName, projectName, budgetproposal);
        } catch (NoNumberException e1) {
            execution = new AcquisitionExecution(budgetproposal,UpdateStateEnum.NO_NUMBER);
        }  catch (Exception e) {
            execution = new AcquisitionExecution(budgetproposal,UpdateStateEnum.INNER_ERROR);
        }
        return new Result<AcquisitionExecution>(true, execution);
    }
    
    /**
     * OK
     * 查询当前用户所在学院所在部门的控制数dcn
     * "/department/queryDCN"
     * @return DepartmentControlnum
     */
    @RequestMapping(value = "queryDCN")
    @ResponseBody
    private DepartmentControlnum queryDCN(
    		HttpServletRequest request, HttpServletResponse response) {
    	//获取用户的collegeName
    	Worker worker = (Worker)request.getSession().getAttribute("user");
    	String collegeName = worker.getCollegeName();
    	//获取用户的departmentName
    	int departmentId = worker.getDepartmentId();
    	Department department = adminservice.queryById(departmentId);
    	String departmentName = department.getName();
    	DepartmentControlnum departmentControlnum = acquisitionService.queryDCN(collegeName, departmentName);
    	return departmentControlnum;
    }
    
    /**
     * OK
     * 插入当前用户所在学院所在部门的预算表budget
     * "/department/insertBudget"
     * @param projectName
     * @param budgetNotes
     * @param budgetAmount
     * @param auditTime
     * @param auditResult
     * @return Result<BudgetExecution>
     */
    @RequestMapping(value = "insertBudget")
    @ResponseBody
    private Result<BudgetExecution> insertBudget( @Param("projectName") String projectName,
    		@Param("budgetNotes") String budgetNotes,
    		@Param("budgetAmount") String budgetAmount,
    		@Param("auditTime") String auditTime,
    		@Param("auditResult") boolean auditResult,
    		HttpServletRequest request, HttpServletResponse response) {
    	//获取用户的collegeName
    	Worker worker = (Worker)request.getSession().getAttribute("user");
    	String collegeName = worker.getCollegeName();
    	
    	//获取用户的departmentName
    	int departmentId = worker.getDepartmentId();
    	Department department = adminservice.queryById(departmentId);
    	String departmentName = department.getName();
    	
    	//String ---> Float
    	float budgetamount = Float.parseFloat(budgetAmount);
    	//判断输入
    	if (collegeName.equals("") || budgetNotes.equals("") || auditTime.equals("")) {
            return new Result<BudgetExecution>(false, "输入内容不能为空");
        }
    	
    	BudgetExecution execution = null;
        try {
            execution = acquisitionService.insertBudget(collegeName, departmentName,
            		projectName, budgetamount, budgetNotes, auditTime, auditResult);
        } catch (RepeatException e1) {
            execution = new BudgetExecution(InsertStateEnum.REPEAT_INSERT);
        }  catch (Exception e) {
            execution = new BudgetExecution(InsertStateEnum.INNER_ERROR);
        }
        return new Result<BudgetExecution>(true, execution);
    }
	
}
