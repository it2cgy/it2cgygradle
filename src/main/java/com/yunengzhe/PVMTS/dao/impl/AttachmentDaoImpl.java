package com.yunengzhe.PVMTS.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yunengzhe.common.base.dao.impl.BaseDaoImpl;
import com.yunengzhe.PVMTS.dao.AttachmentDao;
import com.yunengzhe.PVMTS.domain.po.AttachmentPO;

@Repository("attachmentDaoImpl")
public class AttachmentDaoImpl extends BaseDaoImpl<AttachmentPO> implements AttachmentDao {

	@Override
	public int deleteFile(Map<String, Object> map) {
		return getSqlSession().delete(sqlMapNamespace + ".deleteFile",map);
	}

}
