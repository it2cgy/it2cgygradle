package com.yunengzhe.PVMTS.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yunengzhe.common.base.dao.impl.BaseDaoImpl;
import com.yunengzhe.common.tool.scaffold.util.PageBean;
import com.yunengzhe.common.tool.scaffold.util.SysConst.SortBy;
import com.yunengzhe.PVMTS.dao.UserDao;
import com.yunengzhe.PVMTS.domain.dto.UserSearchKey;
import com.yunengzhe.PVMTS.domain.po.UserInfoPO;
import com.yunengzhe.PVMTS.domain.po.UserPO;
import com.yunengzhe.PVMTS.domain.vo.UserListVO;

@Repository("userDaoImpl")
public class UserDaoImpl extends BaseDaoImpl<UserPO> implements UserDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	public List<UserInfoPO> getUsersByRoleId(int roleId){
		return sqlSession.selectList(sqlMapNamespace + ".findByRoleId",roleId);
	}
	
	public List<UserInfoPO> getUsersByCompanyId(Map<String, Object> map) {
		return sqlSession.selectList(sqlMapNamespace + ".findByCompanyId", map);
	}
	public  void searchUserByPage(PageBean<UserListVO> pageBean,Map<String,Object> map,UserSearchKey searchKey){
		if (map == null)
			map = new HashMap<String, Object>();
		map.put("findBy", "true"); 
		if(StringUtils.isNotBlank(searchKey.getNameKey())){
			map.put("nameKey", searchKey.getNameKey()); 
		}

		if(StringUtils.isNotBlank(searchKey.getNickKey())){
			map.put("nickKey", searchKey.getNickKey()); 
		}
		
		if(StringUtils.isNotBlank(searchKey.getCreateDateKey())){
			map.put("createDateKey", searchKey.getCreateDateKey());
		}
		
		if(StringUtils.isNotBlank(searchKey.getRoleKey())){
			map.put("roleNamestr", searchKey.getRoleKey());
		}
		
		Integer totalRows = sqlSession.selectOne(sqlMapNamespace + POSTFIX_SELECT_COUNT, map);
		pageBean.setTotalRows(totalRows);
		if (totalRows != null && totalRows > 0) {
			map.put("startRow", pageBean.getSimpleStartRow());
			map.put("pageSize", pageBean.getPageRecorders());

			List<UserListVO> list = sqlSession.selectList(sqlMapNamespace + POSTFIX_SELECT_PAGE, map);
			pageBean.setObjList(list);
		} 
	}
	@Override
	public void setDeleteByIds(String ids) {
		
		sqlSession.update(sqlMapNamespace +  ".setDeleteByIds", ids);
	}
}