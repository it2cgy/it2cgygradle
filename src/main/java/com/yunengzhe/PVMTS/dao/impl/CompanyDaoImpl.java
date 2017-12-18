package com.yunengzhe.PVMTS.dao.impl;

import org.springframework.stereotype.Repository;

import com.yunengzhe.common.base.dao.impl.BaseDaoImpl;
import com.yunengzhe.PVMTS.dao.CompanyDao;
import com.yunengzhe.PVMTS.domain.po.CompanyPO;

@Repository("companyDaoImpl")
public class CompanyDaoImpl extends BaseDaoImpl<CompanyPO> implements CompanyDao {

}
