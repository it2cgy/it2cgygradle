package com.yunengzhe.PVMTS.dao.impl;

import org.springframework.stereotype.Repository;

import com.yunengzhe.common.base.dao.impl.BaseDaoImpl;
import com.yunengzhe.PVMTS.dao.TestDao;
import com.yunengzhe.PVMTS.domain.po.TestPO;

@Repository("testDaoImpl")
public class TestDaoImpl extends BaseDaoImpl<TestPO> implements TestDao {

}
