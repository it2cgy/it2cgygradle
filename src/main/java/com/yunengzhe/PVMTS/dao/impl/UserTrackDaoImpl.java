package com.yunengzhe.PVMTS.dao.impl;

import org.springframework.stereotype.Repository;

import com.yunengzhe.common.base.dao.impl.BaseDaoImpl;
import com.yunengzhe.PVMTS.dao.UserTrackDao;
import com.yunengzhe.PVMTS.domain.po.UserTrackPO;

@Repository("userTrackDaoImpl")
public class UserTrackDaoImpl extends BaseDaoImpl<UserTrackPO> implements UserTrackDao {

}
