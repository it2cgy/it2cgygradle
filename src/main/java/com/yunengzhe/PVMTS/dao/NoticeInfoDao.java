package com.yunengzhe.PVMTS.dao;

import com.yunengzhe.common.base.dao.BaseDao;
import com.yunengzhe.PVMTS.domain.po.NoticeInfoPO;

public interface NoticeInfoDao extends BaseDao<NoticeInfoPO>{

	/**
     * 根据主键ID批量逻辑删除
     * @param ids
     * @return
     */
    public int updateToDelete(String ids);

}

