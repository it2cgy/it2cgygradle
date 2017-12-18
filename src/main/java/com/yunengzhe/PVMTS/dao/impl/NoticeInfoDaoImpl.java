package com.yunengzhe.PVMTS.dao.impl;

import org.springframework.stereotype.Repository;

import com.yunengzhe.common.base.dao.impl.BaseDaoImpl;
import com.yunengzhe.PVMTS.dao.NoticeInfoDao;
import com.yunengzhe.PVMTS.domain.po.NoticeInfoPO;

@Repository("noticeInfoDaoImpl")
public class NoticeInfoDaoImpl extends BaseDaoImpl<NoticeInfoPO> implements NoticeInfoDao {

	public int updateToDelete(String ids) {
		return getSqlSession().update("com.yunengzhe.PVMTS.domain.po.NoticeInfoPO.updateToDelelete", ids);
	}

}
