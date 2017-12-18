package com.yunengzhe.PVMTS.dao;

import java.util.Map;

import com.yunengzhe.common.base.dao.BaseDao;
import com.yunengzhe.PVMTS.domain.po.MessageUserInfoPO;

public interface MessageUserInfoDao extends BaseDao<MessageUserInfoPO>{

	int deleteMessage(Map<String, Object> map);

	boolean updateRead(Map<String, Object> map);

}

