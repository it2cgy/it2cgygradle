package com.yunengzhe.PVMTS.controller;

import java.util.HashMap;
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

import com.yunengzhe.PVMTS.domain.dto.ScanInfoDTO;
import com.yunengzhe.PVMTS.domain.po.ScanInfoPO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.domain.vo.ScanInfoVO;
import com.yunengzhe.PVMTS.service.ScanInfoService;


/**
 * @ClassName:ScanInfoController
 * @Description:TODO(扫码上传处理)
 * @author chenguiyang
 * @date 2017年6月16日上午10:16:47
 */
@Controller
@RequestMapping("/scanInfo")
public class ScanInfoController {
	
	private static final Logger logger = LoggerFactory.getLogger(ScanInfoController.class);
	
	@Autowired
	private ScanInfoService scanInfoService;

	
	/**
	 * @Title:uploadInfo
	 * @Description:TODO(上传设备信息) 
	 * @param 
	 * @return void
	 * @throws
	 */
	@RequestMapping(value="/uploadInfo",method=RequestMethod.POST)
	@ResponseBody
	public Object uploadInfo(@RequestBody ScanInfoDTO param){
		try {
			int flag = scanInfoService.uploadInfo(param);
			if(flag==0){
				return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "上传设备信息失败！");
			}
			return RestResponse.responseOk(true);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	
	/**
	 * @Title:downLoadInfo
	 * @Description:TODO(扫码下载设备信息) 
	 * @param @param equipmentId
	 * @param @param request
	 * @param @param response
	 * @return void
	 * @throws
	 */
	@RequestMapping(value="/downLoadInfo/{equipmentId}",method=RequestMethod.GET)
	public Object downLoadInfo(@PathVariable int equipmentId,HttpServletRequest request,HttpServletResponse response){
		try {
			ScanInfoPO info = scanInfoService.downLoadInfo(equipmentId);
			ScanInfoVO infoVO = new ScanInfoVO();
			if(info==null){
				return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "获取信息失败！");
			}
			infoVO.setEquipmentId(info.getEquipmentId());
			infoVO.setEquipmentInfo(info.getEquipmentInfo());
			infoVO.setEquipmentType(info.getEquipmentType());
			return RestResponse.responseOk(infoVO);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	
}
