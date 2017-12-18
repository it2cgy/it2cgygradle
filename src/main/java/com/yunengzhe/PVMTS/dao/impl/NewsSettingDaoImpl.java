package com.yunengzhe.PVMTS.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yunengzhe.common.base.dao.impl.BaseDaoImpl;
import com.yunengzhe.PVMTS.dao.NewsSettingDao;
import com.yunengzhe.PVMTS.domain.dto.UserTokenDTO;
import com.yunengzhe.PVMTS.domain.po.NewsSettingPO;
import com.yunengzhe.PVMTS.domain.po.PowerstationUserPO;

@Repository("newsSettingDaoImpl")
public class NewsSettingDaoImpl extends BaseDaoImpl<NewsSettingPO> implements NewsSettingDao {
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<UserTokenDTO> getUserTokens(String userIds) {
	 return	sqlSession.selectList(sqlMapNamespace +  ".getUserTokens", userIds);
	}

	@Override
	public List<PowerstationUserPO> getPowerstationUser() {
		 return	sqlSession.selectList(sqlMapNamespace +  ".getPowerstationUser");
	}

}
