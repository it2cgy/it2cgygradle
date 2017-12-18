package com.yunengzhe.PVMTS.dao.impl;

import org.springframework.stereotype.Repository;

import com.yunengzhe.common.base.dao.impl.BaseDaoImpl;
import com.yunengzhe.PVMTS.dao.ScanInfoDao;
import com.yunengzhe.PVMTS.domain.po.ScanInfoPO;

@Repository("scanInfoDaoImpl")
public class ScanInfoDaoImpl extends BaseDaoImpl<ScanInfoPO> implements ScanInfoDao {

}
