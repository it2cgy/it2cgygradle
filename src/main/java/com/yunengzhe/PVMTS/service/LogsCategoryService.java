package com.yunengzhe.PVMTS.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.PVMTS.dao.LogsCategoryDao;
import com.yunengzhe.PVMTS.domain.po.LogsCategoryPO;


@Service("logsCategoryService")
public class LogsCategoryService {

	private static final Logger logger = LoggerFactory.getLogger(LogsCategoryService.class);
	
	@Autowired
	private LogsCategoryDao logsCategoryDaoImpl;
	
	public List<LogsCategoryPO> getLogsCateList() {
		// TODO Auto-generated method stub
		return logsCategoryDaoImpl.findAll();
	}

	public boolean addLogsCate(LogsCategoryPO po) {
		// TODO Auto-generated method stub
		return logsCategoryDaoImpl.insert(po)>0;
	}

	public boolean deleteLogsCate(Integer id) {
		// TODO Auto-generated method stub
		return logsCategoryDaoImpl.delete(id)>0;
	}

	public LogsCategoryPO getLogsCateById(Integer id) {
		// TODO Auto-generated method stub
		return logsCategoryDaoImpl.get(id);
	}
	
	
}
