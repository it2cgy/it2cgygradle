package com.yunengzhe.PVMTS.controller.page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yunengzhe.PVMTS.controller.MeteorInfoController;
import com.yunengzhe.PVMTS.domain.po.MeteorInfoPO;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.service.MeteorInfoService;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;

@Controller
@RequestMapping("/weatherPageController")
public class weatherPageController {

	@Autowired
	private MeteorInfoService meteorInfoService;
	/**
	 * @Title:userListPage
	 * @Description:TODO(用一句话描述一下这个方式的作用) 
	 * @param 
	 * @return void
	 * @throws
	 */
	@RequestMapping(value="/weatherPage/{powerStationId}")
	public String weatherPage(Model model,HttpServletRequest request,HttpServletResponse response,@PathVariable int powerStationId){
		UserVO user = TokenUtil.getLoginUser();
		if(user == null){
			return "redirect:user/login";
		} 
		MeteorInfoPO meteor = meteorInfoService.getMeteor(powerStationId);
		model.addAttribute("meteor",meteor);
		if(user.getRoleList().get(0).getRoleId()!=4){
			return "/weatherstation/weatherstation";
		}else{
			return "/weatherstation/thirdweatherstation";
		}
	}
}
