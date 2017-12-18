package com.yunengzhe.PVMTS.controller;

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

import com.yunengzhe.PVMTS.domain.dto.RulesInfoDTO;
import com.yunengzhe.PVMTS.domain.dto.RulesUpdateInfoDTO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.domain.response.RestResponseVO;
import com.yunengzhe.PVMTS.domain.vo.RulesInfoVO;
import com.yunengzhe.PVMTS.domain.vo.RulesReaderEditVO;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.service.RulesInfoService;
import com.yunengzhe.PVMTS.util.StringUtil;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;

@Controller
@RequestMapping("/rulesInfo")
public class RulesInfoController {
	
	private static final Logger logger = LoggerFactory.getLogger(RulesInfoController.class);
	
	@Autowired
	private RulesInfoService rulesInfoService;
	
	/**
	 * 新增制度
	 * @param session
	 * @param RulesInfoDTO
	 * @return
	 */
	@RequestMapping(value="/addRules",method=RequestMethod.POST)
	@ResponseBody
	public RestResponseVO addRules(HttpServletRequest request,@RequestBody RulesInfoDTO RulesInfoDTO,HttpServletResponse response){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			RulesInfoVO RulesInfoVO = new RulesInfoVO();
			RulesInfoVO = rulesInfoService.addRules(user,RulesInfoDTO);;
			return RestResponse.responseOk(RulesInfoVO);
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "新增制度失败");
		}
	}
	
	/**
	 * 发布公告
	 * @param id
	 * @param status
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/updatePublishStatus/{id}/{status}")
	@ResponseBody
	public RestResponseVO updatePublishStatus(HttpServletRequest request,@PathVariable String id,@PathVariable String status,
			HttpServletResponse response){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			RulesInfoVO RulesInfoVO = new RulesInfoVO();
			RulesInfoVO = rulesInfoService.updatePublishStatus(id,status);
			return RestResponse.responseOk(RulesInfoVO);
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "发布公告失败");
		}
	}
	/**
	 * 编辑铺数据
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/queryNoticeOne/{id}")
	@ResponseBody
	public RestResponseVO queryNoticeOne(HttpServletRequest request,@PathVariable String id,HttpServletResponse response){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			RulesReaderEditVO RulesReaderEditVO = new RulesReaderEditVO();
			RulesReaderEditVO = rulesInfoService.queryNoticeOne(id);
			return RestResponse.responseOk(RulesReaderEditVO);
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "编辑铺数据失败");
		}
	}
	/**
	 * 编辑保存、发布
	 * @param session
	 * @param RulesUpdateInfoDTO
	 * @return
	 */
	@RequestMapping(value="/updateRules",method=RequestMethod.POST)
	@ResponseBody
	public RestResponseVO updateRules(HttpServletRequest request,@RequestBody RulesUpdateInfoDTO RulesUpdateInfoDTO,HttpServletResponse response){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			RulesInfoVO RulesInfoVO = new RulesInfoVO();
			if(Integer.valueOf(RulesUpdateInfoDTO.getStatus())==1){
				if(StringUtil.empty(RulesUpdateInfoDTO.getFileName())){
					return RestResponse.response(HttpServletResponse.SC_BAD_REQUEST, "附件不能为空");
				}
			}
			RulesInfoVO = rulesInfoService.updateRules(RulesUpdateInfoDTO);
			return RestResponse.responseOk(RulesInfoVO);
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "插入失败-参数有误");
		}
	}
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deleteRules/{id}")
	@ResponseBody
	public RestResponseVO deleteRules(HttpServletRequest request,@PathVariable String id,HttpServletResponse response){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			RulesInfoVO RulesInfoVO = new RulesInfoVO();
			if(StringUtil.empty(id)){
				RulesInfoVO.setResult(false);
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "无效token");
			}else{
				rulesInfoService.deleteRules(id);
				RulesInfoVO.setResult(true);
				return RestResponse.responseOk(RulesInfoVO);
			}
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "插入失败-参数有误");
		}
	}
}
