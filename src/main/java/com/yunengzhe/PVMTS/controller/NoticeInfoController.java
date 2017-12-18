package com.yunengzhe.PVMTS.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import com.yunengzhe.PVMTS.domain.dto.NoticeInfoDTO;
import com.yunengzhe.PVMTS.domain.dto.NoticeReaderDTO;
import com.yunengzhe.PVMTS.domain.dto.NoticeUpdateInfoDTO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.domain.response.RestResponseVO;
import com.yunengzhe.PVMTS.domain.vo.LetterVO;
import com.yunengzhe.PVMTS.domain.vo.NoticeEditInfoVO;
import com.yunengzhe.PVMTS.domain.vo.NoticeInfoVO;
import com.yunengzhe.PVMTS.domain.vo.NoticeReadInfoVO;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.service.NoticeInfoService; 
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;
import com.yunengzhe.commons.authentication.HttpSessionUtil;

@Controller
@RequestMapping("/noticeInfo")
public class NoticeInfoController {
	
	private static final Logger logger = LoggerFactory.getLogger(NoticeInfoController.class);
	
	@Autowired
	private NoticeInfoService noticeInfoService;
	
	/**
	 * 新增公告
	 * @param session
	 * @param noticeInfoDTO
	 * @return
	 */
	@RequestMapping(value="/addNotice",method=RequestMethod.POST)
	@ResponseBody
	public RestResponseVO addNotice(HttpServletRequest request,@RequestBody NoticeInfoDTO noticeInfoDTO,HttpServletResponse response){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			NoticeInfoVO NoticeInfoVO = new NoticeInfoVO();
	  		NoticeInfoVO = noticeInfoService.addNotice(user,noticeInfoDTO);
			return RestResponse.responseOk(NoticeInfoVO);
			
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "新增失败");
		}
	}
	
	/**
	 * 编辑公告
	 * @param session
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
			NoticeEditInfoVO NoticeEditInfoVO = new NoticeEditInfoVO();
			NoticeEditInfoVO = noticeInfoService.queryNoticeOne(id);
			return RestResponse.responseOk(NoticeEditInfoVO);
			
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "编辑失败");
		}
	}
	/**
	 * 校验ID是否存在
	 * @param session
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/checkid/{id}")
	@ResponseBody
	public RestResponseVO checkid(HttpServletRequest request,@PathVariable String id,HttpServletResponse response){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			NoticeInfoVO NoticeInfoVO = new NoticeInfoVO();
			NoticeInfoVO = noticeInfoService.checkid(id);
			return RestResponse.responseOk(NoticeInfoVO);
			
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "校验失败");
		}
	}
	/**
	 * 发布公告
	 * @param session
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping(value="/publishNotice/{id}/{status}")
	@ResponseBody
	public RestResponseVO publishNotice(HttpServletRequest request,@PathVariable String id,@PathVariable String status,HttpServletResponse response){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			NoticeInfoVO NoticeInfoVO = new NoticeInfoVO();
			NoticeInfoVO = noticeInfoService.publishNotice(id,status);
			return RestResponse.responseOk(NoticeInfoVO);
			
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "发布失败");
		}
	}
	/**
	 * 更新公告
	 * @param session
	 * @param NoticeUpdateInfoDTO
	 * @return
	 */
	@RequestMapping(value="/updateNotice",method=RequestMethod.POST)
	@ResponseBody
	public RestResponseVO updateNotice(HttpServletRequest request,@RequestBody NoticeUpdateInfoDTO NoticeUpdateInfoDTO,HttpServletResponse response){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			NoticeInfoVO NoticeInfoVO = new NoticeInfoVO();
			NoticeInfoVO = noticeInfoService.updateNotice(NoticeUpdateInfoDTO);
			return RestResponse.responseOk(NoticeInfoVO);
			
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "更新失败");
		}
	}
	/**
	 * 删除公告
	 * @param session
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/deleteNotice/{ids}")
	@ResponseBody
	public RestResponseVO deleteNotice(HttpServletRequest request,@PathVariable String ids,HttpServletResponse response){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			NoticeInfoVO NoticeInfoVO = new NoticeInfoVO();
	  		noticeInfoService.deleteNotice(ids);
			return RestResponse.responseOk(NoticeInfoVO);
			
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "删除失败");
		}
	}
}
