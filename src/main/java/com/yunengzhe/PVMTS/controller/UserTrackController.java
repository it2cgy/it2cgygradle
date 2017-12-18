package com.yunengzhe.PVMTS.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunengzhe.PVMTS.domain.dto.UserTrackDTO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.service.UserTrackService;


/**
 * @ClassName:UserTrackController
 * @Description:TODO(用户轨迹处理)
 * @author chenguiyang
 * @date 2017年6月16日上午11:24:09
 */
@Controller
@RequestMapping("/userTrack")
public class UserTrackController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserTrackController.class);
	
	@Autowired
	private UserTrackService userTrackService;
	
	
	
	/**
	 * @Title:addUserTrack
	 * @Description:TODO(更新当前人员的运动轨迹) 
	 * @param 
	 * @return void
	 * @throws
	 */
	@RequestMapping(value="/updateUserTrack")
	@ResponseBody
	public Object updateUserTrack(@RequestBody UserTrackDTO userTrack){
		if(userTrack.getLat()==null){
			return RestResponse.responseCode(0,"纬度不能为空！");
		}
		if(userTrack.getLng()==null){
			return RestResponse.responseCode(0,"经度不能为空！");
		}
		try {
			 userTrackService.updateUserTrack(userTrack);
			 return RestResponse.responseCode(1,"更新用户轨迹成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	
	}
	

}
