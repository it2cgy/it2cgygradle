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

import com.yunengzhe.PVMTS.domain.dto.RulesSearchDTO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.domain.response.RestResponseVO;
import com.yunengzhe.PVMTS.domain.response.page.Paginator;
import com.yunengzhe.PVMTS.domain.vo.ResultListVO;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.service.RulesReaderService;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;

@Controller
@RequestMapping("/rulesReader")
public class RulesReaderController {
	
	private static final Logger logger = LoggerFactory.getLogger(RulesReaderController.class);
	
	@Autowired
	private RulesReaderService rulesReaderService;
	/**
	 * 获取制度列表
	 * @param session
	 * @param page
	 * @param rows
	 * @param RulesSearchDTO
	 * @return
	 */
	@RequestMapping(value="/queryRulesList",method=RequestMethod.POST)
	@ResponseBody
	public Object queryRulesList(@RequestBody RulesSearchDTO RulesSearchDTO,
		HttpServletRequest request,HttpServletResponse response){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			Integer page = Integer.valueOf(RulesSearchDTO.getPage());
			Integer pagesize = Integer.valueOf(RulesSearchDTO.getPagesize());
			if(page==null){
				page = 1;
			}
			if(pagesize==null){
				pagesize = Paginator.DEFAULT_PAGESIZE;
			}
			ResultListVO resultListVO = rulesReaderService.queryNoticeList(user,page,pagesize,RulesSearchDTO);
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
	@RequestMapping(value="/queryRuleOne/{id}")
	@ResponseBody
	public RestResponseVO queryRuleOne(HttpServletRequest request,@PathVariable Integer id,HttpServletResponse response){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			rulesReaderService.queryRuleOne(user, id);
			return RestResponse.responseOk(); 
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "插入失败-参数有误");
		}
	}
}
