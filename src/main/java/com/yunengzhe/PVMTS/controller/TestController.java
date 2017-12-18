package com.yunengzhe.PVMTS.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunengzhe.PVMTS.service.TestService;

/**
 * <p>Title: TestController</p>
 * @author ynz
 * <p>Description</p>
 * @date 2017年11月21日 下午1:09:08
 */
@Controller
@RequestMapping("/test")
public class TestController {
	
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	private TestService testService;

	@RequestMapping(value="/logcat")
	@ResponseBody
	public void logcat(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String,Object> map){
		try {
			logger.info("APP应用开始记录进程信息");
			String info = map.get("infos").toString();
			testService.insertTestInfo(info);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}
