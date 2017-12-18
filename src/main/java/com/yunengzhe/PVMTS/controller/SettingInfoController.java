package com.yunengzhe.PVMTS.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunengzhe.PVMTS.domain.po.SettingInfoPO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.domain.response.RestResponseVO;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.service.SettingInfoService;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;

@Controller
@RequestMapping("/settingInfo")
public class SettingInfoController {
	
	private static final Logger logger = LoggerFactory.getLogger(SettingInfoController.class);
	
	@Autowired
	private SettingInfoService settingInfoService;
	/**
	 * 修改推送设置
	 * @param setting
	 * @return已测
	 */ 
	@RequestMapping(value="updateUserSetting",method=RequestMethod.POST)
	@ResponseBody
	public RestResponseVO updateUserSetting(@RequestBody SettingInfoPO setting,HttpServletRequest request,HttpServletResponse response){
		boolean flag = false;
		UserVO user = TokenUtil.getLoginUser();
		if(user==null){
			return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
		}
		try {
			setting.setUserId(user.getUserid());
			SettingInfoPO setting2 = settingInfoService.getSetting(user.getUserid());
			if(setting2==null){
				flag = settingInfoService.insert(setting);
			}else{
				flag = settingInfoService.updateSetting(setting);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage(),e);
			return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "系统出错");
		}
		return RestResponse.responseOk(flag);
	}
	/**
	 * 获取当前消息设置
	 * @param setting
	 * @return已测
	 */ 
	@RequestMapping(value="getSetting",method=RequestMethod.POST)
	@ResponseBody
	public RestResponseVO getSetting(HttpServletRequest request,HttpServletResponse response){
		boolean flag = false;
		UserVO user = TokenUtil.getLoginUser();
		if(user==null){
			return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
		}
		SettingInfoPO setting = null;
		try {
			setting = settingInfoService.getSetting(user.getUserid());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage(),e);
			return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "系统出错");
		}
		return RestResponse.responseOk(setting);
	}
}
