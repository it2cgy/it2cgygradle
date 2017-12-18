package com.yunengzhe.PVMTS.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.common.base.service.BaseService;
import com.yunengzhe.common.tool.scaffold.util.PageBean;
import com.yunengzhe.common.tool.scaffold.util.SysConst.SortBy;
import com.yunengzhe.common.base.dao.BaseDao;
import com.yunengzhe.PVMTS.dao.SyncDatalogDao;
import com.yunengzhe.PVMTS.domain.po.RolePO;
import com.yunengzhe.PVMTS.domain.po.SyncDatalogPO;
import com.yunengzhe.PVMTS.domain.vo.ResultListVO;
import com.yunengzhe.PVMTS.domain.vo.role.RoleVO;


@Service("syncDatalogService")
public class SyncDatalogService {

	private static final Logger logger = LoggerFactory.getLogger(SyncDatalogService.class);
	
	@Autowired
	private SyncDatalogDao syncDatalogDaoImpl;
	 
	public void saveSyncDataLog(SyncDatalogPO logPO){
		syncDatalogDaoImpl.insert(logPO);
	}
	
	public void updateSyncDataLog(SyncDatalogPO logPO){
		syncDatalogDaoImpl.update(logPO);
	}
	
	public ResultListVO<SyncDatalogPO> getSyncData(String name,int page,int pageSize){
		ResultListVO<SyncDatalogPO>  results = new ResultListVO<SyncDatalogPO>();
		List<RoleVO> list = new ArrayList<RoleVO>();
		Map<String,Object> param = new HashMap<String,Object>();
		 
		PageBean<SyncDatalogPO> pageBean = new PageBean<SyncDatalogPO>();
		pageBean.setCurrentPage(page);
		pageBean.setPageRecorders(pageSize);
		syncDatalogDaoImpl.find4Page(pageBean, param,"create_date",SortBy.倒序);
		List<SyncDatalogPO> listData = pageBean.getObjList();
		 
		results.setResultList(listData);
		results.setCounts(pageBean.getTotalRows());
		return results;
	}
}
