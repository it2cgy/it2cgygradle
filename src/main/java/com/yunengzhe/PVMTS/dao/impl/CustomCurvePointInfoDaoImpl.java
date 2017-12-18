package com.yunengzhe.PVMTS.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yunengzhe.common.base.dao.impl.BaseDaoImpl;
import com.yunengzhe.PVMTS.dao.CustomCurvePointInfoDao;
import com.yunengzhe.PVMTS.domain.po.CustomCurvePointInfoPO;

@Repository("customCurvePointInfoDaoImpl")
public class CustomCurvePointInfoDaoImpl extends BaseDaoImpl<CustomCurvePointInfoPO> implements CustomCurvePointInfoDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public int deleteByIds(String Ids){
		return sqlSession.delete(sqlMapNamespace + ".deleteByIds", Ids);
	}
}
