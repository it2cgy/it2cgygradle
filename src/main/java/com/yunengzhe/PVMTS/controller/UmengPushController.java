package com.yunengzhe.PVMTS.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.mortbay.util.ajax.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunengzhe.PVMTS.service.UmengPushService;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;

/**
 * 友盟推送设置
 * @author ynz
 *
 */
@Controller
@RequestMapping("/umengPush")
public class UmengPushController {
	
	private static final Logger logger = LoggerFactory.getLogger(UmengPushController.class);
	
	@Autowired
	private UmengPushService umengPushService;
	
	/**
	 * 登录用户id与登录设备deviceToken绑定
	 * @return
	 */
	@RequestMapping(value = "/binding",method = RequestMethod.POST)
	@ResponseBody
	public JSONObject bindingToken(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String,String> map){
		JSONObject json = new JSONObject();
		logger.info("开始绑定用户设备id");
		logger.info("设备deviceToken："+map.get("deviceToken"));
		try {
			
			String deviceToken = map.get("deviceToken");
			String deviceType = map.get("deviceType");
			if(StringUtils.isBlank(deviceToken)){
				json.put("message", "无法获取设备token！");
				return json;
			}
			if(StringUtils.isBlank(deviceType)){
				json.put("message", "无法获取设备类型！");
				return json;
			}
			String token = request.getHeader("token");
			if(StringUtils.isBlank(token)){
				json.put("message","无法获取token数据");
				return json;
			}
			int userId = TokenUtil.getUserIdByToken(token);
			logger.info("获取用户id:"+userId);
			umengPushService.bindingToken(userId, deviceToken,deviceType);
			json.put("message","绑定成功");
			return json;
		} catch (Exception e) {
			json.put("message","系统异常");
			logger.error(e.getMessage(),e);
			return json;
		}
	}

}
