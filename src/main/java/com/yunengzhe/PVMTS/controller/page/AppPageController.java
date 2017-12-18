package com.yunengzhe.PVMTS.controller.page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yunengzhe.PVMTS.domain.po.VersionInfoPO;
import com.yunengzhe.PVMTS.service.VersionInfoService;

@Controller
@RequestMapping("/appPage")
public class AppPageController {
	private static final Logger logger = LoggerFactory.getLogger(AppPageController.class);
	@Autowired
	private VersionInfoService versionInfoService;
	@RequestMapping(value="/toApp")
	public String toApp(Model model,HttpServletRequest request,HttpServletResponse response){
		String userAgent = request.getHeader("User-Agent");
		logger.info("---------------userAgent ------------>:"+userAgent);
		VersionInfoPO versionInfo = new VersionInfoPO();
		String type="1";//默认安卓下载地址
		if(!userAgent.isEmpty()){
			if(userAgent.contains("Android")){
				logger.info("---------------Android ------------>:");
				type="1";
			}else if(userAgent.contains("iPhone")){
				logger.info("---------------iPhone ------------>:");
				type="2"; 
			} 
			versionInfo = versionInfoService.findUniqueByMap(type);
		}
		return "redirect:"+versionInfo.getDowloadUrl();
	}
	@RequestMapping(value="/toApp/{type}")
	public String toApp(Model model,HttpServletRequest request,HttpServletResponse response,@PathVariable String type){
		if((!"1".equals(type)) && (!"2".equals(type))){
			type = "1";
		}
		VersionInfoPO versionInfo = versionInfoService.findUniqueByMap(type);
		return "redirect:"+versionInfo.getDowloadUrl();
	}
}
