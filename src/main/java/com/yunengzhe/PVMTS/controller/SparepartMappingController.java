package com.yunengzhe.PVMTS.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunengzhe.PVMTS.domain.po.SparepartMappingPO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.service.SparepartMappingService;

/**
 * @ClassName:SparepartMappingController
 * @Description:TODO(备品备件处理)
 * @author chenguiyang
 * @date 2017年6月7日下午1:21:44
 */
@Controller
@RequestMapping("/sparepartMapping")
public class SparepartMappingController {
	
	private static final Logger logger = LoggerFactory.getLogger(SparepartMappingController.class);
	
	@Autowired
	private SparepartMappingService sparepartMappingService;

	
	/**
	 * @Title:spareInfo
	 * @Description:TODO(获取工单备品备件信息) 
	 * @param @return
	 * @return JSONObject
	 * @throws
	 */
	@RequestMapping(value="/spareInfo/{orderNum}",method=RequestMethod.GET)
	@ResponseBody
	public Object spareInfo(@PathVariable String orderNum){
		try {
			List<SparepartMappingPO> sparepartInfo = sparepartMappingService.getSparepart(orderNum);
			if(sparepartInfo == null){
				return RestResponse.response(HttpServletResponse.SC_BAD_REQUEST, "获取备品备件信息失败或无备品备件信息！");
			}
			return RestResponse.responseOk(sparepartInfo);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
}
