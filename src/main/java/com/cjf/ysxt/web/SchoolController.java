package com.cjf.ysxt.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cjf.ysxt.dao.DepartmentDao;
import com.cjf.ysxt.dao.ProjectDao;
import com.cjf.ysxt.dto.AcquisitionExecution;
import com.cjf.ysxt.dto.BudgetExecution;
import com.cjf.ysxt.dto.BudgetExecutionP;
import com.cjf.ysxt.dto.CCNExecution;
import com.cjf.ysxt.dto.FSTExecution;
import com.cjf.ysxt.dto.FSTExecutionA;
import com.cjf.ysxt.dto.FSTExecutionD;
import com.cjf.ysxt.dto.Result;
import com.cjf.ysxt.dto.SSTExecution;
import com.cjf.ysxt.dto.SSTExecutionD;
import com.cjf.ysxt.entity.AcquisitionTable;
import com.cjf.ysxt.entity.Budget;
import com.cjf.ysxt.entity.Department;
import com.cjf.ysxt.entity.FirstSummaryTable;
import com.cjf.ysxt.entity.Project;
import com.cjf.ysxt.entity.SecondSummaryTable;
import com.cjf.ysxt.enums.InsertStateEnum;
import com.cjf.ysxt.enums.UpdateStateEnum;
import com.cjf.ysxt.exception.NoNumberException;
import com.cjf.ysxt.exception.RepeatException;
import com.cjf.ysxt.service.SchoolService;
import com.cjf.ysxt.service.impl.ExcelService;
import com.cjf.ysxt.tool.ViewExcel;


@Controller
@RequestMapping(value = "school")
public class SchoolController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@Autowired
	private SchoolService schoolService;
	@Autowired
	private ExcelService excelService;
	@Autowired
	private ProjectDao projectDao;
	@Autowired
	private DepartmentDao departmentDao;
	
	/**
	 * OK
	 * 获取每一个学院总的预算fst
	 * "/school/queryCollegeBudget"
	 * @return List<FirstSummaryTable>
	 */
    @RequestMapping(value = "queryCollegeBudget")
    @ResponseBody 
    private List<FirstSummaryTable> queryCollegeBudget() {
    	List<FirstSummaryTable> firstSummaryTables = schoolService.queryCollegeBudget();
    	return firstSummaryTables;
    }
    
    /**
     * OK
     * 获取每一个学院总的预算sst
     * "/school/queryCollegeBudget2"
     * @return List<SecondSummaryTable>
     */
    @RequestMapping(value = "queryCollegeBudget2")
    @ResponseBody
    private List<SecondSummaryTable> queryCollegeBudget2() {
    	List<SecondSummaryTable> secondSummaryTables = schoolService.queryCollegeBudget2();
    	return secondSummaryTables;
    }
    
    /**
     * OK
     * 获取某学院所有部门的总预算fst
     * "/school/queryDepartmentBudget"
     * @param collegeName
     * @return List<FirstSummaryTable>
     */
    @RequestMapping(value = "queryDepartmentBudget")
    @ResponseBody
    private List<FSTExecutionD> queryDepartmentBudget(@Param("collegeName") String collegeName) {
    	//Worker worker = (Worker)request.getSession().getAttribute("user");
    	//String collegeName = worker.getCollegeName();
    	List<FSTExecutionD> fstExecutions = schoolService.queryDepartmentBudget(collegeName);
    	System.out.println("test" + fstExecutions);
    	return fstExecutions;
    }
    
    /**
     * OK
     * 获取某学院所有部门的总预算sst
     * "/school/queryDepartmentBudget2"
     * @param collegeName
     * @return List<SecondSummaryTable>
     */
    @RequestMapping(value = "queryDepartmentBudget2")
    @ResponseBody
    private List<SSTExecutionD> queryDepartmentBudget2(@Param("collegeName") String collegeName) {
    	List<SSTExecutionD> sstExecutions= schoolService.queryDepartmentBudget2(collegeName);
    	return sstExecutions;
    }
    
    /**
     * OK
     * 插入collegeControlnum，某一学院的预算控制数
     * "/school/insertccn"
     * @param collegeName
     * @param budgetControlNum
     * @param notes
     * @return Result<CCNExecution>
     */
    @RequestMapping(value = "insertccn")
    @ResponseBody
    private Result<CCNExecution> insert(@Param("collegeName") String collegeName, 
    		@Param("budgetControlNum") String budgetControlNum,
    		@Param("notes") String notes,    		
    		HttpServletRequest request, HttpServletResponse response) {
    	if (collegeName.equals("") || budgetControlNum.equals("") || notes.equals("")) {
            return new Result<CCNExecution>(false, "输入内容不能为空");
        }
    	float budgetControlNumber = Float.parseFloat(budgetControlNum);
    	
        CCNExecution execution = null;
        try {
            execution = schoolService.insert(collegeName, budgetControlNumber, notes);
        } catch (RepeatException e1) {
            execution = new CCNExecution(InsertStateEnum.REPEAT_INSERT);
        }  catch (Exception e) {
            execution = new CCNExecution(InsertStateEnum.INNER_ERROR);
        }
        return new Result<CCNExecution>(true, execution);
    }
    
    /**
     * OK
     * 更新secondSummaryTable某一个学院某一个部门的状态
     * "/school/updatesst"
     * @param collegeName
     * @param departmentName
     * @param Status
     * @return Result<SSTExecution>
     */
    @RequestMapping(value = "updatesst")
    @ResponseBody
    private Result<SSTExecution> update(@Param("collegeName") String collegeName, 
    		@Param("departmentName") String departmentName,
    		@Param("Status") boolean Status,    		
    		HttpServletRequest request, HttpServletResponse response) {
    	if (collegeName.equals("") || departmentName.equals("")) {
            return new Result<SSTExecution>(false, "输入内容不能为空");
        }
    	
    	SSTExecution execution = null;
        try {
            execution = schoolService.update(collegeName, departmentName, Status);
        } catch (NoNumberException e1) {
            execution = new SSTExecution(Status,UpdateStateEnum.NO_NUMBER);
        }  catch (Exception e) {
            execution = new SSTExecution(Status,UpdateStateEnum.INNER_ERROR);
        }
        return new Result<SSTExecution>(true, execution);
    }
    
    /**
     * OK
     * 更新firstSummaryTable某一个学院某一个部门的状态
     * @param collegeName
     * @param departmentName
     * @param Status
     * @return
     */
    @RequestMapping(value = "updatefst")
    @ResponseBody
    private Result<FSTExecutionA> updatefst(@Param("collegeName") String collegeName, 
    		@Param("departmentName") String departmentName,
    		@Param("Status") boolean Status,    		
    		HttpServletRequest request, HttpServletResponse response) {
    	if (collegeName.equals("") || departmentName.equals("")) {
            return new Result<FSTExecutionA>(false, "输入内容不能为空");
        }
    	
    	FSTExecutionA execution = null;
        try {
            execution = schoolService.updatefst(collegeName, departmentName, Status);
        } catch (NoNumberException e1) {
            execution = new FSTExecutionA(Status,UpdateStateEnum.NO_NUMBER);
        }  catch (Exception e) {
            execution = new FSTExecutionA(Status,UpdateStateEnum.INNER_ERROR);
        }
        return new Result<FSTExecutionA>(true, execution);
    }
    
    /**
     * OK
     * 更新secondSummaryTable某一个学院所有部门的状态
     * @param collegeName
     * @param Status
     * @return
     */
    @RequestMapping(value = "updateByCollege")
    @ResponseBody
    private Result<SSTExecution> updateByCollege(@Param("collegeName") String collegeName, 
    		@Param("Status") boolean Status,    		
    		HttpServletRequest request, HttpServletResponse response) {
    	if (collegeName.equals("")) {
            return new Result<SSTExecution>(false, "输入内容不能为空");
        }
    	
    	SSTExecution execution = null;
        try {
            execution = schoolService.updateByCollege(collegeName, Status);
        } catch (NoNumberException e1) {
            execution = new SSTExecution(Status,UpdateStateEnum.NO_NUMBER);
        }  catch (Exception e) {
            execution = new SSTExecution(Status,UpdateStateEnum.INNER_ERROR);
        }
        return new Result<SSTExecution>(true, execution);
    }
    
    /**
     * OK
     * 更新firstSummaryTable某一个学院所有部门的状态
     * @param collegeName
     * @param Status
     * @return
     */
    @RequestMapping(value = "updateByCollegefst")
    @ResponseBody
    private Result<FSTExecution> updateByCollegefst(@Param("collegeName") String collegeName, 
    		@Param("Status") boolean Status,    		
    		HttpServletRequest request, HttpServletResponse response) {
    	if (collegeName.equals("")) {
            return new Result<FSTExecution>(false, "输入内容不能为空");
        }
    	
    	FSTExecution execution = null;
        try {
            execution = schoolService.updateByCollegefst(collegeName, Status);
        } catch (NoNumberException e1) {
            execution = new FSTExecution(Status,UpdateStateEnum.NO_NUMBER);
        }  catch (Exception e) {
            execution = new FSTExecution(Status,UpdateStateEnum.INNER_ERROR);
        }
        return new Result<FSTExecution>(true, execution);
    }
    
    /**
     * OK
     * 查询所有学院的某一个项目的预算和
     * "/school/queryProjectBudget"
     * @return List<Budget>
     */
    @RequestMapping(value = "queryProjectBudget")
    @ResponseBody
    private List<BudgetExecutionP> queryProjectBudget() {
    	List<BudgetExecutionP> budgetExecutions= schoolService.queryProjectBudget();
    	return budgetExecutions;
    }
    
    /**
     * OK
     * 查询某个学院的所有预算条目budget 
     * "/school/queryBudgetByCollegeName"
     * @param collegeName
     * @return List<Budget>
     */
    @RequestMapping(value = "queryBudgetByCollegeName")
    @ResponseBody
    private List<BudgetExecution> queryBudgetByCollegeName(@Param("collegeName") String collegeName) {
    	List<BudgetExecution> budgetExecutions= schoolService.queryBudgetByCollegeName(collegeName);
    	return budgetExecutions;
    }
    
    /**
     * OK
     * 查询某个学院的某个部门的所有预算条目budget
     * "/school/queryBudgetByDC"
     * @param collegeName
     * @param departmentName
     * @return List<Budget>
     */
    @RequestMapping(value = "queryBudgetByDC")
    @ResponseBody
    private List<BudgetExecution> queryBudgetByDC(@Param("collegeName") String collegeName,
    		@Param("departmentName") String departmentName) {
    	List<BudgetExecution> budgetExecutions= schoolService.queryBudgetByDC(collegeName, departmentName);
    	return budgetExecutions;
    }
    
    /**
     * OK
     * 查询某个学院的某个部门的所有采集条目Ac
     * @param collegeName
     * @param departmentName
     * @return
     */
    @RequestMapping(value = "queryAcByDC")
    @ResponseBody
    private List<AcquisitionExecution> queryAcByDC(@Param("collegeName") String collegeName,
    		@Param("department") String departmentName) {
    	List<AcquisitionExecution> acquisitionExecutions= schoolService.queryAcByDC(collegeName, departmentName);
    	return acquisitionExecutions;
    }
    
    /**
     * OK
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping("export")
    public ModelAndView export(ModelMap map) throws Exception{
        Map<String, Map<String, Float>> list = excelService.selectAllInfo();
        ArrayList<String> titles = new ArrayList<String>();
		titles.add("学院");
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
