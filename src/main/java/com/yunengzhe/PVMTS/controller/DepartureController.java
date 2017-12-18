package com.yunengzhe.PVMTS.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.yunengzhe.PVMTS.domain.dto.DepartureSaveDTO;
import com.yunengzhe.PVMTS.domain.po.AttachmentPO;
import com.yunengzhe.PVMTS.domain.po.DeparturePO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.domain.vo.DepartureVO;
import com.yunengzhe.PVMTS.service.AttachmentService;
import com.yunengzhe.PVMTS.service.DepartureService;
import com.yunengzhe.PVMTS.util.FileUtil;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;


/**
 * @ClassName:DepartureController
 * @Description:TODO(离场申请)
 * @author chenguiyang
 * @date 2017年6月7日下午4:27:38
 */
@Controller
@RequestMapping("/departure")
public class DepartureController {
	
	private static final Logger logger = LoggerFactory.getLogger(DepartureController.class);
	
	@Autowired
	private DepartureService departureService;

	@Autowired
	private AttachmentService attachmentService;
	/**
	 * @Title:departureInfo
	 * @Description:TODO(获取离场信息) 
	 * @param @param orderNum
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @return JSONObject
	 * @throws
	 */
	@RequestMapping(value="/departureInfo/{orderNum}",method=RequestMethod.GET)
	@ResponseBody
	public Object departureInfo(@PathVariable String orderNum,HttpServletRequest request,HttpServletResponse response){
		DepartureVO result = new DepartureVO();
		try {
			logger.debug("获取离场信息");
			DeparturePO departure = departureService.departureInfo(orderNum);
			if(departure!=null){
				AttachmentPO file = new AttachmentPO();
				file.setFiletype(FileUtil.DEPARTURE);
				file.setFormId(""+departure.getId());
				List<AttachmentPO> fileList = attachmentService.getAttaList(file);
				result.setId(departure.getId());
				result.setOrderNum(departure.getOrderNum());
				result.setConclusion(departure.getConclusion());
				result.setDepartureExamine(departure.getExamineInfo());
				result.setAttachmentList(fileList);
			}
			return RestResponse.responseOk(result);
		} catch (Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	
	/**
	 * @Title:physicalSubmit
	 * @Description:TODO(离场申请提交) 
	 * @param @return
	 * @return JSONObject
	 * @throws
	 */
	@RequestMapping(value="/departureSubmit/{orderId}",method=RequestMethod.POST)
	@ResponseBody
	public Object departureSubmit(@PathVariable int orderId,HttpServletRequest request,HttpServletResponse response){
		try {
			int flag = departureService.departureSubmit(orderId);
			if(flag==0){
				return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "离场申请提交失败！");
			}
			return RestResponse.responseOk();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	
	/**
	 * @Title:physicalSubmit
	 * @Description:TODO(离场申请保存) 
	 * @param @return
	 * @return JSONObject
	 * @throws
	 */
	@RequestMapping(value="/saveDeparture",method=RequestMethod.POST)
	@ResponseBody
	public Object saveDeparture(@RequestBody DepartureSaveDTO param,HttpServletRequest request,HttpServletResponse response){
		try {
			 String token = request.getHeader("token");
			 if(StringUtils.isBlank(token)){//Token为空
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "token无效！");
			 }
			 int userId = TokenUtil.getUserIdByToken(token);//通过token获取用户信息
			 if(userId==0){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "token无效！");
			 }
			departureService.saveDeparture(param,userId);
			return RestResponse.responseOk();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	
}
