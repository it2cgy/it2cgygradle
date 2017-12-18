package com.yunengzhe.PVMTS.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunengzhe.PVMTS.domain.po.CompanyPO;
import com.yunengzhe.PVMTS.domain.po.UserPO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.service.CompanyService;
import com.yunengzhe.PVMTS.service.UserService;
import com.yunengzhe.commons.authentication.HttpSessionUtil;

 
@Controller
@RequestMapping("/global")
public class GlobalController {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalController.class);
	 
	@RequestMapping(value="/local/{local}")
	@ResponseBody
	public Object changeLocal(HttpServletRequest request,HttpServletResponse response,@PathVariable String local){ 
		try {  
			HttpSessionUtil.setAttribute("local", local);
			return RestResponse.responseOk();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	
	
}
