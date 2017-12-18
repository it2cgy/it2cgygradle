package com.yunengzhe.PVMTS.dao.impl;

import org.springframework.stereotype.Repository;

import com.yunengzhe.common.base.dao.impl.BaseDaoImpl;
import com.yunengzhe.PVMTS.dao.ReportsDao;
import com.yunengzhe.PVMTS.domain.po.ReportsPO;

@Repository("reportsDaoImpl")
public class ReportsDaoImpl extends BaseDaoImpl<ReportsPO> implements ReportsDao {

}
