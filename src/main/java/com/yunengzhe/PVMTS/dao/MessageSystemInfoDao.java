package com.yunengzhe.PVMTS.dao;

import java.util.List;
import java.util.Map;

import com.yunengzhe.common.base.dao.BaseDao;
import com.yunengzhe.common.tool.scaffold.util.PageBean;
import com.yunengzhe.PVMTS.domain.po.MessageSystemInfoPO;
import com.yunengzhe.PVMTS.domain.po.WorkOrderInfoPO;

public interface MessageSystemInfoDao extends BaseDao<MessageSystemInfoPO>{

	int getNoRead(Map<String, Object> map);

	List<MessageSystemInfoPO> getMassageByType(Map<String, Object> map);

	Integer getCountByMap(Map<String, Object> map);
}

