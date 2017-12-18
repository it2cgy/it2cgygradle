package com.yunengzhe.PVMTS.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.domain.response.RestResponseVO;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.domain.vo.VersionVO;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;
@Controller
@RequestMapping("/version")
public class VersionController {
	/**
	 * 获取版本信息
	 * @param request
	 * @param response
	 * @return
	 */
	 
	@RequestMapping(value="/newVersion") 
	@ResponseBody
	public RestResponseVO newVersion(HttpServletRequest request,HttpServletResponse response){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			VersionVO versionVO = new VersionVO();
			versionVO.setVersion("longieb 1.0");
			versionVO.setMessage("公测版");
			return RestResponse.responseOk(versionVO);
		}catch(Exception e){
			return RestResponse.responseError();
		}
	}
}
