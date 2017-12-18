package com.yunengzhe.PVMTS.controller.page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yunengzhe.PVMTS.domain.po.MailConfigPO;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.service.MailConfigService;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;

/**
 * @ClassName:MailPushConfigController
 * @Description:TODO(邮件推送管理)
 * @author chenguiyang
 * @date 2017年9月11日下午2:46:19
 */
@Controller
@RequestMapping("/mailPage")
public class MailPushConfigController {
	private static final Logger logger = LoggerFactory.getLogger(MailPushConfigController.class);
	
	@Autowired
	private MailConfigService mailConfigService;
	
	@RequestMapping(value="/pushConfigPage")
	public String pushConfigPage(Model model ,HttpServletRequest request,HttpServletResponse response){
		UserVO user = TokenUtil.getLoginUser();
		if(user == null){
			return "redirect:/user/login";
		} 
		MailConfigPO p = mailConfigService.getConfigInfo();
		model.addAttribute("mailConfig",p);
		logger.info("--------------->推送管理页面");
		return "/mailConfig/mailConfig";
	}
}
