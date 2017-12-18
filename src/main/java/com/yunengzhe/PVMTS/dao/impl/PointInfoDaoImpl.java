package com.yunengzhe.PVMTS.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yunengzhe.common.base.dao.impl.BaseDaoImpl;
import com.yunengzhe.PVMTS.dao.PointInfoDao;
import com.yunengzhe.PVMTS.domain.po.PointInfoPO;
import com.yunengzhe.PVMTS.domain.vo.EquipmentInfoVO;

@Repository("pointInfoDaoImpl")
public class PointInfoDaoImpl extends BaseDaoImpl<PointInfoPO> implements PointInfoDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	public List<PointInfoPO> getPowerStationEquioments(Integer powerStationId){
		return sqlSession.selectList(sqlMapNamespace + ".selectEquipmentByPowerStationId", powerStationId);
	}
	public List<EquipmentInfoVO> getEquioments(Map<String, Object> map){
		return sqlSession.selectList(sqlMapNamespace + ".selectEquipments", map);
	}
}
