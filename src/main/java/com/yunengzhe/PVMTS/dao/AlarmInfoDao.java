package com.yunengzhe.PVMTS.dao;

import java.util.List;
import java.util.Map;

import com.yunengzhe.common.base.dao.BaseDao;
import com.yunengzhe.PVMTS.domain.po.AlarmInfoPO;
import com.yunengzhe.PVMTS.domain.po.AlarmListInfo;
import com.yunengzhe.PVMTS.domain.vo.AlarmCountByUipmentVO;
import com.yunengzhe.PVMTS.domain.vo.AlarmCountVO;

public interface AlarmInfoDao extends BaseDao<AlarmInfoPO>{

	int getcount(Map<String, Object> map);

	List<AlarmInfoPO> findList(Map map);

	List<AlarmInfoPO> getAlarmHostoryList(Integer powerStationId);

	int getAlarmCount(Integer powerStationId);

	List<AlarmCountByUipmentVO> getAlarmCountByUipment(Map map);

	List<AlarmInfoPO> getAlarmListBySearch(Map<String, Object> map);

	int getAlarmSum(Integer powerStationId);

	int getNoReadCount(Integer userid);

	int getCountBySearch(Map<String, Object> map);

	AlarmInfoPO getAlarmDispose(Integer id);

}
