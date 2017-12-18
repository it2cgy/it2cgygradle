package com.yunengzhe.PVMTS.dao.impl;

import org.springframework.stereotype.Repository;

import com.yunengzhe.common.base.dao.impl.BaseDaoImpl;
import com.yunengzhe.PVMTS.dao.LogsCategoryDao;
import com.yunengzhe.PVMTS.domain.po.LogsCategoryPO;

@Repository("logsCategoryDaoImpl")
public class LogsCategoryDaoImpl extends BaseDaoImpl<LogsCategoryPO> implements LogsCategoryDao {

}
