package com.yunengzhe.PVMTS.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yunengzhe.common.base.dao.impl.BaseDaoImpl;
import com.yunengzhe.PVMTS.dao.RulesReaderDao;
import com.yunengzhe.PVMTS.domain.dto.NoticeOwnerDTO;
import com.yunengzhe.PVMTS.domain.dto.RulesOwnerDTO;
import com.yunengzhe.PVMTS.domain.po.NoticeReaderPO;
import com.yunengzhe.PVMTS.domain.po.RulesReaderPO;

@Repository("rulesReaderDaoImpl")
public class RulesReaderDaoImpl extends BaseDaoImpl<RulesReaderPO> implements RulesReaderDao {

	@Override
	public List<RulesReaderPO> findByRulesIds(RulesOwnerDTO RulesOwnerDTO) {
		return getSqlSession().selectList("com.yunengzhe.PVMTS.domain.po.RulesReaderPO.selectByRulesIds", RulesOwnerDTO);
	}
}
