package cn.dlb.cm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.dlb.cm.entity.PageBean;
import cn.dlb.cm.service.recordHistory.RecordHistoryService;

@Controller
@RequestMapping("/history")
public class RecordHistoryController {
	
	@Autowired
	private RecordHistoryService historyService;
	
	
	/**
	 * 
	 * 方法描述:返回各种历史记录,通过前台传来需要的类型和搜索条件来查询出不同结果
	 * @param bean
	 * @param model
	 * @param id
	 * @param user
	 * @param contractName
	 * @param userType
	 * @param type
	 * @return 返回要显示的网页的url
	 * 时间:2016-11-29
	 * 作者:路遥
	 */
	//&type=${type}&id=${id}&user=${user}&contractName=${contractName}&userType=${userType}
	@RequestMapping("/downHistory.action")
	public String downloadHistory(PageBean bean,Model model,String id,String user,String contractName,String userType,String type){
		
		PageBean pageBean=historyService.getDownloadHistory(bean, id, user, contractName, userType,type);
		//将查询条件和分页结果传到前台
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("id", id);
		model.addAttribute("userType", userType);
		model.addAttribute("contractName", contractName);
		model.addAttribute("user", user);
		model.addAttribute("type", type);
		return "/personalCenter/downloadRecord.jsp";
	}
	
	
}
