package com.yunengzhe.PVMTS.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yunengzhe.common.base.dao.impl.BaseDaoImpl;
import com.yunengzhe.PVMTS.dao.CurveInfoDao;
import com.yunengzhe.PVMTS.domain.po.CurveInfoPO;
import com.yunengzhe.PVMTS.domain.vo.CurveDetailVO;

@Repository("curveInfoDaoImpl")
public class CurveInfoDaoImpl extends BaseDaoImpl<CurveInfoPO> implements CurveInfoDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public List<CurveDetailVO> getCurveDetail(String ids){
		return sqlSession.selectList(sqlMapNamespace + ".selectCurveDetail", ids);
	}
}
