package com.yunengzhe.PVMTS.interceptor;
 

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.yunengzhe.PVMTS.controller.PowerStationController;
import com.yunengzhe.PVMTS.controller.UserController;
import com.yunengzhe.PVMTS.domain.vo.PowerStationBaseInfoVO;
import com.yunengzhe.PVMTS.domain.vo.RolesmenuHandleVO;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.service.PowerStationService;
import com.yunengzhe.commons.authentication.HttpSessionUtil;
 

public class CommonInterceptor implements HandlerInterceptor{
	@Autowired
	private PowerStationService powerStationService; 
	
	public void afterCompletion(HttpServletRequest servletRequest,
			HttpServletResponse servletResponse, Object object, Exception exception)
					throws Exception { 
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse servletResponse,
			Object object, ModelAndView modelAndView) throws Exception { 

		String powerStationId = (String) request.getParameter("powerStationId");
		PowerStationBaseInfoVO PowerStationBaseInfoVO = powerStationService.getPowerStationInfo(powerStationId);
		if(modelAndView!=null && StringUtils.isNumeric(powerStationId) && powerStationId!=""){ 
			//modelAndView.addObject(attributeName, attributeValue);
			modelAndView.getModelMap().addAttribute("leyepowerStationId", Integer.valueOf(powerStationId));
			modelAndView.getModelMap().addAttribute("leyepowerStationBaseInfo", PowerStationBaseInfoVO);
			
		}
		UserVO userVO =  (UserVO) HttpSessionUtil.getAttribute(UserController.PVMTS_USER);
		HashMap<String,RolesmenuHandleVO> menuList = null;
		if(userVO!=null){
			menuList = userVO.getMenuList();
		}
		if(menuList!=null){
			if(modelAndView!=null){ 
				modelAndView.getModelMap().addAttribute("menuConfig", menuList); 
			}
		}
		
	}

	public boolean preHandle(HttpServletRequest servletRequest, HttpServletResponse servletResponse,
			Object object) throws Exception {


		return true; 
	}
}
