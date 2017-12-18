package com.yunengzhe.PVMTS.controller.page;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yunengzhe.PVMTS.domain.vo.RolesmenuHandleVO;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.service.RolesMenusService;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;

/**
 * @ClassName:RolesMenuPageController
 * @Description:TODO(角色与菜单功能页面处理)
 * @author chenguiyang
 * @date 2017年8月4日上午9:10:01
 */
@Controller
@RequestMapping("rolesMenus")
public class RolesMenuPageController {
	private static final Logger logger = LoggerFactory.getLogger(RolesMenuPageController.class);
	
	@Autowired
	private RolesMenusService rolesMenusService;
	
	/**
	 * @Title:rolesMenuConfigPage
	 * @Description:TODO(跳转到角色管理与菜单管理页面) 
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value="/rolesConfig")
	public String rolesMenuConfigPage(Integer roleId,HttpServletRequest request,HttpServletResponse response,Model model){
		logger.info("--------------->跳转到角色菜单配置页面");
		UserVO user = TokenUtil.getLoginUser();
		if(user == null){
			return "redirect:user/login";
		} 
		List<RolesmenuHandleVO> result = rolesMenusService.getRoleMenuList(roleId);
		model.addAttribute("menus",result);
		return "/roleAndMenus/RolesMenuConfig";
	}
}
