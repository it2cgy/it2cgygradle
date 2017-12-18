package com.yunengzhe.PVMTS.dao;

import com.yunengzhe.common.base.dao.BaseDao;
import com.yunengzhe.PVMTS.domain.po.LetterConsigneePO;

public interface LetterConsigneeDao extends BaseDao<LetterConsigneePO>{

	/**
     * 根据主键ID批量逻辑删除
     * @param ids
     * @return
     */
    public int updateToDelete(String ids);

}

