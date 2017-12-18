package com.yunengzhe.PVMTS.dao.impl;

import org.springframework.stereotype.Repository;

import com.yunengzhe.common.base.dao.impl.BaseDaoImpl;
import com.yunengzhe.PVMTS.dao.ExamineDao;
import com.yunengzhe.PVMTS.domain.po.ExaminePO;

@Repository("examineDaoImpl")
public class ExamineDaoImpl extends BaseDaoImpl<ExaminePO> implements ExamineDao {

}
