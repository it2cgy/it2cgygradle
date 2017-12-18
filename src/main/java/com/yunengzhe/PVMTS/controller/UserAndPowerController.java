package com.yunengzhe.PVMTS.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yunengzhe.PVMTS.service.UserAndPowerService;

@Controller
@RequestMapping("/userAndPower")
public class UserAndPowerController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserAndPowerController.class);
	
	@Autowired
	private UserAndPowerService userAndPowerService;

	
	
	@RequestMapping(value="/test")
	public void test(HttpServletRequest request,HttpServletResponse response){
		userAndPowerService.getUserList(21);
	}
}
