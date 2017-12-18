package com.yunengzhe.PVMTS.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yunengzhe.PVMTS.dao.AlarmInfoDao;
import com.yunengzhe.PVMTS.domain.po.AlarmInfoPO;
import com.yunengzhe.PVMTS.domain.po.AlarmListInfo;
import com.yunengzhe.PVMTS.domain.vo.AlarmCountByUipmentVO;
import com.yunengzhe.PVMTS.domain.vo.AlarmCountVO;
import com.yunengzhe.common.base.dao.impl.BaseDaoImpl;

@Repository("alarmInfoDaoImpl")
public class AlarmInfoDaoImpl extends BaseDaoImpl<AlarmInfoPO> implements AlarmInfoDao {
	@Override
	public int getcount(Map<String, Object> map) {
		return getSqlSession().selectOne(sqlMapNamespace + ".getcount",map);
	}
	@Override
	public List<AlarmInfoPO> findList(Map map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(sqlMapNamespace + ".findList",map);
	}

	@Override
	public List<AlarmInfoPO> getAlarmHostoryList(Integer powerStationId) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(sqlMapNamespace + ".getAlarmHostoryList",powerStationId);
	}

	@Override
	public int getAlarmCount(Integer powerStationId) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(sqlMapNamespace + ".getAlarmCount",powerStationId);
	}
	@Override
	public List<AlarmCountByUipmentVO> getAlarmCountByUipment(Map map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(sqlMapNamespace + ".getAlarmCountByUipment",map);
	}
	@Override
	public List<AlarmInfoPO> getAlarmListBySearch(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(sqlMapNamespace + ".getAlarmListBySearch",map);
	}
	@Override
	public int getAlarmSum(Integer powerStationId) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(sqlMapNamespace + ".getAlarmSum",powerStationId);
	}
	@Override
	public int getNoReadCount(Integer userId) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(sqlMapNamespace + ".getNoReadCount",userId);
	}
	@Override
	public int getCountBySearch(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(sqlMapNamespace + ".getCountBySearch",map);
	}
	@Override
	public AlarmInfoPO getAlarmDispose(Integer id) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(sqlMapNamespace + ".getAlarmDispose",id);
	}
}
