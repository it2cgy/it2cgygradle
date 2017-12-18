package com.yunengzhe.PVMTS.dao;

import java.util.Map;

import com.yunengzhe.PVMTS.domain.po.AttachmentPO;
import com.yunengzhe.common.base.dao.BaseDao;

public interface AttachmentDao extends BaseDao<AttachmentPO>{
	
	
	public int deleteFile(Map<String,Object> map);

}

