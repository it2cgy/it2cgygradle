package com.yunengzhe.PVMTS.dao.impl;

import org.springframework.stereotype.Repository;

import com.yunengzhe.common.base.dao.impl.BaseDaoImpl;
import com.yunengzhe.PVMTS.dao.MeteorInfoDao;
import com.yunengzhe.PVMTS.domain.po.MeteorInfoPO;

@Repository("meteorInfoDaoImpl")
public class MeteorInfoDaoImpl extends BaseDaoImpl<MeteorInfoPO> implements MeteorInfoDao {

}
