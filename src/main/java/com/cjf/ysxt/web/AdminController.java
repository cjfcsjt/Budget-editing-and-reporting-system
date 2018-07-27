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
import com.cjf.ysxt.dto.CollegeExecution;
import com.cjf.ysxt.dto.DCNExecution;
import com.cjf.ysxt.dto.DepartmentExecution;
import com.cjf.ysxt.dto.FSTExecution;
import com.cjf.ysxt.dto.Result;
import com.cjf.ysxt.dto.SSTExecution;
import com.cjf.ysxt.dto.WorkerExecution;
import com.cjf.ysxt.entity.AcquisitionTable;
import com.cjf.ysxt.entity.Budget;
import com.cjf.ysxt.entity.College;
import com.cjf.ysxt.entity.CollegeControlnum;
import com.cjf.ysxt.entity.Department;
import com.cjf.ysxt.entity.FirstSummaryTable;
import com.cjf.ysxt.entity.Worker;
import com.cjf.ysxt.enums.DeleteStateEnum;
import com.cjf.ysxt.enums.InsertStateEnum;
import com.cjf.ysxt.enums.UpdateStateEnum;
import com.cjf.ysxt.exception.NoNumberException;
import com.cjf.ysxt.exception.RepeatException;
import com.cjf.ysxt.service.AdminService;
import com.cjf.ysxt.service.CollegeService;
import com.cjf.ysxt.service.WorkerService;


@Controller
@RequestMapping(value = "admin")
public class AdminController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CollegeService collegeService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private WorkerService workerService;
	
    
	@RequestMapping(value = "queryDepartmentByCollegeName")
    @ResponseBody
    private List<Department> queryDepartmentByCollegeName(
    		HttpServletRequest request, HttpServletResponse response) {
		Worker worker = (Worker)request.getSession().getAttribute("user");
    	String collegeName = worker.getCollegeName();
    	List<Department> departments = adminService.queryDepartmentByCollegeName(collegeName);
    	return departments;
    }
	
    /**
     * 查询所有学院
     * "/admin/queryAllCollege"
     * @return List<College>
     */
    @RequestMapping(value = "queryAllCollege")
    @ResponseBody
    private List<College> queryAllCollege(
    		HttpServletRequest request, HttpServletResponse response) {
    	List<College> colleges = adminService.queryAllCollege();
    	return colleges;
    }
    /**
     * 查询所有工作人员
     * "/admin/queryAllworker"
     * @return List<Worker>
     */
    @RequestMapping(value = "queryAllworker")
    @ResponseBody
    private List<WorkerExecution> queryAllworker(
    		HttpServletRequest request, HttpServletResponse response) {
    	List<WorkerExecution> workerExecutions = adminService.queryAllworker();
    	return workerExecutions;
    }
    
    /**
     * 查询所有部门
     * "/admin/queryAllDepartment"
     * @return List<Department>
     */
    @RequestMapping(value = "queryAllDepartment")
    @ResponseBody
    private List<Department> queryAllDepartment(
    		HttpServletRequest request, HttpServletResponse response) {
    	List<Department> departments = adminService.queryAllDepartment();
    	return departments;
    }
    
    /**
     * 插入学院
     * "/admin/insertCollege"
     * @param collegeName
     * @param address
     * @param head
     * @param status
     * @return Result<CollegeExecution>
     */
    @RequestMapping(value = "insertCollege")
    @ResponseBody
    private Result<CollegeExecution> insertCollege( @Param("collegeName") String collegeName,
    		@Param("address") String address,
    		@Param("head") String head,
    		@Param("status") boolean status,
    		HttpServletRequest request, HttpServletResponse response) {
    	
    	if (collegeName.equals("") || address.equals("") || head.equals("")) {
            return new Result<CollegeExecution>(false, "输入内容不能为空");
        }
    	CollegeExecution execution = null;
        try {
            execution = adminService.insertCollege(collegeName, address, head, status);
        } catch (RepeatException e1) {
            execution = new CollegeExecution(InsertStateEnum.REPEAT_INSERT);
        }  catch (Exception e) {
            execution = new CollegeExecution(InsertStateEnum.INNER_ERROR);
        }
        return new Result<CollegeExecution>(true, execution);
    }
    
    /**
     * 插入工作人员
     * "/admin/insertWorker"
     * @param workerid
     * @param name
     * @param sex
     * @param tel
     * @param title
     * @param address
     * @param birth
     * @param password
     * @param collegeName
     * @param departmentName
     * @return Result<WorkerExecution>
     */
    @RequestMapping(value = "insertWorker")
    @ResponseBody
    private Result<WorkerExecution> insertWorker( @Param("workerid") String workerid,
    		@Param("name") String name,
    		@Param("sex") String sex,
    		@Param("tel") String tel,
    		@Param("title") String title,
    		@Param("address") String address,
    		@Param("birth") String birth,
    		@Param("password") String password,
    		@Param("collegeName") String collegeName,
    		@Param("departmentName") String departmentName,
    		HttpServletRequest request, HttpServletResponse response) {
    	
    	if (workerid.equals("") || name.equals("") || sex.equals("") || tel.equals("")|| title.equals("")
    			|| address.equals("")|| birth.equals("") || password.equals("") || collegeName.equals("")
    			|| departmentName.equals("")) {
            return new Result<WorkerExecution>(false, "输入内容不能为空");
        }
    
    	
    	WorkerExecution execution = null;
        try {
            execution = adminService.insertWorker(workerid, name, sex, tel, title, address, 
            		birth, password, collegeName, departmentName);
        } catch (RepeatException e1) {
            execution = new WorkerExecution(InsertStateEnum.REPEAT_INSERT);
        }  catch (Exception e) {
            execution = new WorkerExecution(InsertStateEnum.INNER_ERROR);
        }
        return new Result<WorkerExecution>(true, execution);
    }
    
    /**
     * 插入部门
     * "/admin/insertDepartment"
     * @param departmentName
     * @param head
     * @param tel
     * @param collegeName
     * @param departmentStatus
     * @return Result<DepartmentExecution>
     */
    @RequestMapping(value = "insertDepartment")
    @ResponseBody
    private Result<DepartmentExecution> insertDepartment(@Param("departmentName") String departmentName,
    		@Param("head") String head,
    		@Param("tel") String tel,
    		@Param("collegeName") String collegeName,
    		@Param("departmentStatus") boolean departmentStatus,    		
    		HttpServletRequest request, HttpServletResponse response) {
    	
    	if (departmentName.equals("") || head.equals("") || tel.equals("")|| collegeName.equals("")) {
            return new Result<DepartmentExecution>(false, "输入内容不能为空");
        }
    	DepartmentExecution execution = null;
        try {
            execution = adminService.insertDepartment(departmentName, head, tel, collegeName, departmentStatus);
        } catch (RepeatException e1) {
            execution = new DepartmentExecution(InsertStateEnum.REPEAT_INSERT);
        }  catch (Exception e) {
            execution = new DepartmentExecution(InsertStateEnum.INNER_ERROR);
        }
        return new Result<DepartmentExecution>(true, execution);
    }
    
    /**
     * 删除一个学院
     * "/admin/deleteCollege"
     * @param collegeName
     * @return Result<CollegeExecution>
     */
    @RequestMapping(value = "deleteCollege")
    @ResponseBody
    private Result<CollegeExecution> deleteCollege( @Param("String") String collegeName,
    		HttpServletRequest request, HttpServletResponse response) {
    	if (collegeName.equals("") ) {
            return new Result<CollegeExecution>(false, "输入内容不能为空");
        }
    	CollegeExecution execution = null;
        try {
            execution = adminService.deleteCollege(collegeName);
        } catch (NoNumberException e1) {
            execution = new CollegeExecution(DeleteStateEnum.NO_NUMBER);
        }  catch (Exception e) {
            execution = new CollegeExecution(DeleteStateEnum.INNER_ERROR);
        }
        return new Result<CollegeExecution>(true, execution);
    }
    
    /**
     * 删除一个工作人员
     * "/admin/deleteWorker"
     * @param workerid
     * @return Result<WorkerExecution>
     */
    @RequestMapping(value = "deleteWorker")
    @ResponseBody
    private Result<WorkerExecution> insertCollege( @Param("workerid") String workerid,
    		HttpServletRequest request, HttpServletResponse response) {
    	if (workerid.equals("") ) {
            return new Result<WorkerExecution>(false, "输入内容不能为空");
        }
    	
    	WorkerExecution execution = null;
        try {
            execution = adminService.deleteWorker(workerid);
        } catch (NoNumberException e1) {
            execution = new WorkerExecution(DeleteStateEnum.NO_NUMBER);
        }  catch (Exception e) {
            execution = new WorkerExecution(DeleteStateEnum.INNER_ERROR);
        }
        return new Result<WorkerExecution>(true, execution);
    }
    
    /**
     * 删除一个部门
     * "/admin/deleteDepartment"
     * @param departmentName
     * @param collegeName
     * @return Result<DepartmentExecution>
     */
    @RequestMapping(value = "deleteDepartment")
    @ResponseBody
    private Result<DepartmentExecution> deleteDepartment( @Param("departmentName") String departmentName,
    		@Param("collegeName") String collegeName,
    		HttpServletRequest request, HttpServletResponse response) {
    	if (departmentName.equals("")||collegeName.equals("") ) {
            return new Result<DepartmentExecution>(false, "输入内容不能为空");
        }
    	DepartmentExecution execution = null;
        try {
            execution = adminService.deleteDepartment(departmentName, collegeName);
        } catch (NoNumberException e1) {
            execution = new DepartmentExecution(DeleteStateEnum.NO_NUMBER);
        }  catch (Exception e) {
            execution = new DepartmentExecution(DeleteStateEnum.INNER_ERROR);
        }
        return new Result<DepartmentExecution>(true, execution);
    }
    
    
	
}
