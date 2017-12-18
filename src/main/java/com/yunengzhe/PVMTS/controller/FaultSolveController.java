package com.yunengzhe.PVMTS.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yunengzhe.PVMTS.domain.dto.FaultSolveDTO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.service.FaultSolveService;


/**
 * @ClassName:FaultSolveController
 * @Description:TODO(故障处理  记录处理人员 处理时间 处理说明 附件等)
 * @author chenguiyang
 * @date 2017年6月20日下午1:29:38
 */
@Controller
@RequestMapping("/faultSolve")
public class FaultSolveController {
	
	private static final Logger logger = LoggerFactory.getLogger(FaultSolveController.class);
	
	@Autowired
	private FaultSolveService faultSolveService;

	
	
	
	/**
	 * @Title:faultSolveInfo
	 * @Description:TODO(故障详情接口 --包含处理人员 处理时间 以及故障的基本信息) 
	 * @param @return
	 * @return Object
	 * @throws
	 */
	@RequestMapping(value="/getSolveInfo")
	public Object getSolveInfo(){
	    
		
		return null;
	}
	
	
	/**
	 * @Title:faultSolve
	 * @Description:TODO(插入处理人员  处理时间等信息处理故障) 
	 * @param @return
	 * @return Object
	 * @throws
	 */
	@RequestMapping(value="/updateSolveInfo")
	public Object updateSolveInfo(@RequestBody FaultSolveDTO param){
		try {
			
			 return null;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	
}
