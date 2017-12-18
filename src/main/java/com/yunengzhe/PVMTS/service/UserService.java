package com.yunengzhe.PVMTS.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.PVMTS.dao.UserDao;
import com.yunengzhe.PVMTS.domain.dto.AddUserDTO;
import com.yunengzhe.PVMTS.domain.dto.MobileBindingDTO;
import com.yunengzhe.PVMTS.domain.dto.UserSearchKey;
import com.yunengzhe.PVMTS.domain.po.UserAndRolePO;
import com.yunengzhe.PVMTS.domain.po.UserInfoPO;
import com.yunengzhe.PVMTS.domain.po.UserPO;
import com.yunengzhe.PVMTS.domain.vo.ResultListVO;
import com.yunengzhe.PVMTS.domain.vo.UserListVO;
import com.yunengzhe.PVMTS.domain.vo.UserVO;
import com.yunengzhe.PVMTS.util.templateSMS.TemplateSMS;
import com.yunengzhe.common.tool.scaffold.util.PageBean;
import com.yunengzhe.commons.authentication.CacheUserUtil;
import com.yunengzhe.commons.authentication.TokenUtil;
import com.yunengzhe.commons.authentication.vo.CacheUserInfo;
import com.yunengzhe.commons.authentication.vo.TokenVO;
import com.yunengzhe.commons.util.CacheManUtil;
import com.yunengzhe.commons.util.Md5Util;
import com.yunengzhe.commons.util.PropertiesUtil;


@Service("userService")
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	public static final String CACHE_IMAGE_PREFIX = "PVMTS_IMAGE_CODE";	//pvmts图片验证码前缀

	public static final int CACHE_IMAGE_TIME = 10;	//pvmts图片验证码过期时间
	
	public static final String CACHE_CODE_PREFIX = "PVMTS_CODE";	
	
	public static final int CACHE_CODE_TIME = 5;	
	
	public static final String CACHE_USERBYID = "PVMTS_USERBYID";
	public static final long TOKEN_EXPIRES_USER = PropertiesUtil.getLong("user_token_expires_time",0);

	public static final long TOKEN_EXPIRES_APP = PropertiesUtil.getLong("app_token_expires_time",0);
	
	@Autowired
	private UserDao userDaoImpl;
	
	@Autowired
	private UserAndRoleService userAndRoleService;
	
	
	public void setDeleteByIds(String ids) {
		if(StringUtils.isNotBlank(ids)){ 
			userDaoImpl.setDeleteByIds(ids);
		}
	}
	
	/**
	 * @Title:cacheAllUser
	 * @Description:TODO(缓存所有用户信息) 
	 * @param 
	 * @return void
	 * @throws
	 */
	public void cacheAllUser(){
		logger.info("start cache all user");
		List<UserPO> users =  userDaoImpl.findAll();
		for(UserPO userPO:users){ 
			this.cacheUser(userPO);
		}
		logger.info("end cache all user user size:"+users.size());
	}
	
	/**
	 * @Title:cacheUser
	 * @Description:TODO(缓存用户信息  通过用户Id查询用户信息) 
	 * @param @param userPO
	 * @return void
	 * @throws
	 */
	public void cacheUser(UserPO userPO){
		List<UserAndRolePO> roleList = userAndRoleService.getRoleByUserId(userPO.getId());
		UserVO userVO = this.getVoFromPo(userPO);
		userVO.setRoleList(roleList);
		CacheManUtil.addObj2Json(CACHE_USERBYID,""+userPO.getId(),userVO);//缓存用户信息
	}
	/**
	 * @Title:getUserByUserName
	 * @Description:TODO(to) 
	 * @param @return
	 * @return UserPO
	 * @throws
	 */
	public UserPO getUserByUserName(String username){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("username", username);
		//param.put("mobile", username);
		//param.put("email", username);
//		param.put("password", userpass);
		List<UserPO> user = userDaoImpl.findByMapOneEqual(param);
		return user.size()>0?user.get(0):null;
		
	}
	
	
	/**
	 * @Title:getVoFromPO
	 * @Description:TODO(用一句话描述一下这个方式的作用) 
	 * @param @return
	 * @return UserVO
	 * @throws
	 */
	public UserVO getVoFromPo(UserPO userPO){
		UserVO userVO = new UserVO();
		if(userPO.getId()!=null){userVO.setUserid(userPO.getId());}
		if(userPO.getUsername()!=null){userVO.setUsername(userPO.getUsername());}
		if(userPO.getCompanyId()!=null){userVO.setCompanyId(userPO.getCompanyId());}
		if(userPO.getEmail()!=null){userVO.setEmail(userPO.getEmail());}
		if(userPO.getHeadshot()!=null){userVO.setHeadshot(userPO.getHeadshot());}
		if(userPO.getNickname()!=null){userVO.setNickname(userPO.getNickname());}
		if(userPO.getSex()!=null){userVO.setSex(userPO.getSex());}
		if(userPO.getStatus()!=null){userVO.setStatus(userPO.getStatus());}
		if(userPO.getTelephone()!=null){userVO.setTelephone(userPO.getTelephone());}
		if(userPO.getWxheadurl()!=null){userVO.setWxheadimgurl(userPO.getWxheadurl());}
		if(userPO.getWxnickname()!=null){userVO.setWxnickname(userPO.getWxnickname());}
		if(userPO.getWxopenid()!=null){userVO.setWxopenId(userPO.getWxopenid());}
		if(userPO.getWxsex()!=null){userVO.setWxsex(userPO.getWxsex());}
		if(userPO.getWxunionid()!=null){userVO.setWxunionId(userPO.getWxunionid());}
		if(userPO.getCreateUserid()!=null){userVO.setCreateUserid(userPO.getCreateUserid());}
		if(userPO.getInputCompany()!=null){userVO.setInputCompany(userPO.getInputCompany());}
		return userVO;
	}
	
	/**
	 * @Title:getUser
	 * @Description:TODO(根据用户id查询用户信息) 
	 * @param @param userid
	 * @param @return
	 * @return UserPO
	 * @throws
	 */
	public UserPO getUser(int userid) {
		return userDaoImpl.get(userid);
	}
	
	/**
	 * 修改用户信息 重置密码等调用
	 * @Title:resetPsd
	 * @Description:TODO(用一句话描述一下这个方式的作用) 
	 * @param 
	 * @return void
	 * @throws
	 */
	public int updateUser(UserPO param){
		int flag = 0;
		try {
			flag = userDaoImpl.update(param);
//			this.cacheUser(param);//更新redis
		} catch (Exception e) {
			logger.error(e.getMessage(),"更新用户信息失败");
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * @Title:getTokenFromUser
	 * @Description:TODO(生成token) 
	 * @param @param userid
	 * @param @return
	 * @return TokenVO
	 * @throws
	 */
	public TokenVO getTokenFromUser(int userid){
		if(userid<=0){
			logger.error("生成token失败,appid or passwork is blank");
			return null;
		}
		try {
			return TokenUtil.getTokenFromUser(userid, TOKEN_EXPIRES_USER);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("生成token失败",e);
		} 
		return null;
	}
	
	/**
	 * @Title:addUser
	 * @Description:TODO(添加用户) 
	 * @param 
	 * @return void
	 * @throws
	 */
	public int editUser(AddUserDTO param){
		int flag =0;
		UserPO user = new UserPO();
		user.setId(param.getUserId());
		user.setCompanyId(param.getCompanyId());
		user.setHeadshot(param.getHeadshot());
		user.setUsername(param.getUsername());
		user.setEmail(param.getEmail());
		user.setNickname(param.getRealname());
		user.setUpdateTime(new Date());
		user.setStatus(0);
		user.setInputCompany(param.getInputCompany());
		user.setTelephone(param.getTelephone());
		flag=userDaoImpl.update4Selective(user);
		if(flag == 0){
			return 0;
		}
		if(param.getRoleId()!=null){
			List<UserAndRolePO> paramList = new ArrayList<UserAndRolePO>();
			userAndRoleService.delUserRole(param.getUserId());
			String[] str = param.getRoleId().split(",");
			for(int i=0;i<str.length;i++){
				UserAndRolePO userAndRole = new UserAndRolePO();
				userAndRole.setRoleId(Integer.valueOf(str[i]));
				userAndRole.setUserId(user.getId());
				userAndRole.setCreateTime(new Date());
				userAndRole.setUpdateTime(new Date());
				paramList.add(userAndRole);
			}
			userAndRoleService.addUserRole(paramList);
		}
		return flag;
	}
	/**
	 * @Title:addUser
	 * @Description:TODO(添加用户) 
	 * @param 
	 * @return void
	 * @throws
	 */
	public int addUser(AddUserDTO param){
		int flag =0;
		UserPO user = new UserPO();
		user.setPassword(Md5Util.MD5Encode("123456").toUpperCase());
		user.setCompanyId(param.getCompanyId());
		user.setHeadshot(param.getHeadshot());
		user.setUsername(param.getUsername());
		user.setEmail(param.getEmail());
		user.setNickname(param.getRealname());
		user.setCreateTime(new Date());
		user.setUpdateTime(new Date());
		user.setInputCompany(param.getInputCompany());
		user.setCreateUserid(param.getCreateUserId());
		user.setTelephone(param.getTelephone());
		user.setStatus(0);
		flag=userDaoImpl.insert(user);//新增用户
		if(flag == 0){
			return 0;
		}
		List<UserAndRolePO> paramList = new ArrayList<UserAndRolePO>();
		if(param.getRoleId()!=null){
			String[] str = param.getRoleId().split(",");
			for(int i=0;i<str.length;i++){
				UserAndRolePO userAndRole = new UserAndRolePO();
				userAndRole.setRoleId(Integer.valueOf(str[i]));
				userAndRole.setUserId(user.getId());
				userAndRole.setCreateTime(new Date());
				userAndRole.setUpdateTime(new Date());
				paramList.add(userAndRole);
			}
		}
		userAndRoleService.addUserRole(paramList);
		return flag;
	}
	
	/**
	 * @Title:mobileBindingCheck
	 * @Description:TODO(校验微信账号是否绑定手机号) 
	 * @param 
	 * @return void
	 * @throws
	 */
	public List<UserPO> mobileBindingCheck(MobileBindingDTO param){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("wxopenid",param.getWxopenId());
		paramMap.put("wxunionid", param.getWxunionId());
		List<UserPO> listData = userDaoImpl.findByMap(paramMap);
		return listData;
	}
	/**
	 * @Title:resetUserPsd
	 * @Description:TODO(重置密码为123456) 
	 * @param @param userId
	 * @param @return
	 * @return int
	 * @throws
	 */
	public int resetUserPsd(int userId){
		int flag = 0;
		UserPO user = new UserPO();
		user.setId(userId);
		user.setPassword(Md5Util.MD5Encode("123456").toUpperCase());
		flag=userDaoImpl.update4Selective(user);
		return flag;
	}
	
	/**
	 * @Title:getUserList
	 * @Description:TODO(查询公司下的所有用户) 
	 * @param @param companyId
	 * @param @return
	 * @return List<UserPO>
	 * @throws
	 */
	public ResultListVO<UserListVO> getUserList(int companyId,int page,int pageSize,UserSearchKey searchKey){
		ResultListVO<UserListVO> results = new ResultListVO<UserListVO>();
		PageBean<UserListVO> pageBean = new PageBean<UserListVO>();
		pageBean.setCurrentPage(page);
		pageBean.setPageRecorders(pageSize);
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("companyId", companyId);
		userDaoImpl.searchUserByPage(pageBean, param,searchKey);
		List<UserListVO> userList = pageBean.getObjList();
		results.setResultList(userList);
		results.setCounts(pageBean.getTotalRows());
		return results;
	}
	
	
	/**
	 * @Title:getUserList
	 * @Description:TODO(查询公司下的所有用户 - 运维人员或调度人员) 
	 * @param @param companyId
	 * @param @return
	 * @return List<UserPO>
	 * @throws
	 */
	public ResultListVO<UserListVO> getUserList(int companyId,int page,int pageSize,UserSearchKey searchKey,int userType){
		ResultListVO<UserListVO> results = new ResultListVO<UserListVO>();
		PageBean<UserListVO> pageBean = new PageBean<UserListVO>();
		pageBean.setCurrentPage(page);
		pageBean.setPageRecorders(pageSize);
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("companyId", companyId);
		param.put("companyId", companyId);
		userDaoImpl.searchUserByPage(pageBean, param,searchKey);
		List<UserListVO> userList = pageBean.getObjList();
		results.setResultList(userList);
		results.setCounts(pageBean.getTotalRows());
		return results;
	}
	/**
	 * 获取对id的user数据
	 * @param ids
	 * @return
	 */
	public List<UserPO> getUsersInfo(String ids){
		List<UserPO> list = userDaoImpl.findByIds(ids);
		return list;
	}
	/**
	 * 获取指定公司id的公司的所有用户
	 * @param companyId
	 * @return
	 */
	public List<UserInfoPO> getCompanyUsersInfo(String companyId,String roleId,String realName){
		Map map = new HashMap();
		map.put("companyId", companyId);
		map.put("roleId", roleId);
		map.put("realName", realName);
		List<UserInfoPO> list = userDaoImpl.getUsersByCompanyId(map);
		return list;
	}
	/**
	 * 获取指定角色用户
	 * @param companyId
	 * @return
	 */
	public List<UserInfoPO> getRoleUsersInfo(int roleId){
		List<UserInfoPO> list = userDaoImpl.getUsersByRoleId(roleId);
		return list;
	}
	
	/**
	 * @Title:addImageCode
	 * @Description:TODO(缓存图片验证码到redis中) 
	 * @param @param tag
	 * @param @param imageCode
	 * @return void
	 * @throws
	 */
	public void addImageCode(String tag,String imageCode){
		CacheManUtil.addStringExpire(CACHE_IMAGE_PREFIX, tag, imageCode, CACHE_IMAGE_TIME);
	}
	/**
	 * @Title:getImageCode
	 * @Description:TODO(获取图片验证码) 
	 * @param @param tag
	 * @param @return
	 * @return String
	 * @throws
	 */
	public String getImageCode(String tag){
		String imageCode = CacheManUtil.getString(CACHE_IMAGE_PREFIX, tag);
		CacheManUtil.remove(CACHE_IMAGE_PREFIX, tag);
		return imageCode;
	}
	
	/**
	 * @Title:sendMsg
	 * @Description:TODO(发送短信验证码) 
	 * @param @param param
	 * @param @return
	 * @return UserVO
	 * @throws
	 */
	public Boolean sendMsg(String phone){
		try {
				//获取6位随机验证码
				String random = getRandomNum(6);
				String status =TemplateSMS.getSendSMSStatus(phone, random+",5");
				logger.info("发送短信验证码#####"+random);
				if ("000000".equals(status)) { //成功发送验证码
					addCacheCode(phone,random);
					return true;
				}
		} catch (Exception e) {
			logger.error(e.getMessage(),"短信验证码发送失败！");
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * @Description: 获取随机数  参数：随机数的位数 
	 * @return String    返回类型 
	 * @throws
	 */
	private String getRandomNum(int longth){
		Random random = new Random();
		String result="";
		for(int i=0;i<longth;i++){
		  result+=random.nextInt(10);
		}
		return result;
	}
	
	/** 
	 *缓存的短信验证码
	 */ 
	public void addCacheCode(String phone,String code) {
		logger.info(phone+":"+code);
		CacheManUtil.addStringExpire(CACHE_CODE_PREFIX, phone,code,CACHE_CODE_TIME);
	}

	public List<UserPO> findUserList(String userName) {
		return userDaoImpl.findBy("nickname", userName, true);
	}
}
