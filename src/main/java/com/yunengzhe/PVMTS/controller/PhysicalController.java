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

import com.yunengzhe.PVMTS.domain.dto.PhysicalDTO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.domain.vo.PhysicalVO;
import com.yunengzhe.PVMTS.service.PhysicalService;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;



/**
 * @ClassName:PhysicalController
 * @Description:TODO(电站体检)
 * @author chenguiyang
 * @date 2017年6月7日下午2:58:57
 */
@Controller
@RequestMapping("/physical")
public class PhysicalController {
	
	private static final Logger logger = LoggerFactory.getLogger(PhysicalController.class);
	
	@Autowired
	private PhysicalService physicalService;

	
	/**
	 * @Title:saveExamine
	 * @Description:TODO(保存检查标准信息) 
	 * @param @param examineDTO
	 * @param @return
	 * @return JSONObject
	 * @throws
	 */
	@RequestMapping(value="/editExamine")
	@ResponseBody
	public Object editExamine(@RequestBody PhysicalDTO physicalDTO,HttpServletRequest request,HttpServletResponse response){
		try {
			 String token = request.getHeader("token");
			 if(StringUtils.isBlank(token)){//Token为空
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "token无效！");
			 }
			 int userId = TokenUtil.getUserIdByToken(token);//通过token获取用户信息
			 if(userId==0){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "token无效！");
			 }
			if(physicalDTO==null){
				return RestResponse.response(HttpServletResponse.SC_BAD_REQUEST, "检查信息为空！");
			}
			int flag = physicalService.editExamine(physicalDTO,userId);
			if(flag==0){
				return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "保存检查标准信息失败！");
			}
			return RestResponse.responseOk(true);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	
	/**
	 * @Title:inspectionItem
	 * @Description:TODO(获取电站体检信息) 
	 * @param @return
	 * @return JSONObject
	 * @throws
	 */
	@RequestMapping(value="/physicalInfo/{orderNum}",method=RequestMethod.GET)
	@ResponseBody
	public Object physicalInfo(@PathVariable String orderNum,HttpServletRequest request,HttpServletResponse response){
		try {
			 String token = request.getHeader("token");
			 if(StringUtils.isBlank(token)){//Token为空
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "token无效！");
			 }
			 int userId = TokenUtil.getUserIdByToken(token);//通过token获取用户信息
			 if(userId==0){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "token无效！");
			 }
			logger.debug("获取工单下的电站体检信息");
			List<PhysicalVO> physical = physicalService.physicalInfo(orderNum);
			if(physical==null){
				return RestResponse.response(HttpServletResponse.SC_BAD_REQUEST, "获取工单下的电站体检信息失败或无体检信息！");
			}
			return RestResponse.responseOk(physical);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
}
