package com.yunengzhe.PVMTS.controller.page;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yunengzhe.PVMTS.domain.po.PushMailPO;
import com.yunengzhe.PVMTS.domain.po.RoleAndPowerPO;
import com.yunengzhe.PVMTS.domain.po.RolePO;
import com.yunengzhe.PVMTS.domain.po.UserAndRolePO;
import com.yunengzhe.PVMTS.domain.vo.PowerStationBaseInfoVO;
import com.yunengzhe.PVMTS.domain.vo.RolesmenuHandleVO;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.domain.vo.role.RoleAndPowerVO;
import com.yunengzhe.PVMTS.service.PowerStationService;
import com.yunengzhe.PVMTS.service.PushMailService;
import com.yunengzhe.PVMTS.service.RoleAndPowerService;
import com.yunengzhe.PVMTS.service.RoleService;
import com.yunengzhe.PVMTS.service.RolesMenusService;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;

/**
 * @ClassName:RolePageController
 * @Description:TODO(角色页面跳转处理)
 * @author chenguiyang
 * @date 2017年8月2日上午9:40:47
 */
@Controller
@RequestMapping("/rolePage")
public class RolePageController{
	@Autowired
	private RolesMenusService rolesMenusService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private PowerStationService powerStationService; 
	@Autowired
	private RoleAndPowerService roleAndPowerService;
	@Autowired
	private PushMailService pushMailService;
	/**
	 * 公司管理员
	 */
	public final static int COMPANY_ADMIN_ROLEID  = 2;
	/**
	 * @Title:roleListPage
	 * @Description:TODO(跳转到角色列表页面) 
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value="/roleListPage")
	public String roleListPage(Model model,HttpServletRequest request,HttpServletResponse response,String admin){
		UserVO user = TokenUtil.getLoginUser();
		if(user == null){
			return "redirect:user/login";
		} 
		
		model.addAttribute("admin",true);
		return "/role/roleList";
	}
	
	/**
	 * @Title:editRolePage
	 * @Description:TODO(跳转到编辑角色页面) 
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value="editRolePage")
	public String editRolePage(Integer roleId,Model model,HttpServletRequest request,HttpServletResponse response){
		UserVO user = TokenUtil.getLoginUser();
		if(user == null){
			return "redirect:user/login";
		} 
		int companyId = user.getCompanyId();
		RolePO roleInfo = roleService.getRoleInfoById(roleId);
		model.addAttribute("roleInfo",roleInfo);
//		List<PowerStationBaseInfoVO> result = powerStationService.getPowerStationBaseInfo("",""+companyId, "");
		Integer roleid = 0;
		List<UserAndRolePO> rolleList = user.getRoleList();
		if(rolleList.size()>0){
			roleid = rolleList.get(0).getRoleId();
		}
		if(roleid==COMPANY_ADMIN_ROLEID){//系统管理员
			roleid=null;
		}
		PushMailPO p = pushMailService.getPushMail(roleId);
		model.addAttribute("pushMail",p);
		List<PowerStationBaseInfoVO> result = powerStationService.getPowerStationBaseInfo("",companyId+"","",roleid==null?null:roleid.toString());
		RoleAndPowerPO param = new RoleAndPowerPO();
		param.setRoleId(roleId);
		List<RoleAndPowerPO> roleAndPower = roleAndPowerService.getRoleAndPower(param);
		List<String> roleAndPowerList = new ArrayList<String>();
		for(RoleAndPowerPO rpo : roleAndPower){
			roleAndPowerList.add(""+rpo.getPowerstationId());
		}
		List<RoleAndPowerVO> rpList = new ArrayList<RoleAndPowerVO>();
		for(PowerStationBaseInfoVO vo : result){
			RoleAndPowerVO rp = new RoleAndPowerVO();
			if(roleAndPowerList.contains(""+vo.getId())){
				rp.setIsChecked(1);
			}else{
				rp.setIsChecked(0);
			}	
			rp.setPowerstationId(vo.getId());
			rp.setPowerstationName(vo.getName());
			rpList.add(rp);
		}
		model.addAttribute("roleAndPower", rpList);
		List<RolesmenuHandleVO> menus = rolesMenusService.getRoleMenuList(roleId);
		model.addAttribute("menus",menus);
		model.addAttribute("admin",true);
		return "/role/editRole";
	}
	
	/**
	 * @Title:addRolePage
	 * @Description:TODO(跳转到增加角色页面) 
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value="/addRolePage")
	public String addRolePage(Model model,HttpServletRequest request,HttpServletResponse response){
		UserVO user = TokenUtil.getLoginUser();
		if(user == null){
			return "redirect:user/login";
		} 
		int companyId = user.getCompanyId();
		Integer roleid = 0;
		List<UserAndRolePO> rolleList = user.getRoleList();
		if(rolleList.size()>0){
			roleid = rolleList.get(0).getRoleId();
		}
		if(roleid==COMPANY_ADMIN_ROLEID){//系统管理员
			roleid=null;
		}
		List<PowerStationBaseInfoVO> result = powerStationService.getPowerStationBaseInfo("",companyId+"","",roleid==null?null:roleid.toString());
		model.addAttribute("powerstation", result);
		List<RolesmenuHandleVO> menus = rolesMenusService.getRoleMenuList(null);
		model.addAttribute("menus",menus);
		model.addAttribute("admin",true);
		return "/role/addRole";
	}
	
	
}
