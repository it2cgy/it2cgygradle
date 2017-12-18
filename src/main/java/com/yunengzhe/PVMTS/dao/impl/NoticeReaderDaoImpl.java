package com.yunengzhe.PVMTS.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yunengzhe.common.base.dao.impl.BaseDaoImpl;
import com.yunengzhe.PVMTS.dao.NoticeReaderDao;
import com.yunengzhe.PVMTS.domain.dto.NoticeOwnerDTO;
import com.yunengzhe.PVMTS.domain.po.NoticeReaderPO;

@Repository("noticeReaderDaoImpl")
public class NoticeReaderDaoImpl extends BaseDaoImpl<NoticeReaderPO> implements NoticeReaderDao {

	public List<NoticeReaderPO> findByNoticeIds(NoticeOwnerDTO noticeOwnerDTO) {
		return getSqlSession().selectList("com.yunengzhe.PVMTS.domain.po.NoticeReaderPO.selectByNoticeIds", noticeOwnerDTO);
	}

}
