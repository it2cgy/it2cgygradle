package com.yunengzhe.PVMTS.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yunengzhe.PVMTS.service.MenusService;


/**
 * @ClassName:MenusController
 * @Description:TODO(用户菜单管理)
 * @author chenguiyang
 * @date 2017年8月3日下午4:33:55
 */
@Controller
@RequestMapping("/menus")
public class MenusController {
	
	private static final Logger logger = LoggerFactory.getLogger(MenusController.class);
	
	@Autowired
	private MenusService menusService;
	
	
	

}
