package com.yunengzhe.PVMTS.dao;

import com.yunengzhe.common.base.dao.BaseDao;
import com.yunengzhe.PVMTS.domain.po.SettingInfoPO;

public interface SettingInfoDao extends BaseDao<SettingInfoPO>{

	int updateSetting(SettingInfoPO setting);

}

