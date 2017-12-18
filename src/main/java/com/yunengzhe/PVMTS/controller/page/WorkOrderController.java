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
 * @ClassName:WorkOrderController
 * @Description:TODO(工单处理)
 * @author chenguiyang
 * @date 2017年6月28日下午2:39:19
 */
@Controller
@RequestMapping("/workorder")
public class WorkOrderController {
	
	private static final Logger logger = LoggerFactory.getLogger(CompantUserController.class);
	
	/**
	 * @Title:userListPage
	 * @Description:TODO(跳转到工单列表页面) 
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value="/workOrderList")
	public String userListPage(HttpServletRequest request,HttpServletResponse response){
		logger.info("--------------->跳转到我的工单页面");
		UserVO user = TokenUtil.getLoginUser();
		if(user == null){
			return "redirect:user/login";
		} 
		return "/workorder/workOrderList";
	}
	
}
