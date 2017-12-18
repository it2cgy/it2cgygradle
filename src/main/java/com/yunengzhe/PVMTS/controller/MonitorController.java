package com.yunengzhe.PVMTS.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunengzhe.common.util.TimeUtil;


@Controller
@RequestMapping("/monitorController")
public class MonitorController {

	@RequestMapping(value = "/test",method = RequestMethod.GET)
	@ResponseBody
	public Object test(HttpServletResponse response){
		Map<String,String> result = new HashMap<String,String>();
		Date date = new Date();
		result.put("backData", TimeUtil.date2String(date,"yyyy-MM-dd HH:mm:ss"));
		return result;
	}
}
