package com.yunengzhe.PVMTS.controller.page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yunengzhe.PVMTS.domain.po.TaskConfigPO;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.service.TaskConfigService;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;

/**
 * @ClassName:ReportPageController
 * @Description:TODO(报表管理页面)
 * @author chenguiyang
 * @date 2017年6月26日下午3:11:51
 */
@Controller
@RequestMapping("/reportPage")
public class ReportPageController {
	private static final Logger logger = LoggerFactory.getLogger(ReportPageController.class);
	
	@Autowired
	private TaskConfigService taskConfigService;
	
	@RequestMapping(value="/reportList")
	public String reportList(HttpServletRequest request,HttpServletResponse response){
		UserVO user = TokenUtil.getLoginUser();
		if(user == null){
			return "redirect:/user/login";
		}
		logger.info("--------------->报表列表页面");
		return "/report/reportList";
	}
	
	@RequestMapping(value="/reportDailyList")
	public String reportDailyList(Model model,HttpServletRequest request,HttpServletResponse response){
		UserVO user = TokenUtil.getLoginUser();
		if(user == null){
			return "redirect:/user/login";
		}
		logger.info("--------------->报表列表页面");
		TaskConfigPO p = taskConfigService.getConfig();
		model.addAttribute("taskConfig",p);
		return "/report/reportDailyList";
	}
	
}
