package com.yunengzhe.PVMTS.controller.page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yunengzhe.PVMTS.domain.po.AlarmInfoPO;
import com.yunengzhe.PVMTS.domain.vo.PowerStationListVO;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.service.AlarmInfoService;
import com.yunengzhe.PVMTS.service.PowerStationService;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;

/**
 * @ClassName:AlarmPageController
 * @Description:TODO(报警管理)
 * @author chenguiyang
 * @date 2017年7月4日上午9:27:31
 */
@Controller
@RequestMapping("/alarmPage")
public class AlarmPageController {
	private static final Logger logger = LoggerFactory.getLogger(AlarmPageController.class);
	
	@Autowired
	private AlarmInfoService alarmInfoService;
	@Autowired
	private PowerStationService powerStationService;
	
	@RequestMapping(value="/configList")
	public String userListPage(Model model,HttpServletRequest request,HttpServletResponse response,String admin){
		logger.info("--------------->公司用户列表页面");
		UserVO user = TokenUtil.getLoginUser();
		if(user == null){
			return "redirect:user/login";
		} 
		model.addAttribute("admin",true);
		
		return "/alarm/configList";
	}
	@RequestMapping(value="/configEditList")
	public String userEditListPage(Model model,HttpServletRequest request,HttpServletResponse response,String admin,Integer id){
		logger.info("--------------->公司用户列表页面");
		UserVO user = TokenUtil.getLoginUser();
		if(user == null){
			return "redirect:user/login";
		} 
		model.addAttribute("admin",true);
		model.addAttribute("id",id);
		
		return "/alarm/configEdit";
	}
	
	@RequestMapping(value="/configAdd")
	public String configAdd(Model model,HttpServletRequest request,HttpServletResponse response,String admin){
		logger.info("--------------->公司用户列表页面");
		UserVO user = TokenUtil.getLoginUser();
		if(user == null){
			return "redirect:user/login";
		} 
		model.addAttribute("admin",true);
		
		return "/alarm/configAdd";
	}
	
	/**
	 * @Title:userListPage
	 * @Description:TODO(跳转到历史报警页面) 
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value="/historyAlarm")
	public String historyAlarm(HttpServletRequest request,HttpServletResponse response){
		logger.info("--------------->跳转到历史报警页面");
		UserVO user = TokenUtil.getLoginUser();
		if(user == null){
			return "redirect:user/login";
		} 
		return "/alarm/historyAlarm";
	}
	
	
	/**
	 * @Title:userListPage
	 * @Description:TODO(跳转到实时报警页面) 
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value="/actualAlarm")
	public String actualAlarm(HttpServletRequest request,HttpServletResponse response){
		logger.info("--------------->跳转到实时报警页面");
		UserVO user = TokenUtil.getLoginUser();
		if(user == null){
			return "redirect:user/login";
		} 
		return "/alarm/actualAlarm";
	}
	/**
	 * @Title:userListPage
	 * @Description:TODO(跳转到确认报警页面) 
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value="/affirmAlarm")
	public String affirmAlarm(Model model,HttpServletRequest request,HttpServletResponse response,Integer id){
		logger.info("--------------->跳转到实时报警页面");
		UserVO user = TokenUtil.getLoginUser();
		if(user == null){
			return "redirect:user/login";
		} 
		AlarmInfoPO alarmByid = alarmInfoService.getAlarmDispose(id);
		PowerStationListVO powerInfoById = powerStationService.getPowerInfoById(alarmByid.getPowerStationId()+"");
		model.addAttribute("alarm", alarmByid);
		model.addAttribute("power", powerInfoById);
		return "/alarm/alarmDispose";
	}
	
	/**
	 * @Title:userListPage
	 * @Description:TODO(跳转到确认报警页面) 
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value="/affirmAlarmInfo")
	public String affirmAlarmInfo(Model model,HttpServletRequest request,HttpServletResponse response,Integer id){
		logger.info("--------------->跳转到实时报警页面");
		UserVO user = TokenUtil.getLoginUser();
		if(user == null){
			return "redirect:user/login";
		} 
		AlarmInfoPO alarmByid = alarmInfoService.getAlarmDispose(id);
		PowerStationListVO powerInfoById = powerStationService.getPowerInfoById(alarmByid.getPowerStationId()+"");
		model.addAttribute("alarm", alarmByid);
		model.addAttribute("power", powerInfoById);
		return "/alarm/alarmDisposeInfo";
	}
	
	

}
