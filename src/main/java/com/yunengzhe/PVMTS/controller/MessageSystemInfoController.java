package com.yunengzhe.PVMTS.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunengzhe.PVMTS.domain.po.MessageSystemInfoPO;
import com.yunengzhe.PVMTS.domain.po.SettingInfoPO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.domain.response.RestResponseVO;
import com.yunengzhe.PVMTS.domain.response.page.Paginator;
import com.yunengzhe.PVMTS.domain.vo.IsReadCount;
import com.yunengzhe.PVMTS.domain.vo.ResultListVO;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.service.MessageSystemInfoService;
import com.yunengzhe.PVMTS.service.SettingInfoService;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;
import com.yunengzhe.commons.authentication.vo.CacheUserInfo;

@Controller
@RequestMapping("/messageSystemInfo")
public class MessageSystemInfoController {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageSystemInfoController.class);
	@Autowired
	private MessageSystemInfoService messageSystemInfoService;
	
	@Autowired
	private SettingInfoService settingInfoService; 
	/**
	 * 获取用户设定
	 * 如果用户没有设定，默认值都为1
	 */
	public SettingInfoPO getSetting(HttpServletRequest request,HttpServletResponse response,Integer userId){
		SettingInfoPO setting = settingInfoService.getSetting(userId);
		if(setting==null){
			setting = new SettingInfoPO(0, 1, 1, 1, 1, 0);
		}
		return setting;
	}
	
	/**
	 * 查看消息中心未读数量
	 * @return已测
	 */
	@RequestMapping(value="/getIsReadCount",method=RequestMethod.POST)
	@ResponseBody
	public RestResponseVO getIsReadCount(HttpServletRequest request,HttpServletResponse response){ 
	 
		UserVO user = TokenUtil.getLoginUser();
		if(user==null){
			return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
		}
		IsReadCount count = null;
		try {
			count = new IsReadCount();
			int noRead = messageSystemInfoService.getNoRead(1,1,user.getUserid());//I级报警
			if(getSetting(request,response,user.getUserid()).getTwoAlarm()==1){
				noRead += messageSystemInfoService.getNoRead(1,2,user.getUserid());//II级报警
			}
			if(getSetting(request,response,user.getUserid()).getThreeAlarm()==1){
				noRead += messageSystemInfoService.getNoRead(1,3,user.getUserid());//III级报警
			}
			count.setAlarmCount(noRead);
			if(getSetting(request,response,user.getUserid()).getSystem()==1){
				count.setNoticeCount(messageSystemInfoService.getNoRead(3,0,user.getUserid()));//公告
			}
			if(getSetting(request,response,user.getUserid()).getLetter()==1){
				count.setLetterCount(messageSystemInfoService.getNoRead(2,0,user.getUserid()));//站内信
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage(),e);
			return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "系统出错");
		}
		return RestResponse.responseOk(count);
	}
	
	/**
	 * 查看某类型的消息列表
	 * @return已测---
	 */ 
	@RequestMapping(value="/getMassageByType/{massageType}",method=RequestMethod.GET)
	@ResponseBody
	public Object getMassageByType(HttpServletRequest request,HttpServletResponse response,@PathVariable int massageType,Integer page,Integer pagesize){
		ResultListVO vo = null;
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
		try {
			 vo = messageSystemInfoService.getMassageByType(massageType,user.getUserid(),Integer.valueOf(page),Integer.valueOf(pagesize));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage(),e);
			return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "系统出错");
		}
		logger.debug("getMassageByType---------->"+vo.getResultList().toString());
		return RestResponse.responseList(request, vo.getCounts(),vo.getResultList());
	}
	/**
	 * 删除消息
	 * @param ids
	 * @return已测
	 */
	@RequestMapping(value="/deleteMessage/{id}",method=RequestMethod.GET)
	@ResponseBody
	public RestResponseVO deleteMessage(HttpServletRequest request,HttpServletResponse response,@PathVariable int id){
		boolean deleteMessage = false;
		UserVO user = TokenUtil.getLoginUser();
		if(user==null){
			return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
		}
		try {
			deleteMessage = messageSystemInfoService.deleteMessage(id,user.getUserid());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage(),e);
			return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "系统出错");
		}
		 return RestResponse.responseOk(deleteMessage);
	};
	/**
	 * 用户已读更改
	 * @param 消息id
	 * @return
	 */
	@SuppressWarnings({ "unused" })
	@RequestMapping(value="/updateRead/{id}",method=RequestMethod.GET)
	@ResponseBody
	public RestResponseVO updateRead(HttpServletRequest request,HttpServletResponse response,@PathVariable int id){
		List<MessageSystemInfoPO> massageByType = null;
		boolean updateRead = false;
		UserVO user = TokenUtil.getLoginUser();
		if(user==null){
			return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
		}
		try {
			updateRead = messageSystemInfoService.updateRead(id,user.getUserid());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage(),e);
			return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "系统出错");
		}
		return RestResponse.responseOk(updateRead);
	}

}
