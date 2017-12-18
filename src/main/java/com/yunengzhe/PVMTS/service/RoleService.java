package com.yunengzhe.PVMTS.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.PVMTS.dao.RoleDao;
import com.yunengzhe.PVMTS.domain.po.RolePO;
import com.yunengzhe.PVMTS.domain.po.WorkOrderInfoPO;
import com.yunengzhe.PVMTS.domain.vo.ResultListVO;
import com.yunengzhe.PVMTS.domain.vo.WorkOrderInfoVO;
import com.yunengzhe.PVMTS.domain.vo.role.RoleVO;
import com.yunengzhe.common.tool.scaffold.util.PageBean;

/**
 * @ClassName:RoleService
 * @Description:TODO(角色处理)
 * @author chenguiyang
 * @date 2017年6月14日上午9:47:06
 */
@Service("roleService")
public class RoleService {

	private static final Logger logger = LoggerFactory.getLogger(RoleService.class);
	
	@Autowired
	private RoleDao roleDaoImpl;
	 
	
	/**
	 * @Title:getRoles
	 * @Description:TODO(获取用户角色列表) 
	 * @param 
	 * @return void
	 * @throws
	 */
	public List<RolePO> getRoles(){
		logger.debug("加载角色列表");
		List<RolePO> result = new ArrayList<RolePO>();
		List<RolePO> listData = roleDaoImpl.findAll();
		for(RolePO r : listData){
			int id = r.getId();
			if(id!=1){
				result.add(r);
			}
		}
		return result;
	}
	
	
	/**
	 * @Title:insertRole
	 * @Description:TODO(新增角色) 
	 * @param 
	 * @return void
	 * @throws
	 */
	public void insertRole(RolePO param){
		try {
			roleDaoImpl.insert(param);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	/**
	 * @Title:updateRole
	 * @Description:TODO(更新角色信息) 
	 * @param 
	 * @return void
	 * @throws
	 */
	public void updateRole(RolePO param){
		try {
			roleDaoImpl.update(param);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	
	/**
	 * @Title:getRolesPage
	 * @Description:TODO(获取用户角色) 
	 * @param @return
	 * @return ResultListVO<T>
	 * @throws
	 */
	public ResultListVO<RoleVO> getRolesPage(String roleName,int page,int pageSize){
		ResultListVO<RoleVO>  results = new ResultListVO<RoleVO>();
		List<RoleVO> roleList = new ArrayList<RoleVO>();
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("roleType", 1);
		param.put("roleName",roleName);
		PageBean<RolePO> pageBean = new PageBean<RolePO>();
		pageBean.setCurrentPage(page);
		pageBean.setPageRecorders(pageSize);
		roleDaoImpl.find4Page(pageBean, param,true);
		List<RolePO> listData = pageBean.getObjList();
		if(listData.size()>0){
			for(RolePO r : listData){
				RoleVO rv = new RoleVO();
				rv.setId(r.getId());
				rv.setRoleName(r.getRoleName());
				rv.setCreateTime(r.getCreateTime());
				roleList.add(rv);
			}
		}
		results.setResultList(roleList);
		results.setCounts(pageBean.getTotalRows());
		return results;
	}
	
	/**
	 * @Title:getRoleInfo
	 * @Description:TODO(获取用户角色信息通过id) 
	 * @param @return
	 * @return RolePO
	 * @throws
	 */
	public RolePO getRoleInfoById(int roleId){
		List<RolePO> role =  roleDaoImpl.findByIds(""+roleId);
		return role.size()>0?role.get(0):null;
	}
	
	public void delRole(int roleId){
		roleDaoImpl.delete(roleId);
	}
}
