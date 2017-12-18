package com.yunengzhe.PVMTS.dao.impl;

import org.springframework.stereotype.Repository;

import com.yunengzhe.common.base.dao.impl.BaseDaoImpl;
import com.yunengzhe.PVMTS.dao.VersionInfoDao;
import com.yunengzhe.PVMTS.domain.po.VersionInfoPO;

@Repository("versionInfoDaoImpl")
public class VersionInfoDaoImpl extends BaseDaoImpl<VersionInfoPO> implements VersionInfoDao {

}
