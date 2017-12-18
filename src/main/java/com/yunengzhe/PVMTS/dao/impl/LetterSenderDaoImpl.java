package com.yunengzhe.PVMTS.dao.impl;

import org.springframework.stereotype.Repository;

import com.yunengzhe.common.base.dao.impl.BaseDaoImpl;
import com.yunengzhe.PVMTS.dao.LetterSenderDao;
import com.yunengzhe.PVMTS.domain.po.LetterSenderPO;

@Repository("senderDaoImpl")
public class LetterSenderDaoImpl extends BaseDaoImpl<LetterSenderPO> implements LetterSenderDao {

	public int updateToDelete(String ids) {
		return getSqlSession().update("com.yunengzhe.PVMTS.domain.po.LetterSenderPO.updateToDelelete", ids);
	}
}
