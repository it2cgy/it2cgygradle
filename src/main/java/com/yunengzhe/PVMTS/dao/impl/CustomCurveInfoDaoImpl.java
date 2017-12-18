package com.yunengzhe.PVMTS.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yunengzhe.common.base.dao.impl.BaseDaoImpl;
import com.yunengzhe.PVMTS.dao.CustomCurveInfoDao;
import com.yunengzhe.PVMTS.domain.po.CustomCurveInfoPO;
import com.yunengzhe.PVMTS.domain.vo.CustomCurveInfoVO;

@Repository("customCurveInfoDaoImpl")
public class CustomCurveInfoDaoImpl extends BaseDaoImpl<CustomCurveInfoPO> implements CustomCurveInfoDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	public List<CustomCurveInfoVO> findCurveList(Map map) {
		return sqlSession.selectList(sqlMapNamespace + ".findCurveList", map);
	}
}
