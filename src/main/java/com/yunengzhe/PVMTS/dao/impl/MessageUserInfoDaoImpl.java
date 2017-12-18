package com.yunengzhe.PVMTS.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yunengzhe.common.base.dao.impl.BaseDaoImpl;
import com.yunengzhe.PVMTS.dao.MessageUserInfoDao;
import com.yunengzhe.PVMTS.domain.po.MessageUserInfoPO;

@Repository("messageUserInfoDaoImpl")
public class MessageUserInfoDaoImpl extends BaseDaoImpl<MessageUserInfoPO> implements MessageUserInfoDao {

	@Override
	public int deleteMessage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().delete(sqlMapNamespace + ".deleteMessage", map);
	}

	@Override
	public boolean updateRead(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().update(sqlMapNamespace + ".updateRead", map)==1;
	}

}
