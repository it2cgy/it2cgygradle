package com.yunengzhe.PVMTS.dao.impl;

import org.springframework.stereotype.Repository;

import com.yunengzhe.PVMTS.dao.TrackInfoDao;
import com.yunengzhe.PVMTS.domain.po.TrackInfoPO;
import com.yunengzhe.common.base.dao.impl.BaseDaoImpl;

@Repository("trackInfoDaoImpl")
public class TrackInfoDaoImpl extends BaseDaoImpl<TrackInfoPO> implements TrackInfoDao {

}
