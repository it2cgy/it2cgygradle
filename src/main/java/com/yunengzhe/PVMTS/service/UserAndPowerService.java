package com.yunengzhe.PVMTS.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.PVMTS.dao.UserAndPowerDao;
import com.yunengzhe.PVMTS.domain.po.UserAndPowerPO;

/**
 * @ClassName:UserAndPowerService
 * @Description:TODO(用户与电站对应表)
 * @author chenguiyang
 * @date 2017年6月13日下午2:18:05
 */
@Service("userAndPowerService")
public class UserAndPowerService {

	private static final Logger logger = LoggerFactory.getLogger(UserAndPowerService.class);
	
	@Autowired
	private UserAndPowerDao userAndPowerDaoImpl;
	@Autowired
	private UserAndRoleService userAndRoleService;
	
	
	/**
	 * @Title:getUserAndPower
	 * @Description:TODO(查询电站下的运维用户列表) 
	 * @param @return
	 * @return List<UserPO>
	 * @throws
	 */
	public List<UserAndPowerPO> getUserAndPower(int powerstationId){
		logger.debug("#--------------#查询运维用户列表 -通过电站id");
		List<UserAndPowerPO> listData = userAndPowerDaoImpl
						.findBy("powerstationId", powerstationId);
//		if(listData.size()>0){
//			for(UserAndPowerPO userAndPower : listData){
//				UserAndRolePO  userAndRole = userAndRoleService.getRoleByUserId(userAndPower.getUserId());
//				if(userAndRole!=null && userAndRole.getRoleId()!=2){
//					listData.remove(userAndPower);
//				}
//			}
//		}
		return listData;
	}
	
	/**
	 * @Title:getUserList
	 * @Description:TODO(获取电站下的所有用户列表) 
	 * @param @param powerstationId
	 * @param @return
	 * @return List<UserAndPowerPO>
	 * @throws
	 */
	public List<UserAndPowerPO> getUserList(int powerstationId){
		logger.debug("#--------------#查询用户列表 -通过电站id");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("powerstationId", powerstationId);
		List<UserAndPowerPO> listData = userAndPowerDaoImpl.findByMap(map);
		return listData;
	}
	
}
