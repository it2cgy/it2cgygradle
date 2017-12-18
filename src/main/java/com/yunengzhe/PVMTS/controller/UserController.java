package com.yunengzhe.PVMTS.controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.yunengzhe.PVMTS.domain.dto.AddUserDTO;
import com.yunengzhe.PVMTS.domain.dto.MobileBindingDTO;
import com.yunengzhe.PVMTS.domain.dto.ResetpsdDTO;
import com.yunengzhe.PVMTS.domain.dto.UserDTO;
import com.yunengzhe.PVMTS.domain.dto.UserSearchKey;
import com.yunengzhe.PVMTS.domain.dto.WechatBindingDTO;
import com.yunengzhe.PVMTS.domain.po.UserAndRolePO;
import com.yunengzhe.PVMTS.domain.po.UserInfoPO;
import com.yunengzhe.PVMTS.domain.po.UserPO;
import com.yunengzhe.PVMTS.domain.response.RestResponse;
import com.yunengzhe.PVMTS.domain.response.page.Paginator;
import com.yunengzhe.PVMTS.domain.vo.PowerStationBaseInfoVO;
import com.yunengzhe.PVMTS.domain.vo.ResultListVO;
import com.yunengzhe.PVMTS.domain.vo.RolesmenuHandleVO;
import com.yunengzhe.PVMTS.domain.vo.UserBaseInfoVO;
import com.yunengzhe.PVMTS.domain.vo.UserListVO;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.service.PowerStationService;
import com.yunengzhe.PVMTS.service.RolesMenusService;
import com.yunengzhe.PVMTS.service.UserAndRoleService;
import com.yunengzhe.PVMTS.service.UserService;
import com.yunengzhe.PVMTS.util.StringUtil;
import com.yunengzhe.PVMTS.util.UserUtil;
import com.yunengzhe.PVMTS.util.userInfo.TokenUtil;
import com.yunengzhe.commons.authentication.HttpSessionUtil;
import com.yunengzhe.commons.util.AESUtil;
import com.yunengzhe.commons.util.CacheManUtil;
import com.yunengzhe.commons.util.JsonUtil;
import com.yunengzhe.commons.util.Md5Util;



/**
 * @ClassName:UserController
 * @Description:TODO(用户管理)
 * @author chenguiyang
 * @date 2017年6月12日下午6:46:56
 */
@Controller
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	public static final String CACHE_USERBYID = "PVMTS_USERBYID";
	public static final String PVMTS_USER = "user";

	@Autowired
	private UserService userService;

	@Autowired
	private UserAndRoleService userAndRoleService;
	
	@Autowired
	private RolesMenusService rolesMenusService;
	@Autowired
	private PowerStationService powerStationService; 

	/**
	 * @Title:login
	 * @Description:TODO(登录) 
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @return Object
	 * @throws
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	@JsonInclude(Include.NON_NULL)
	public Object login(@RequestBody UserDTO user){
		try {
			if(StringUtils.isBlank(user.getUsername())){
				return RestResponse.responseCode(0, "用户名不能为空！");
			}
			if(StringUtils.isBlank(user.getPassword())){
				return RestResponse.responseCode(0, "密码不能为空！");
			}
			String userpass = Md5Util.MD5Encode(user.getPassword()).toUpperCase();
			UserPO userPO = userService.getUserByUserName(user.getUsername());
			if(userPO==null){
				return RestResponse.responseCode(0, "用户名错误！");
			}
			if(!userpass.equals(userPO.getPassword().toUpperCase())){
				return RestResponse.responseCode(0, "密码错误！");
			}
			
			List<UserAndRolePO> roleList = userAndRoleService.getRoleByUserId(userPO.getId());
			String roleIds = "";
			List<Integer> results = new ArrayList<Integer>();
			for(UserAndRolePO rolePO:roleList){ 
				List<PowerStationBaseInfoVO> powerStations = powerStationService.getPowerStationByRole(rolePO.getRoleId());
				for(PowerStationBaseInfoVO info:powerStations){
					results.add(info.getId());
				}
				roleIds+=rolePO.getRoleId()+",";
			}
			UserVO userVO = userService.getVoFromPo(userPO);
			if(roleList.size()>0){
				HashMap<String, RolesmenuHandleVO> resultMap  = rolesMenusService.getRoleAndMenu(roleIds.substring(0,roleIds.length()-1));
				userVO.setMenuList(resultMap);
			}
			userVO.setRoleList(roleList);
			userVO.setPowerStationList(results);
			CacheManUtil.addObj2Json(CACHE_USERBYID,""+userPO.getId(),userVO);//缓存用户信息
			String token = AESUtil.encrypt(userPO.getId().toString(),"2wscde3!&werqqwe");
			userVO.setToken(token);
			userVO.setDepartmentName("测试部门");
			HttpSessionUtil.setAttribute(PVMTS_USER,userVO);
			return RestResponse.responseCode(1, "登录成功");
		} catch (Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	

	/**
	 * @Title:login
	 * @Description:TODO(返回登录页) 
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @return Object
	 * @throws
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(){
		return "/login/login";
	}


	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(){
		try {
			if(HttpSessionUtil.getAttribute(PVMTS_USER)!=null){  

				logger.info("退出登录"+JsonUtil.beanToJson(HttpSessionUtil.getAttribute(PVMTS_USER)));
			}else{
				logger.info("退出登录,但是没用户信息");
			}
			HttpSessionUtil.removeAttribute(PVMTS_USER);
			HttpSessionUtil.destroy();
		} catch (Exception e) {
			logger.error(e.getMessage(),e); 
			e.printStackTrace();
		}
		return "/login/login";
	}

	/**
	 * @Title:mobileBinding
	 * @Description:TODO(检查微信账号是否已经绑定手机号) 
	 * @param @return
	 * @return JSONObject
	 * @throws
	 */
	@RequestMapping(value="/mobileBinding")
	@ResponseBody
	@JsonInclude(Include.NON_NULL)
	public Object mobileBinding(@RequestBody MobileBindingDTO param){
		logger.info("-----------------------------#检查微信账号是否已经绑定手机号#-------------------------------");
		try {
			List<UserPO> listData = userService.mobileBindingCheck(param);
			if(listData.size()==0 || listData==null){
				return RestResponse.responseCode(0, "账号未绑定");
			}
			UserVO userVO = userService.getVoFromPo(listData.get(0));
			return RestResponse.responseCode(1, "获取用户信息成功", userVO);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	/**
	 * @Title:wechatBound
	 * @Description:TODO(微信与账号绑定) 
	 * @param 
	 * @return void
	 * @throws
	 */
	@RequestMapping(value="/wechatBinding")
	@ResponseBody
	public Object wechatBinding(@RequestBody WechatBindingDTO param){
		logger.info("-----------------------------#微信与账号绑定#-------------------------------");
		try {
			if(StringUtils.isBlank(param.getUsername())){
				return RestResponse.responseCode(0, "用户名不能为空！");
			}
			if(StringUtils.isBlank(param.getPassword())){
				return RestResponse.responseCode(0, "密码不能为空！");
			}
			if(StringUtils.isBlank(param.getWxopenId())){
				return RestResponse.responseCode(0, "openId不能为空！");
			}
			if(StringUtils.isBlank(param.getWxunionId())){
				return RestResponse.responseCode(0, "unionId不能为空！");
			}
			/**
			 * 校验用户名密码是否正确
			 */
			String userpass = Md5Util.MD5Encode(param.getPassword()).toUpperCase();
			UserPO userPO = userService.getUserByUserName(param.getUsername());
			if(userPO==null){
				return RestResponse.responseCode(0, "用户名或密码错误！");
			}
			/**
			 * 保存用户绑定的微信信息
			 */
			userPO.setWxheadurl(param.getWxheadurl());
			userPO.setWxnickname(param.getWxnickname());
			userPO.setWxopenid(param.getWxopenId());
			userPO.setWxsex(param.getWxsex());
			userPO.setWxunionid(param.getWxunionId());
			userService.updateUser(userPO);
			/**
			 * 设置返回参数
			 */
			UserVO userVO = userService.getVoFromPo(userPO);
			String token = AESUtil.encrypt(userPO.getId().toString(),"2wscde3!&werqqwe");
			CacheManUtil.addObj2Json(CACHE_USERBYID,""+userPO.getId(),userVO);//缓存用户信息
			userVO.setToken(token);
			return RestResponse.responseCode(1, "微信绑定成功！", userVO);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	/**
	 * @Title:login
	 * @Description:TODO(ajax登录) 
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @return UserVO
	 * @throws
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value="/api/login")
	@ResponseBody
	public Object apiLogin(@RequestBody UserDTO user){
		try {
			if(StringUtils.isBlank(user.getUsername())){
				return RestResponse.responseCode(0, "用户名不能为空");
			}
			if(StringUtils.isBlank(user.getPassword())){
				return RestResponse.responseCode(0, "密码不能为空");
			}
			String userpass = Md5Util.MD5Encode(user.getPassword()).toUpperCase();
			UserPO userPO = userService.getUserByUserName(user.getUsername());
			if(userPO==null){
				return RestResponse.responseCode(0, "用户名或密码错误");
			}
			if(!userPO.getPassword().toUpperCase().equals(userpass)){
				return RestResponse.responseCode(0, "用户名或密码错误");
			}
			List<UserAndRolePO> roleList = userAndRoleService.getRoleByUserId(userPO.getId());
			UserVO userVO = userService.getVoFromPo(userPO);
			userVO.setRoleList(roleList);
			if(userVO==null){
				return RestResponse.responseCode(0, "用户名或密码错误");
			}
			userVO.setDepartmentName("测试部门");
			String token = AESUtil.encrypt(userPO.getId().toString(),"2wscde3!&werqqwe");
			CacheManUtil.addObj2Json(CACHE_USERBYID,""+userPO.getId(),userVO);//缓存用户信息
			userVO.setToken(token);
			return RestResponse.responseCode(1, userVO);
		} catch (Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}


	/**
	 * @Title:userInfoByToken
	 * @Description:TODO(获取用户信息 通过token) 
	 * @param @return
	 * @return UserVO
	 * @throws
	 */
	@RequestMapping(value="/userInfo")
	@ResponseBody
	public Object userInfoByToken(HttpServletRequest request,HttpServletResponse response){
		try {
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			return RestResponse.responseCode(1, user);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}

	/**
	 * @Title:ssoLogin
	 * @Description:TODO(修改密码接口)
	 * @param @param model
	 * @throws
	 */
	@RequestMapping(value="/resetpsw",method=RequestMethod.POST)
	@ResponseBody
	public  Object resetpwd(@RequestBody ResetpsdDTO param,HttpServletRequest request,HttpServletResponse response) {
		String oldPassword =  param.getOldPassword();
		String newPassword =  param.getNewPassword(); 
		try {   
			String token = request.getHeader("token");
			if(StringUtils.isBlank(token)){//Token为空
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "token无效！");
			}
			int userId = TokenUtil.getUserIdByToken(token);//通过token获取用户信息
			if(userId==0){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "请先登录！");
			}
			if(StringUtils.isBlank(oldPassword) || StringUtils.isBlank(newPassword)){
				return RestResponse.response(HttpServletResponse.SC_BAD_REQUEST,"原始密码或新密格式不正确!");
			}
			UserPO userPO = userService.getUser(userId);
			if(userPO == null){
				return RestResponse.response(HttpServletResponse.SC_BAD_REQUEST,"用户不存在!");
			}
			if(!Md5Util.MD5Encode(oldPassword).toUpperCase().equals(userPO.getPassword().toUpperCase())){
				return RestResponse.response(HttpServletResponse.SC_BAD_REQUEST,"原始密码不正确!");
			}
			userPO.setPassword(Md5Util.MD5Encode(newPassword));
			userPO.setUpdateTime(new Date());
			int flag = userService.updateUser(userPO);
			if(flag == 0){
				return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"重置密码失败!");
			}
			return RestResponse.responseOk(true);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	} 
	/**
	 * @Title:addUser
	 * @Description:TODO(用一句话描述一下这个方式的作用) 
	 * @param @param param["3","2"]
	 * @return void
	 * @throws
	 */
	@RequestMapping(value="/addUser",method=RequestMethod.POST)
	@ResponseBody
	public Object addUser(@RequestBody AddUserDTO param,HttpServletRequest request,HttpServletResponse response){
		try {
			String token = request.getHeader("token");
			if(StringUtils.isBlank(token)){//Token为空
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "token无效！");
			}
			int userId = TokenUtil.getUserIdByToken(token);//通过token获取用户信息
			if(userId==0){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "请先登录！");
			}
			
			UserPO userPO = userService.getUserByUserName(param.getUsername());
			if(userPO!=null){
				return RestResponse.responseCode(0,"用户已经存在!");
			}
			param.setCreateUserId(userId);
			int flag = userService.addUser(param);
			if(flag==0){
				return RestResponse.response(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"用户创建失败!");
			}
			return RestResponse.responseCode(1,"用户创建成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}

	/**
	 * @Title:addUser
	 * @Description:TODO(更新用户) 
	 * @param @param param["3","2"]
	 * @return void
	 * @throws
	 */
	@RequestMapping(value="/editUser",method=RequestMethod.POST)
	@ResponseBody
	public Object editUser(@RequestBody AddUserDTO param){
		try {
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			
			int flag = userService.editUser(param);
			if(flag==0){
				return RestResponse.responseCode(0,"用户创建失败！");
			}
			UserPO userPO = userService.getUser(param.getUserId());
			userService.cacheUser(userPO);
			
			if(user.getUserid().intValue()==userPO.getId().intValue()) {
				
				List<UserAndRolePO> roleList = userAndRoleService.getRoleByUserId(userPO.getId());
				String roleIds = "";
				List<Integer> results = new ArrayList<Integer>();
				for(UserAndRolePO rolePO:roleList){ 
					List<PowerStationBaseInfoVO> powerStations = powerStationService.getPowerStationByRole(rolePO.getRoleId());
					for(PowerStationBaseInfoVO info:powerStations){
						results.add(info.getId());
					}
					roleIds+=rolePO.getRoleId()+",";
				}
				UserVO userVO = userService.getVoFromPo(userPO);
				if(roleList.size()>0){
					HashMap<String, RolesmenuHandleVO> resultMap  = rolesMenusService.getRoleAndMenu(roleIds.substring(0,roleIds.length()-1));
					userVO.setMenuList(resultMap);
				}
				userVO.setRoleList(roleList);
				userVO.setPowerStationList(results);
				CacheManUtil.addObj2Json(CACHE_USERBYID,""+userPO.getId(),userVO);//缓存用户信息
				String token = AESUtil.encrypt(""+userVO.getUserid(),"2wscde3!&werqqwe");
				userVO.setToken(token);
				HttpSessionUtil.setAttribute(PVMTS_USER,userVO);
			}
			
			return RestResponse.responseCode(1,"用户创建成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	/**
	 * @Title:resetPsd
	 * @Description:TODO(重置密码) 
	 * @param @return
	 * @return JSONObject
	 * @throws
	 */
	@RequestMapping(value="/resetPsd/{userId}")
	@ResponseBody
	public Object resetPsd(@PathVariable int userId,HttpServletRequest request,HttpServletResponse response){
		try{
			int flag = userService.resetUserPsd(userId);
			if(flag==0){
				return RestResponse.responseCode(0,"重置密码失败！");
			}
			return RestResponse.responseCode(1,"重置密码成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}

	/**
	 * @Title:companys
	 * @Description:TODO(获取公司下的所有用户) 
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @return Object
	 * @throws
	 */
	@RequestMapping(value="/userList/{companyId}")
	@ResponseBody
	public Object userList(@PathVariable int companyId,Integer page,Integer pagesize,@RequestBody UserSearchKey searchKey,HttpServletRequest request,HttpServletResponse response){
		try {
			UserVO user = TokenUtil.getLoginUser();
			if(user==null){
				return RestResponse.response(HttpServletResponse.SC_UNAUTHORIZED, "需要登陆");
			}
			if(page==null){
				page = 1;
			}
			if(pagesize==null){
				pagesize = Paginator.DEFAULT_PAGESIZE;
			}
			ResultListVO<UserListVO> result = userService.getUserList(companyId,Integer.valueOf(page),Integer.valueOf(pagesize),searchKey);
			return RestResponse.responseList(request, result.getCounts(), result.getResultList()); 
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}

	@RequestMapping(value="/delete")
	@ResponseBody
	public Object deleteUsers(String ids,Integer page,Integer pagesize,HttpServletRequest request,HttpServletResponse response){
		try {
			if(StringUtils.isNotBlank(ids)){
				userService.setDeleteByIds(ids);
				String[] split = ids.split(",");
				for (int i = 0; i < split.length; i++) {
					userAndRoleService.delUserRole(Integer.parseInt(split[i]));
				}
				return RestResponse.responseOk();
			}
			return RestResponse.responseOk();
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	
	/* 获取对id的user数据
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/getUsersInfo/{ids}")
	@ResponseBody
	public Object getUsersInfo(@PathVariable String ids){
		List<UserBaseInfoVO> result = new ArrayList<UserBaseInfoVO>();
		List<UserPO> list = userService.getUsersInfo(ids);
		for(int i=0;i<list.size();i++){
			UserPO UserPO = list.get(i);
			UserBaseInfoVO UserBaseInfoVO = new UserBaseInfoVO();
			UserBaseInfoVO.setUserid(UserPO.getId());
			UserBaseInfoVO.setUsername(UserPO.getUsername());
			UserBaseInfoVO.setNickname(UserPO.getNickname());
			UserBaseInfoVO.setTelephone(UserPO.getTelephone());
			result.add(UserBaseInfoVO);
		}
		try {
			return RestResponse.responseCode(1,"success",result);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	/**
	 * 获取指定公司id的公司的所有用户
	 * @param companyId
	 * @return
	 */
	@RequestMapping(value="/getCompanyUsersInfo")
	@ResponseBody
	public Object getCompanyUsersInfo(String companyId,String roleId,String realName){
		List<UserBaseInfoVO> result = new ArrayList<UserBaseInfoVO>();
		if(StringUtil.empty(companyId)){
			companyId = null;
		}
		if(StringUtil.empty(roleId)){
			roleId = null;
		}
		if(StringUtil.empty(realName)){
			realName = null;
		}
		List<UserInfoPO> list = userService.getCompanyUsersInfo(companyId,roleId,realName);
		for(int i=0;i<list.size();i++){
			UserInfoPO UserInfoPO = list.get(i);
			UserBaseInfoVO UserBaseInfoVO = new UserBaseInfoVO();
			UserBaseInfoVO.setUserid(UserInfoPO.getId());
			UserBaseInfoVO.setUsername(UserInfoPO.getUsername());
			UserBaseInfoVO.setNickname(UserInfoPO.getNickname());
			UserBaseInfoVO.setTelephone(UserInfoPO.getTelephone());
			UserBaseInfoVO.setRoleList(UserInfoPO.getRoleList());
			UserBaseInfoVO.setRolesName(UserUtil.getRolesName(UserInfoPO.getRoleList()));
			result.add(UserBaseInfoVO);
		}
		try {
			return RestResponse.responseCode(1,"success",result);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}
	/**
	 * 获取指定角色用户
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value="/getRoleUsersInfo")
	@ResponseBody
	public Object getRoleUsersInfo(int roleId){
		List<UserInfoPO> list = userService.getRoleUsersInfo(roleId);
		try {
			return RestResponse.responseCode(1,"success",list);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestResponse.responseError();
		}
	}





}
