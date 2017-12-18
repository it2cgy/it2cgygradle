package com.yunengzhe.PVMTS.dao.impl;

import org.springframework.stereotype.Repository;

import com.yunengzhe.common.base.dao.impl.BaseDaoImpl;
import com.yunengzhe.PVMTS.dao.SettingInfoDao;
import com.yunengzhe.PVMTS.domain.po.SettingInfoPO;

@Repository("settingInfoDaoImpl")
public class SettingInfoDaoImpl extends BaseDaoImpl<SettingInfoPO> implements SettingInfoDao {

	@Override
	public int updateSetting(SettingInfoPO setting) {
		// TODO Auto-generated method stub
		return getSqlSession().update(sqlMapNamespace + ".updateSetting", setting);
	}

}
