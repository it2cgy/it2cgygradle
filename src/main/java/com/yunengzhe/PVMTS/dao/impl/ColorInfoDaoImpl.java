package com.yunengzhe.PVMTS.dao.impl;

import org.springframework.stereotype.Repository;

import com.yunengzhe.common.base.dao.impl.BaseDaoImpl;
import com.yunengzhe.PVMTS.dao.ColorInfoDao;
import com.yunengzhe.PVMTS.domain.po.ColorInfoPO;

@Repository("colorInfoDaoImpl")
public class ColorInfoDaoImpl extends BaseDaoImpl<ColorInfoPO> implements ColorInfoDao {

}
