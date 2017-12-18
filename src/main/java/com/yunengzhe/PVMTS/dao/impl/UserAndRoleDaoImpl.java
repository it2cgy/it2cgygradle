package com.yunengzhe.PVMTS.dao.impl;

import org.springframework.stereotype.Repository;

import com.yunengzhe.common.base.dao.impl.BaseDaoImpl;
import com.yunengzhe.PVMTS.dao.UserAndRoleDao;
import com.yunengzhe.PVMTS.domain.po.UserAndRolePO;

@Repository("userAndRoleDaoImpl")
public class UserAndRoleDaoImpl extends BaseDaoImpl<UserAndRolePO> implements UserAndRoleDao {

}
