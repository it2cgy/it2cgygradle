package com.yunengzhe.PVMTS.controller;


import java.util.Map;

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

import com.yunengzhe.PVMTS.domain.dto.NoticeSearchDTO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.domain.response.RestResponseVO;
import com.yunengzhe.PVMTS.domain.response.page.Paginator;
import com.yunengzhe.PVMTS.domain.vo.NoticeReadInfoVO;
import com.yunengzhe.PVMTS.domain.vo.ResultListVO;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.service.NoticeReaderService;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;

@Controller
@RequestMapping("/noticeReader")
public class NoticeReaderController {
	
	private static final Logger logger = LoggerFactory.getLogger(NoticeReaderController.class);
	
	@Autowired
	private NoticeReaderService noticeReaderService;
	
	/**
	 * 获取公告列表
	 * @param session
	 * @param page
	 * @param rows
	 * @param NoticeSearchDTO
	 * @return
	 */
	@RequestMapping(value="/queryNoticeList",method=RequestMethod.POST)
	@ResponseBody
	public Object queryNoticeList(HttpServletRequest request,
			@RequestBody NoticeSearchDTO NoticeSearchDTO,HttpServletResponse response){
		try{
			Integer page = Integer.valueOf(NoticeSearchDTO.getPage());
			Integer pagesize = Integer.valueOf(NoticeSearchDTO.getPagesize());
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			if(page==null){
				page = 1;
			}
			if(pagesize==null){
				pagesize = Paginator.DEFAULT_PAGESIZE;
			}
			ResultListVO resultListVO = noticeReaderService.queryNoticeList(user,page,pagesize,NoticeSearchDTO);
			return RestResponse.responseList(request, resultListVO.getCounts(), resultListVO.getResultList()); 
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	
	/**
	 * 查看
	 * @param session
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/queryNoticeOne/{id}")
	@ResponseBody
	public RestResponseVO queryNoticeOne(HttpServletRequest request,@PathVariable Integer id ,HttpServletResponse response){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			NoticeReadInfoVO NoticeReadInfoVO = noticeReaderService.queryNoticeOne(user.getUserid(),id);
			return RestResponse.responseOk(NoticeReadInfoVO);
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
}
