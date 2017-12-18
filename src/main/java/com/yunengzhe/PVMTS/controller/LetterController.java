package com.yunengzhe.PVMTS.controller;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunengzhe.PVMTS.domain.dto.LetterDTO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.domain.response.RestResponseVO;
import com.yunengzhe.PVMTS.domain.vo.LetterVO;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.service.LetterService;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;
import com.yunengzhe.commons.authentication.CacheUserUtil;
import com.yunengzhe.commons.authentication.HttpSessionUtil;
import com.yunengzhe.commons.authentication.vo.CacheUserInfo;

@Controller
@RequestMapping("/letter")
public class LetterController {
	
	private static final Logger logger = LoggerFactory.getLogger(LetterController.class);
	
	@Autowired
	private LetterService letterService;
	
	/**
	 * 站内信写信
	 * @param session
	 * @param letterDTO
	 * @return
	 */
	@RequestMapping(value="/addLetter",method=RequestMethod.POST)
	@ResponseBody
	public RestResponseVO addLetter(HttpServletRequest request,@RequestBody LetterDTO letterDTO,
			HttpServletResponse response){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			letterService.addLetter(user.getUserid(),letterDTO);
			LetterVO letterVO = new LetterVO();
			letterVO.setResult(true);
			return RestResponse.responseOk(letterVO);
		}catch(Exception e){
			return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "添加信件失败");
		}
	}
	
}
