package com.cjf.ysxt.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cjf.ysxt.dao.DepartmentDao;
import com.cjf.ysxt.dao.FirstSummaryTableDao;
import com.cjf.ysxt.dao.ProjectDao;
import com.cjf.ysxt.dto.AcquisitionExecution;
import com.cjf.ysxt.dto.BudgetExecution;
import com.cjf.ysxt.dto.DCNExecution;
import com.cjf.ysxt.dto.FSTExecution;
import com.cjf.ysxt.dto.FSTExecutionA;
import com.cjf.ysxt.dto.FSTExecutionD;
import com.cjf.ysxt.dto.Result;
import com.cjf.ysxt.dto.SSTExecution;
import com.cjf.ysxt.dto.SSTExecutionD;
import com.cjf.ysxt.entity.AcquisitionTable;
import com.cjf.ysxt.entity.Budget;
import com.cjf.ysxt.entity.CollegeControlnum;
import com.cjf.ysxt.entity.Department;
import com.cjf.ysxt.entity.FirstSummaryTable;
import com.cjf.ysxt.entity.Project;
import com.cjf.ysxt.entity.Worker;
import com.cjf.ysxt.enums.InsertStateEnum;
import com.cjf.ysxt.enums.UpdateStateEnum;
import com.cjf.ysxt.exception.NoNumberException;
import com.cjf.ysxt.exception.RepeatException;
import com.cjf.ysxt.service.CollegeService;
import com.cjf.ysxt.service.SchoolService;
import com.cjf.ysxt.service.impl.ExcelService;
import com.cjf.ysxt.tool.ViewExcel;


@Controller
@RequestMapping(value = "college")
public class CollegeController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CollegeService collegeService;
	@Autowired
	private SchoolService schoolService;
	@Autowired
	private FirstSummaryTableDao firstSummaryTableDao;
	@Autowired
	private ExcelService excelService;
	@Autowired
	private ProjectDao projectDao;
	@Autowired
	private DepartmentDao departmentDao;
	
	/**
	 * OK
	 * "/college/getByStatus"
	 * 根据状态来查询采集表Ac
	 * @param auditResult
	 * @return List<AcquisitionExecution>
	 */
    @RequestMapping(value = "getByStatus")
    @ResponseBody
    private List<AcquisitionExecution> getByStatus(@Param("auditResult") boolean auditResult,
    		HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("auditResult="+auditResult);
    	Worker worker = (Worker)request.getSession().getAttribute("user");
    	String collegeName = worker.getCollegeName();
    	List<AcquisitionExecution> acquisitionExecutions = collegeService.getByStatus(collegeName, auditResult);
    	
    	return acquisitionExecutions;
    }
    
    /**
     * OK
     * 根据状态查询预算表 budget
     * "/college/getBudgetByStatus"
     * @param auditResult
     * @return List<BudgetExecution>
     */
    @RequestMapping(value = "getBudgetByStatus")
    @ResponseBody
    private List<BudgetExecution> getBudgetByStatus(@Param("auditResult") boolean auditResult,
    		HttpServletRequest request, HttpServletResponse response) {
    	Worker worker = (Worker)request.getSession().getAttribute("user");
    	String collegeName = worker.getCollegeName();
    	List<BudgetExecution> budgetExecutions = collegeService.getBudgetByStatus(collegeName, auditResult);
    	return budgetExecutions;
    }
    
    /**
     * 更新fst用户所在学院所有部门的状态
     * @param collegeName
     * @param departmentName
     * @param Status
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "updatefst")
    @ResponseBody
    private Result<FSTExecutionA> updatefst(
    		@Param("Status") boolean Status,    		
    		HttpServletRequest request, HttpServletResponse response) {
    	FSTExecutionA execution = null;
        try {
        	Worker worker = (Worker)request.getSession().getAttribute("user");
        	String collegeName = worker.getCollegeName();
        	List<FirstSummaryTable> firstSummaryTables = firstSummaryTableDao.queryByCollegeName(collegeName);
        	for(FirstSummaryTable firstSummaryTable : firstSummaryTables) {
        		int departmentid = firstSummaryTable.getDepartmentId();
        		Department department = departmentDao.queryByDepartmentId(departmentid);
        		String departmentName = department.getName();
        		execution = schoolService.updatefst(collegeName, departmentName, Status);
        	}
        } catch (NoNumberException e1) {
            execution = new FSTExecutionA(Status,UpdateStateEnum.NO_NUMBER);
        }  catch (Exception e) {
            execution = new FSTExecutionA(Status,UpdateStateEnum.INNER_ERROR);
        }
        return new Result<FSTExecutionA>(true, execution);
    }
    
    
    
    /**
     * OK
     * 根据当前用户的学院，更改Ac中某一个部门某一个项目的状态
     * "/college/update"
     * @param departmentName
     * @param projectName
     * @param auditResult
     * @return Result<AcquisitionExecution>
     */
    @RequestMapping(value = "updateAC")
    @ResponseBody
    private Result<AcquisitionExecution> update( 
    		@Param("departmentName") String departmentName,
    		@Param("projectName") String projectName,
    		@Param("auditResult") boolean auditResult,    		
    		HttpServletRequest request, HttpServletResponse response) {
    	Worker worker = (Worker)request.getSession().getAttribute("user");
    	String collegeName = worker.getCollegeName();
    	if (collegeName.equals("") || departmentName.equals("")|| projectName.equals("")) {
            return new Result<AcquisitionExecution>(false, "输入内容不能为空");
        }
    	
    	AcquisitionExecution execution = null;
        try {
            execution = collegeService.update(collegeName, departmentName, projectName, auditResult);
        } catch (NoNumberException e1) {
            execution = new AcquisitionExecution(auditResult,UpdateStateEnum.NO_NUMBER);
        }  catch (Exception e) {
            execution = new AcquisitionExecution(auditResult,UpdateStateEnum.INNER_ERROR);
        }
        return new Result<AcquisitionExecution>(true, execution);
    }
    
    /**
     * OK
     * 插入FST，根据用户当前学院，查询每一个部门的每一个项目，如果某个部门的所有项目都已经审核通过，那么该部门插入FST
     * "/college/insertFST"
     * @param notes
     * @param auditTime
     * @return Result<FSTExecution>
     */
    @RequestMapping(value = "insertFST")
    @ResponseBody
    private Result<FSTExecution> insertFST( 
    		@Param("notes") String notes,
    		@Param("auditTime") String auditTime,    		
    		HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("notes="+notes+"auditTime"+auditTime);
    	Worker worker = (Worker)request.getSession().getAttribute("user");
    	String collegeName = worker.getCollegeName();
    	if (collegeName.equals("") || notes.equals("") || auditTime.equals("")) {
            return new Result<FSTExecution>(false, "输入内容不能为空");
        }
    	
    	FSTExecution execution = null;
        try {
            execution = collegeService.insertFST(collegeName, notes, auditTime);
        } catch (RepeatException e1) {
            execution = new FSTExecution(InsertStateEnum.REPEAT_INSERT);
        }  catch (Exception e) {
            execution = new FSTExecution(InsertStateEnum.INNER_ERROR);
        }
        return new Result<FSTExecution>(true, execution);
    }
    
    /**
     * OK
     * 插入SST，根据用户当前学院，查询每一个部门的每一个项目，如果某个部门的所有项目都已经审核通过，那么该部门插入SST
     * "/college/insertSST"
     * @param notes
     * @param auditTime
     * @param request
     * @param response
     * @return Result<SSTExecution>
     */
    @RequestMapping(value = "insertSST")
    @ResponseBody
    private Result<SSTExecution> insertSST( 
    		@Param("notes") String notes,
    		@Param("auditTime") String auditTime,    		
    		HttpServletRequest request, HttpServletResponse response) {
    	Worker worker = (Worker)request.getSession().getAttribute("user");
    	String collegeName = worker.getCollegeName();
    	if (collegeName.equals("") || notes.equals("") || auditTime.equals("")) {
            return new Result<SSTExecution>(false, "输入内容不能为空");
        }
    	
    	SSTExecution execution = null;
        try {
            execution = collegeService.insertSST(collegeName, notes, auditTime);
        } catch (RepeatException e1) {
            execution = new SSTExecution(InsertStateEnum.REPEAT_INSERT);
        }  catch (Exception e) {
            execution = new SSTExecution(InsertStateEnum.INNER_ERROR);
        }
        return new Result<SSTExecution>(true, execution);
    }
    
    /**
     * OK
     * 根据用户当前学院，查询FST所有条目
     * "/college/queryALL"
     * @return List<FSTExecution>
     */
    @RequestMapping(value = "queryALL")
    @ResponseBody
    private List<FSTExecutionD> queryALL(
    		HttpServletRequest request, HttpServletResponse response) {
    	Worker worker = (Worker)request.getSession().getAttribute("user");
    	String collegeName = worker.getCollegeName();
    	List<FSTExecutionD> fstExecutions = collegeService.queryALL(collegeName);
    	return fstExecutions;
    }
    
    /**
     * OK
     * 根据用户当前学院，查询SST所有条目
     * "/college/queryALL"
     * @return List<SSTExecution>
     */
    @RequestMapping(value = "queryALL2")
    @ResponseBody
    private List<SSTExecutionD> queryALL2(
    		HttpServletRequest request, HttpServletResponse response) {
    	Worker worker = (Worker)request.getSession().getAttribute("user");
    	String collegeName = worker.getCollegeName();
    	List<SSTExecutionD> sstExecutions = collegeService.queryALL2(collegeName);
    	return sstExecutions;
    }
    
    /**
     * OK
     * 根据用户当前学院，查询CCN的所有条目
     * "/college/queryByCollegeName"
     * @return CollegeControlnum
     */
    @RequestMapping(value = "queryByCollegeName")
    @ResponseBody
    private CollegeControlnum queryByCollegeName(
    		HttpServletRequest request, HttpServletResponse response) {
    	Worker worker = (Worker)request.getSession().getAttribute("user");
    	String collegeName = worker.getCollegeName();
    	CollegeControlnum collegeControlnum = collegeService.queryByCollegeName(collegeName);
    	return collegeControlnum;
    }
    
    /**
     * OK
     * 插入DCN，根据用户当前学院，插入某一个部门的预算控制数
     * "/college/insertDCN"
     * @param departmentName
     * @param notes
     * @param budgetControlNum
     * @return Result<DCNExecution>
     */
    @RequestMapping(value = "insertDCN")
    @ResponseBody
    private Result<DCNExecution> insertDCN( @Param("departmentName") String departmentName,
    		@Param("notes") String notes,
    		@Param("budgetControlNum") String budgetControlNum,    		
    		HttpServletRequest request, HttpServletResponse response) {
    	Worker worker = (Worker)request.getSession().getAttribute("user");
    	String collegeName = worker.getCollegeName();
    	if (departmentName.equals("") || notes.equals("") || budgetControlNum.equals("")) {
            return new Result<DCNExecution>(false, "输入内容不能为空");
        }
    	float budgetControlNumber = Float.parseFloat(budgetControlNum);
    	DCNExecution execution = null;
        try {
            execution = collegeService.insertDCN(collegeName, departmentName, budgetControlNumber, notes);
        } catch (RepeatException e1) {
            execution = new DCNExecution(InsertStateEnum.REPEAT_INSERT);
        }  catch (Exception e) {
            execution = new DCNExecution(InsertStateEnum.INNER_ERROR);
        }
        return new Result<DCNExecution>(true, execution);
    }
    
    /**
     * OK
     * 根据用户当前学院查询budget的所有条目
     * "/college/queryBudgetByCollegeName"
     * @return List<BudgetExecution>
     */
    @RequestMapping(value = "queryBudgetByCollegeName")
    @ResponseBody
    private List<BudgetExecution> queryBudgetByCollegeName(
    		HttpServletRequest request, HttpServletResponse response) {
    	Worker worker = (Worker)request.getSession().getAttribute("user");
    	String collegeName = worker.getCollegeName();
    	List<BudgetExecution> budgetExecutions = collegeService.queryBudgetByCollegeName(collegeName);
    	return budgetExecutions;
    }
    /**
     * OK
     * 根据用户当前学院，查询某一个部门的budget
     * "/college/queryBudgetByDC"
     * @param departmentName
     * @return List<BudgetExecution>
     */
    @RequestMapping(value = "queryBudgetByDC")
    @ResponseBody
    private List<BudgetExecution> queryBudgetByDC(@Param("departmentName") String departmentName,
    		HttpServletRequest request, HttpServletResponse response) {
    	Worker worker = (Worker)request.getSession().getAttribute("user");
    	String collegeName = worker.getCollegeName();
    	List<BudgetExecution> budgetExecutions = collegeService.queryBudgetByDC(collegeName, departmentName);
    	return budgetExecutions;
    }
    
    /**
     * 根据用户当前学院，查询某一个部门的Ac
     * @param departmentName
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "queryAcByDC")
    @ResponseBody
    private List<AcquisitionExecution> queryAcByDC(@Param("departmentName") String departmentName,
    		HttpServletRequest request, HttpServletResponse response) {
    	Worker worker = (Worker)request.getSession().getAttribute("user");
    	String collegeName = worker.getCollegeName();
    	List<AcquisitionExecution> acquisitionExecutions = collegeService.queryAcByDC(collegeName, departmentName);
    	return acquisitionExecutions;
    }
    /**
     * OK
     * 根据用户当前所在的学院，更新某一个部门某一个项目的状态
     * "/college/updateBudgetStatus"
     * @param departmentName
     * @param projectName
     * @param auditResult
     * @return Result<BudgetExecution>
     */
    @RequestMapping(value = "updateBudgetStatus")
    @ResponseBody
    private Result<BudgetExecution> updateBudgetStatus( 
    		@Param("departmentName") String departmentName,
    		@Param("projectName") String projectName,
    		@Param("auditResult") boolean auditResult,    		
    		HttpServletRequest request, HttpServletResponse response) {
    	Worker worker = (Worker)request.getSession().getAttribute("user");
    	String collegeName = worker.getCollegeName();
    	if (collegeName.equals("") || departmentName.equals("")|| projectName.equals("")) {
            return new Result<BudgetExecution>(false, "输入内容不能为空");
        }
    	BudgetExecution execution = null;
        try {
            execution = collegeService.updateBudgetStatus(collegeName, departmentName, projectName, auditResult);
        } catch (NoNumberException e1) {
            execution = new BudgetExecution(auditResult,UpdateStateEnum.NO_NUMBER);
        }  catch (Exception e) {
            execution = new BudgetExecution(auditResult,UpdateStateEnum.INNER_ERROR);
        }
        return new Result<BudgetExecution>(true, execution);
    }
    
    
    
    
    
    @RequestMapping("export")
    public ModelAndView export(ModelMap map,HttpServletRequest request, HttpServletResponse response) throws Exception{
    	Worker worker = (Worker)request.getSession().getAttribute("user");
    	String collegeName = worker.getCollegeName();
        Map<String, Map<String, Float>> list = excelService.selectAllInfo2(collegeName);
        ArrayList<String> titles = new ArrayList<String>();
		titles.add("科室");
		List<Project> projects = projectDao.queryAll(0, 100);
		for(Project project : projects) {
			titles.add(project.getName());
		}
        //String[] titles={"学院","差旅费","购置费","修缮费"};
        ViewExcel excel=new ViewExcel(titles);
        map.put("excelList", list);
        return new ModelAndView(excel,map);
    }
	
}
