package com.yunengzhe.PVMTS.dao.impl;

import org.springframework.stereotype.Repository;

import com.yunengzhe.common.base.dao.impl.BaseDaoImpl;
import com.yunengzhe.PVMTS.dao.LetterDao;
import com.yunengzhe.PVMTS.domain.po.LetterPO;

@Repository("letterDaoImpl")
public class LetterDaoImpl extends BaseDaoImpl<LetterPO> implements LetterDao {

}
