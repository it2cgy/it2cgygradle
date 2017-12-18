package com.yunengzhe.PVMTS.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yunengzhe.common.base.dao.impl.BaseDaoImpl;
import com.yunengzhe.PVMTS.dao.PushMailDao;
import com.yunengzhe.PVMTS.domain.po.PushMailPO;
import com.yunengzhe.PVMTS.domain.vo.UserEmailVO;

@Repository("pushMailDaoImpl")
public class PushMailDaoImpl extends BaseDaoImpl<PushMailPO> implements PushMailDao {

	@Override
	public List<UserEmailVO> getUserEmail() {
		return getSqlSession().selectList(sqlMapNamespace + ".getUserEmail");
	}

}
