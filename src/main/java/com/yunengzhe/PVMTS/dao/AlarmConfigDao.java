package com.yunengzhe.PVMTS.dao;

import com.yunengzhe.common.base.dao.BaseDao;
import com.yunengzhe.PVMTS.domain.po.AlarmConfigPO;

public interface AlarmConfigDao extends BaseDao<AlarmConfigPO>{
	public void deleteByIds(String ids); 
}

