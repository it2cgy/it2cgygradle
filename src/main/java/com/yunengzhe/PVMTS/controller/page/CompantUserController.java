package com.yunengzhe.PVMTS.controller.page;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yunengzhe.PVMTS.domain.po.CompanyPO;
import com.yunengzhe.PVMTS.domain.po.RolePO;
import com.yunengzhe.PVMTS.domain.po.UserAndRolePO;
import com.yunengzhe.PVMTS.domain.po.UserPO;
import com.yunengzhe.PVMTS.domain.vo.UserAndRoleVO;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.service.CompanyService;
import com.yunengzhe.PVMTS.service.RoleService;
import com.yunengzhe.PVMTS.service.UserAndRoleService;
import com.yunengzhe.PVMTS.service.UserService;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;

/**
 * @ClassName:CompantUserController
 * @Description:TODO(页面跳转)
 * @author chenguiyang
 * @date 2017年6月21日上午11:29:19
 */
@Controller
@RequestMapping("/compantUser")
public class CompantUserController {

	private static final Logger logger = LoggerFactory.getLogger(CompantUserController.class);
	@Autowired
	private RoleService roleService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserAndRoleService userAndRoleService;
	/**
	 * @Title:userListPage
	 * @Description:TODO(用一句话描述一下这个方式的作用) 
	 * @param 
	 * @return void
	 * @throws
	 */
	@RequestMapping(value="/userListPage")
	public String userListPage(Model model,HttpServletRequest request,HttpServletResponse response,String admin){
		logger.info("--------------->公司用户列表页面");
		UserVO user = TokenUtil.getLoginUser();
		if(user == null){
			return "redirect:user/login";
		} 
		model.addAttribute("admin",true);
		return "/user/userList";
	}
	
	/**
	 * @Title:userDetails
	 * @Description:TODO(跳转到用户详情页面) 
	 * @param @param request
	 * @param @param response
	 * @param @return  userId 用户id  flag 1-编辑 0-查看
	 * @return String
	 * @throws
	 */
	@RequestMapping(value="/editUser")
	public String editUser(String admin,int userId,int flag,Model model,HttpServletRequest request,HttpServletResponse response){
		UserVO user = TokenUtil.getLoginUser();
		if(user == null){
			return "redirect:user/login";
		} 
		
		UserPO userPO = userService.getUser(userId);
		model.addAttribute("userInfo",userPO);
		List<CompanyPO> companyList = companyService.getCompanies();
		model.addAttribute("companyList", companyList);
		List<RolePO> roleList =  roleService.getRoles();
		List<UserAndRolePO> userAndRoleList = userAndRoleService.getRoleByUserId(userId);
		List<UserAndRoleVO> result = new ArrayList<UserAndRoleVO>();
		for(RolePO role : roleList){
			UserAndRoleVO userRoleVO = new UserAndRoleVO();
			userRoleVO.setId(role.getId());
			userRoleVO.setRoleName(role.getRoleName());
			for(UserAndRolePO userAndRolePO : userAndRoleList){
				if(role.getId()==userAndRolePO.getRoleId()){
					userRoleVO.setSelected(true);
					break;
				}else{
					userRoleVO.setSelected(false);
				}
			}
			result.add(userRoleVO);
		}
		model.addAttribute("roleList", result);
		model.addAttribute("flag",flag);
		model.addAttribute("admin",true);
		return "/user/editUser";
	}
	
	
	/**
	 * @Title:userListPage
	 * @Description:TODO(新增用户) 
	 * @param 
	 * @return void
	 * @throws
	 */
	@RequestMapping(value="/addUserPage")
	public String addUserPage(String admin,Model model,HttpServletRequest request,HttpServletResponse response){
		logger.info("--------------->公司用户列表页面");
		UserVO user = TokenUtil.getLoginUser();
		if(user == null){
			return "redirect:user/login";
		} 
		
		List<CompanyPO> companyList = companyService.getCompanies();
		model.addAttribute("companyList", companyList);
		List<RolePO> roleList =  roleService.getRoles();
		model.addAttribute("roleList", roleList);
		
		model.addAttribute("admin",true);
		return "/user/addUser";
	}
	
}
