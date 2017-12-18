package com.yunengzhe.PVMTS.controller.page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;

/**
 * @ClassName:HistoryController
 * @Description:TODO(lishichaxun )
 * @author chenguiyang
 * @date 2017年6月22日下午6:34:16
 */
@Controller
@RequestMapping("/history")
public class HistoryController {

	private static final Logger logger = LoggerFactory.getLogger(HistoryController.class);
	
	/**
	 * @Title:userListPage
	 * @Description:TODO(用一句话描述一下这个方式的作用) 
	 * @param 
	 * @return void
	 * @throws
	 */
	@RequestMapping(value="/historyPage")
	public String historyPage(HttpServletRequest request,HttpServletResponse response){
		logger.info("--------------->历史查询页面");
		UserVO user = TokenUtil.getLoginUser();
		if(user == null){
			return "redirect:/user/login";
		} 
		return "/history/history";
	}
	
}
