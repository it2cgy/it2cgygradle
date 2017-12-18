package com.yunengzhe.PVMTS.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yunengzhe.common.base.dao.impl.BaseDaoImpl;
import com.yunengzhe.common.tool.scaffold.util.PageBean;
import com.yunengzhe.PVMTS.dao.MessageSystemInfoDao;
import com.yunengzhe.PVMTS.domain.po.MessageSystemInfoPO;
import com.yunengzhe.PVMTS.domain.po.WorkOrderInfoPO;

@Repository("messageSystemInfoDaoImpl")
public class MessageSystemInfoDaoImpl extends BaseDaoImpl<MessageSystemInfoPO> implements MessageSystemInfoDao {

	@Override
	public int getNoRead(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(sqlMapNamespace + ".getNoRead",map);
	}

	@Override
	public List<MessageSystemInfoPO> getMassageByType(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(sqlMapNamespace + ".getMassageByType",map);
	}
	
	@Override
	public Integer getCountByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(sqlMapNamespace + ".getCountByMap", map);
	}
}
