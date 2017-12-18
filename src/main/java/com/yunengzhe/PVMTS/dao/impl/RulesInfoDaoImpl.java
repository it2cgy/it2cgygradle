package com.yunengzhe.PVMTS.dao.impl;

import org.springframework.stereotype.Repository;

import com.yunengzhe.common.base.dao.impl.BaseDaoImpl;
import com.yunengzhe.PVMTS.dao.RulesInfoDao;
import com.yunengzhe.PVMTS.domain.po.RulesInfoPO;

@Repository("rulesInfoDaoImpl")
public class RulesInfoDaoImpl extends BaseDaoImpl<RulesInfoPO> implements RulesInfoDao {

	public int updateToDelete(String ids) {
		return getSqlSession().update("com.yunengzhe.PVMTS.domain.po.RulesInfoPO.updateToDelelete", ids);
	}

}
