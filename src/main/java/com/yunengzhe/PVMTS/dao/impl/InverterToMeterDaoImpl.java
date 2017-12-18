package com.yunengzhe.PVMTS.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yunengzhe.common.base.dao.impl.BaseDaoImpl;
import com.yunengzhe.PVMTS.dao.InverterToMeterDao;
import com.yunengzhe.PVMTS.domain.po.InverterToMeterPO;
import com.yunengzhe.PVMTS.domain.vo.longi.InverterToMeterVO;

@Repository("inverterToMeterDaoImpl")
public class InverterToMeterDaoImpl extends BaseDaoImpl<InverterToMeterPO> implements InverterToMeterDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	public List<InverterToMeterVO> getAmmeterInfo(Integer id){
		return sqlSession.selectList(sqlMapNamespace + ".selectAmmeterInfo", id);
	}
}
