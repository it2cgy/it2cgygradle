package com.yunengzhe.PVMTS.dao;

import com.yunengzhe.common.base.dao.BaseDao;
import com.yunengzhe.common.tool.scaffold.util.PageBean;

import java.util.List;
import java.util.Map;

import com.yunengzhe.PVMTS.domain.dto.UserSearchKey;
import com.yunengzhe.PVMTS.domain.po.UserInfoPO;
import com.yunengzhe.PVMTS.domain.po.UserPO;
import com.yunengzhe.PVMTS.domain.vo.UserListVO;

public interface UserDao extends BaseDao<UserPO>{
	public List<UserInfoPO> getUsersByRoleId(int roleId);
	public List<UserInfoPO> getUsersByCompanyId(Map<String, Object> map);
	public void setDeleteByIds(String ids); 
	public  void searchUserByPage(PageBean<UserListVO> pageBean,Map<String,Object> map,UserSearchKey searchKey);
}

