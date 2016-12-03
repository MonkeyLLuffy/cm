package cn.dlb.cm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import cn.dlb.cm.entity.Department;
import cn.dlb.cm.service.department.DepartmentService;

@Controller
@RequestMapping("/department")
public class DepartmentController extends BaseController{
	@Resource
	DepartmentService service;
	/**
	 * 方法简述：部门设置
	 * @return
	 * 时间：2016-11-28
	 * 作者：刘群
	 */
	@RequestMapping("/departmentSetting.action")
	public String departmentSetting(){
		return "/departmentSetting.html";
	}

	/**
	 * 方法简述：新增一个部门
	 * @param departmentName
	 * @return
	 * 时间：2016-11-28
	 * 作者：刘群
	 */
	@RequestMapping("/addDepartment.action")
	@ResponseBody
	public String addDepartment(Department department) {
		System.out.println(department.getDepartmentName());
		return service.addDepartment(department);
	}
	
	
	@RequestMapping("/delete.action")
	@ResponseBody
	public String delete(Integer id) {
		return service.delete(id)+"";
	}
	@RequestMapping("deleteByIds.action")
	@ResponseBody
	public int deleteByIds(Integer[] ids){
		return service.deleteByIds(ids);
	}
	
	
	/**
	 * 方法简述：部门列表
	 * @param condition
	 * @param rows
	 * @param page
	 * @return
	 * 时间：2016-11-27
	 * 作者：刘群
	 */
	@RequestMapping("getDepartmentWithPaging.action")
	@ResponseBody
	public String getDepartmentWithPaging(String condition,int rows,int page){
		Map<String, Object> map = service.getDepartmentWithPaging(condition,rows,page);
		String resultString = new Gson().toJson(map).toString();
		System.out.println(resultString);
		return resultString;
	}
	
	
	
}
