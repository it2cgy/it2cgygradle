package com.yunengzhe.PVMTS.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yunengzhe.common.base.dao.impl.BaseDaoImpl;
import com.yunengzhe.PVMTS.dao.MeasurementBoxDao;
import com.yunengzhe.PVMTS.domain.po.MeasurementBoxPO;
import com.yunengzhe.PVMTS.domain.vo.longi.MeasurementBoxAndInverterVO;

@Repository("measurementBoxDaoImpl")
public class MeasurementBoxDaoImpl extends BaseDaoImpl<MeasurementBoxPO> implements MeasurementBoxDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	public List<MeasurementBoxAndInverterVO> findMeasureInverter(String ids){
		ids = checkSqlParam(ids);
		return sqlSession.selectList(sqlMapNamespace + ".selectByInverterIds", ids);
	}
}
