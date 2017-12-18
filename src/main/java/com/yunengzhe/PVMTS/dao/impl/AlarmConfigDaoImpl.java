package com.yunengzhe.PVMTS.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yunengzhe.common.base.dao.impl.BaseDaoImpl;
import com.yunengzhe.PVMTS.dao.AlarmConfigDao;
import com.yunengzhe.PVMTS.domain.po.AlarmConfigPO;

@Repository("alarmConfigDaoImpl")
public class AlarmConfigDaoImpl extends BaseDaoImpl<AlarmConfigPO> implements AlarmConfigDao {
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public void deleteByIds(String ids) {
		sqlSession.delete(sqlMapNamespace +  ".deleteByIds", ids);
	}

}
