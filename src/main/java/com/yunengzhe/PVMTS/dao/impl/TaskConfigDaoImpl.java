package com.yunengzhe.PVMTS.dao.impl;

import org.springframework.stereotype.Repository;

import com.yunengzhe.common.base.dao.impl.BaseDaoImpl;
import com.yunengzhe.PVMTS.dao.TaskConfigDao;
import com.yunengzhe.PVMTS.domain.po.TaskConfigPO;

@Repository("taskConfigDaoImpl")
public class TaskConfigDaoImpl extends BaseDaoImpl<TaskConfigPO> implements TaskConfigDao {

}
