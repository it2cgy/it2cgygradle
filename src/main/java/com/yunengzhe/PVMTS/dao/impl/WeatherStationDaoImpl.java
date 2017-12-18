package com.yunengzhe.PVMTS.dao.impl;

import org.springframework.stereotype.Repository;

import com.yunengzhe.common.base.dao.impl.BaseDaoImpl;
import com.yunengzhe.PVMTS.dao.WeatherStationDao;
import com.yunengzhe.PVMTS.domain.po.WeatherStationPO;

@Repository("weatherStationDaoImpl")
public class WeatherStationDaoImpl extends BaseDaoImpl<WeatherStationPO> implements WeatherStationDao {

}
