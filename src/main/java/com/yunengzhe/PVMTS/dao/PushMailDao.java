package com.yunengzhe.PVMTS.dao;

import com.yunengzhe.common.base.dao.BaseDao;

import java.util.List;

import com.yunengzhe.PVMTS.domain.po.PushMailPO;
import com.yunengzhe.PVMTS.domain.vo.UserEmailVO;

public interface PushMailDao extends BaseDao<PushMailPO>{

	
	public List<UserEmailVO> getUserEmail();
}

