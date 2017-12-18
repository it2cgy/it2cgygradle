package com.yunengzhe.PVMTS.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunengzhe.PVMTS.domain.dto.UserGetuiDTO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.service.UserGetuiService;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;


/**
 * @ClassName:UserGetuiController
 * @Description:TODO(用户消息推送处理)
 * @author chenguiyang
 * @date 2017年7月3日下午2:06:52
 */
@Controller
@RequestMapping("/userGetui")
public class UserGetuiController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserGetuiController.class);
	
	@Autowired
	private UserGetuiService userGetuiService;

	/**
	 * @Title:setClientID
	 * @Description:TODO(设置用户设备与用户id绑定) 
	 * @param @param request
	 * @param @param response
	 * @return void
	 * @throws
	 */
	@RequestMapping(value="/setClientID",method=RequestMethod.POST)
	@ResponseBody
	public Object setClientID(@RequestBody UserGetuiDTO param,HttpServletRequest request,HttpServletResponse response){
		try {
			 String token = request.getHeader("token");
			 if(StringUtils.isBlank(token)){//Token为空
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "token无效！");
			 }
			 int userId = TokenUtil.getUserIdByToken(token);//通过token获取用户信息
			 if(userId==0){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "token无效！");
			 }
			 if(StringUtils.isBlank(param.getClientId())){
				 return RestResponse.response(HttpServletResponse.SC_BAD_REQUEST, "设备id为空了!!!");
			 }
			 if(StringUtils.isBlank(param.getClientType())){
				 return RestResponse.response(HttpServletResponse.SC_BAD_REQUEST, "设备类型为空了!!!"); 
			 }
			 userGetuiService.setClientID(userId, param.getClientId(), param.getClientType());
			 return RestResponse.responseOk(true);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	
	/**
	 * @Title:getClientID
	 * @Description:TODO(根据用户id查询设备id) 
	 * @param @return
	 * @return Object
	 * @throws
	 */
	@RequestMapping(value="/getClientID",method=RequestMethod.GET)
	@ResponseBody
	public Object getClientID(HttpServletRequest request,HttpServletResponse response){
		try {
			 String token = request.getHeader("token");
			 if(StringUtils.isBlank(token)){//Token为空
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "token无效！");
			 }
			 int userId = TokenUtil.getUserIdByToken(token);//通过token获取用户信息
			 if(userId==0){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "token无效！");
			 }
			 String cid = userGetuiService.getClientID(userId);
			 return RestResponse.responseOk(cid);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
}
