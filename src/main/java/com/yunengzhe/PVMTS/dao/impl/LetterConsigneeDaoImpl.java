package com.yunengzhe.PVMTS.dao.impl;

import org.springframework.stereotype.Repository;

import com.yunengzhe.common.base.dao.impl.BaseDaoImpl;
import com.yunengzhe.PVMTS.dao.LetterConsigneeDao;
import com.yunengzhe.PVMTS.domain.po.LetterConsigneePO;

@Repository("consigneeDaoImpl")
public class LetterConsigneeDaoImpl extends BaseDaoImpl<LetterConsigneePO> implements LetterConsigneeDao {

	public int updateToDelete(String ids) {
		return getSqlSession().update("com.yunengzhe.PVMTS.domain.po.LetterConsigneePO.updateToDelelete", ids);
	}

}
