package com.yunengzhe.PVMTS.dao;

import com.yunengzhe.common.base.dao.BaseDao;

import java.util.List;

import com.yunengzhe.PVMTS.domain.dto.RulesOwnerDTO;
import com.yunengzhe.PVMTS.domain.po.RulesReaderPO;

public interface RulesReaderDao extends BaseDao<RulesReaderPO>{

	/**
	 * 根据ids,查询Rules对象
	* @param ids
	* @return 
	 */
	public List<RulesReaderPO> findByRulesIds(RulesOwnerDTO RulesOwnerDTO);

}

