package com.yunengzhe.PVMTS.dao;

import com.yunengzhe.common.base.dao.BaseDao;

import java.util.List;

import com.yunengzhe.PVMTS.domain.dto.NoticeOwnerDTO;
import com.yunengzhe.PVMTS.domain.po.NoticeReaderPO;

public interface NoticeReaderDao extends BaseDao<NoticeReaderPO>{

	/**
	 * 根据ids,查询notice对象
	* @param ids
	* @return 
	 */
	public List<NoticeReaderPO> findByNoticeIds(NoticeOwnerDTO noticeOwnerDTO);

}

