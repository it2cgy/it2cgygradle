package com.yunengzhe.PVMTS.dao.impl;

import org.springframework.stereotype.Repository;

import com.yunengzhe.common.base.dao.impl.BaseDaoImpl;
import com.yunengzhe.PVMTS.dao.DeviceRatedDao;
import com.yunengzhe.PVMTS.domain.po.DeviceRatedPO;

@Repository("deviceRatedDaoImpl")
public class DeviceRatedDaoImpl extends BaseDaoImpl<DeviceRatedPO> implements DeviceRatedDao {

}
