package com.yunengzhe.PVMTS.controller.page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunengzhe.PVMTS.controller.InverterController;
import com.yunengzhe.PVMTS.controller.PowerStationController;
import com.yunengzhe.PVMTS.domain.response.RestResponseVO;
import com.yunengzhe.PVMTS.domain.vo.InverterDetailsVO;
import com.yunengzhe.PVMTS.domain.vo.PowerStationBaseInfoVO;
import com.yunengzhe.PVMTS.domain.vo.PowerStationDetailsVO;
import com.yunengzhe.PVMTS.domain.vo.PowerStationListVO;
import com.yunengzhe.PVMTS.domain.vo.RolesmenuHandleVO;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.service.PowerStationService;
import com.yunengzhe.PVMTS.service.RolesMenusService;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;
import com.yunengzhe.commons.authentication.HttpSessionUtil;

@Controller

public class IndexPageController {
	
	@Autowired 
	private static final Logger logger = LoggerFactory.getLogger(IndexPageController.class);
	

	@Autowired
	private PowerStationService powerStationService;
	@Autowired
	private RolesMenusService rolesMenusService;
	
	@RequestMapping("/index")
	public String index(Model model, HttpServletRequest request,HttpServletResponse response){
		UserVO user = TokenUtil.getLoginUser();
		if(user == null){
			return "redirect:/user/login";
		} 
		Boolean indexflag = false;
		Boolean subIndexflag = false;
		Boolean isAdmin = false;
		for(int i=0;i<user.getRoleList().size();i++){
			if(user.getRoleList().get(i).getRoleId()==4){//第三方角色
				subIndexflag = true;
			}
			
			if(user.getRoleList().get(i).getRoleId()==3){//集控中心角色
				indexflag = true;
			}
			
			if(user.getRoleList().get(i).getRoleId()==2){//公司管理员
				isAdmin = true;
			}
		}
//		List<RolesmenuHandleVO> roleList = rolesMenusService.getRoleMenuList(user.getRoleList().get(0).getRoleId());
		if(isAdmin){
			model.addAttribute("admin",true);
		}
//		if(indexflag || isAdmin){//集控中心首页  
//			model.addAttribute("admin",true);
//			return "/index/index";
//		}
		
		if(subIndexflag){ 
			return "/index/thirdIndex";//第三方首页
		}else{
			return "/index/index";
		}
	  
	}
	
	@RequestMapping("/index/{powerStationId}")
	public String sunIndex(Model model, HttpServletRequest request,HttpServletResponse response,@PathVariable Integer powerStationId){
		UserVO user = TokenUtil.getLoginUser();
		if(user == null){
			return "redirect:/user/login";
		} 
		
		 
		
		PowerStationBaseInfoVO result = powerStationService.getPowerStationInfo(""+powerStationId);
		model.addAttribute("leyepowerStationBaseInfo", result);
		model.addAttribute("powerstation",result);
		model.addAttribute("leyepowerStationId", powerStationId);
		//HttpSessionUtil.setAttribute("leyepowerStationId", powerStationId);
		return "/index/subsiteIndex";
	}
}
