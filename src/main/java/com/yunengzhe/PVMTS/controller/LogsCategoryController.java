package com.yunengzhe.PVMTS.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunengzhe.PVMTS.domain.po.LogsCategoryPO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.domain.response.RestResponseVO;
import com.yunengzhe.PVMTS.service.LogsCategoryService;

@Controller
@RequestMapping("/logsCategory")
public class LogsCategoryController {
	
	private static final Logger logger = LoggerFactory.getLogger(LogsCategoryController.class);
	
	@Autowired
	private LogsCategoryService logsCategoryService;
	@RequestMapping(value="/getLogsCateList",method=RequestMethod.GET)
	@ResponseBody
	public RestResponseVO getLogsCateList(){
		List<LogsCategoryPO> list = logsCategoryService.getLogsCateList();
		return RestResponse.responseOk(list);
	}
	 
	@RequestMapping(value="/addLogsCate",method=RequestMethod.POST)
	@ResponseBody
	public RestResponseVO addLogsCate(HttpServletRequest request,HttpServletResponse response,@RequestBody LogsCategoryPO po) {
		logsCategoryService.addLogsCate(po);
		LogsCategoryPO logsCateById = logsCategoryService.getLogsCateById(po.getId());
		return RestResponse.responseOk(logsCateById);
	}
	@RequestMapping(value="/deleteLogsCate/{id}",method=RequestMethod.GET)
	@ResponseBody
	public RestResponseVO deleteLogsCate(HttpServletRequest request,HttpServletResponse response,@PathVariable Integer id) {
		boolean flag = logsCategoryService.deleteLogsCate(id);
		return RestResponse.responseOk(flag);
	}
}
