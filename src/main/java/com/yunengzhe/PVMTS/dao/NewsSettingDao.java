package com.yunengzhe.PVMTS.dao;

import java.util.List;

import com.yunengzhe.common.base.dao.BaseDao;
import com.yunengzhe.PVMTS.domain.dto.UserTokenDTO;
import com.yunengzhe.PVMTS.domain.po.NewsSettingPO;
import com.yunengzhe.PVMTS.domain.po.PowerstationUserPO;

public interface NewsSettingDao extends BaseDao<NewsSettingPO>{

	
	public List<UserTokenDTO> getUserTokens(String userIds);
	
	public List<PowerstationUserPO> getPowerstationUser();
}

