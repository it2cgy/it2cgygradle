package com.yunengzhe.PVMTS.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunengzhe.PVMTS.domain.po.NewsSettingPO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.service.NewsSettingService;
import com.yunengzhe.PVMTS.util.StringUtil;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;

@Controller
@RequestMapping("/newsSetting")
public class NewsSettingController {
	
	private static final Logger logger = LoggerFactory.getLogger(NewsSettingController.class);
	
	@Autowired
	private NewsSettingService newsSettingService;
	
	/**
	 * 获取配置
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/getNewsSetting/{userId}")
	@ResponseBody
	public Object getNewsSetting(@PathVariable Integer userId){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			NewsSettingPO NP = newsSettingService.getNewsSetting(userId);
			return RestResponse.responseOk(NP);
		}catch(Exception e){ 
			logger.error(e.getMessage(),e);
			return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "系统异常");
		}
	}
	
	/**
	 * 修改配置
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/updateNewsSetting")
	@ResponseBody
	public Object updateNewsSetting(@RequestBody Map<String,Integer> map){
		try{
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			Integer id = map.get("id");
			if(id==null){
				return RestResponse.response(HttpServletResponse.SC_BAD_REQUEST, "请求参数有错！");
			}
			Integer push = map.get("push");
			Integer sound = map.get("sound");
			Integer vibrate = map.get("vibrate");
			NewsSettingPO newsSettingPO = new NewsSettingPO();
			newsSettingPO.setId(id);
			Date date = new Date();
			newsSettingPO.setUpdateTime(date);
			if(push!=null){
				newsSettingPO.setPush(push);
			}
			if(sound!=null){
				newsSettingPO.setSound(sound);
			}
			if(vibrate!=null){
				newsSettingPO.setVibrate(vibrate);
			}
			newsSettingService.updateNewsSetting(newsSettingPO);
			return RestResponse.responseOk();
		}catch(Exception e){ 
			logger.error(e.getMessage(),e);
			return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "系统异常");
		}
	}

}
