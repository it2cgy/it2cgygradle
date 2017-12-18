package com.yunengzhe.PVMTS.controller;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/bindingToken")
public class BindingTokenController {
	private static final Logger logger = LoggerFactory.getLogger(BindingTokenController.class);

	
	@RequestMapping(value = "/binding",method = RequestMethod.POST)
	@ResponseBody
	public JSONObject bindingToken(){
		
		logger.info("开始绑定用户设备id");
		JSONObject json = new JSONObject();
		json.put("message","绑定成功");
		return json;
	}
}
