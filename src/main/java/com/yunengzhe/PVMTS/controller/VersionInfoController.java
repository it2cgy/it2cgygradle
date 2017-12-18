package com.yunengzhe.PVMTS.controller;

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

import com.yunengzhe.PVMTS.domain.po.VersionInfoPO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.domain.response.RestResponseVO;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.domain.vo.VersionVO;
import com.yunengzhe.PVMTS.service.VersionInfoService;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;

@Controller
@RequestMapping("/versionInfo")
public class VersionInfoController {
	
	private static final Logger logger = LoggerFactory.getLogger(VersionInfoController.class);
	
	@Autowired
	private VersionInfoService versionInfoService;
	/**
	 * 获取最新版本信息
	 * @param request
	 * @param response
	 * @return  code 0 是最新   1  不是最新   data 数据
	 */
	 
	@RequestMapping(value="/checkVersion/{versionType}/{versionNumber}",method=RequestMethod.GET) 
	@ResponseBody
	public RestResponseVO checkVersion(HttpServletRequest request,HttpServletResponse response,@PathVariable String versionType,@PathVariable String versionNumber){
		try{
			/**
			 * app第一次安装没有token缓存
			 */
//			UserVO user = TokenUtil.getLoginUser();
//			if(user==null){
//				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
//			}
			VersionInfoPO VersionInfo = new VersionInfoPO();
			VersionInfo.setVersionType(versionType);
			VersionInfo.setVersionNumber(versionNumber);
			logger.debug(VersionInfo.toString());
			Integer checkVersion = versionInfoService.checkVersion(VersionInfo);
			if(checkVersion == null){
				return RestResponse.responseCode(0, "参数错误");
			}
			if(checkVersion == 0){
				return RestResponse.responseCode(checkVersion, "当前已是最新版本");
			}else{ 
				VersionInfoPO findUniqueByMap = versionInfoService.findUniqueByMap(versionType);
				logger.debug(findUniqueByMap.toString());
				return RestResponse.responseCode(checkVersion, findUniqueByMap);
			}
		}catch(Exception e){
			return RestResponse.responseError();
		}
	}
}
