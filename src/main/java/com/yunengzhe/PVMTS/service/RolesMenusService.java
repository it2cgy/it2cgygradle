package com.yunengzhe.PVMTS.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.PVMTS.dao.RolesMenusDao;
import com.yunengzhe.PVMTS.domain.po.MenusPO;
import com.yunengzhe.PVMTS.domain.po.RolesMenusPO;
import com.yunengzhe.PVMTS.domain.vo.RolesmenuHandleVO;


@Service("rolesMenusService")
public class RolesMenusService {

	private static final Logger logger = LoggerFactory.getLogger(RolesMenusService.class);
	
	@Autowired
	private RolesMenusDao rolesMenusDaoImpl;
	@Autowired
	private MenusService menusService;
	
	/**
	 * @Title:getRoleMenuList
	 * @Description:TODO(获取角色对应菜单列表) 
	 * @param @return
	 * @return List<RolesmenuHandleVO>
	 * @throws
	 */
	public List<RolesmenuHandleVO> getRoleMenuList(Integer roleId){
		List<RolesmenuHandleVO> result = new ArrayList<RolesmenuHandleVO>();
		List<MenusPO>  menus = menusService.getMenusList();
		if(roleId==null){
			for(MenusPO p : menus){
				RolesmenuHandleVO v = new RolesmenuHandleVO();
				Map<String,Object> param = new HashMap<String,Object>();
				v.setId(p.getId());
				v.setName(p.getName());
				v.setHave_menu(0);
				v.setHandle_premission(0);
				result.add(v);
			}
		}else{
			for(MenusPO p : menus){
				RolesmenuHandleVO v = new RolesmenuHandleVO();
				Map<String,Object> param = new HashMap<String,Object>();
				param.put("roleId", roleId);
				param.put("menuId", p.getId());
				RolesMenusPO po = rolesMenusDaoImpl.findUniqueByMap(param);
				v.setId(p.getId());
				v.setName(p.getName());
				
				if(po!=null){
					v.setHave_menu(1);
					v.setHandle_premission(po.getHandlePremission());
				}else{
					v.setHave_menu(0);
					v.setHandle_premission(0);
				}
				result.add(v);
			}
		}
		return result;
	}
	
	public HashMap<String,RolesmenuHandleVO> getRoleAndMenu(String roleIds){
		HashMap<String,RolesmenuHandleVO> resultMap = new HashMap<String,RolesmenuHandleVO>();
		List<MenusPO>  menus = menusService.getMenusList();
		for(MenusPO p : menus){
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("menuId",p.getId());
			param.put("roleIds",roleIds);
			List<RolesMenusPO> rolesMenu =  rolesMenusDaoImpl.getRoleMenuList(param);
			RolesmenuHandleVO v = new RolesmenuHandleVO();
			v.setId(p.getId());
			v.setName(p.getName());
			v.setHave_menu(0);
			v.setHandle_premission(0);
			boolean flag = false;
			for(RolesMenusPO rm : rolesMenu){
				if(rm.getMenuId()==p.getId()){
					v.setHave_menu(1);
				}
				if(rm.getHandlePremission()==1){
					flag=true;
				}
			}
			if(flag){
				v.setHandle_premission(1);
			}else{
				v.setHandle_premission(0);
			}
			resultMap.put(""+p.getId(), v);
		}
		return resultMap;
	}
	
	
	/**
	 * @Title:insertRoleMenus
	 * @Description:TODO(批量插入) 
	 * @param 
	 * @return void
	 * @throws
	 */
	public void insertRoleMenusBatch(List<RolesMenusPO> param){
		rolesMenusDaoImpl.insertBatch(param);
	}
	
	public void delRoleMenus(int roleId){
		RolesMenusPO model = new RolesMenusPO();
		model.setRoleId(roleId);
		rolesMenusDaoImpl.delete(model);
	}
	
}
