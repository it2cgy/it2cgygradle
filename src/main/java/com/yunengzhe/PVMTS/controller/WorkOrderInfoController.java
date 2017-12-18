package com.yunengzhe.PVMTS.controller;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.yunengzhe.PVMTS.domain.dto.AddOrderDTO;
import com.yunengzhe.PVMTS.domain.dto.CompletionDTO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.domain.response.page.Paginator;
import com.yunengzhe.PVMTS.domain.vo.MaintenanceUserVO;
import com.yunengzhe.PVMTS.domain.vo.PowerStationBaseInfoVO;
import com.yunengzhe.PVMTS.domain.vo.ResultListVO;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.domain.vo.WorkOrderInfoVO;
import com.yunengzhe.PVMTS.service.PowerStationService;
import com.yunengzhe.PVMTS.service.WorkOrderInfoService;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;
import com.yunengzhe.commons.authentication.HttpSessionUtil;



/**
 * @ClassName:WorkOrderInfoController
 * @Description:TODO(工单处理Controller)
 * @author chenguiyang
 * @date 2017年6月6日上午9:47:13
 */
@Controller
@RequestMapping("/workOrderInfo")
public class WorkOrderInfoController {
	private static final Logger logger = LoggerFactory.getLogger(WorkOrderInfoController.class);
	
	@Autowired
	private WorkOrderInfoService workOrderInfoService;
	
	@Autowired
	private PowerStationService powerStationService;
	
	/**
	 * @Title:orderlist
	 * @Description:TODO(获取工单列表)
	 * @author state 0-进行中 1为已完成  没有全部查询的情况 
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @return WorkOrderInfoVO
	 * @throws
	 */
	@RequestMapping(value="/orderlist/{state}",method=RequestMethod.GET)
	@ResponseBody
	public Object orderlist(@PathVariable String state,Integer page,Integer pagesize,HttpServletRequest request,HttpServletResponse response){
		try {
			 String token = request.getHeader("token");
			 if(StringUtils.isBlank(token)){//Token为空
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "token无效！");
			 }
			 int userId = TokenUtil.getUserIdByToken(token);//通过token获取用户信息
			 if(userId==0){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "token无效！");
			 }
			 if(page==null){
				page = 1;
			 }
			 if(pagesize==null){
				pagesize = Paginator.DEFAULT_PAGESIZE;
			 }
			 ResultListVO<WorkOrderInfoVO> workOrderInfoListVO =workOrderInfoService.orderList(userId,state,Integer.valueOf(page),Integer.valueOf(pagesize));
			 return RestResponse.responseList(request, workOrderInfoListVO.getCounts(), workOrderInfoListVO.getResultList()); 
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	
	/**
	 * @Title:createOrder
	 * @Description:TODO(生成工单) 
	 * @param 
	 * @return void
	 * @throws
	 */
	@RequestMapping(value="/addWorkOrder",method=RequestMethod.POST)
	@ResponseBody
	public Object addWorkOrder(@RequestBody AddOrderDTO orderDTO){
		try {
			logger.debug("#--------------------------生成工单------------------------#");
			if(orderDTO.getFaultInfo()==null){
				return RestResponse.response(HttpServletResponse.SC_BAD_REQUEST, "请选择故障信息！");
			}
			if(orderDTO.getPhysical()==null){
				return RestResponse.response(HttpServletResponse.SC_BAD_REQUEST, "请选择电站体检检查项目！");
			}
			if(orderDTO.getDepartture()==null){
				return RestResponse.response(HttpServletResponse.SC_BAD_REQUEST, "请选择离场检查项目！");
			}
			if(orderDTO.getPowerstationId()==null || orderDTO.getPowerstationName()==null){
				return RestResponse.response(HttpServletResponse.SC_BAD_REQUEST, "请选择电站！");
			}
			int flag = workOrderInfoService.addWorkOrder(orderDTO);
			if(flag==0){
				return RestResponse.response(HttpServletResponse.SC_BAD_REQUEST, "工单生成失败！");
			}
			return RestResponse.responseOk(true);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	
	/**
	 * @Title:orderSubmit
	 * @Description:TODO(工单提交) 
	 * @param orderNum 表单编号  isFinish任务是否已经全部处理
	 * @return void
	 * @throws
	 */
	@RequestMapping(value="/orderSubmit/{orderId}/{orderNum}",method=RequestMethod.POST)
	@ResponseBody
	public Object orderSubmit(@PathVariable int orderId,@PathVariable String orderNum,HttpServletRequest request,HttpServletResponse response){
		try {
			String token = request.getHeader("token");
			if(StringUtils.isBlank(token)){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "token无效！");
			}
			Boolean isLogin = TokenUtil.isLogin(token);
			if(!isLogin){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "请先登录！");
			}
			int flag = workOrderInfoService.orderSubmit(orderId,orderNum);
			if(flag==0){
				return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "工单提交失败！");
			}
			return RestResponse.responseOk(true);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	
	
	/**
	 * @Title:physicalSubmit
	 * @Description:TODO(电站体检提交-完成体检) 
	 * @param @return
	 * @return JSONObject
	 * @throws
	 */
	@RequestMapping(value="/physicalSubmit/{orderId}",method=RequestMethod.POST)
	@ResponseBody
	public Object physicalSubmit(@PathVariable int orderId,HttpServletRequest request,HttpServletResponse response){
		try {
			int flag = workOrderInfoService.physicalSubmit(orderId);
			if(flag==0){
				return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "完成体检提交失败！");
			}
			return RestResponse.responseOk(true);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	
	/**
	 * @Title:workOrderCompletion
	 * @Description:TODO(7天工单完成量) 
	 * @param @param request
	 * @param @return  startDate  endDate 或者userid
	 * select count(*),DATE_FORMAT(t.create_time,'%Y-%m-%d') as date from pvmts_workorder_info t where t.state=0 group by t.create_time
	 * @return JSONObject
	 * @throws
	 */
	@RequestMapping(value="/workOrderCompletion",method=RequestMethod.GET)
	@ResponseBody
	public Object workOrderCompletion(HttpServletRequest request,HttpServletResponse response,String startDate,String endDate){
		CompletionDTO completionDTO = new CompletionDTO();
		try {
			 if(StringUtils.isBlank(startDate)){
				 return RestResponse.response(HttpServletResponse.SC_BAD_REQUEST, "请输入开始时间！");
			 }
			 completionDTO.setStartDate(startDate);
			 if(StringUtils.isBlank(endDate)){
				 return RestResponse.response(HttpServletResponse.SC_BAD_REQUEST, "请输入截止时间！");
			 }
			 completionDTO.setEndDate(endDate);
			 String token = request.getHeader("token");
			 int userId = TokenUtil.getUserIdByToken(token);
			 if(userId==0){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "token无效");
			 }
			completionDTO.setUserId(userId);
			Object result = workOrderInfoService.workOrderCompletion(completionDTO);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	
	
	/**
	 * @Title:maintenanceStaffs
	 * @Description:TODO(加载电站列表) 
	 * @param 
	 * @return void
	 * @throws
	 */
	@RequestMapping(value="/getPowerStationBaseInfo")
	@ResponseBody
	public Object getPowerStationBaseInfo(HttpSession session){
		UserVO user = (UserVO)HttpSessionUtil.getAttribute("user");
		String companyId = user.getCompanyId()+"";
		List<PowerStationBaseInfoVO> list = powerStationService.getPowerStationBaseInfo("",companyId,"","");
		PowerStationBaseInfoVO powerStationVO = new PowerStationBaseInfoVO();
		powerStationVO.setId(0);
		powerStationVO.setName("请选择电站");
		list.add(powerStationVO);
		try {
			return RestResponse.responseOk(list);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	
	/**
	 * @Title:maintenanceUsers
	 * @Description:TODO(加载当前电站下的运维人员用户列表) 
	 * @param @return
	 * @return Object
	 * @throws
	 */
	@RequestMapping(value="/maintenanceUsers")
	@ResponseBody
	public Object maintenanceUsers(HttpServletRequest request,HttpServletResponse response,int powerStationId){
		List<MaintenanceUserVO> list = workOrderInfoService.getMaintenanceUsers(powerStationId);
		try {
			return RestResponse.responseOk(list);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	
	
	/**
	 * @Title:initData
	 * @Description:TODO(初始化页面数据) 
	 * @param @return
	 * @return JSONObject
	 * @throws
	 */
	@RequestMapping(value="/initData")
	@ResponseBody
	public Object initData(HttpServletRequest request,HttpServletResponse response){
		try {
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	
}