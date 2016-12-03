package cn.dlb.cm.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dlb.cm.service.permission.PermissionService;

@Controller
@RequestMapping("/permission")
public class PermissionController extends BaseController{

	@Resource
	PermissionService service;
	/**
	 * 方法简述：获取三级权限列表
	 * @return
	 * 时间：2016-11-30
	 * 作者：刘群
	 */
	@RequestMapping("getPermission.action")
	@ResponseBody
	public String getPermission() {
		return service.getPermission();
	}
	
	/**
	 * 方法简述：获取改角色的所有权限
	 * @param roleId
	 * @return
	 * 时间：2016-11-30
	 * 作者：刘群
	 */
	@RequestMapping("getRolePermission.action")
	@ResponseBody
	public String getRolePermission(Integer roleId) {
		return service.getRolePermission(roleId);
	}
	
}
