package com.yunengzhe.PVMTS.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunengzhe.PVMTS.domain.dto.NoticeInfoDTO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.domain.response.RestResponseVO;
import com.yunengzhe.PVMTS.domain.vo.NoticeInfoVO;
import com.yunengzhe.PVMTS.domain.vo.NoticeReadInfoVO;
import com.yunengzhe.PVMTS.domain.vo.NoticeTypeVO;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.service.NoticeTypeService; 
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;
import com.yunengzhe.commons.authentication.HttpSessionUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/noticeType")
public class NoticeTypeController {
	
	private static final Logger logger = LoggerFactory.getLogger(NoticeTypeController.class);
	
	@Autowired
	private NoticeTypeService noticeTypeService;
	
	/**
	 * 获取公告类型
	 * @return
	 */
	@RequestMapping(value="/queryNoticeType")
	@ResponseBody
	public RestResponseVO queryNoticeType(HttpServletRequest request,HttpServletResponse response){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			List<NoticeTypeVO> list = new ArrayList<NoticeTypeVO>();
			list = noticeTypeService.queryNoticeType();
			if(list.size()==0){
				return RestResponse.response(HttpServletResponse.SC_BAD_REQUEST, "公告类型表数据为空");
			}else{
				return RestResponse.responseOk(list);
			}
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
}
