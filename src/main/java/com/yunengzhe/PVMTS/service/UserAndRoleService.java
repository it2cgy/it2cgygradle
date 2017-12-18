package com.yunengzhe.PVMTS.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.PVMTS.dao.UserAndRoleDao;
import com.yunengzhe.PVMTS.domain.po.UserAndRolePO;


@Service("userAndRoleService")
public class UserAndRoleService {

	private static final Logger logger = LoggerFactory.getLogger(UserAndRoleService.class);
	
	@Autowired
	private UserAndRoleDao userAndRoleDaoImpl;
	
	/**
	 * @Title:getRoleId
	 * @Description:TODO(根据用户id获取用户角色id) 
	 * @param @return
	 * @return UserAndRolePO
	 * @throws
	 */
	public List<UserAndRolePO>  getRoleByUserId(int userId){
		List<UserAndRolePO> list = userAndRoleDaoImpl.findBy("userId",userId);
		return list;
	}
	
	
	/**
	 * @Title:getRoleId
	 * @Description:TODO(根据用户角色id查询当前角色是否占用) 
	 * @param @return
	 * @return UserAndRolePO
	 * @throws
	 */
	public List<UserAndRolePO>  getRoleAndUser(int roleId){
		List<UserAndRolePO> list = userAndRoleDaoImpl.findBy("roleId",roleId);
		return list;
	}
	
	/**
	 * @Title:addUserRole
	 * @Description:TODO(新增用户后分配角色) 
	 * @param 
	 * @return void
	 * @throws
	 */
	public void addUserRole(List<UserAndRolePO>  param){
		userAndRoleDaoImpl.insertBatch(param);
	}
	/**
	 * @Title:delUserRole
	 * @Description:TODO(删除当前人员的所有角色) 
	 * @param @param userId
	 * @return void
	 * @throws
	 */
	public void delUserRole(int userId){
		UserAndRolePO user = new UserAndRolePO();
		user.setUserId(userId);
		userAndRoleDaoImpl.delete(user);
	}
	 
}
