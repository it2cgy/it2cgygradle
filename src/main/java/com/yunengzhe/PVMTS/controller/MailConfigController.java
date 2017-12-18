package com.yunengzhe.PVMTS.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunengzhe.PVMTS.AlarmThread;
import com.yunengzhe.PVMTS.domain.dto.MailConfigDTO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.service.MailConfigService;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;

/**
 * @ClassName:MailConfigController
 * @Description:TODO()
 * @author chenguiyang
 * @date 2017年9月11日下午4:19:15
 */
@Controller
@RequestMapping("/mailConfig")
public class MailConfigController {
	
	private static final Logger logger = LoggerFactory.getLogger(MailConfigController.class);

	@Autowired
	private MailConfigService mailConfigService;
	
	
	
	
	/**
	 * @Title:updateConfig
	 * @Description:TODO(更新邮件推送设置) 
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @return Object
	 * @throws
	 */
	@RequestMapping(value="/updateMail")
	@ResponseBody
	public Object updateMail(@RequestBody MailConfigDTO param ,HttpServletRequest request,HttpServletResponse response){
		try {
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			mailConfigService.update(param);
			return RestResponse.responseOk();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "系统异常");	
		}
	}
	
	
	
}
