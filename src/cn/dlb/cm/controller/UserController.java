package cn.dlb.cm.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dlb.cm.entity.User;
import cn.dlb.cm.service.user.UserService;

import com.google.gson.Gson;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	
	@Resource
	private UserService userService;
	/**
	 * 
	 * 方法描述:跳转到登录界面
	 * @return 跳转/user/login.jsp
	 * 作者:路遥
	 */
	@RequestMapping("/loginUI.action")
	public String loginUI(){
		return "/user/login.jsp";
	}
	//展示顶部数据
	@RequestMapping("/showTop.action")
	public String showTop(){
		return "/top.jsp";
	}
	//
	
	//展示Iframe
	@RequestMapping("/showFrame.action")
	public String showFrame(Model model){
		List <String> departList=userService.findAllDepart();
		
		List <String> contractTypeList=userService.findAllContractType();
		
		model.addAttribute("depart", departList);
		model.addAttribute("contractType", contractTypeList);
		return "/frame.jsp";
	}
	//退出功能
	@RequestMapping("/exit.action")
	public String exit(HttpSession session){
		session.invalidate();
		
		return "/user/login.jsp";
	}
	
	//跳转个人中心
	@RequestMapping("/recordHistory.action")
	public String recordHistory(HttpSession session){
		return "/personalCenter/recordHistory.jsp";
	}
	
	@RequestMapping("dataAnalysis.action")
	public String dataAnalysis(Integer id){
		return "totalCompanyDataAnalysis.html";//partCompanyDataAnalysis.html
	}
	
	/**路遥
	 * 时间 2016-11-28
	 * 页面跳转
	 */
	@RequestMapping("/systemManage.action")
	public String systemManage(){
		return "/manageSetting.html";
	}
		
	/**
	 * 方法简述：进入管理页面
	 * @return
	 * 时间：2016-11-25
	 * 作者：刘群
	 */
	@RequestMapping("/manageSetting.action")
	public String manageSetting(){
		return "/manageSetting.html";
	}
	
	
	
	/**
	 * 方法简述：进入用户设置页面
	 * @return
	 * 时间：2016-11-25
	 * 作者：刘群
	 */
	@RequestMapping("/userSetting.action")
	public String userSetting(){
		return "/userSetting.html";
	}
	
	/**
	 * 
	 * 方法描述:检测用户名密码是否正确,正确
	 * @return 正确跳转 /main.jsp,错误跳转回/user/login.jsp
	 * 作者:路遥
	 */
	@RequestMapping("/login.action")
	public String login(User user,HttpSession session,Model model){
		if(user==null||user.getUsername()==null||user.getPassword()==null){
			return "/user/login.jsp";
		}
		User queryResult=userService.check(user);
		if(queryResult==null){
			model.addAttribute("msg", "账户名或密码有误!");
			return "/user/login.jsp";
		}
		session.setAttribute("user", queryResult);
		session.setAttribute("username", queryResult.getTrueName());
		return "/main.jsp";
	}
	
	
	/**
	 * 方法简述：
	 * @return
	 * 时间：2016-11-25
	 * 作者：刘群
	 */
	@RequestMapping("getUserWithPaging.action")
	@ResponseBody
	public String getUserWithPaging(String condition,int rows,int page){
		
		Map<String, Object> map = userService.getUserWithPaging(condition, rows, page);
		String result = JSONObject.fromObject(map).toString();
		System.out.println(result);
		return result;
	}
	
	@RequestMapping("jumpSetRole.action")
	public String jumpSetRole(Integer id){
		System.out.println("id:"+id);
		return "setRole.html?id="+id;
	}
	
	/**
	 * 方法简述：选择角色
	 * @param roleIds
	 * @param userId
	 * @return
	 * 时间：2016-11-30
	 * 作者：刘群
	 */
	@RequestMapping("setRole.action")
	@ResponseBody
	public int setRole(Integer[] roleIds,Integer userId){
		System.out.println("user:"+userId);
		return userService.setRole(roleIds,userId);
	}
	
	@RequestMapping("update.action")
	@ResponseBody
	public int update(User user){
		return userService.update(user);
	}
	
	@RequestMapping("add.action")
	@ResponseBody
	public int add(User user){
		return userService.add(user);
	}
	@RequestMapping("resetPassword.action")
	@ResponseBody
	public int resetPassword(Integer id){
		return userService.resetPassword(id);
	}
	
	@RequestMapping("delete.action")
	@ResponseBody
	public int delete(Integer id){
		return userService.delete(id);
	}
	
	/**
	 * 方法简述：批量删除用户
	 * @param ids
	 * @return
	 * 时间：2016-11-29
	 * 作者：刘群
	 */
	@RequestMapping("deleteByIds.action")
	@ResponseBody
	public int deleteByIds(Integer[] ids){
		System.out.println(ids);
		return userService.deleteByIds(ids);
	}
	
	
}
