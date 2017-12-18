package com.yunengzhe.PVMTS.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.PVMTS.dao.RoleAndPowerDao;
import com.yunengzhe.PVMTS.domain.po.RoleAndPowerPO;


@Service("roleAndPowerService")
public class RoleAndPowerService {

	private static final Logger logger = LoggerFactory.getLogger(RoleAndPowerService.class);
	
	@Autowired
	private RoleAndPowerDao roleAndPowerDaoImpl;
	
	/**
	 * @Title:getRoleAndPower
	 * @Description:TODO(获取角色与电站对应表) 
	 * @param 
	 * @return void
	 * @throws
	 */
	public List<RoleAndPowerPO> getRoleAndPower(RoleAndPowerPO param){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("roleId", param.getRoleId());
		paramMap.put("powerstationId",param.getPowerstationId());
		List<RoleAndPowerPO> result = roleAndPowerDaoImpl.findByMap(paramMap);
		return result;
	}
	
	/**
	 * @Title:getRoleAndPower
	 * @Description:TODO(获取角色与电站对应表) 
	 * @param 
	 * @return void
	 * @throws
	 */
	public List<RoleAndPowerPO> getRoleAndPower(){
		List<RoleAndPowerPO> result = roleAndPowerDaoImpl.findAll();
		return result;
	}
	/**
	 * @Title:addRoleAndPower
	 * @Description:TODO(新增电站与角色对应数据) 
	 * @param 
	 * @return void
	 * @throws
	 */
	public void addRoleAndPower(RoleAndPowerPO param){
		try {
			roleAndPowerDaoImpl.insert(param);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		
	}
	
	/**
	 * @Title:delRoleAndPower
	 * @Description:TODO(删除对应关系) 
	 * @param @param param
	 * @return void
	 * @throws
	 */
	public int delRoleAndPower(RoleAndPowerPO param) {
		int flag = 0;
		try {
			flag = roleAndPowerDaoImpl.delete(param);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return flag;
	}
}
