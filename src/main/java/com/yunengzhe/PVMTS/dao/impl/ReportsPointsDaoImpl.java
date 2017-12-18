package com.yunengzhe.PVMTS.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yunengzhe.common.base.dao.impl.BaseDaoImpl;
import com.yunengzhe.PVMTS.dao.ReportsPointsDao;
import com.yunengzhe.PVMTS.domain.po.ReportsPointsPO;

@Repository("reportsPointsDaoImpl")
public class ReportsPointsDaoImpl extends BaseDaoImpl<ReportsPointsPO> implements ReportsPointsDao {

	
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public List<ReportsPointsPO> getPointsList(String ids){
		return sqlSession.selectList(sqlMapNamespace + ".getPointsList", ids);
	}
}
