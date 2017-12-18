package com.yunengzhe.PVMTS.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunengzhe.PVMTS.domain.dto.role.RoleDTO;
import com.yunengzhe.PVMTS.domain.dto.role.RoleDTO.MenuConfig;
import com.yunengzhe.PVMTS.domain.po.PushMailPO;
import com.yunengzhe.PVMTS.domain.po.RoleAndPowerPO;
import com.yunengzhe.PVMTS.domain.po.RolePO;
import com.yunengzhe.PVMTS.domain.po.RolesMenusPO;
import com.yunengzhe.PVMTS.domain.po.UserAndRolePO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.domain.response.page.Paginator;
import com.yunengzhe.PVMTS.domain.vo.ResultListVO;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.domain.vo.role.RoleVO;
import com.yunengzhe.PVMTS.service.PushMailService;
import com.yunengzhe.PVMTS.service.RoleAndPowerService;
import com.yunengzhe.PVMTS.service.RoleService;
import com.yunengzhe.PVMTS.service.RolesMenusService;
import com.yunengzhe.PVMTS.service.UserAndRoleService;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;


/**
 * @ClassName:RoleController
 * @Description:TODO(角色管理)
 * @author chenguiyang
 * @date 2017年6月14日上午9:44:59
 */
@Controller
@RequestMapping("/role")
public class RoleController {
	
	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private RoleAndPowerService roleAndPowerService;
	
	@Autowired
	private UserAndRoleService userAndRoleService;
	
	@Autowired
	private RolesMenusService rolesMenusService;
	
	@Autowired
	private PushMailService pushMailService;
	/**
	 * @Title:getRoles
	 * @Description:TODO(加载角色列表) 
	 * @param @param request
	 * @param @param response
	 * @return void
	 * @throws
	 */
	@RequestMapping(value="/getRoles")
	@ResponseBody
	public Object getRoles(HttpServletRequest request,HttpServletResponse response){
		List<RolePO> result = new ArrayList<RolePO>();
		try {
			result = roleService.getRoles();
			return RestResponse.responseCode(1, "获取角色列表！", result);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	
	
	
	/**
	 * @Title:getRolesPage
	 * @Description:TODO(获取用户角色列表 --分页) 
	 * @param @return
	 * @return Object
	 * @throws
	 */
	@RequestMapping(value="/getRolesPage")
	@ResponseBody
	public Object getRolesPage(String roleName,Integer page,Integer pagesize,HttpServletRequest request,HttpServletResponse response){
		try {
			 String token = request.getHeader("token");
			 if(StringUtils.isBlank(token)){//Token为空
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "token无效！");
			 }
			 int userId = TokenUtil.getUserIdByToken(token);//通过token获取用户信息
			 if(userId==0){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "token无效！");
			 }
			 if(page==null){
					page = 1;
			 }
			 if(pagesize==null){
				pagesize = Paginator.DEFAULT_PAGESIZE;
			 }
			 if(StringUtils.isBlank(roleName)){
				 roleName=null;
			 }
			 ResultListVO<RoleVO> roleListVO =roleService.getRolesPage(roleName,Integer.valueOf(page),Integer.valueOf(pagesize));
			 return RestResponse.responseList(request, roleListVO.getCounts(), roleListVO.getResultList()); 
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	
	
	/**
	 * @Title:addRole
	 * @Description:TODO(新增角色) 
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @return Object
	 * @throws
	 */
	@RequestMapping(value="/addRole")
	@ResponseBody
	public Object addRole(@RequestBody RoleDTO r,HttpServletRequest request,HttpServletResponse response){
		try {
			UserVO user = TokenUtil.getLoginUser();
			if(user == null){
				return "redirect:user/login";
			} 
			int companyId = user.getCompanyId();
			RolePO param = new RolePO();
			param.setCreateTime(new Date());
			param.setUpdateTime(new Date());
			param.setRoleName(r.getRoleName());
			param.setCompanyId(companyId);
			param.setRoleType(1);
			PushMailPO push = new PushMailPO();
			push.setRoleId(param.getId());
			if(r.isPushConfig()){
				push.setPush(1);
			}else{
				push.setPush(0);
			}
			if(r.getRoleId()==null){
				roleService.insertRole(param);
			}else{
				param.setId(r.getRoleId());
				roleService.updateRole(param);
			}
			push.setRoleId(param.getId());
			if(r.getPushId()==null){
				pushMailService.insert(push);
			}else{
				push.setId(r.getPushId());
				pushMailService.update(push);
			}
			RoleAndPowerPO roleAndPowerPO = new RoleAndPowerPO();
			roleAndPowerPO.setRoleId(param.getId());
			roleAndPowerService.delRoleAndPower(roleAndPowerPO);
			if(r.getPowerList()!=null&&!"null".equals(r.getPowerList())&&!"".equals(r.getPowerList())){
				String[] powerlist = r.getPowerList().split(",");
				if(powerlist.length>0){

					for(String s : powerlist){
						RoleAndPowerPO roleAndpower = new RoleAndPowerPO();
						roleAndpower.setRoleId(param.getId());
						roleAndpower.setPowerstationId(Integer.valueOf(s));
						roleAndPowerService.addRoleAndPower(roleAndpower);
					}
				}
			}
			List<RolesMenusPO> menuConfig = new ArrayList<RolesMenusPO>();
			if(r.getMenuConfig().size()>0){
				rolesMenusService.delRoleMenus(param.getId());
				for(MenuConfig m : r.getMenuConfig()){
					if(m.isPremis()){
						RolesMenusPO p = new RolesMenusPO();
						p.setCreateTime(new Date());
						p.setUpdateTime(new Date());
						p.setRoleId(param.getId());
						p.setMenuId(m.getId());
						if(m.isHandle()) {
							p.setHandlePremission(1);
						}else{
							p.setHandlePremission(0);
						}
						menuConfig.add(p);
					}
				}
			}
			rolesMenusService.insertRoleMenusBatch(menuConfig);
			return RestResponse.responseOk(true);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	
	/**
	 * @Title:delRoleById
	 * @Description:TODO(删除角色) 
	 * @param @param roleId
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @return Object
	 * @throws
	 */
	@RequestMapping(value="delRoleById")
	@ResponseBody
	public Object delRoleById(Integer roleId,HttpServletRequest request,HttpServletResponse response){
		try {
			List<UserAndRolePO> userAndRoleList = userAndRoleService.getRoleAndUser(roleId);
			if(userAndRoleList.size()>0){
				return RestResponse.responseOk(false);
			}
			RoleAndPowerPO param =  new RoleAndPowerPO();
			param.setRoleId(roleId);
			List<RoleAndPowerPO> roleAndPowerList = roleAndPowerService.getRoleAndPower(param);
			if(roleAndPowerList.size()>0){
				return RestResponse.responseOk(false);
			}
			roleService.delRole(roleId);
			rolesMenusService.delRoleMenus(roleId);
			return RestResponse.responseOk(true);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
}
