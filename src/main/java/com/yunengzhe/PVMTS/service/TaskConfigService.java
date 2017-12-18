package com.yunengzhe.PVMTS.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunengzhe.common.base.service.BaseService;
import com.yunengzhe.common.base.dao.BaseDao;
import com.yunengzhe.PVMTS.dao.TaskConfigDao;
import com.yunengzhe.PVMTS.domain.po.TaskConfigPO;


@Service("taskConfigService")
public class TaskConfigService {

	private static final Logger logger = LoggerFactory.getLogger(TaskConfigService.class);
	
	@Autowired
	private TaskConfigDao taskConfigDaoImpl;
	 
	
	public TaskConfigPO getConfig(){
		List<TaskConfigPO> task = taskConfigDaoImpl.findAll();
		return task.size()>0?task.get(0):null;
	}
	
	public void updateConfig(TaskConfigPO p){
		taskConfigDaoImpl.update4Selective(p);
	}
	
	public void insertConfig(TaskConfigPO p){
		taskConfigDaoImpl.insert(p);
	}
}
