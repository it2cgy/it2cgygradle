package com.yunengzhe.PVMTS.controller.page;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yunengzhe.PVMTS.domain.po.CompanyPO;
import com.yunengzhe.PVMTS.domain.po.RolePO;
import com.yunengzhe.PVMTS.domain.po.UserAndRolePO;
import com.yunengzhe.PVMTS.domain.po.UserPO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.domain.vo.UserAndRoleVO;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.service.CompanyService;
import com.yunengzhe.PVMTS.service.RoleService;
import com.yunengzhe.PVMTS.service.UserAndRoleService;
import com.yunengzhe.PVMTS.service.UserService;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;

import io.netty.handler.codec.http.HttpResponse;

/**
 * @ClassName:UserInfoPageController
 * @Description:TODO(个人信息处理)
 * @author chenguiyang
 * @date 2017年7月12日上午10:11:01
 */
@Controller
@RequestMapping("/userInfoPage")
public class UserInfoPageController {

	
	@Autowired
	private RoleService roleService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserAndRoleService userAndRoleService;
	
	/**
	 * @Title:userInfoPage
	 * @Description:TODO(跳转到个人中心页面) 
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value="/userInfoPage")
	public String userInfoPage(int powerStationId,Model model,HttpServletRequest request,HttpServletResponse response){
		UserVO user = TokenUtil.getLoginUser();
		if(user == null){
			return "redirect:user/login";
		} 
		int userId=TokenUtil.getUserIdByToken(user.getToken());
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
		model.addAttribute("powerStationId", powerStationId);
		return "/user/personal";
	}
	
	
	/**
	 * @Title:userInfoPage
	 * @Description:TODO(修改密码页面) 
	 * @param @param model
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value="/updatePsdPage")
	public String updatePsdPage(int powerStationId,Model model,HttpServletRequest request,HttpServletResponse response){
		UserVO user = TokenUtil.getLoginUser();
		if(user == null){
			return "redirect:user/login";
		} 
		int userId=TokenUtil.getUserIdByToken(user.getToken());
		model.addAttribute("userId",userId);
		model.addAttribute("powerStationId", powerStationId);
		return "/user/updatepsd";
	}
}
