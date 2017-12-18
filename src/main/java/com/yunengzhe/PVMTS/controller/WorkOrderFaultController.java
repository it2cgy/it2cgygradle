package com.yunengzhe.PVMTS.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunengzhe.PVMTS.domain.po.WorkOrderFaultPO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.service.WorkOrderFaultService;



/**
 * @ClassName:WorkOrderFaultController
 * @Description:TODO(工单与故障信息管理处理)
 * @author chenguiyang
 * @date 2017年6月7日上午11:32:25
 */
@Controller
@RequestMapping("/workOrderFault")
public class WorkOrderFaultController {
	
	private static final Logger logger = LoggerFactory.getLogger(WorkOrderFaultController.class);
	
	@Autowired
	private WorkOrderFaultService workOrderFaultService;

	
	
	/**
	 * @Title:faultList
	 * @Description:TODO(获取工单下的故障列表) 
	 * @param @return
	 * @return JSONObject
	 * @throws
	 */
	@RequestMapping(value="/faultList/{orderNum}",method=RequestMethod.GET)
	@ResponseBody
	public Object faultList(@PathVariable String orderNum,HttpServletRequest request,HttpServletResponse response){
		try {
			logger.debug("--------------#根据工单编号获取故障列表");
			List<WorkOrderFaultPO> orderInfo = workOrderFaultService.getFaultInfo(orderNum);
			if(orderInfo == null){
				return RestResponse.response(HttpServletResponse.SC_BAD_REQUEST, "获取工单下的故障列表失败或无故障信息！");
			}
			return RestResponse.responseOk(orderInfo);
		} catch (Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
}
