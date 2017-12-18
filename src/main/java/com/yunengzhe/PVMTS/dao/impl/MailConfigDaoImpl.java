package com.yunengzhe.PVMTS.dao.impl;

import org.springframework.stereotype.Repository;

import com.yunengzhe.common.base.dao.impl.BaseDaoImpl;
import com.yunengzhe.PVMTS.dao.MailConfigDao;
import com.yunengzhe.PVMTS.domain.po.MailConfigPO;

@Repository("mailConfigDaoImpl")
public class MailConfigDaoImpl extends BaseDaoImpl<MailConfigPO> implements MailConfigDao {

}
